package model;

public class Monom implements Comparable<Monom>{
    private int grad;
    private int coeficient;

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public int getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public Monom(int c, int g)
    {
        this.grad=g;
        this.coeficient=c;
    }

    @Override
    public int compareTo(Monom o) {
        if(this.grad>o.grad) return -1;
        else if(this.grad<o.grad) return 1;
        return 0;
    }
}
