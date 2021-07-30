//
//  HomePage.swift
//  iosApp
//
//  Created by Ciaran Sloan on 29/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

struct HomePage {
    let route: HomeRoute
    let icon: String
    let selected: Bool
}

enum HomeRoute : Equatable {
    case Articles(SectionUIModel?)
    case Bookmarks
    case Sections
}
