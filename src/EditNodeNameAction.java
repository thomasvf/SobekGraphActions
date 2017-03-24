/**
 * Created by thoma on 04/03/2017.
 *
 */
public class EditNodeNameAction extends UserAction {
    private String oldName;
    private String newName;

    public EditNodeNameAction(String oldName, String newName, int order){
        super(order);
        this.oldName = oldName;
        this.newName = newName;
    }

    public EditNodeNameAction() {
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override
    public String toString() {
        return "EditNodeNameAction{" +
                "oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                '}';
    }
}
