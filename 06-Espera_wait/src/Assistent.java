import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private Random random = new Random();
    private double chance = 0.5;

    public Assistent(String name, Esdeveniment e) {
        super(name);
        this.esdeveniment = e;
    }

    public boolean ferAlgo(double probabilitat) {
        return random.nextDouble() > probabilitat;
    }

    public void dormir() {
        int temp = random.nextInt(1000);
        try {
            sleep(temp);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void run() {
        while (true) {
            if (ferAlgo(chance)) { // si es mes de 50%
                try {
                    esdeveniment.ferReserva(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else { // si es menys de 50%
                esdeveniment.cancelaReserva(this);
            }
            dormir();
        }
    }
}
