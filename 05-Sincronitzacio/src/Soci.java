import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int espera_max = 100;
    private Random random;
    private int maxAnys = 10;

    public synchronized Compte getCompte() {
        return compte;
    }

    public Soci() {
        compte = Compte.getInstance();
    }

    public synchronized void ingresar(float quantitat) {
        quantitat += compte.getSaldo();
        compte.setSaldo(quantitat);
    }

    public synchronized void retirar(float quantitat) {
        quantitat = compte.getSaldo() - quantitat;
        compte.setSaldo(quantitat);
    }

    public void dormir() {
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
                    ingresar(aportacio);
                    dormir();
                } else { // senar
                    retirar(aportacio);
                    dormir();
                }
            }
            anys++;
        }

    }

}
