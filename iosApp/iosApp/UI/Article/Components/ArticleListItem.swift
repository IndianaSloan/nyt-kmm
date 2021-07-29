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
    var article: ArticleUIModel
    var body: some View {
            VStack {
                HStack(alignment: .center) {
                    ImageView(withURL: article.thumbnailUrl ?? "").frame(width: Dimens.ArticleThumb, height: Dimens.ArticleThumb)
                        .padding(PaddingStyles.ArticleImage)
                    VStack(alignment: .leading) {
                        Text(article.title).font(Type.Headline1)
                            .padding(PaddingStyles.ArticleHeadline)
                            .fixedSize(horizontal: false, vertical: true)
                        Divider()
                        Text(article.description_).font(Type.Body1)
                            .padding(PaddingStyles.ArticleBody)
                            .fixedSize(horizontal: false, vertical: true)
                    }
                }
                HStack {
                    Text(article.postedDate).font(.system(.caption)).padding(.all, 4).foregroundColor(Color.gray)
                    Image(Icons.Bookmarks).renderingMode(.template).padding(.all, 4).foregroundColor(Color.gray)
                    Image(Icons.Share).renderingMode(.template).padding(.all, 4).foregroundColor(Color.gray)
                }.frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.trailing, 16)
                .padding(.bottom, 16)
            }.background(AppColors.ColorSurface)
            .cornerRadius(Dimens.RadiusLarge)
            .shadow(radius: Dimens.ArticleElevation)
    }
}
