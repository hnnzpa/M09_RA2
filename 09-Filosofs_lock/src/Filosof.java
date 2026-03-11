import java.util.concurrent.ThreadLocalRandom;

public class Filosof extends Thread {
    private String name;
    private int nComencal;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    
    private long iniciGana; 
    private long fiGana;     
    private double gana;     

    public Filosof(String name, int comencal) {
        this.name = name;
        this.nComencal = comencal;
        this.gana = 0;
        this.iniciGana = 0;
        this.fiGana = 0;
    }

    public void setForquilles(Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    }

    public double getGana() {
        return gana;
    }

    private void pensar() {
        System.out.println(name + " pensant");
        iniciGana = System.currentTimeMillis(); 
        try {
            sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        fiGana = System.currentTimeMillis(); 
        gana = calcularGana(); 
        
        System.out.printf("%s té forquilles esq(%d) dreta(%d)\n", 
            name, forquillaEsquerra.getNumero(), forquillaDreta.getNumero());
        System.out.printf("%s menja amb gana %.1f\n", name, gana);
        
        try {
            sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(name + " ha acabat de menjar");
        resetGana(); 
    }

    private boolean agafarForquilles() {
        if (agafarForquillaEsquerra()) {
            if (agafarForquillaDreta()) {
                return true; 
            } else {
                deixaForquillaEsquerra();
                return false;
            }
        }
        return false; // No hem pogut agafar ni la primera
    }

    private boolean agafarForquillaEsquerra() {
        if (forquillaEsquerra.agafar(this.nComencal)) {
            return true;
        }
        return false; 
    }   

    private boolean agafarForquillaDreta() {
        if (forquillaDreta.agafar(this.nComencal)) {
            return true;
        }
        return false;   
    }

    private void deixaForquillaEsquerra() {
        forquillaEsquerra.deixar(this.nComencal);
    }

    private void deixaForquillaDreta() {
        forquillaDreta.deixar(this.nComencal);
    }

    
    private void deixarForquilles() {
        deixaForquillaDreta();  // Primer dreta
        deixaForquillaEsquerra(); // Després esquerra
        System.out.println(name + " deixa les forquilles");
    }

    private double calcularGana() {
        if (iniciGana == 0) return 0;
        return (fiGana - iniciGana) / 1000.0; 
    }

    private void resetGana() {
        iniciGana = 0;
        fiGana = 0;
        gana = 0;
    }

    @Override
    public void run() {
        while (true) {
            pensar(); 
            
            while (!agafarForquilles()) {
                try {
                    sleep(ThreadLocalRandom.current().nextInt(100, 300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            menjar();
            deixarForquilles();
        }
    }
}