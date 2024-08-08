package lk.ijse.dao;

public class DAOFactory {

    private static  DAOFactory daoFactory;

    public DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        EMPLOYEE, ITEM, PRESCRIPTION, SUPPLIER, ORDER, ORDER_DETAIL, CUSTOMER
    }
    public CrudDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return  new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case PRESCRIPTION:
                return new PrescriptionDAOImpl();
            default:
                return null;
        }
    }

}
