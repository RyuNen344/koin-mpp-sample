import SwiftUI
import shared

@main
class iOSApp: App {

    let koinApplication: KoinApplication

    required init() {
        // init Napier
        NapierExtKt.debugBuild()

        // init DI Container
        koinApplication = KoinExtKt.doInitKoin()

        // init ViewModelFactory
        ViewModelFactory.shared.initialize(koinApplication: koinApplication)

        // 3回instanceを生成してscoped instanceのhashを表示させる
        printScopedInstance()

        // qualifierを使用して一意のinstanceのhashを表示させる
        printQualifiedInstance()

        /**
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped start
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped1 start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf8088
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped1 end
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped2 start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2be4048
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped2 start
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped3 start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf00a8
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped3 start
         DEBUG 8   iosApp                              0x0000000103e1d245  - scoped end
         DEBUG 8   iosApp                              0x0000000103e1d245  - singleton start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bec388
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bec388
         DEBUG 8   iosApp                              0x0000000103e1d245  - singleton end
         DEBUG 8   iosApp                              0x0000000103e1d245  - string qualifier start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf01e8
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf01e8
         DEBUG 8   iosApp                              0x0000000103e1d245  - string qualifier end
         DEBUG 8   iosApp                              0x0000000103e1d245  - type qualifier start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bec478
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bec478
         DEBUG 8   iosApp                              0x0000000103e1d245  - type qualifier end
         DEBUG 8   iosApp                              0x0000000103e1d245  - enum qualifier start
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf4d18
         DEBUG koin.InstancePrinter#print - com.ryunen344.koin.mpp.sample.koin.InstancePrinter@2bf4d18
         DEBUG 8   iosApp                              0x0000000103e1d245  - enum qualifier end
         */
    }

    private func printScopedInstance() {
        NapierExt.d(message: "scoped start", throwable: nil, tag_: nil)
        NapierExt.d(message: "scoped1 start", throwable: nil, tag_: nil)
        let owner1 = ScopeOwner()
        owner1.printInstanceHash()
        owner1.close()
        NapierExt.d(message: "scoped1 end", throwable: nil, tag_: nil)

        NapierExt.d(message: "scoped2 start", throwable: nil, tag_: nil)
        let owner2 = ScopeOwner()
        owner2.printInstanceHash()
        owner2.close()
        NapierExt.d(message: "scoped2 start", throwable: nil, tag_: nil)

        NapierExt.d(message: "scoped3 start", throwable: nil, tag_: nil)
        let owner3 = ScopeOwner()
        owner3.printInstanceHash()
        owner3.close()
        NapierExt.d(message: "scoped3 start", throwable: nil, tag_: nil)
        NapierExt.d(message: "scoped end", throwable: nil, tag_: nil)
    }

    private func printQualifiedInstance() {
        NapierExt.d(message: "singleton start", throwable: nil, tag_: nil)
        let singletone1: InstancePrinter = koinApplication.get(klass: InstancePrinter.self)
        singletone1.print()
        let singletone2: InstancePrinter = koinApplication.get(klass: InstancePrinter.self)
        singletone2.print()
        NapierExt.d(message: "singleton end", throwable: nil, tag_: nil)

        NapierExt.d(message: "string qualifier start", throwable: nil, tag_: nil)
        let stringNamed1: InstancePrinter = koinApplication.get(klass: InstancePrinter.self, qualifier: KoinExtKt.stringQualifier(value: QualifierKt_.namedQualifier))
        stringNamed1.print()
        let stringNamed2: InstancePrinter = koinApplication.get(klass: InstancePrinter.self, qualifier: KoinExtKt.stringQualifier(value: QualifierKt_.namedQualifier))
        stringNamed2.print()
        NapierExt.d(message: "string qualifier end", throwable: nil, tag_: nil)

        NapierExt.d(message: "type qualifier start", throwable: nil, tag_: nil)
        let type1: InstancePrinter = koinApplication.get(klass: InstancePrinter.self, qualifier: KoinExtKt.typeQualifier(objCClass: KlassQualifier.self))
        type1.print()
        let type2: InstancePrinter = koinApplication.get(klass: InstancePrinter.self, qualifier: KoinExtKt.typeQualifier(objCClass: KlassQualifier.self))
        type2.print()
        NapierExt.d(message: "type qualifier end", throwable: nil, tag_: nil)

        NapierExt.d(message: "enum qualifier start", throwable: nil, tag_: nil)
        let android1 : InstancePrinter = koinApplication.get(klass: InstancePrinter.self, qualifier: Mobile.android.qualifier)
        android1.print()
        let android2: InstancePrinter  = koinApplication.get(klass: InstancePrinter.self, qualifier: Mobile.android.qualifier)
        android2.print()
        NapierExt.d(message: "enum qualifier end", throwable: nil, tag_: nil)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

extension KoinApplication {
    func get<T, R>(prot: T, qualifier: Qualifier? = nil) -> R where T : Protocol  {
        return koin.get(objCProtocol: prot, qualifier: qualifier) as! R
    }

    func get<T, R>(klass: T, qualifier: Qualifier? = nil) -> R {
        return koin.get(objCClass: klass as! AnyClass, qualifier: qualifier) as! R
    }
}
