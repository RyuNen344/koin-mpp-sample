import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        VStack {
            SettingsText(viewModel: ViewModelFactory.shared.createSettingViewModel())
            Text("fuga")
            Text("fuga")
            Text("fuga")
            Text("fuga")
            Text("fuga")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct SettingsText: View {
    
    let viewModel: SettingsViewModel
    
    var body: some View {
        Text(viewModel.getSystemInfo())
    }
}
