package lk.ijse.pos_system.bo;

import lk.ijse.pos_system.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos_system.bo.custom.impl.HomeBOImpl;
import lk.ijse.pos_system.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos_system.bo.custom.impl.OrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes {
        CUSTOMER , ITEM , ORDER , HOME
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER: return new CustomerBOImpl();
            case ITEM: return new ItemBOImpl();
            case ORDER: return new OrderBOImpl();
            case HOME: return new HomeBOImpl();
            default: return null;
        }

    }

}
