import java.io.Serializable;

/**
 * Created by thoma on 04/03/2017.
 *
 */
public class UserAction implements Serializable{
    private int order;

    public UserAction(int order){
        this.order = order;
    }

    public UserAction(){}

    public int getOrder(){
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}