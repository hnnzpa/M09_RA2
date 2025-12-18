public class Futbolista extends Thread{

    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols = 0; 
    private int ntirades = 0; 


    public static int getNumJugadors() {
        return NUM_JUGADORS;
    }
    

    public int getNgols() {
        return ngols;
    }


    public int getNtirades() {
        return ntirades;
    }


    public static int getNumTirades() {
        return NUM_TIRADES;
    }

    public static float getProbabilitat() {
        return PROBABILITAT;
    }

    public Futbolista(String nom){
        super(nom);
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
                ntirades++; 
            if (Math.random() >= PROBABILITAT) {
                ngols++; 
            }
        }
        System.out.printf("%s   \t -> %d gols \n", this.getName(), ngols);
    }

    public static void main(String[] args) {
        String[] noms = "Piqué,Vinicius,Torres,Ramos,Ronaldo,Lewan,Belli,Arnau,Aspas,Messi,MBapé".split(",");

        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];

        System.out.println("Inici dels xuts -----------");
        System.out.println("Fi del xuts ---------------");
        System.out.println("------ Estadístiques ------");

        for (int i = 0; i < noms.length; i++) {
            jugadors[i] = new Futbolista(noms[i]);
            jugadors[i].start();  
        }

        for (int i = 0; i < jugadors.length; i++) {
            try {
                jugadors[i].join(); 
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }


    }

}
