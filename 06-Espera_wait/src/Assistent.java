import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private Random random = new Random();

    public Assistent(String name, Esdeveniment e) {
        super(name);
        this.esdeveniment = e;
    }


    public void dormir() {
        int temp = random.nextInt(1000);
        try {
            sleep(temp);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean ferAlgo(double probabilitat) {
        return random.nextDouble() > probabilitat;
    }

    private double chance = 0.7;
    public void run() {
        while (true) {
            if (ferAlgo(chance)) { // si es mes de 70%
                try {
                    esdeveniment.ferReserva(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else { // si es menys de 30%
                esdeveniment.cancelaReserva(this);
            }
            dormir();
        }
    }
}
