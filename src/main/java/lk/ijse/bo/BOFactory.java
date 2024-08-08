package lk.ijse.bo;

public class BOFactory {

    private static BOFactory boFactory;

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,ITEM,PRESCRIPTION,SUPPLIER,PLACE_ORDER
    }

    public BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes){
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case EMPLOYEE:
                return (T) new EmployeeBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionBoImpl();
            case SUPPLIER:
                return (T) new SupplierBoImpl();
            case PLACE_ORDER:
                return (T) new PlaceOrderBoImpl();
            default:
                return null;
        }
    }
}
