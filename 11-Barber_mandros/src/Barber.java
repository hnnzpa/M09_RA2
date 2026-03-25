import java.util.Random;

public class Barber extends Thread {
    private Barberia barberia; 
    private Random rand = new Random();

    public Barber(String name, Barberia barberia) {
        super(name);
        this.barberia = barberia;
    }

    public void tallarCabell(){
        try {
            Integer suma = rand.nextInt(0, 100);
            sleep(900 + suma);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            Client c = barberia.seguentClient();

            if (c == null) {
                synchronized (barberia) {
                    try {
                        System.out.println(getName() + " dormint...");
                        barberia.wait(); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Tallant cabell client " + c.getNom());
                tallarCabell();
            }
        }
    }

}
