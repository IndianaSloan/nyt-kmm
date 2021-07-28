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
        HStack(alignment: .center) {
            ImageView(withURL: article.thumbnailUrl ?? "").frame(width: Dimens.ArticleThumb, height: Dimens.ArticleThumb)
                .padding(PaddingStyles.ArticleImage)
            VStack(alignment: .leading) {
                Text(article.title).font(Type.Headline1)
                    .padding(PaddingStyles.ArticleHeadline)
                Divider()
                Text(article.description_).font(Type.Body1)
                    .padding(PaddingStyles.ArticleBody)
            }
        }.background(AppColors.ColorSurface)
        .cornerRadius(Dimens.RadiusLarge)
        .shadow(radius: Dimens.ArticleElevation)
    }
}
