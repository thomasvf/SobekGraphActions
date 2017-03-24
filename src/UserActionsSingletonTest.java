import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thoma on 05/03/2017.
 *
 */
public class UserActionsSingletonTest {

    @Test
    public void testLists() throws IOException {
        UserActionsSingleton userActionsSingleton = UserActionsSingleton.getInstance();

        userActionsSingleton.addEdge("node1", "node2");
        userActionsSingleton.addNode("node3");
        userActionsSingleton.addNode("node4");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");

        userActionsSingleton.saveToJson("jsonbackup.json");
        userActionsSingleton.reset();
        ArrayList<UserAction> userActions;

        System.out.println("After reset, loaded from json:");
        try {
            userActionsSingleton.loadFromJson("jsonbackup.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("All actions: ");
        userActions = userActionsSingleton.getAllActions();
        for(UserAction userAction : userActions){
            System.out.println(userAction + " With order: " + userAction.getOrder());
        }

        ArrayList<AddNodeAction> addNodeActions = userActionsSingleton.getAddNodeActions();
        System.out.println("Add node actions: ");
        for(AddNodeAction userAction : addNodeActions){
            System.out.println("Node name: " + userAction.getNodeName() + " Order: " + userAction.getOrder());
        }

        ArrayList<AddEdgeAction> addEdgeActions = userActionsSingleton.getAddEdgeActions();
        System.out.println("Add edge actions: ");
        for(AddEdgeAction userAction : addEdgeActions){
            System.out.println("Origin: " + userAction.getOriginNodeName() + " Destiny: " + userAction.getDestinyNodeName()+
                    " Order: " + userAction.getOrder());
        }

        ArrayList<EditNodeNameAction> editActions = userActionsSingleton.getEditNodeNameActions();
        System.out.println("Edit node actions: ");
        for(EditNodeNameAction userAction : editActions){
            System.out.println("Old name: " + userAction.getOldName() + " New name: " + userAction.getNewName() +
                    " Order: " + userAction.getOrder());
        }

        ArrayList<NodeClickAction> clickActions = userActionsSingleton.getNodeClickActions();
        System.out.println("Click node actions: ");
        for(NodeClickAction userAction : clickActions){
            System.out.println("Node name: " + userAction.getNodeName() + " Order: " + userAction.getOrder());
        }

        ArrayList<RemoveNodeAction> removeActions = userActionsSingleton.getRemoveNodeActions();
        System.out.println("Remove node actions: ");
        for(RemoveNodeAction userAction : removeActions){
            System.out.println("Node name: " + userAction.getNodeName() + " Order: " + userAction.getOrder());
        }

        ArrayList<RemoveEdgeAction> removeEdgeActions = userActionsSingleton.getRemoveEdgeActions();
        System.out.println("Remove edge actions: ");
        for(RemoveEdgeAction userAction : removeEdgeActions){
            System.out.println("Origin: " + userAction.getOriginNodeName() + " Destiny: " + userAction.getDestinyNodeName()+
                    " Order: " + userAction.getOrder());
        }

        ArrayList<String> serverResponse = userActionsSingleton.
                sendPostWithJson("http://192.168.13.106/UserActionsSingleton/UserActionsReceiver.php",
                "USER_ACTIONS");
        System.out.println("Server response: ");
        for(String response : serverResponse){
            System.out.println(response);
        }
    }

    @Test
    public void testConnectionJson() throws IOException{
        UserActionsSingleton userActionsSingleton = UserActionsSingleton.getInstance();

        userActionsSingleton.addEdge("node1", "node2");
        userActionsSingleton.addNode("node3");
        userActionsSingleton.addNode("node4");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");

        ArrayList<String> serverResponse = userActionsSingleton.saveJsonToServer();
        System.out.println("Server response: ");
        for(String response : serverResponse){
            System.out.println(response);
        }
    }

    @Test
    public void testToString(){
        UserActionsSingleton userActionsSingleton = UserActionsSingleton.getInstance();
        userActionsSingleton.addEdge("node1", "node2");
        userActionsSingleton.addNode("node3");
        userActionsSingleton.addNode("node4");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");

        ArrayList<UserAction> userActions = userActionsSingleton.getAllActions();
        for(UserAction userAction : userActions){
            System.out.println(userAction);
        }
    }
}