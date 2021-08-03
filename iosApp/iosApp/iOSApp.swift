import SwiftUI
import shared

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        KoinKt.doInitKoin()
        return true
    }
}

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    let database: NytDatabase = PersistenceModule().database
    
	var body: some Scene {
		WindowGroup {
            HomeScreen(database: database)
		}
	}
}
