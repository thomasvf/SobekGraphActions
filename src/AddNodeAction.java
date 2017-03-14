/**
 * Created by thoma on 04/03/2017.
 *
 */
public class AddNodeAction extends UserAction{

    private String nodeName;

    public AddNodeAction(String name, int order){
        super(order);
        nodeName = name;
    }

    public AddNodeAction(){}

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
