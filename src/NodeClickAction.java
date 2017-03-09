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

    public String getNodeName() {
        return nodeName;
    }
}
