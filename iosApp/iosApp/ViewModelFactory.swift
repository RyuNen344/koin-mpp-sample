import Foundation
import shared

class ViewModelFactory {
    
    public static let shared = ViewModelFactory()
    
    private var koinApplication: KoinApplication?
    
    private init() {
        koinApplication = nil
    }
    
    func initialize(koinApplication: KoinApplication) {
        self.koinApplication = koinApplication
    }
    
    func createSettingViewModel() -> SettingsViewModel {
        // get instance from DI Container
        let settingsRepository = koinApplication!.koin.get(objCProtocol: SettingsRepository.self, qualifier: nil) as! SettingsRepository
        
        return SettingsViewModel(settingsRepository: settingsRepository)
    }
}
