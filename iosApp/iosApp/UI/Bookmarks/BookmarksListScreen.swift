//
//  BookmarksListScreen.swift
//  iosApp
//
//  Created by Ciaran Sloan on 30/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BookmarksListScreen: View {
    
    @StateObject var viewModel: BookmarksListViewModel
    
    var body: some View {
        let uiState = viewModel.uiState
        if (uiState.isKind(of: BookmarkListState.Content.self)) {
            ScrollView {
                LazyVStack {
                    ForEach((uiState as! BookmarkListState.Content).items, id: \.self) { uiModel in
                        BookmarkListItem(uiModel: uiModel, onRemovedTapped: {
                            viewModel.onRemovedClicked(article: uiModel)
                        }, onShareTap: {
                            shareContent(webUrl: uiModel.webUrl)
                        })
                        .padding(PaddingStyles.ArticleListItem)
                        .onTapGesture {
                            print("Clicked \(uiModel.title)")
                        }
                    }
                }
            }.background(Color.init("ColorBackground"))
        } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
            Spacer()
            ZStack(alignment: .center){
                ProgressView()
            }
        } else {
            ZStack {
                EmptyView()
            }
        }
    }
    
    func shareContent(webUrl: String) {
        guard let data = URL(string: webUrl) else { return }
        let av = UIActivityViewController(activityItems: [data], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController?.present(av, animated: true, completion: nil)
    }
}


// Used to Create a new instance of BookmarkListScreen while initializing its ViewModel
public func BookmarkListScreenFactory() -> some View {
    BookmarksListScreen(viewModel: .init())
}
