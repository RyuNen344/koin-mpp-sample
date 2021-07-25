import Foundation
import shared

class NapierExt {
    private static let napier = Napier()
    
    static func d(message: String, throwable: KotlinThrowable?, tag_ tag: String?) {
        napier.d(message: message, throwable: throwable, tag_: tag)
    }
    
    static func i(message: String, throwable: KotlinThrowable?, tag_ tag: String?) {
        napier.i(message: message, throwable: throwable, tag_: tag)
    }
}
