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
    @State var image: UIImage = UIImage(named: "image_placholder")!
    
    init(withURL url: String) {
        imageLoader = ImageLoader(urlString: url)
    }
    
    var body: some View {
        Image(uiImage: image)
            .resizable()
            .scaledToFill()
            .frame(width:110, height:100, alignment: .center)
            .clipped()
            .cornerRadius(6)
            .onReceive(imageLoader.didChange) { data in
                self.image = UIImage(data: data) ?? UIImage()
            }
    }
}
