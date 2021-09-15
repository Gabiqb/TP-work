package controller;

import model.Model;
import model.Monom;
import model.Polinom;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private static Model m;
    private static View v;

    public static Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }

    public static View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public Controller() {
        m = new Model();
        v = new View();
        ExtragereDate extragereDate = new ExtragereDate();
        v.getM1().getConfirma1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m.getP1().setPolinom(new ArrayList<Monom>());
                    String s1 = v.getM1().getP1().getText();
                    s1 = s1.replace("^", "");
                    s1 = s1.toLowerCase();
                    s1 = s1.replaceAll("\\s|\\t", "");
                    String[] s2 = s1.split("((?=\\+)|(?=\\-))");
                    for (int i = 0; i < s2.length; i = i + 1) {
                        Monom m1 = null;
                        if (s2[i].charAt(s2[i].length() - 1) == 'x')
                            s2[i] += "1";
                        String[] saux = s2[i].split("x");
                        if (saux.length >= 2) {
                            if (saux[0].equals("-"))
                                m1 = new Monom(-1, Integer.parseInt(saux[1]));
                            else if (saux[0].equals("+") || saux[0].equals(""))
                                m1 = new Monom(1, Integer.parseInt(saux[1]));
                            else
                                m1 = new Monom(Integer.parseInt(saux[0]), Integer.parseInt(saux[1]));
                        }
                        if (saux.length < 2) {
                            if (saux.length == 0 || saux[0].equals("+"))
                                m1 = new Monom(1, 1);
                            else if (saux[0].equals("-"))
                                m1 = new Monom(-1, 1);
                            else if (saux[0].length() > 1)
                                m1 = new Monom(Integer.parseInt(saux[0]), 0);
                            else
                                m1 = new Monom(Integer.parseInt(saux[0]), 0);
                        }
                        m.getP1().getPolinom().add(m1);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    v.getEd().setVisible(true);
                } catch (NumberFormatException e2) {
                    v.getEd().setVisible(true);
                } catch (StringIndexOutOfBoundsException e3) {
                    v.getEd().setVisible(true);
                }
            }
        });
        v.getM1().getConfirma2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m.getP2().setPolinom(new ArrayList<Monom>());
                    String s1 = v.getM1().getP2().getText();
                    s1 = s1.toLowerCase();
                    s1 = s1.replace("^", "");
                    s1 = s1.replaceAll("\\s|\\t", "");
                    String[] s2 = s1.split("((?=\\+)|(?=\\-))");
                    for (int i = 0; i < s2.length; i = i + 1) {
                        Monom m1 = null;
                        if (s2[i].charAt(s2[i].length() - 1) == 'x')
                            s2[i] += "1";
                        String[] saux = s2[i].split("x");
                        if (saux.length >= 2) {
                            if (saux[0].equals("-"))
                                m1 = new Monom(-1, Integer.parseInt(saux[1]));
                            else if (saux[0].equals("+") || saux[0].equals(""))
                                m1 = new Monom(1, Integer.parseInt(saux[1]));
                            else
                                m1 = new Monom(Integer.parseInt(saux[0]), Integer.parseInt(saux[1]));
                        }
                        if (saux.length < 2) {
                            if (saux.length == 0 || saux[0].equals("+"))
                                m1 = new Monom(1, 1);
                            else if (saux[0].equals("-"))
                                m1 = new Monom(-1, 1);
                            else if (saux[0].length() > 1)
                                m1 = new Monom(Integer.parseInt(saux[0]), 0);
                            else
                                m1 = new Monom(Integer.parseInt(saux[0]), 0);
                        }
                        m.getP2().getPolinom().add(m1);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    v.getEd().setVisible(true);
                } catch (NumberFormatException e2) {
                    v.getEd().setVisible(true);
                } catch (StringIndexOutOfBoundsException e3) {
                    v.getEd().setVisible(true);
                }
            }
        });
        v.getM1().getAduna().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setP3(CalculPolinom.adunaP(m.getP1(), m.getP2()));
                v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                v.getM1().getRest().setVisible(false);
                v.getM1().getRestlabel().setVisible(false);
            }
        });
        v.getM1().getScade().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setP3(CalculPolinom.scadeP(m.getP1(), m.getP2()));
                v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                v.getM1().getRest().setVisible(false);
                v.getM1().getRestlabel().setVisible(false);
            }
        });
        v.getM1().getIntegreaza().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setP3(CalculPolinom.integrareP(m.getP1()));
                v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                v.getM1().getRest().setVisible(false);
                v.getM1().getRestlabel().setVisible(false);
            }
        });
        v.getM1().getDeriveaza().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setP3(CalculPolinom.derivareP(m.getP1()));
                v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                v.getM1().getRest().setVisible(false);
                v.getM1().getRestlabel().setVisible(false);
            }
        });
        v.getM1().getInmulteste().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setP3(CalculPolinom.inmultireP(m.getP1(), m.getP2()));
                v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                v.getM1().getRest().setVisible(false);
                v.getM1().getRestlabel().setVisible(false);
            }
        });
        v.getM1().getImparte().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m.setP3(CalculPolinom.imparteP(m.getP1(), m.getP2()));
                    if (m.getP3() == null) {
                        v.getEimp().setVisible(true);
                    } else {
                        v.getM1().getP3().setText(CalculPolinom.conversieTXT(false, m.getP3()));
                        v.getM1().getRest().setText(CalculPolinom.conversieTXT(true, m.getP3()));
                        afiseazaRest();
                    }
                } catch (IndexOutOfBoundsException e1) {
                    v.getEd().setVisible(true);
                }
            }
        });
    }
    public void afiseazaRest() {
        v.getM1().getRest().setVisible(true);
        v.getM1().getRestlabel().setVisible(true);
    }

}
