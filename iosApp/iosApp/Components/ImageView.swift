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
            .frame(width:110, height:110, alignment: .center)
            .clipped()
            .cornerRadius(6)
            .onReceive(imageLoader.$data) { data in
                guard let data = data else { return }
                self.image = UIImage(data: data) ?? UIImage()
            }
            .onAppear(perform: { self.imageLoader.loadImage() })
    }
}
