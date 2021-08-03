//
//  ArticleListViewModel.swift
//  iosApp
//
//  Created by Ciaran Sloan on 28/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class ArticleListViewModel: ObservableObject {
    @Published var uiState: ArticleListState
    var stateManager: ArticleStateManager
    var stateWatcher: Closeable?
    var section: SectionUIModel?
    
    init(_ section: SectionUIModel? = nil, _ database: NytDatabase) {
        self.section = section
        self.stateManager = ArticleStateManager(database: database)
        uiState = ArticleListState.Empty()
        stateWatcher = self.stateManager.collectState { [weak self] newState in
            self?.uiState = newState
        }
        stateManager.getArticles(sectionUIModel: section)
    }
    
    func onBookmarkTapped(article: ArticleUIModel) {
        if (article.isFavorite) {
            stateManager.unBookmarkArticle(articleId: article.id)
        } else {
            stateManager.bookmarkArticle(articleId: article.id)
        }
    }
    
    deinit {
        stateWatcher?.close()
    }
    
    func onScrolledToLastItem() {
        stateManager.loadNextPage()
    }
}
