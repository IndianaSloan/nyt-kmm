//
//  TabBarIcon.swift
//  iosApp
//
//  Created by Ciaran Sloan on 29/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TabBarIcon: View {
    
    let width: CGFloat
    let iconName: String
    let isSelected: Bool
    
    init(width: CGFloat, iconName: String, isSelected: Bool) {
        self.width = width
        self.iconName = iconName
        self.isSelected = isSelected
    }
    
    var body: some View {
        VStack {
            Image(iconName)
                .renderingMode(.template)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: width, height: 24)
                .padding(.top, 10)
                .foregroundColor(isSelected ? Color.init("ColorPrimary") : Color.init("ColorSecondary"))
                
            Spacer()
        }
    }
}
