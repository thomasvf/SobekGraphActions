import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by thoma on 05/03/2017.
 *
 */
public class UserActionsSingletonTest {

    @Test
    public void test(){
        UserActionsSingleton userActionsSingleton = UserActionsSingleton.getInstance();

        userActionsSingleton.addEdge("node1", "node2");
        userActionsSingleton.addNode("node3");
        userActionsSingleton.addNode("node4");
        userActionsSingleton.removeNode("node2");
        userActionsSingleton.editNodeName("node3", "node3newname");
        userActionsSingleton.nodeClick("node1");
        userActionsSingleton.removeEdge("node3", "node2");

        System.out.println("Before saving: ");
        for(UserAction userAction : userActionsSingleton.getAllActions()){
            System.out.println(userAction);
        }

        try {
            userActionsSingleton.saveUserActions("backup");
        } catch (IOException e) {
            e.printStackTrace();
        }
        userActionsSingleton.reset();
        System.out.println("After saving and reset: ");
        for(UserAction userAction : userActionsSingleton.getAllActions()){
            System.out.println(userAction);
        }

        try {
            userActionsSingleton.loadUserActions("backup.data");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("After loading: ");
        for(UserAction userAction : userActionsSingleton.getAllActions()){
            System.out.println(userAction.getOrder());
        }


        try {
            userActionsSingleton.saveToJson("jsonbackup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}