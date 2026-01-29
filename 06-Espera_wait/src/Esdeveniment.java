import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<Assistent> assistents = new ArrayList<Assistent>();;
    private int nMax;
    private int placesDisp;

    public Esdeveniment(int n) {
        this.nMax = n;
        placesDisp = nMax;
    }

    public synchronized void ferReserva(Assistent assistent) throws Exception {
        while (placesDisp == 0) {
            wait();
        }
        assistents.add(assistent);
        placesDisp--;
        System.out.printf("%s ha fet una reserva. Places disponibles: %d\n", assistent.getName(), placesDisp);
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (reservat(assistent)) {
            assistents.remove(assistent);
            placesDisp++;
            System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d\n", assistent.getName(),
                    placesDisp);
        } else {
            System.out.printf("%s no ha pogut cancel·la una reserva inexistent. Places disponibles: %d\n",
                    assistent.getName(), placesDisp);

        }
    }

    public boolean reservat(Assistent assistent) {
        return assistents.contains(assistent);
    }
}
