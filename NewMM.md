# Kotlin Multiplatform Mobile will go Beta in Spring 2022 :patipati:
## つまりKotlin NativeのMemory Managementも新しいやつが(ほぼ)Stableになってるのでは・・？

# ご存じですか、kotlin 1.5系のKotlin Native追加機能おさらい
## 1.5.0
- [perfomance向上](https://kotlinlang.org/docs/whatsnew15.html#performance-improvements)
- [Memory Leak Checkerの追加](https://kotlinlang.org/docs/whatsnew15.html#deactivation-of-the-memory-leak-checker)
  - 内部開発用パラメータだったがユニットテストなど特定のケースではまだ有用なので公開された

```kotlin
Platform.isMemoryLeakCheckerActive = true
```

## 1.5.20
- [perfomance向上](https://kotlinlang.org/docs/whatsnew1520.html#compiler-bug-fixes)
- [KDocをObjective-C Headerに出力可能に](https://kotlinlang.org/docs/whatsnew1520.html#opt-in-export-of-kdoc-comments-to-generated-objective-c-headers)
  - [KDoc](https://kotlinlang.org/docs/kotlin-doc.html)

    gradle.ktsを👇のように設定すると

    ```kotlin
    kotlin {
        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
            compilations.get("main").kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }
    ```

    ```kotlin
    /**
     * Prints the sum of the arguments.
     * Properly handles the case when the sum doesn't fit in 32-bit integer.
     */
    fun printSum(a: Int, b: Int) = println(a.toLong() + b)
    ```

    👆みたいなコメントが👇みたいになる swiftでも見れる

    ```objectivec
    /**
     * Prints the sum of the arguments.
     * Properly handles the case when the sum doesn't fit in 32-bit integer.
     */
    + (void)printSumA:(int32_t)a b:(int32_t)b __attribute__((swift_name("printSum(a:b:)")));
    ```

## 1.5.30
- [Apple silicon support](https://kotlinlang.org/docs/whatsnew1530.html#apple-silicon-support)
    ```
    macosArm64
    iosSimulatorArm64
    watchosSimulatorArm64
    tvosSimulatorArm64
    ```

    ```kotlin
    kotlin {
        ios()
        // Add the ARM64 simulator target
        iosSimulatorArm64()

        val iosMain by sourceSets.getting
        val iosTest by sourceSets.getting
        val iosSimulatorArm64Main by sourceSets.getting
        val iosSimulatorArm64Test by sourceSets.getting

        // Set up dependencies between the source sets
        iosSimulatorArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
    ```

- [CocoaPods Gradle Pluginが使いやすくなった](https://kotlinlang.org/docs/whatsnew1530.html#improved-kotlin-dsl-for-the-cocoapods-gradle-plugin)
  - Specify the dynamic or static version of the framework
  - Enable export dependencies explicitly
  - Enable Bitcode embedding
  - The Kotlin CocoaPods Gradle plugin supports custom names in the Xcode build configuration. It will also help you if you’re using special names for the build configuration in Xcode, for example Staging.

    ```kotlin
    cocoapods {
        frameworkName = "MyFramework" // This property is deprecated
        // and will be removed in future versions
        // New DSL for framework configuration:
        framework {
            // All Framework properties are supported
            // Framework name configuration. Use this property instead of
            // deprecated 'frameworkName'
            baseName = "MyFramework"
            // Dynamic framework support
            isStatic = false
            // Dependency export
            export(project(":anotherKMMModule"))
            transitiveExport = true
            // Bitcode embedding
            embedBitcode(BITCODE)
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }
    ```

- [Swift 5.5 async/await interoperability](https://kotlinlang.org/docs/whatsnew1530.html#experimental-interoperability-with-swift-5-5-async-await)
  - kotlinの`suspend fun`とSwift5.5の`async/await`相互改善
  - kotlin1.4で`suspend fun`がcompletionHandlerとして提供されるようになったが、1.5.30からswift5.5に対しては`async`として提供できるようになった

- [Improved Swift/Objective-C mapping for objects and companion objects](https://kotlinlang.org/docs/whatsnew1530.html#improved-swift-objective-c-mapping-for-objects-and-companion-objects)
    ```kotlin
    object MyObject {
        val x = "Some value"
    }

    class MyClass {
        companion object {
            val x = "Some value"
        }
    }
    ```

    👆のようなやつは👇でアクセスできるようになった

    ```swift
    MyObject.shared
    MyObject.shared.x
    MyClass.companion
    MyClass.Companion.shared
    ```

# Kotlin Native Update

- [5月mm update](https://blog.jetbrains.com/kotlin/2021/05/kotlin-native-memory-management-update/)
  - GCの大まかな種類
    - Reference-counting garbage collection
      - オブジェクト・グラフのリーフから始まり、特定のメモリ領域への参照数を追跡します。プログラムで使用されている生きたオブジェクトは、ゼロではない参照カウントを持つことになります。
    - Tracing garbage collection
      - オブジェクトグラフのルートから始まり、グラフをトラバースしてすべての生きているオブジェクトを特定します。プログラムで使用されていない死んだオブジェクトには到達しません。
      - 一定のタイミングでGCが走る、Full GCのタイミングがあり、その瞬間処理が止まるのでStop The Worldとか呼ばれる(JVM)
  - iOSのMemory Management
    - ARC(Automatic Reference Counting)
    - 強参照がなくなった瞬間にメモリ解放される
    - NSObjectクラスが参照の数を管理するフィールドを持っており、compileタイムにカウントする処理を仕込む
    - クラスに触る際にカウントをインクリメント、デクリメントする処理が入るためループ等になるとオーバーヘッドがある
    - [Swift Doc](https://docs.swift.org/swift-book/LanguageGuide/AutomaticReferenceCounting.html)
    - [Objective C GNU版 Doc](https://clang.llvm.org/docs/AutomaticReferenceCounting.html)
  - 現状のKotlin/NativeのGCはどうなっているのか
    - 遅延参照カウントGC(deferred reference-counting garbage collector)
    - シンプルなため選択したが、開発効率悪化に影響している状態
    - 他にも問題あり
      - 一時停止がないわけではない　割り当て解除を延期してグループ化してオーバーヘッドを削減しようとすると一時停止の頻度は減るが処理時間が長くなる
      - コレクターはアプリの重要なスレッドのブロックを回避しなければならないので安易にバックグラウンドスレッドに移動できない(だから現状freezeメソッドとかがある)
      - Tracing garbage collectionのほうがはるかに柔軟だしマルチスレッドプログラミングで使用しやすいがcompile timeとrun timeで複雑なインフラを構築しなければならないのが弱点
  - 新しいKotlin/NativeのGCはどうなるのか
    - Tracing garbage collection
    - 複雑な仕組みが必要
      - UIアプリケーションはレイテンシーに敏感なメインスレッドを持っているので、Stop-the-WorldのガベージコレクションしかサポートしていないデザインはKotlin/Nativeではダメ
      - 他にはリーク時のtracability, weak referenceのsupport, kotlinのクラスがplatformのobjectに割り当てられた際の参照解放方法の提供を目的としている
  - 現在は単純なa simple stop-the-world mark and sweep garbage collectorを実装している
    - 本番には乗らないがテストで使用することで問題点を洗い出す狙い
    - 上記のCore実装の致命的なバグを発見しておきたい
    - マルチスレッド対応のTrace Garbage Collectorを作成してcoroutineに適用してテストしていく予定
    - 実装できたらパフォーマンスとかも気にしていく予定

- [Prototype Garbage Collector KT-42296](https://youtrack.jetbrains.com/issue/KT-42296)
  - Simple Stop The World mark and sweep GCの実装完了
  - 今まで不足していた外部コードの実行スレッドの追跡、GCとKotlinのスレッドの同期、GCトリガーなどが含まれた
  - 実際にはガベージを収集しないno-opGCも実装した 調査や診断の目的に役立つ場合がある
  - 新しいガベージコレクタは、新しいメモリマネージャの基盤を提供します。
  - オブジェクトの共有とトップレベルのプロパティへのアクセスに関する厳格な制限を解除できた
  - 新しいメモリマネージャーを有効にすると、フリーズしなくてもどのスレッドからでもすべてのオブジェクトとプロパティにアクセスできる(1部例外あり)
  - コアライブラリを新しいメモリマネージャー（stdlib、kotlinx.coroutines、Ktorクライアント）に適合させました。
  - 新しいメモリマネージャーの一部として、デフォルトで[mimalloc allocator](https://github.com/microsoft/mimalloc)を有効にし（1.6.20以降）、最上位プロパティの遅延初期化を導入しました（KT-46771）。
    - -Xallocator=mimallocをつけよう
  - 新しいメモリマネージャーの開発プレビューは、Kotlin 1.6.0（1.6.0-M1以降）のオプションとして利用できます。詳細については、ブログ投稿を参照してください。
  - 次のステップは、新しいメモリマネージャをAlphaKT-49520に昇格させることです。
  - gcのalgorighmはsingle thread stop the world weap algorithmになった
  - パフォーマンスが悪いので目下の課題として取り組んでいる

- [8月preview release blog](https://blog.jetbrains.com/kotlin/2021/08/try-the-new-kotlin-native-memory-manager-development-preview/)
  - 新しいKotlin/Nativeの自動メモリマネージャは、スレッド間のオブジェクト共有に関する既存の制限を解除し、完全にリークフリーなコンカレントプログラミングのプリミティブを提供します。このプリミティブは安全で、開発者による特別な管理やアノテーションを必要としません。
  - 新しいバージョンのcoroutineとktorはすでに対応済みなのでfreezeなしでbackgroundで動作するobjectをfreezeなしで触れる
  - AtomicReferenceを使用している場合だけはfreezeが必要
  - sampleがある[Repository](https://github.com/Kotlin/kmm-production-sample/tree/new-mm-demo)
  - hands onも更新されてる [リンク](https://github.com/kotlin-hands-on/KNConcurrencyHandson/tree/new-mm-demo)
  - freeze
  - concurrent mark-sweep等のさらに効率的なアルゴリズムも検討している
  - gradle.propetiesに*kotlin.native.binary.memoryModel=experimental*を追加すると使える
- [Migration Guide](https://github.com/JetBrains/kotlin/blob/master/kotlin-native/NEW_MM.md)
  - トップレベルプロパティに`@SharedImmutable`を付与しなくてもプロパティにアクセスして変更できるようになった
  - スレッドを跨いだオブジェクト操作の際に`freeze`しなくてよくなった
  - `Worker.executeAfter`は操作の`freeze`が不要になった
  - `Worker.execute`はproducerがisolateなオブジェクトのサブグラフを返す必要がなくなった
  - `AtomicReference`, `FreezableAtomicReference`の参照サイクルがメモリーリークを起こさなくなった


- [Concurrencyの現状](https://kotlinlang.org/docs/kmm-concurrency-overview.html)

```
Garbage collection and reference counting﻿
Objective-C and Swift use reference counting. Kotlin/Native has its own garbage collection too. Kotlin/Native garbage collection is integrated with Objective-C/Swift reference counting. You do not need to use anything special to control the lifetime of Kotlin/Native instances from Swift or Objective-C.
```


[SwfitとKotlinのMMの違い](https://blog.indoorway.com/swift-vs-kotlin-the-differences-in-memory-management-860828edf8)
