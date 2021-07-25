import Foundation
import shared

class SettingsViewModel: ObservableObject {
    
    let settingsRepository: SettingsRepository
    
    init(settingsRepository: SettingsRepository) {
        self.settingsRepository = settingsRepository
    }
    
    func getSystemInfo() -> String {
        return settingsRepository.getSystemInfo()
    }
}
