import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by thoma on 05/03/2017.
 *
 */
public class UserActionsSingleton {
    private GraphUserActions graphUserActions;

    private UserActionsSingleton(){
        graphUserActions = new GraphUserActions();
    }

    private static class GraphUserActionsSingletonHelper {
        private static final UserActionsSingleton instance = new UserActionsSingleton();
    }

    public static UserActionsSingleton getInstance(){
        return GraphUserActionsSingletonHelper.instance;
    }

    public void reset(){
        graphUserActions = new GraphUserActions();
    }

    public void addNode(String name){
        graphUserActions.addNode(name);
    }

    public void removeNode(String name){
        graphUserActions.removeNode(name);
    }

    public void editNodeName(String oldName, String newName){
        graphUserActions.editNodeName(oldName, newName);
    }

    public void addEdge(String origin, String destiny){
        graphUserActions.addEdge(origin, destiny);
    }

    public void removeEdge(String origin, String destiny){
        graphUserActions.removeEdge(origin, destiny);
    }

    public void nodeClick(String name){
        graphUserActions.nodeClick(name);
    }

    public ArrayList<AddNodeAction> getAddNodeActions() {
        return graphUserActions.getAddNodeActions();
    }

    public ArrayList<RemoveNodeAction> getRemoveNodeActions() {
        return graphUserActions.getRemoveNodeActions();
    }

    public ArrayList<EditNodeNameAction> getEditNodeNameActions() {
        return graphUserActions.getEditNodeNameActions();
    }

    public ArrayList<AddEdgeAction> getAddEdgeActions() {
        return graphUserActions.getAddEdgeActions();
    }

    public ArrayList<RemoveEdgeAction> getRemoveEdgeActions() {
        return graphUserActions.getRemoveEdgeActions();
    }

    public ArrayList<NodeClickAction> getNodeClickActions() {
        return graphUserActions.getNodeClickActions();
    }

    public ArrayList<UserAction> getAllActions() {
        return graphUserActions.allActions();
    }

    public void saveUserActions(String filepath) throws IOException {
        FileOutputStream fout = new FileOutputStream(filepath.concat(".data"));
        ObjectOutputStream objOut = new ObjectOutputStream(fout);
        objOut.writeObject(graphUserActions);
        fout.close();
        objOut.close();
    }

    public void loadUserActions(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        if(object instanceof GraphUserActions){
            graphUserActions = (GraphUserActions) object;
        }
        objectInputStream.close();
        fileInputStream.close();
    }

    public String getUserActionsAsJson()  throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(graphUserActions);
    }

    public void saveToJson(String filepath) throws IOException {
        PrintWriter printWriter = new PrintWriter(filepath.concat(".json"));
        printWriter.println(getUserActionsAsJson());
        printWriter.close();
    }
}
