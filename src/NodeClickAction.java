/**
 * Created by thoma on 04/03/2017.
 *
 */
public class NodeClickAction extends UserAction{
    private String nodeName;

    public NodeClickAction(String name, int order){
        super(order);
        nodeName = name;
    }

    public NodeClickAction() {
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return "NodeClickAction{" +
                "nodeName='" + nodeName + '\'' +
                '}';
    }
}
