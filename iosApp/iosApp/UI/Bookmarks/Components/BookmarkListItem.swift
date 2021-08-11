//
//  BookmarkListItem.swift
//  iosApp
//
//  Created by Sandip Chaulagain on 11/08/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BookmarkListItem: View {
    let bookmark: BookmarkUIModel
    let onRemovedTapped: () -> Void
    let onSharedTapped: () -> Void
    var bookmarkColor = Color.init("ColorSecondary")
    
    init(uiModel: BookmarkUIModel, onRemovedTapped: @escaping () -> Void, onShareTap: @escaping () -> Void) {
        self.bookmark = uiModel
        self.onRemovedTapped = onRemovedTapped
        self.onSharedTapped = onShareTap
    }
    
    var body: some View {
        VStack {
            HStack(alignment: .center) {
                ImageView(withURL: bookmark.thumbnailUrl ?? "").frame(width: Dimens.ArticleThumb, height: Dimens.ArticleThumb)
                    .padding(PaddingStyles.ArticleImage)
                VStack(alignment: .leading) {
                    Text(bookmark.title).font(Type.Headline1)
                        .padding(PaddingStyles.ArticleHeadline)
                        .fixedSize(horizontal: false, vertical: true)
                        .foregroundColor(Color.init("ColorPrimary"))
                    Divider()
                    Text(bookmark.description_).font(Type.Body1)
                        .padding(PaddingStyles.ArticleBody)
                        .fixedSize(horizontal: false, vertical: true)
                        .foregroundColor(Color.init("ColorCaption"))
                }
            }
            HStack {
                Text(bookmark.postedDate)
                    .font(.system(.caption))
                    .padding(.all, 4)
                    .foregroundColor(Color.init("ColorSecondary"))
                Image(systemName: "minus.circle.fill")
                    .renderingMode(.template)
                    .padding(.all, 4)
                    .foregroundColor(bookmarkColor)
                    .onTapGesture {
                      onRemovedTapped()
                    }
                
                Image(Icons.Share)
                    .renderingMode(.template)
                    .padding(.all, 4)
                    .foregroundColor(Color.init("ColorSecondary"))
                    .onTapGesture {
                        onSharedTapped()
                    }
            }.frame(maxWidth: .infinity, alignment: .trailing)
            .padding(.trailing, 16)
            .padding(.bottom, 16)
        }.background(Color.init("ColorSurface"))
        .cornerRadius(Dimens.RadiusLarge)
        .shadow(radius: Dimens.ArticleElevation)
    }
}
