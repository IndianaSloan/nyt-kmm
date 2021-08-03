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
    
    private let persistenceModule: PersistenceModule = PersistenceModule()
    
    private static var sharedDatabase: NytDatabase = {
        return persistenceModule.database
    }()
    
    class func shared() -> NytDatabase {
        return sharedDatabase
    }
}
