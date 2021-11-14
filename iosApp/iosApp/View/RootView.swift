import SwiftUI
import shared

struct RootView: View {
    var body: some View {
        VStack {
            NavigationView {
                VStack {
                    NavigationLink(destination: FirstView()) {
                        // NOTE: Labelを指定すると遷移先へのリンクが自動的に生成される
                        Text("Move to FirstView")
                    }
                    NavigationLink(destination: SecondView()) {
                        // NOTE: Labelを指定すると遷移先へのリンクが自動的に生成される
                        Text("Move to SecondView")
                    }
                    NavigationLink(destination: ThirdView()) {
                        // NOTE: Labelを指定すると遷移先へのリンクが自動的に生成される
                        Text("Move to ThirdView")
                    }
                }
            }
            Button(action: {
                GarbageCollectorKt.unsafePerformGC()
            }, label: {
                Text("Run GC")
            })
        }
    }
}
