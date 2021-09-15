package controller;

import model.Monom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExtragereDate {
    public ExtragereDate() {
        Controller.getV().getM1().getConfirma1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getM().getP1().setPolinom(new ArrayList<Monom>());
                    String s1 = Controller.getV().getM1().getP1().getText();
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
                        Controller.getM().getP1().getPolinom().add(m1);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    Controller.getV().getEd().setVisible(true);
                } catch (NumberFormatException e2) {
                    Controller.getV().getEd().setVisible(true);
                } catch (StringIndexOutOfBoundsException e3) {
                    Controller.getV().getEd().setVisible(true);
                }
            }
        });
        Controller.getV().getM1().getConfirma2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getM().getP2().setPolinom(new ArrayList<Monom>());
                    String s1 = Controller.getV().getM1().getP2().getText();
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
                        Controller.getM().getP2().getPolinom().add(m1);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    Controller.getV().getEd().setVisible(true);
                } catch (NumberFormatException e2) {
                    Controller.getV().getEd().setVisible(true);
                } catch (StringIndexOutOfBoundsException e3) {
                    Controller.getV().getEd().setVisible(true);
                }
            }
        });
    }
}
