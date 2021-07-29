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
    let systemIconName: String
    let isSelected: Bool
    
    init(width: CGFloat, iconName: String, isSelected: Bool) {
        self.width = width
        self.systemIconName = iconName
        self.isSelected = isSelected
    }
    
    var body: some View {
        VStack {
            Image(systemName: systemIconName)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: width, height: 24)
                .padding(.top, 10)
                .foregroundColor(isSelected ? Color.black : Color.gray)
            Spacer()
        }
    }
}
