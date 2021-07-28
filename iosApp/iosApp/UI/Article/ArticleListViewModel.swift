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
    let stateManager: ArticleStateManager = ArticleStateManager()
    var stateWatcher: Closeable?
    var section: SectionUIModel?
    
    init(_ section: SectionUIModel? = nil) {
        self.section = section
        uiState = ArticleListState.Empty()
        stateWatcher = self.stateManager.collectState { [weak self] newState in
            self?.uiState = newState
        }
        stateManager.getArticles(section: section)
    }
    
    deinit {
        stateWatcher?.close()
    }
    
    func onScrolledToLastItem() {
        stateManager.loadNextPage()
    }
}
