//
//  BookmarksListViewModel.swift
//  iosApp
//
//  Created by Sandip Chaulagain on 11/08/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class BookmarksListViewModel : ObservableObject {
    
    @Published var uiState: BookmarkListState
    var stateManager: BookmarkStateManager
    var stateWatcher: Closeable?
    
    init(_ section: SectionUIModel? = nil) {
        self.stateManager = BookmarkStateManager(database: AppDatabase.shared())
        uiState = BookmarkListState.Empty()
        stateWatcher = self.stateManager.collectState { [weak self] newState in
            self?.uiState = newState
        }
        stateManager.getBookmarks()
    }
    
    func onRemovedClicked(article: BookmarkUIModel) {
        stateManager.unBookmarkArticle(articleId: article.id)
    }
    
    deinit {
        stateWatcher?.close()
    }
}
