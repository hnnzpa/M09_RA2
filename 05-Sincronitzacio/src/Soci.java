import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10.0f;
    private int espera_max = 100;
    private Random random;
    private int maxAnys = 10;

    public synchronized Compte getCompte() {
        return compte;
    }

    public Soci() {
        compte = Compte.getInstance();
    }

    public void dormir() {
        random = new Random();
        int temp = random.nextInt(espera_max) + 1;
        try {
            sleep(temp);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void run() {
        int anys = 0;
        while (anys < maxAnys) {
            for (int i = 1; i < 13; i++) {
                if (i % 2 == 0) { // parell
                    compte.ingresar(aportacio);
                    dormir();
                } else { // senar
                    compte.retirar(aportacio);
                    dormir();
                }
            }
            anys++;
        }

    }

}
