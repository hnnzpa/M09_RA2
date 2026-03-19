import java.util.ArrayList;
import java.util.List;

public class Estanc extends Thread {

    public List<Tabac> tabac = new ArrayList<>();
    public List<Llumi> llumis = new ArrayList<>();
    public List<Paper> papers = new ArrayList<>();
    private boolean obert = true;

    public Estanc() {
        for (int i = 0; i < 3; i++) {
            tabac.add(new Tabac());
            llumis.add(new Llumi());
            papers.add(new Paper());
        }
    }

    @Override
    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void nouSubministrament() {
        int index = (int) (Math.random() * 3);

        switch (index) {
            case 0:
                addTabac();
                System.out.println("Estanc ha posat Tabac");
                break;
            case 1:
                addLlumi();
                System.out.println("Estanc ha posat Llumi");
                break;
            case 2:
                addPaper();
                System.out.println("Estanc ha posat Paper");
                break;
        }
        notifyAll(); 
    }

    public void addTabac() {
        tabac.add(new Tabac());
    }

    public void addLlumi() {
        llumis.add(new Llumi());
    }

    public void addPaper() {
        papers.add(new Paper());
    }

    public synchronized Tabac venTabac() {
        while (tabac.isEmpty() && obert) {
            esperar();
        }
        if (!tabac.isEmpty()) {
            return tabac.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        while (llumis.isEmpty() && obert) {
            esperar();
        }
        if (!llumis.isEmpty()) {
            return llumis.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        while (papers.isEmpty() && obert) {
            esperar();
        }
        if (!papers.isEmpty()) {
            return papers.remove(0);
        }
        return null;
    }

    public void tancarEstanc() {
        obert = false;
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Estanc tancat.");
    }

    public void esperar() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}