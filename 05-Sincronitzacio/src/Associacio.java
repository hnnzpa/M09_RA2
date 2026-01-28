public class Associacio {
    private int numSocis = 1000;
    Soci[] socis = new Soci[numSocis];

    public Associacio() {
        for (int i = 0; i < socis.length; i++) {
            socis[i] = new Soci();
        }
    }

    public void iniciaCompteTempsSocis() {
        for (Soci soci : socis) {
            soci.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci soci : socis) {
            try {
                soci.join();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void mostraBalancComptes() {
        float total = 0.0f;
        for (Soci soci : socis) {
            Compte compte = soci.getCompte();
            total += compte.getSaldo();
        }
        System.out.printf("El saldo total dels socis estimat: 0.\n Saldo total real: %.2f\n", total);
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();

    }

}
