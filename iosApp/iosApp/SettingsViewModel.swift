import Foundation
import shared

class SettingsViewModel: ObservableObject {
    
    let settingsRepository: SettingsRepository
    
    init(settingsRepository: SettingsRepository) {
        self.settingsRepository = settingsRepository
    }
    
    func getSystemInfo() -> String {
        NapierExt.d(message: "\(settingsRepository)", throwable: nil, tag_: nil)
        return settingsRepository.getSystemInfo()
    }
}
