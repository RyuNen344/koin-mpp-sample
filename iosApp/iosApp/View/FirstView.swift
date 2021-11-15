import SwiftUI
import shared

struct FirstView: View {
    
    @State var groupList: [shared.Group] = []
    
    var body: some View {
        VStack {
            Text(groupList.count.description)
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
//                updateListAsync()
                updateListBackground()
            }, label: {
                Text("Generate instance")
            })
        }
    }
    
    private func updateListAsync() {
        DispatchQueue.main.async {
            groupList += DataGeneratorKt.createGroupList()
        }
    }
    
    private func updateListBackground() {
        DataGeneratorKt.createGroupListInBackGround { (result, error) in
            guard let groups = result else {return}
            groupList += groups
        }
    }
}
