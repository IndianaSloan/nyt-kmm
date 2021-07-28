import SwiftUI
import shared

struct ArticleListScreen: View {
    @StateObject var viewModel: ArticleListViewModel
    var body: some View {
        let uiState = viewModel.uiState
        if (uiState.isKind(of: ArticleListState.Content.self)) {
            ScrollView {
                LazyVStack {
                    ForEach((uiState as! ArticleListState.Content).items, id: \.self) { uiModel in
                        ArticleListItem(article: uiModel)
                            .padding(PaddingStyles.ArticleListItem)
                            .onAppear {
                                if (uiModel == (uiState as! ArticleListState.Content).items.last) {
                                    viewModel.onScrolledToLastItem()
                                }
                            }
                    }
                }
            }.background(AppColors.ColorBackground)
        } else if (uiState.isKind(of: ArticleListState.Loading.self)) {
            ZStack(alignment: .center){
                ProgressView()
            }
        } else {
            EmptyView()
        }
    }
}

struct PaddingStyles {
    static let ArticleImage = EdgeInsets(top: Dimens.PaddingDefault, leading: Dimens.PaddingDefault, bottom: Dimens.PaddingDefault, trailing: Dimens.PaddingDefault)
    static let ArticleHeadline = EdgeInsets(top: Dimens.PaddingDefault, leading: 0, bottom: 0, trailing: Dimens.PaddingDefault)
    static let ArticleBody = EdgeInsets(top: 0, leading: 0, bottom: Dimens.PaddingDefault, trailing: Dimens.PaddingDefault)
    static let ArticleListItem = EdgeInsets(top: Dimens.PaddingFourth, leading: Dimens.PaddingHalf, bottom: Dimens.PaddingFourth, trailing: Dimens.PaddingHalf)
}

// Used to Create a new instance of ArticleListScreen while initializing its ViewModel
// with the passed in Section
public func ArticleListScreenFactory(section: SectionUIModel? = nil) -> some View {
    ArticleListScreen(viewModel: .init(section))
}
