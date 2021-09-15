package controller;

import model.Monom;
import model.Polinom;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CalculPolinom {
    public static Polinom adunaP(Polinom p1, Polinom p2) {
        Polinom p3 = new Polinom();
        p3.setPolinom(p1.getPolinom());
        for (Monom m : p3.getPolinom()) {
            for (Monom m2 : p2.getPolinom()) {
                if (m.getGrad() == m2.getGrad()) {
                    Monom m3 = CalculMonom.adunaM(m, m2);
                    p3.getPolinom().set(CalculMonom.pozitieMonom(p3, m), m3);
                }
            }
        }
        boolean gasit;
        for (Monom m2 : p2.getPolinom()) {
            gasit = false;
            for (Monom m1 : p3.getPolinom()) {
                if (m2.getGrad() == m1.getGrad())
                    gasit = true;
            }
            if (gasit == false)

                p3.getPolinom().add(m2);
        }
        return p3;
    }

    public static Polinom scadeP(Polinom p1, Polinom p2) {
        Polinom p3 = new Polinom();
        p3.setPolinom(p1.getPolinom());
        for (Monom m : p3.getPolinom()) {
            for (Monom m2 : p2.getPolinom()) {
                if (m.getGrad() == m2.getGrad()) {
                    Monom m3 = CalculMonom.scadeM(m, m2);
                    p3.getPolinom().set(CalculMonom.pozitieMonom(p3, m), m3);
                }
            }
        }
        boolean gasit;
        for (Monom m2 : p2.getPolinom()) {
            gasit = false;
            for (Monom m1 : p3.getPolinom()) {
                if (m2.getGrad() == m1.getGrad())
                    gasit = true;
            }
            if (gasit == false) {
                m2.setCoeficient(m2.getCoeficient() * -1);
                p3.getPolinom().add(m2);
            }
        }
        return p3;
    }

    public static Polinom integrareP(Polinom p1) {
        Polinom p2 = new Polinom();
        for (Monom i : p1.getPolinom()) {
            p2.getPolinom().add(CalculMonom.integrare(i));
        }
        return p2;
    }

    public static Polinom derivareP(Polinom p1) {
        Polinom p2 = new Polinom();
        for (Monom i : p1.getPolinom()) {
            if (i.getGrad() > 0) p2.getPolinom().add(CalculMonom.derivare(i));
            else if (i.getGrad() == 0) {
                i.setCoeficient(0);
            }
        }
        Iterator<Monom> it = p2.getPolinom().iterator();
        while (it.hasNext()) {
            Monom auxm = it.next();
            if (auxm.getCoeficient() == 0)
                it.remove();
        }
        return p2;
    }

    public static Polinom inmultireP(Polinom p1, Polinom p2) {
        Polinom p3 = new Polinom();
        for (Monom i : p1.getPolinom())
            for (Monom j : p2.getPolinom()) {
                p3.getPolinom().add(CalculMonom.inmultireM(i, j));
            }
        for(int i=0;i<p3.getPolinom().size();i++)
            for(int j=i+1;j<p3.getPolinom().size();j++)
            {
                if(p3.getPolinom().get(i).getGrad()==p3.getPolinom().get(j).getGrad())
                {
                    p3.getPolinom().set(i,CalculMonom.adunaM(p3.getPolinom().get(i),p3.getPolinom().get(j) ));
                    p3.getPolinom().set(j,new Monom(0,0));
                }
            }
        return p3;
    }

    public static int gradMax(Polinom p) {
        int g = -1;
        for (Monom m : p.getPolinom())
            if (m.getGrad() > g)
                g = m.getGrad();
        return g;
    }

    public static boolean verificaImp(Polinom p1, Polinom p2) {
        Collections.sort(p1.getPolinom());
        Collections.sort(p2.getPolinom());

        for (int i = 0; i < p2.getPolinom().size(); i++)
            for (int j = 0; j < p2.getPolinom().size(); j++)
                if (p1.getPolinom().get(i).getCoeficient() % p2.getPolinom().get(j).getCoeficient() != 0)
                    return false;
        return true;

    }

    public static Polinom imparteP(Polinom p1, Polinom p2) {

        if (verificaImp(p1, p2)) {

            Polinom cat = new Polinom(); //catul
            int grad = gradMax(p1);
            int gp2 = gradMax(p2);
            while (grad >= gp2) {
                Polinom aux = new Polinom();
                Collections.sort(p1.getPolinom());
                Monom m2 = new Monom(p1.getPolinom().get(0).getCoeficient(), p1.getPolinom().get(0).getGrad());
                m2 = CalculMonom.imparteM(m2, p2.getPolinom().get(0));
                cat.getPolinom().add(m2);
                aux.getPolinom().add(m2);
                aux = CalculPolinom.inmultireP(aux, p2);
                p1 = CalculPolinom.scadeP(p1, aux);
                Iterator<Monom> it = p1.getPolinom().iterator();
                while (it.hasNext()) {
                    Monom auxm = it.next();
                    if (auxm.getCoeficient() == 0)
                        it.remove();
                }
                grad = gradMax(p1);
            }
            cat = CalculPolinom.eliminaegal(cat); //elimina monoame cu acelasi grad ( aduna )
            cat.setPolinom(cat.getPolinom()); //catul
            cat.setRest(p1.getPolinom()); //restul
            return cat;

        }
        return null;
    }

    public static Polinom eliminaegal(Polinom p1) {
        Iterator<Monom> it = p1.getPolinom().iterator();
        Iterator<Monom> it2 = it;
        while (it.hasNext()) {
            Monom m1 = it.next();
            while (it2.hasNext()) {
                Monom m2 = it.next();
                if (m1.getGrad() == m2.getGrad()) {
                    m1 = CalculMonom.adunaM(m1, m2);
                    it2.remove();
                }
            }
        }
        return p1;
    }

    public static String conversieTXT(boolean type, Polinom p1) {
        List<Monom> l1;
        if (type == false) //doar catul
            l1 = p1.getPolinom();
        else //restul
            l1 = p1.getRest();

        String t = "";
        Collections.sort(l1);
        for (Monom i : l1) {
            String local = "";
            if (i.getCoeficient() == 0)
                local = "";
            else if (i.getCoeficient() == -1 && i.getGrad() == 1)
                local = "-x" + "+";
            else if (i.getCoeficient() == 1 && i.getGrad() == 1)
                local = "x" + "+";
            else if (i.getGrad() == 1)
                local = String.valueOf(i.getCoeficient()) + "x" + "+";
            else if (i.getCoeficient() == -1 && i.getGrad() > 1)
                local = "-x^" + String.valueOf(i.getGrad()) + "+";
            else if (i.getCoeficient() == 1 && i.getGrad() > 1)
                local = "x^" + String.valueOf(i.getGrad()) + "+";
            else if (i.getGrad() == 0)
                local = String.valueOf(i.getCoeficient()) + "+";
            else
                local = String.valueOf(i.getCoeficient()) + "x^" + String.valueOf(i.getGrad()) + "+";
            t = t + local;
        }
        t = t.replace("+-", "-");
        while (t.endsWith("+")) {
            t = t.substring(0, t.length() - 1);
        }
        if (t.equals(""))
            t = "0";
        return t;

    }
}
