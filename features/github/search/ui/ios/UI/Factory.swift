import UIKit
import SwiftUI

@objc
public class Factory: NSObject {

    @objc
    public func compose(model: Model) -> UIViewController {
        let view = Content(model: model)
        return UIHostingController(rootView: view)
    }
}
