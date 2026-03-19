import java.util.ArrayList;
import java.util.List;

public class Barri {
    private Estanc estanc;
    private List<Fumador> fumadors;

    public Barri() {
        estanc = new Estanc();
        fumadors = new ArrayList<>();
        
        for (int i = 1; i <= 3; i++) {
            fumadors.add(new Fumador(estanc, i));
        }
        
    }

    public static void main(String[] args) {
        Barri barri = new Barri();

        barri.estanc.start();

        for (Fumador fumador : barri.fumadors) {
            fumador.start();
        }

        for (Fumador fumador : barri.fumadors) {
            try {
                fumador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        barri.estanc.tancarEstanc();
    }
}