//
//  ImageView.swift
//  iosApp
//
//  Created by Ciaran Sloan on 25/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ImageView: View {
    @ObservedObject var imageLoader: ImageLoader
    @State var image: UIImage = UIImage(named: "img_placeholder")!
    
    init(withURL url: String) {
        imageLoader = ImageLoader(urlString: url)
    }
    
    var body: some View {
        Image(uiImage: image)
            .resizable()
            .scaledToFill()
            .frame(width: Dimens.ArticleThumb, height: Dimens.ArticleThumb)
            .clipped()
            .cornerRadius(Dimens.RadiusMedium)
            .onReceive(imageLoader.$data) { data in
                guard let data = data else { return }
                self.image = UIImage(data: data) ?? UIImage()
            }
            .onAppear(perform: { self.imageLoader.loadImage() })
    }
}
