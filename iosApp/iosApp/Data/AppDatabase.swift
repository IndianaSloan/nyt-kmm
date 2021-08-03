//
//  AppDatabase.swift
//  iosApp
//
//  Created by Ciaran Sloan on 03/08/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class AppDatabase {
        
    private static var sharedDatabase: NytDatabase = {
        let persistenceModule = PersistenceModule()
        return persistenceModule.database
    }()
    
    class func shared() -> NytDatabase {
        return sharedDatabase
    }
}
