/**
 * Created by thoma on 04/03/2017.
 *
 */
public class AddEdgeAction extends UserAction{
    private String originNodeName;
    private String destinyNodeName;

    public AddEdgeAction(String originNodeName, String destinyNodeName, int order) {
        super(order);
        this.originNodeName = originNodeName;
        this.destinyNodeName = destinyNodeName;
    }

    public AddEdgeAction() {
    }

    public String getOriginNodeName() {
        return originNodeName;
    }

    public String getDestinyNodeName() {
        return destinyNodeName;
    }

    public void setOriginNodeName(String originNodeName) {
        this.originNodeName = originNodeName;
    }

    public void setDestinyNodeName(String destinyNodeName) {
        this.destinyNodeName = destinyNodeName;
    }

    @Override
    public String toString() {
        return "AddEdgeAction{" +
                "originNodeName='" + originNodeName + '\'' +
                ", destinyNodeName='" + destinyNodeName + '\'' +
                '}';
    }
}
