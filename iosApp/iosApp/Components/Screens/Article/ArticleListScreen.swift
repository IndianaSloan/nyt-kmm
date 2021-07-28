import SwiftUI
import shared

struct ArticleListScreen: View {
    
    @StateObject var viewModel: ArticleListViewModel
    
    var body: some View {
        let uiState = viewModel.uiState
        ScrollView {
            if (uiState.isKind(of: ArticleListState.Content.self)) {
                LazyVStack {
                    ForEach((uiState as! ArticleListState.Content).items, id: \.self) {
                        ArticleListItem(article: $0).padding(EdgeInsets(top: 8, leading: 8, bottom: 0, trailing: 8))
                    }
                }
//                List((uiState as! ArticleListState.Content).items, id: \.id) { uiModel in
//
//                }.listStyle()
            } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
                ProgressView()
            } else {
                Text("No Data")
            }
        }.background(Color.gray)
    }
}

private struct ArticleListItem: View {
    var article: ArticleUIModel
    var body: some View {
        HStack(alignment: .center) {
            ImageView(withURL: article.thumbnailUrl ?? "").frame(width: 110, height: 110, alignment: .center).padding(EdgeInsets(top: 16, leading: 16, bottom: 16, trailing: 16)).onAppear {
                
            }
            VStack(alignment: .leading) {
                Text(article.title).font(.system(size: 16).bold()).padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 16))
                Divider()
                Text(article.description_).font(.system(size: 14)).padding(EdgeInsets(top: 0, leading: 0, bottom: 16, trailing: 16))
            }
        }.background(Color.white).cornerRadius(3.0).shadow(radius: 2)
    }
//    var body: some View {
//        HStack(alignment: .center) {
//            ImageView(withURL: "https://static01.nyt.com/images/2021/07/28/us/politics/28virus-briefing-nyc/28virus-briefing-nyc-articleInline.jpg").frame(width: 110, height: 110, alignment: .center).padding(EdgeInsets(top: 16, leading: 16, bottom: 16, trailing: 16))
//            VStack(alignment: .leading) {
//                Text("All of New York City falls under the C.D.C. guidelines for indoor masking.").font(.system(size: 16).bold()).padding(EdgeInsets(top: 16, leading: 0, bottom: 0, trailing: 16))
//                Divider()
//                Text("Agency officials said that Americans should wear masks indoors in parts of the country that have recorded more than 50 new infections per 100,000 residents over the previous week.").font(.system(size: 14)).padding(EdgeInsets(top: 0, leading: 0, bottom: 16, trailing: 16))
//            }
//        }.background(Color.white).cornerRadius(3.0).shadow(radius: 2)
//    }
}

struct ArticleListItem_Previews: PreviewProvider {
    static var previews: some View {
//        let item = ArticleUIModel(id: "123", title: "All of New York City falls under the C.D.C. guidelines for indoor masking.", description: "Agency officials said that Americans should wear masks indoors in parts of the country that have recorded more than 50 new infections per 100,000 residents over the previous week.", postedDate: "July 28", webUrl: "https://www.nytimes.com/2021/07/28/nyregion/nyc-indoor-mask-guidelines.html", thumbnailUrl: "https://static01.nyt.com/images/2021/07/28/us/politics/28virus-briefing-nyc/28virus-briefing-nyc-articleInline.jpg", isFavorite: false)
        //ArticleListItem()
        Text("Hello World Test")
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
