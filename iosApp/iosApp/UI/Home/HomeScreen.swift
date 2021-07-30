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
                    case HomeRoute.Articles(let sectionUIModel):
                        ArticleListScreenFactory(section: sectionUIModel).navigationBarHidden(true)
                    case HomeRoute.Bookmarks:
                        BookmarksListScreen().navigationBarHidden(true)
                    case HomeRoute.Sections:
                        SectionListScreen { uiModel in
                            self.router.currentPage = HomeRoute.Articles(uiModel)
                        }.navigationBarHidden(true)
                    }
                
                    let tabs = [HomePage(route: .Articles(nil), icon: Icons.Home, selected: currentRoute == .Articles(nil)),
                                HomePage(route: .Bookmarks, icon: Icons.Bookmarks, selected: currentRoute == .Bookmarks),
                                HomePage(route: .Sections, icon: Icons.Menu, selected: currentRoute == .Sections)]
                    // Will fill all available white space, forcing BottomTabBar to be pinned to bottom of screen
                    Spacer()
                    BottomTabBar(geometry: geometry, height: Dimens.ToolbarHeight, tabs: tabs) { route in
                        self.router.currentPage = route
                    }
                }.navigationBarHidden(true)
            }
        }
    }
}

class HomeRouter : ObservableObject {
    @Published var currentPage: HomeRoute = HomeRoute.Articles(nil)
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
