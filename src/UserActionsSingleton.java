import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

    public ArrayList<String> sendSerializable(String address) throws IOException{
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        outputStream.writeObject(this.graphUserActions);
        outputStream.close();

        int status = connection.getResponseCode();
        if(status != HttpURLConnection.HTTP_OK){
            throw new IOException("Error connecting to server: " + status);
        }

        ArrayList<String> response = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.add(line);
        }
        reader.close();
        connection.disconnect();

        return response;
    }

    public void loadFromJson(String filepath) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        graphUserActions = mapper.readValue(new File(filepath), GraphUserActions.class);
    }

    public String getUserActionsAsJson()  throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(graphUserActions);
    }

    public void saveToJson(String filepath) throws IOException {
        PrintWriter printWriter = new PrintWriter(filepath);
        printWriter.println(getUserActionsAsJson());
        printWriter.close();
    }

    public ArrayList<String> sendPostWithJson(String address, String postKey) throws IOException {
        //Create connection
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        //Build query
        String query = postKey + "=" + URLEncoder.encode(this.getUserActionsAsJson(), "UTF-8");
        //Starts connection with query
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(query);
        writer.close();

        //Get response from server
        int status = connection.getResponseCode();
        if(status != HttpURLConnection.HTTP_OK){
            throw new IOException("Error connecting to server: " + status);
        }

        ArrayList<String> response = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.add(line);
        }
        reader.close();
        connection.disconnect();

        return response;
    }

    public ArrayList<String> saveJsonToServer() throws IOException{
        return this.sendPostWithJson("http://192.168.1.105/SobekGraphActionsService/index.php",
                "USER_ACTIONS");
    }
}
