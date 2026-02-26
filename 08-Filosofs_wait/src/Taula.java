import java.util.ArrayList;

public class Taula {
    private ArrayList<Filosof> comensals;
    private ArrayList<Forquilla> forquilles;
    private int numeroFilosofs;

    public Taula(int numeroFilosofs) {
        this.numeroFilosofs = numeroFilosofs;
        this.comensals = new ArrayList<>(numeroFilosofs);
        this.forquilles = new ArrayList<>(numeroFilosofs);

        for (int i = 0; i < numeroFilosofs; i++) {
            forquilles.add(new Forquilla(i));
        }

        for (int i = 0; i < numeroFilosofs; i++) {
            Filosof filosof = new Filosof("fil" + i, i);
            comensals.add(filosof);
        }

        for (int i = 0; i < numeroFilosofs; i++) {
            Filosof filosof = comensals.get(i);

            Forquilla forquillaEsq = forquilles.get(i);

            Forquilla forquillaDre = forquilles.get((i + 1) % numeroFilosofs);

            filosof.setForquilles(forquillaEsq, forquillaDre);
        }
    }

    public void showTaula() {
        System.out.println("\n========== CONFIGURACIÓ DE LA TAULA ==========");
        for (int i = 0; i < numeroFilosofs; i++) {
            System.out.printf("Comensal: %s esq: %d dret: %d\n",
                    comensals.get(i).getName(),
                    forquilles.get(i).getNumero(),
                    forquilles.get((i + 1) % numeroFilosofs).getNumero());
        }
        System.out.println("-----------------------------------------------");
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numeroFilosofs = 5; 

        Taula taula = new Taula(numeroFilosofs);
        taula.showTaula();
        taula.cridarATaula();

        Thread.sleep(30000); // Deixar que els filòsofs menjin durant 30 segons

        // Mostrar estadísticas finales
        System.out.println("\n========== ESTADÍSTIQUES FINALS ==========");
        int ganaTotal = 0;
        for (int i = 0; i < numeroFilosofs; i++) {
            Filosof f = taula.comensals.get(i);
            System.out.printf("Filosof-%d: gana = %d\n", i, f.getGana());
            ganaTotal += f.getGana();
        }
        System.out.println("Gana total: " + ganaTotal); // hauria de ser 0
        System.out.println("-----------------------------------------");

        System.exit(0); // Finalitzar el programa
    }
}
