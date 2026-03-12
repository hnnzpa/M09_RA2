import java.util.List;

public class Estanc {

    public List<Tabac> tabac;
    public List<Llumi> llumis;
    public List<Paper> papers;

    public void Estanc(){

        for (int i = 0; i < 3; i++) {
            tabac.add(new Tabac());
            llumis.add(new Llumi());
            papers.add(new Paper());
        }

    }

    public void nouSubministrament(){
        int index = (int) (Math.random() * 3); // Genera un número aleatorio entre 0 y 2

        switch (index) {
            case 0:
                addTabac();
                break;
            case 1:
                addLlumi();
                break;
            case 2:
                addPaper();
                break;
        }
    }

    public void addTabac(){
        tabac.add(new Tabac());
    }

    public void addLlumi(){
        llumis.add(new Llumi());
    }

    public void addPaper(){
        papers.add(new Paper());
    }

    public Tabac venTabac(){
        if (tabac.size() > 0) {
            Tabac tabacShow = tabac.get(0);
            tabac.remove(0);
            return tabacShow;
        }
        return null;
    }

    public Llumi venLlumi(){
        if (llumis.size() > 0) {
            Llumi llumi = llumis.get(0);
            llumis.remove(0);
            return llumi;
        }
        return null;
    }

    public Paper venPaper(){
        if (papers.size() > 0) {
            Paper paper = papers.get(0);
            papers.remove(0);
            return paper;
        }
        return null;
    }

    public void tancarEstanc(){

    }

    public void esperar(){
        
    }

}