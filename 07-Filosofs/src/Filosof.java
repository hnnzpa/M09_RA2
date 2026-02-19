import java.util.concurrent.ThreadLocalRandom;

public class Filosof extends Thread {
    private String name;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;

    public Filosof(String name) {
        this.name = name;
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
        if (gana > 0){
            gana--;
        }
        actualitzaPrioritat();
        try {
            sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        
        if (gana >= 10) {
            prioritat = Thread.MAX_PRIORITY;  // 10
        } else if (gana >= 7) {
            prioritat = Thread.MAX_PRIORITY - 1;  // 9
        } else if (gana >= 5) {
            prioritat = Thread.NORM_PRIORITY + 2;  // 7
        } else if (gana >= 3) {
            prioritat = Thread.NORM_PRIORITY;  // 5
        } else {
            prioritat = Thread.MIN_PRIORITY + 1;  // 2
        }
        
        setPriority(prioritat);
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            boolean haMenjat = false;

            while (!haMenjat) {
                if (forquillaEsquerra.agafar()) { // agafa esquerra
                    System.out.printf("Filosof: %s agafa forquilla esquerra(%d)\n", name,
                            forquillaEsquerra.getNumero());

                    if (forquillaDreta.agafar()) { // agafa dreta - pot menjar
                        System.out.printf("Filosof: %s agafant forquilla dreta(%d)\n", name,
                                forquillaDreta.getNumero());

                        menjar();
                        haMenjat = true;

                        forquillaDreta.deixar(); // ha menjat, deixa dreta
                        forquillaEsquerra.deixar(); // ha menjat, deixa esquerra
                        System.out.printf("Filosof: %s ha acabat de menjar\n", name);
                    }else{ // no ha pogut agafar dreta, deixa esquerra i espera
                        forquillaEsquerra.deixar();
                        System.out.printf("Filosof: %s deixa forquilla esquerra(%d) i espera (dreta ocupada)\n", name,
                                forquillaEsquerra.getNumero());
                        mesGana();
                        esperar();
                    }
                } else { // no pot agafar esquerra, espera
                    esperar();
                }
            }
        }
    }

}
