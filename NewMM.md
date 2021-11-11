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
  - sdafdsa
- [KT-42296](https://youtrack.jetbrains.com/issue/KT-42296)
  - 実際のプロジェクトで使用できる、単純なストップザワールドマークアンドスイープGCの実装が完了しました。これまでに欠落していたすべてのコンポーネントが含まれるようになりました。外部コードを実行するスレッドの追跡、GCスレッドとKotlinスレッド間の同期、GCトリガーです。
  - また、実際にはガベージを収集しないno-opGCも実装しました。調査や診断の目的に役立つ場合があります。
  - 新しいガベージコレクタは、新しいメモリマネージャの基盤を提供します。これにより、オブジェクトの共有とトップレベルのプロパティへのアクセスに関する厳格な制限を解除できます。新しいメモリマネージャーを有効にすると、フリーズしなくても、どのスレッドからでもすべてのオブジェクトとプロパティにアクセスできます。
  - コアライブラリを新しいメモリマネージャー（stdlib、kotlinx.coroutines、Ktorクライアント）に適合させました。
  - さらに、新しいメモリマネージャーの一部として、デフォルトでmimallocアロケーターを有効にし（1.6.20以降）、最上位プロパティの遅延初期化を導入しました（KT-46771）。
  - 新しいメモリマネージャーの開発プレビューは、Kotlin 1.6.0（1.6.0-M1以降）のオプションとして利用できます。詳細については、ブログ投稿を参照してください。
  - 次のステップは、新しいメモリマネージャをAlphaKT-49520に昇格させることです。
  - gcのalgorighmはsingle thread stop the world weap algorithmになった
  - パフォーマンスが悪いので目下の課題として取り組んでいる
- [8月preview release blog](https://blog.jetbrains.com/kotlin/2021/08/try-the-new-kotlin-native-memory-manager-development-preview/)
  - 新しいKotlin/Nativeの自動メモリマネージャは、スレッド間のオブジェクト共有に関する既存の制限を解除し、完全にリークフリーなコンカレントプログラミングのプリミティブを提供します。このプリミティブは安全で、開発者による特別な管理やアノテーションを必要としません。
  - 新しいバージョンのcoroutineとktorはすでに対応済みなのでfreezeなしでbackgroundで動作するobjectをfreezeなしで触れる
  - sampleがある[Repository](https://github.com/Kotlin/kmm-production-sample/tree/new-mm-demo)
  - hands onも更新されてる [リンク](https://github.com/kotlin-hands-on/KNConcurrencyHandson/tree/new-mm-demo)
  - gradle.propetiesに*kotlin.native.binary.memoryModel=experimental*を追加すると使える
- [Migration Guide](https://github.com/JetBrains/kotlin/blob/master/kotlin-native/NEW_MM.md)
- [Concurrencyの現状](https://kotlinlang.org/docs/kmm-concurrency-overview.html)



```
Garbage collection and reference counting﻿
Objective-C and Swift use reference counting. Kotlin/Native has its own garbage collection too. Kotlin/Native garbage collection is integrated with Objective-C/Swift reference counting. You do not need to use anything special to control the lifetime of Kotlin/Native instances from Swift or Objective-C.
```
