package controller;

import model.Monom;
import model.Polinom;

import static java.lang.Math.abs;

public class CalculMonom {
    public static Monom adunaM(Monom a, Monom b) {
        Monom c = new Monom(a.getCoeficient(), a.getGrad());
        if (a.getGrad() == b.getGrad())
            c.setCoeficient(a.getCoeficient() + b.getCoeficient());
        return c;
    }

    public static Monom scadeM(Monom a, Monom b) {
        Monom c = new Monom(a.getCoeficient(), a.getGrad());
        if (a.getGrad() == b.getGrad())
            c.setCoeficient(a.getCoeficient() - b.getCoeficient());
        return c;
    }

    public static Monom derivare(Monom a) {
        a.setCoeficient(a.getCoeficient() * a.getGrad());
        a.setGrad(a.getGrad() - 1);
        return a;
    }

    public static Monom integrare(Monom a) {
        if (a.getCoeficient() / (a.getGrad() + 1) == 0)
            if(a.getCoeficient()<0)
                a.setCoeficient(-1);
            else
                a.setCoeficient(1);
        else {
            a.setCoeficient(a.getCoeficient() / (a.getGrad() + 1));
        }
        a.setGrad(a.getGrad() + 1);
        return a;
    }

    public static Monom inmultireM(Monom m, Monom m1) {
        Monom m3 = new Monom(m.getCoeficient() * m1.getCoeficient(), m.getGrad() + m1.getGrad());
        return m3;
    }

    public static Monom imparteM(Monom m1, Monom m2) {
        if (abs(m1.getCoeficient()) / abs(m2.getCoeficient()) == 0) {
            if (m1.getCoeficient() < 0)
                m1.setCoeficient(-1);
            else
                m1.setCoeficient(1);
        }
        else if (m1.getGrad() >= 1 && m2.getGrad() >= 1) {
            m1.setGrad(m1.getGrad() - m2.getGrad());
            m1.setCoeficient(m1.getCoeficient() / m2.getCoeficient());
        }
        return m1;
    }

    public static int pozitieMonom(Polinom p, Monom m) {
        for (int i = 0; i < p.getPolinom().size(); i++) {
            if (m.getCoeficient() == p.getPolinom().get(i).getCoeficient() && m.getGrad() == p.getPolinom().get(i).getGrad())
                return i;
        }
        return 0;
    }
}
