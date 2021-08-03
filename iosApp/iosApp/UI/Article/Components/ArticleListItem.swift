//
//  ArticleListItem.swift
//  iosApp
//
//  Created by Ciaran Sloan on 28/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleListItem: View {
    
    let article: ArticleUIModel
    let onBookmarkTapped: () -> Void
    let onSharedTapped: () -> Void
    var bookmarkColor = Color.init("ColorSecondary")
    
    init(uiModel: ArticleUIModel, onBookmarkTap: @escaping () -> Void, onShareTap: @escaping () -> Void) {
        self.article = uiModel
        self.onBookmarkTapped = onBookmarkTap
        self.onSharedTapped = onShareTap
        if (article.isFavorite) {
            bookmarkColor = Color.init("ColorPrimary")
        }
    }
    
    var body: some View {
        VStack {
            HStack(alignment: .center) {
                ImageView(withURL: article.thumbnailUrl ?? "").frame(width: Dimens.ArticleThumb, height: Dimens.ArticleThumb)
                    .padding(PaddingStyles.ArticleImage)
                VStack(alignment: .leading) {
                    Text(article.title).font(Type.Headline1)
                        .padding(PaddingStyles.ArticleHeadline)
                        .fixedSize(horizontal: false, vertical: true)
                        .foregroundColor(Color.init("ColorPrimary"))
                    Divider()
                    Text(article.description_).font(Type.Body1)
                        .padding(PaddingStyles.ArticleBody)
                        .fixedSize(horizontal: false, vertical: true)
                        .foregroundColor(Color.init("ColorCaption"))
                }
            }
            HStack {
                Text(article.postedDate)
                    .font(.system(.caption))
                    .padding(.all, 4)
                    .foregroundColor(Color.init("ColorSecondary"))
                Image(Icons.Bookmarks)
                    .renderingMode(.template)
                    .padding(.all, 4)
                    .foregroundColor(bookmarkColor)
                    .onTapGesture {
                      onBookmarkTapped()
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
