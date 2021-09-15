package model;

public class Model {
    private Polinom p1;
    private Polinom p2;
    private Polinom p3;

    public Polinom getP1() {
        return p1;
    }

    public void setP1(Polinom p1) {
        this.p1 = p1;
    }

    public Polinom getP2() {
        return p2;
    }

    public void setP2(Polinom p2) {
        this.p2 = p2;
    }

    public Polinom getP3() {
        return p3;
    }

    public void setP3(Polinom p3) {
        this.p3 = p3;
    }

    public Polinom getRest() {
        return rest;
    }

    public void setRest(Polinom rest) {
        this.rest = rest;
    }

    private Polinom rest;

    public Model()
    {
       p1=new Polinom();
       p2=new Polinom();
       p3=new Polinom();
       rest=new Polinom();

    }
}
