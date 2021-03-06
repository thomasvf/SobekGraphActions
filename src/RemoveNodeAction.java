/**
 * Created by thoma on 04/03/2017.
 *
 */
public class RemoveNodeAction extends UserAction {

    private String nodeName;

    public RemoveNodeAction(String name, int order){
        super(order);
        nodeName = name;
    }

    public RemoveNodeAction() {
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return "RemoveNodeAction{" +
                "nodeName='" + nodeName + '\'' +
                '}';
    }
}
