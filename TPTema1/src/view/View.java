package view;

public class View {
    private MainFrame m1;
    private ExcDate ed;
    private ExcImpartire eimp;
    public MainFrame getM1() {
        return m1;
    }

    public void setM1(MainFrame m1) {
        this.m1 = m1;
    }

    public ExcDate getEd() {
        return ed;
    }
    public ExcImpartire getEimp() {
        return eimp;
    }



    public View()
    {
        m1=new MainFrame();
        ed=new ExcDate();
        eimp=new ExcImpartire();
    }
}
