import SwiftUI
import shared

struct RootView: View {
    var body: some View {
        VStack {
            NavigationView {
                VStack {
                    NavigationLink(destination: FirstView()) {
                        Text("Move to FirstView")
                    }
                    NavigationLink(destination: SecondView()) {
                        Text("Move to SecondView")
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
