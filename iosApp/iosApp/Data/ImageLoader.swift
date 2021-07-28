//
//  ImageLoader.swift
//  iosApp
//
//  Created by Ciaran Sloan on 25/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine

class ImageLoader: ObservableObject {
    @Published var data: Data?
    private var imageUrlString: String?
    
    init(urlString: String) {
        self.imageUrlString = urlString
    }
    
    func loadImage() {
        if let imageUrlString = imageUrlString {
            guard let url = URL(string: imageUrlString) else { return }
            let task = URLSession.shared.dataTask(with: url) { data, response, error in
                guard let data = data else { return }
                DispatchQueue.main.async {
                    self.data = data
                }
            }
            task.resume()
        }
    }
}
