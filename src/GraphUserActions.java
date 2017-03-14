import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thoma on 04/03/2017.
 *
 */
public class GraphUserActions implements Serializable {
    private static final long serialVersionUID = -5721451274767851011L;

    private int currentOrder;
    private ArrayList<AddNodeAction> addNodeActions;
    private ArrayList<RemoveNodeAction> removeNodeActions;
    private ArrayList<EditNodeNameAction> editNodeNameActions;
    private ArrayList<AddEdgeAction> addEdgeActions;
    private ArrayList<RemoveEdgeAction> removeEdgeActions;
    private ArrayList<NodeClickAction> nodeClickActions;

    public GraphUserActions(){
        currentOrder = 0;
        addNodeActions = new ArrayList<>();
        removeNodeActions = new ArrayList<>();
        editNodeNameActions = new ArrayList<>();
        addEdgeActions = new ArrayList<>();
        removeEdgeActions = new ArrayList<>();
        nodeClickActions = new ArrayList<>();
    }

    public void addNode(String name){
        addNodeActions.add(new AddNodeAction(name, currentOrder));
        currentOrder++;
    }

    public void removeNode(String name){
        removeNodeActions.add(new RemoveNodeAction(name, currentOrder));
        currentOrder++;
    }

    public void editNodeName(String oldName, String newName){
        editNodeNameActions.add(new EditNodeNameAction(oldName, newName, currentOrder));
        currentOrder++;
    }

    public void addEdge(String origin, String destiny){
        addEdgeActions.add(new AddEdgeAction(origin, destiny, currentOrder));
        currentOrder++;
    }

    public void removeEdge(String origin, String destiny){
        removeEdgeActions.add(new RemoveEdgeAction(origin, destiny, currentOrder));
        currentOrder++;
    }

    public void nodeClick(String name){
        nodeClickActions.add(new NodeClickAction(name, currentOrder));
        currentOrder++;
    }

    public ArrayList<AddNodeAction> getAddNodeActions() {
        return addNodeActions;
    }

    public ArrayList<RemoveNodeAction> getRemoveNodeActions() {
        return removeNodeActions;
    }

    public ArrayList<EditNodeNameAction> getEditNodeNameActions() {
        return editNodeNameActions;
    }

    public ArrayList<AddEdgeAction> getAddEdgeActions() {
        return addEdgeActions;
    }

    public ArrayList<RemoveEdgeAction> getRemoveEdgeActions() {
        return removeEdgeActions;
    }

    public ArrayList<NodeClickAction> getNodeClickActions() {
        return nodeClickActions;
    }

    public void setAddNodeActions(ArrayList<AddNodeAction> addNodeActions) {
        this.addNodeActions = addNodeActions;
    }

    public void setRemoveNodeActions(ArrayList<RemoveNodeAction> removeNodeActions) {
        this.removeNodeActions = removeNodeActions;
    }

    public void setEditNodeNameActions(ArrayList<EditNodeNameAction> editNodeNameActions) {
        this.editNodeNameActions = editNodeNameActions;
    }

    public void setAddEdgeActions(ArrayList<AddEdgeAction> addEdgeActions) {
        this.addEdgeActions = addEdgeActions;
    }

    public void setRemoveEdgeActions(ArrayList<RemoveEdgeAction> removeEdgeActions) {
        this.removeEdgeActions = removeEdgeActions;
    }

    public void setNodeClickActions(ArrayList<NodeClickAction> nodeClickActions) {
        this.nodeClickActions = nodeClickActions;
    }

    public ArrayList<UserAction> allActions(){
        ArrayList<UserAction> addRemNodesActions = merge(addNodeActions, removeNodeActions);
        ArrayList<UserAction> addRemEdgeActions = merge(addEdgeActions, removeEdgeActions);
        ArrayList<UserAction> editClickNodeActions = merge(editNodeNameActions, nodeClickActions);

        return merge(addRemNodesActions, merge(addRemEdgeActions, editClickNodeActions));
    }

    private static ArrayList<UserAction> merge(ArrayList<? extends UserAction> a, ArrayList<? extends UserAction> b){
        int aIndex = 0;
        int bIndex = 0;
        int cIndex = 0;
        ArrayList<UserAction> c = new ArrayList<>();

        int lenA = a.size();
        int lenB = b.size();
        while(aIndex < lenA && bIndex < lenB){
            if(a.get(aIndex).getOrder() <= b.get(bIndex).getOrder()){
                c.add(cIndex, a.get(aIndex));
                aIndex++;
            }else{
                c.add(cIndex, b.get(bIndex));
                bIndex++;
            }

            cIndex++;
        }

        while(aIndex < lenA){
            c.add(cIndex, a.get(aIndex));
            aIndex++;
            cIndex++;
        }

        while(bIndex < lenB){
            c.add(cIndex, b.get(bIndex));
            bIndex++;
            cIndex++;
        }

        return c;
    }

}
