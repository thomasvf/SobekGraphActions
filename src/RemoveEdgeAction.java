/**
 * Created by thoma on 04/03/2017.
 *
 */
public class RemoveEdgeAction extends UserAction {
    private String originNodeName;
    private String destinyNodeName;

    public RemoveEdgeAction(String originNodeName, String destinyNodeName, int order) {
        super(order);
        this.originNodeName = originNodeName;
        this.destinyNodeName = destinyNodeName;
    }

    public RemoveEdgeAction() {
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
}
