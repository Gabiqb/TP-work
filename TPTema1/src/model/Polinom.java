package model;

import java.util.ArrayList;

public class Polinom {
    private ArrayList<Monom> polinom;

    public ArrayList<Monom> getPolinom() {
        return polinom;
    }

    public void setPolinom(ArrayList<Monom> polinom) {
        this.polinom = polinom;
    }

    public ArrayList<Monom> getRest() {
        return rest;
    }

    public void setRest(ArrayList<Monom> rest) {
        this.rest = rest;
    }

    private ArrayList<Monom> rest;
    public Polinom()
    {
        polinom=new ArrayList<Monom>();
    }


}
