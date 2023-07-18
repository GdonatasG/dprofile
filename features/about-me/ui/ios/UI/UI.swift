import SwiftUI

@objc
public final class Model: NSObject, ObservableObject {

    @objc
    @Published public var text: String = "Hello world!"
}

struct Content: View {
    @ObservedObject private var model: Model

    init(model: Model) {
        self.model = model
    }

    var body: some View {
        VStack(spacing: 0) {
            Spacer(),
            HStack {
                Spacer()
                Text(model.text)
                Spacer()
            }
            Spacer()
        }
    }
}
