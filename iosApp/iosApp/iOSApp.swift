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
            ContentView()
        }
    }
}
