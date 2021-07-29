//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ciaran Sloan on 25/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject var router: HomeRouter = HomeRouter()
    
    var body: some View {
        NavigationView {
            GeometryReader { geometry in
                VStack {
                    Toolbar()
                    let currentRoute = router.currentPage
                    switch currentRoute {
                    case HomeRoute.Articles:
                        ArticleListScreenFactory().navigationBarHidden(true)
                    case HomeRoute.Bookmarks:
                        ArticleListScreenFactory().navigationBarHidden(true)
                    case HomeRoute.Sections:
                        ArticleListScreenFactory().navigationBarHidden(true)
                    }

                    let tabs = [HomePage(route: .Articles, icon: "homekit", selected: currentRoute == .Articles),
                                HomePage(route: .Bookmarks, icon: "heart", selected: currentRoute == .Bookmarks),
                                HomePage(route: .Sections, icon: "waveform", selected: currentRoute == .Sections)]
                    
                    BottomTabBar(geometry: geometry, height: Dimens.ToolbarHeight, tabs: tabs) { route in
                        self.router.currentPage = route
                    }
                }.navigationBarHidden(true)
            }
        }
    }
}

class HomeRouter : ObservableObject {
    @Published var currentPage: HomeRoute = HomeRoute.Articles
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
