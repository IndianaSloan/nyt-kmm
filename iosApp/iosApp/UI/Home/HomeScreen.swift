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
    var body: some View {
        Toolbar()
        TabView {
            ArticleListScreenFactory()
                .tabItem {
                    Image(systemName: "house.fill")
                }
            ArticleListScreenFactory(section: SectionUIModel(id: "travel", title: "Travel"))
                .tabItem {
                    Image(systemName: "video.circle.fill")
                }
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
