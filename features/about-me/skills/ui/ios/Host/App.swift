import SwiftUI

@main
struct App: SwiftUI.App {
    var body: some Scene {
        WindowGroup {
            Content(model: Model())
        }
    }
}

struct AppView_Previews: PreviewProvider {
    static var previews: some View {
        Content(model: Model())
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Default preview")
    }
}
