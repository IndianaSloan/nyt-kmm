//
//  BottomTabBar.swift
//  iosApp
//
//  Created by Ciaran Sloan on 29/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct BottomTabBar: View {
    
    let geometry: GeometryProxy
    let height: CGFloat
    let tabs: [HomePage]
    let onTabClicked: (HomeRoute) -> Void
    
    init(geometry: GeometryProxy, height: CGFloat = 56, tabs: [HomePage], onTabClicked: @escaping (HomeRoute) -> Void) {
        self.geometry = geometry
        self.height = height
        self.tabs = tabs
        self.onTabClicked = onTabClicked
    }
    
    var body: some View {
        HStack {
            let tabBarWidth = geometry.size.width / CGFloat(tabs.count)
            ForEach(tabs.indices) { index in
                let page = tabs[index]
                TabBarIcon(width: tabBarWidth, iconName: page.icon, isSelected: page.selected).onTapGesture(perform: {
                    self.onTabClicked(page.route)
                })
            }
        }.frame(width: geometry.size.width, height: height).background(AppColors.ColorBackground)
    }
}
