import SwiftUI
import shared

struct ArticleListScreen: View {
    
    @StateObject var viewModel: ArticleListViewModel
    
    var body: some View {
        ZStack {
            let uiState = viewModel.uiState
            if (uiState.isKind(of: ArticleListState.Content.self)) {
                List((uiState as! ArticleListState.Content).items, id: \.id) { article in
                    ArticleListItem(article: article)
                }
            } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
                ProgressView()
            } else {
                Text("No Data")
            }
        }
    }
}

private struct ArticleListItem: View {
    var article: Article
    var body: some View {
        HStack(alignment: .top) {
            ImageView(withURL: article.thumbnailUrl ?? "").frame(width: 50, height: 50, alignment: .center)
            Text(article.title).font(.system(size: 12).bold())
        }
    }
}

class ArticleListViewModel: ObservableObject {
    
    @Published var uiState: ArticleListState
    let stateManager: ArticleStateManager = ArticleStateManager()
    var stateWatcher: Closeable?
    var section: NewsSection?
    
    init(_ section: NewsSection? = nil) {
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
public func ArticleListScreenFactory(section: NewsSection? = nil) -> some View {
    ArticleListScreen(viewModel: .init(section))
}
