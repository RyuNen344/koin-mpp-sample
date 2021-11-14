import SwiftUI
import shared

struct FirstView: View {
    
    @State var groupList: [shared.Group] = []
    
    var body: some View {
        VStack {
            List {
                ForEach(groupList, id: \.self) { group in
                    VStack {
                        Text(group.id.description)
                        Text(group.title)
                        Text(group.url)
                    }
                }
            }
            Button(action: {
                DataGeneratorKt.createGroupListInBackGround { (list, error) in
                    guard let groups = list else {return}
                    groupList += groups
                }
            }, label: {
                Text("Generate instance")
            })
        }
    }
}
