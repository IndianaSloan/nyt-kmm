import SwiftUI
import shared

struct ArticleListScreen: View {
    @StateObject var viewModel: ArticleListViewModel
    var body: some View {
        let uiState = viewModel.uiState
        ScrollView {
            if (uiState.isKind(of: ArticleListState.Content.self)) {
                LazyVStack {
                    ForEach((uiState as! ArticleListState.Content).items, id: \.self) { uiModel in
                        ArticleListItem(article: uiModel)
                            .padding(EdgeInsets(top: 8, leading: 8, bottom: 0, trailing: 8))
                    }
                }
            } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
                ProgressView()
            } else {
                EmptyView()
            }
        }.background(AppColors.ColorBackground)
    }
}

private struct ArticleListItem: View {
    var article: ArticleUIModel
    var body: some View {
        HStack(alignment: .center) {
            ImageView(withURL: article.thumbnailUrl ?? "").frame(width: 110, height: 110, alignment: .center)
                .padding(EdgeInsets(top: 16, leading: 16, bottom: 16, trailing: 16)).onAppear {
                
            }
            VStack(alignment: .leading) {
                Text(article.title).font(.custom("CormorantGaramond-Bold", size: 16))
                    .padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 16))
                Divider()
                Text(article.description_).font(.custom("CormorantGaramond-Regular", size: 14))
                    .padding(EdgeInsets(top: 0, leading: 0, bottom: 16, trailing: 16))
            }
        }.background(Color.white).cornerRadius(9).shadow(color: .gray, radius: 1, x: 0.0, y: 0.0)
    }
}

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
}

// Used to Create a new instance of ArticleListScreen while initializing its ViewModel
// with the passed in Section
public func ArticleListScreenFactory(section: SectionUIModel? = nil) -> some View {
    ArticleListScreen(viewModel: .init(section))
}
