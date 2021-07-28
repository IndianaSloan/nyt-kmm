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
                }.listStyle(PlainListStyle())
            } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
                ProgressView()
            } else {
                Text("No Data")
            }
        }.background(Color.gray)
    }
}

private struct ArticleListItem: View {
    var article: Article
    var body: some View {
        HStack(alignment: .center) {
            ImageView(withURL: article.thumbnailUrl ?? "").frame(width: 110, height: 110, alignment: .center)
            VStack(alignment: .leading) {
                Text(article.title).font(.system(size: 16).bold())
                Divider()
                Text(article.description_).font(.system(size: 14))
            }
        }.background(Color.white).cornerRadius(3.0).shadow(radius: 2)
    }
}

struct ArticleListItem_Previews: PreviewProvider {
    static var previews: some View {
        //let item = Article.init(id: "123", title: "Hello Wrold", description: "Some Deets", postedDate: 0, webUrl: "", thumbnailUrl: "", isFavorite: false)
        //ArticleListItem()
        Text("Hello World Test")
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
