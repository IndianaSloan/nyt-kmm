//
//  SectionListScreen.swift
//  iosApp
//
//  Created by Ciaran Sloan on 30/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SectionListScreen: View {
    @StateObject var viewModel: SectionListViewModel = SectionListViewModel()
    var onSectionTapped: (SectionUIModel) -> Void
    var body: some View {
        let uiState = viewModel.uiState
        if (uiState.isKind(of: SectionListState.Content.self)) {
            ScrollView {
                LazyVStack {
                    ForEach((uiState as! SectionListState.Content).sections, id: \.self) { uiModel in
                        SectionListItem(uiModel: uiModel) {
                            self.onSectionTapped(uiModel)
                        }
                    }
                }
            }
        } else if (uiState.isKind(of: SectionListState.Loading.self)) {
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
}
