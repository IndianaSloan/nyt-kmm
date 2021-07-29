//
//  HomePage.swift
//  iosApp
//
//  Created by Ciaran Sloan on 29/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct HomePage {
    let route: HomeRoute
    let icon: String
    let selected: Bool
}

enum HomeRoute {
    case Articles
    case Bookmarks
    case Sections
}
