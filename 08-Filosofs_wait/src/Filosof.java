import java.util.concurrent.ThreadLocalRandom;

public class Filosof extends Thread {
    private String name;
    private int nComencal;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;

    public Filosof(String name, int comencal) {
        this.name = name;
        this.nComencal = comencal;
        gana = 0;
    }

    public void setForquilles(Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    }

    public int getGana() {
        return gana;
    }

    private void pensar() {
        System.out.println("Filosof: " + name + " pensant");
        try {
            sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        System.out.println("Filosof: " + name + " menjant");
        gana = 0;
        actualitzaPrioritat();
        try { // simula que menja
            sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean agafarForquilles(){
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                return true; 
            } else {
                deixaForquillaEsquerra();
                mesGana();
                esperar();
            }
        }
        mesGana();
        esperar();
        return false;
    } 

    private void deixaForquillaEsquerra(){
        forquillaEsquerra.deixar(this.nComencal); 
        System.out.printf("Filosof: %s deixa forquilla esquerra(%d)\n", name, forquillaEsquerra.getNumero());
    }

    private void deixaForquillaDreta(){
        forquillaDreta.deixar(this.nComencal); 
        System.out.printf("Filosof: %s deixa forquilla dreta(%d)\n", name, forquillaDreta.getNumero());
    }

    private void deixarForquilles(){
        deixaForquillaEsquerra();
        deixaForquillaDreta();
    }

    private boolean agafaForquillaEsquerra(){
        if (forquillaEsquerra.agafar(this.nComencal)) { 
            System.out.printf("Filosof: %s agafa forquilla esquerra(%d)\n", name,
                    forquillaEsquerra.getNumero());
            return true;
        }
        return false; 
    }   

    private boolean agafaForquillaDreta(){
        if (forquillaDreta.agafar(this.nComencal)) { 
            System.out.printf("Filosof: %s agafant forquilla dreta(%d)\n", name,
            forquillaDreta.getNumero());
            return true;
        }
        return false;   
    }
        

    private void esperar() {
        try {
            sleep(ThreadLocalRandom.current().nextInt(500, 1001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   

    private void mesGana() {
        gana++;
        actualitzaPrioritat();
        System.out.printf("Filosof: %s gana=%d\n", name, gana);
    }

    private void actualitzaPrioritat() {
        int prioritat;
        
        if (gana >= 7) {
            prioritat = Thread.MAX_PRIORITY - 1;  // +9
        } else if (gana >= 3) {
            prioritat = Thread.NORM_PRIORITY;  // 5
        } else {
            prioritat = Thread.MIN_PRIORITY;  // 1
        }
        
        setPriority(prioritat);
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            boolean haMenjat = false;

            while (!haMenjat) {
                if (agafarForquilles()) {
                    menjar();
                    deixarForquilles();
                    haMenjat = true;
                } else {
                    mesGana();
                    esperar();
                }
            }
        }
    }
}