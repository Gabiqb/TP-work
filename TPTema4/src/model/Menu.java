package model;

import java.util.HashSet;

public class Menu  {
    public static HashSet<BaseProduct> getProduse() {
        return produse;
    }

    public static void setProduse(HashSet<BaseProduct> produse) {
        Menu.produse = produse;
    }

    private static HashSet<BaseProduct> produse;


}
