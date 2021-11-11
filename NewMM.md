# Kotlin Multiplatform Mobile will go Beta in Spring 2022
## つまりKotlin NativeのMemory Managementも新しいやつが(ほぼ)Stableになってるのでは・・？

# Kotlin Native Update

https://blog.jetbrains.com/kotlin/2021/05/kotlin-native-memory-management-update/
- [preview release blog](https://blog.jetbrains.com/kotlin/2021/08/try-the-new-kotlin-native-memory-manager-development-preview/)
  - 新しいKotlin/Nativeの自動メモリマネージャは、スレッド間のオブジェクト共有に関する既存の制限を解除し、完全にリークフリーなコンカレントプログラミングのプリミティブを提供します。このプリミティブは安全で、開発者による特別な管理やアノテーションを必要としません。
  - 新しいバージョンのcoroutineとktorはすでに対応済みなのでfreezeなしでbackgroundで動作するobjectをfreezeなしで触れる
  - sampleがある[Repository](https://github.com/Kotlin/kmm-production-sample/tree/new-mm-demo)
  - hands onも更新されてる [リンク](https://github.com/kotlin-hands-on/KNConcurrencyHandson/tree/new-mm-demo)
  - gradle.propetiesに*kotlin.native.binary.memoryModel=experimental*を追加すると使える
  - [Migration Guide](https://github.com/JetBrains/kotlin/blob/master/kotlin-native/NEW_MM.md)
  - 実際のプロジェクトで使用できる、単純なストップザワールドマークアンドスイープGCの実装が完了しました。これまでに欠落していたすべてのコンポーネントが含まれるようになりました。外部コードを実行するスレッドの追跡、GCスレッドとKotlinスレッド間の同期、GCトリガーです。

また、実際にはガベージを収集しないno-opGCも実装しました。調査や診断の目的に役立つ場合があります。

新しいガベージコレクタは、新しいメモリマネージャの基盤を提供します。これにより、オブジェクトの共有とトップレベルのプロパティへのアクセスに関する厳格な制限を解除できます。新しいメモリマネージャーを有効にすると、フリーズしなくても、どのスレッドからでもすべてのオブジェクトとプロパティにアクセスできます。

コアライブラリを新しいメモリマネージャー（stdlib、kotlinx.coroutines、Ktorクライアント）に適合させました。

さらに、新しいメモリマネージャーの一部として、デフォルトでmimallocアロケーターを有効にし（1.6.20以降）、最上位プロパティの遅延初期化を導入しました（KT-46771）。

新しいメモリマネージャーの開発プレビューは、Kotlin 1.6.0（1.6.0-M1以降）のオプションとして利用できます。詳細については、ブログ投稿を参照してください。

次のステップは、新しいメモリマネージャをAlphaKT-49520に昇格させることです。

- gcのalgorighmはsingle thread stop the world weap algorithmになった
- パフォーマンスが悪いので目下の課題として取り組んでいる


https://kotlinlang.org/docs/kmm-concurrency-overview.html
