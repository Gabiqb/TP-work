package model;

import business_layer.DeliveryService;
import presentation_layer.*;


public class Model {

    private static final String URL = "jdbc:mysql://localhost/foodorders?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Logitech123123";
    private static OrdersFrame oFrame;
    private static ClientsFrame cFrame;
    private static MeniuPrincipal mPFrame;
    private static RegisterFrame rFrame;
    private static LoginFrame lFrame;
    private static AdminFrame aFrame;


    public static DeliveryService getdService() {
        return dService;
    }

    public static void setdService(DeliveryService dService) {
        Model.dService = dService;
    }

    private static DeliveryService  dService;


    public static AdminFrame getaFrame() {
        return aFrame;
    }

    public static RegisterFrame getrFrame() {
        return rFrame;
    }

    public static void setrFrame(RegisterFrame rFrame) {
        Model.rFrame = rFrame;
    }

    public static LoginFrame getlFrame() {
        return lFrame;
    }

    public static void setlFrame(LoginFrame lFrame) {
        Model.lFrame = lFrame;
    }

    public static void setmPFrame(MeniuPrincipal mPFrame) {
        Model.mPFrame = mPFrame;
    }

    public static MeniuPrincipal getmPFrame() {
        return mPFrame;
    }

    public static OrdersFrame getoFrame() {
        return oFrame;
    }

    public static void setoFrame(OrdersFrame oFrame) {
        Model.oFrame = oFrame;
    }

    public static ClientsFrame getcFrame() {
        return cFrame;
    }

    public static void setcFrame(ClientsFrame cFrame) {
        Model.cFrame = cFrame;
    }
    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setaFrame(AdminFrame aFrame) {
        Model.aFrame = aFrame;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }


}
