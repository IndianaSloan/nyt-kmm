//
//  SectionListViewModel.swift
//  iosApp
//
//  Created by Ciaran Sloan on 30/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class SectionListViewModel : ObservableObject {
    @Published var uiState: shared.SectionListState
    
    let stateManager: SectionListStateManager = SectionListStateManager()
    var stateWatcher: Closeable?
    
    init() {
        uiState = SectionListState.Empty()
        stateWatcher = self.stateManager.collectState { [weak self] newState in
            self?.uiState = newState
        }
        stateManager.getSections()
    }
    
    deinit {
        stateWatcher?.close()
    }
}
