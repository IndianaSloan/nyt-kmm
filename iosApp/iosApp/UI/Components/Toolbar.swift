//
//  Toolbar.swift
//  iosApp
//
//  Created by Ciaran Sloan on 28/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct Toolbar: View {
    var body: some View {
        ZStack {
            Image("img_toolbar")
        }.frame(height: Dimens.ToolbarHeight, alignment: .center)
    }
}

struct Toolbar_Previews: PreviewProvider {
    static var previews: some View {
        Toolbar()
    }
}
