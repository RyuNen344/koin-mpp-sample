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
    }

    var body: some Scene {
        WindowGroup {
            RootView()
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
