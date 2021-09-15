package teste;

import controller.CalculPolinom;
import model.Monom;
import model.Polinom;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class InmultireTest {
    public InmultireTest t;
    public Polinom p1 = new Polinom();
    public Polinom p2 = new Polinom();
    Monom m1 = new Monom(1, 0);
    Monom m2 = new Monom(1, 2);
    Monom m4 = new Monom(-1, 0);
    Monom m3 = new Monom(1, 1);
    public InmultireTest() {
        p1.getPolinom().add(m2);
        p1.getPolinom().add(m4);
        p2.getPolinom().add(m3);
        p2.getPolinom().add(m1);
    }

    public boolean inmultireCorect(Polinom p1, Polinom p2) {
        //(x^2 -1) * (x+1)  = x^3+x^2-x-1
        p1 = new Polinom();
        p2 = new Polinom();
        p1.getPolinom().add(m2);
        p1.getPolinom().add(m4);
        p2.getPolinom().add(m3);
        p2.getPolinom().add(m1);
        Polinom p3 = new Polinom();
        p3.getPolinom().add(new Monom(1, 3));
        p3.getPolinom().add(new Monom(1, 2));
        p3.getPolinom().add(new Monom(-1, 1));
        p3.getPolinom().add(new Monom(-1, 0));
        Polinom p4= CalculPolinom.inmultireP(p1,p2);
        Collections.sort(p4.getPolinom());
        for (int j = 0; j < p4.getPolinom().size(); j++) {
            if (!(p4.getPolinom().get(j).getCoeficient() == p3.getPolinom().get(j).getCoeficient()) || !(p4.getPolinom().get(j).getGrad() == p3.getPolinom().get(j).getGrad()))
                return false;
        }
        return true;
    }
    @Before
    public void setUp() {
        t = new InmultireTest();
    }

    @Test
    public void test3() {
        assertTrue(t.inmultireCorect(p1, p2));
    }
}
