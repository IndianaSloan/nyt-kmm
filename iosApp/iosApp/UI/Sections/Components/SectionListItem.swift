//
//  SectionListItem.swift
//  iosApp
//
//  Created by Ciaran Sloan on 30/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SectionListItem: View {
    var uiModel: SectionUIModel
    var onTapped: () -> Void
    var body: some View {
        HStack {
            Text(uiModel.title).font(Type.Headline1)
            Spacer()
            Image(systemName: "chevron.right")
        }
        .padding(EdgeInsets(top: 16, leading: 16, bottom: 16, trailing: 16))
        .onTapGesture { onTapped() }
    }
}
