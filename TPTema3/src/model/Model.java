package model;

import presentation_layer.*;


public class Model {

    private static final String URL = "jdbc:mysql://localhost/orders?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_NAME ="orders";
    private static final String USER = "root";
    private static final String PASSWORD = "Logitech123123";
    private static OrdersFrame oFrame;
    private static ClientsFrame cFrame;
    private static ProductsFrame pFrame;
    private static MeniuPrincipal mPFrame;

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

    public static ProductsFrame getpFrame() {
        return pFrame;
    }

    public static void setpFrame(ProductsFrame pFrame) {
        Model.pFrame = pFrame;
    }

    public static String getURL() {
        return URL;
    }


    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }


}
