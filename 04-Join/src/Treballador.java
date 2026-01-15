import java.util.Random;

public class Treballador extends Thread{
    private int sou_anual_brut;
    private int edat_inici_treball; 
    private int edat_fi_treball;
    private int edat_actual; 
    private float cobrat;
    private Random rnd;
    private int cobra;
    private int pagaImpostos; 

    public int getEdat() {
        return edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }

    public Treballador(String name, int sou, int inici_treball, int fi_treball){
        super(name);
        this.sou_anual_brut = sou;
        this.edat_inici_treball = inici_treball;
        this.edat_fi_treball = fi_treball;
        edat_actual = 0; 
        cobrat = 0.0f;

    }

    @Override
    public void run(){
        
    }
}
