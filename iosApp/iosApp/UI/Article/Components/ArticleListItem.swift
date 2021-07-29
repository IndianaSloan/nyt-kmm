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
                        Divider()
                        Text(article.description_).font(Type.Body1)
                            .padding(PaddingStyles.ArticleBody)
                    }
                }
                HStack {
                    Text(article.postedDate).padding(.all, 4).foregroundColor(Color.gray)
                    Image("ic_bookmark").renderingMode(.template).padding(.all, 4).foregroundColor(Color.gray)
                    Image("ic_share").renderingMode(.template).padding(.all, 4).foregroundColor(Color.gray)
                }.frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.trailing, 16)
                .padding(.bottom, 16)
            }.background(AppColors.ColorSurface)
            .cornerRadius(Dimens.RadiusLarge)
            .shadow(radius: Dimens.ArticleElevation)
    }
}
