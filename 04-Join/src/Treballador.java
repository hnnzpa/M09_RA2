import java.util.Random;

public class Treballador extends Thread{
    private int sou_anual_brut;
    private int edat_inici_treball; 
    private int edat_fi_treball;
    private int edat_actual; 
    private float cobrat;
    private Random rnd;
    private float cobra;
    private float pagaImpostos; 

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
        cobra = sou_anual_brut/12.0f;
    }

    public void cobra(){
        cobrat += cobra;
    }

    public void pagaImpostos(){
        float impostos = cobra*0.24f;
        pagaImpostos += impostos;
        cobrat -= impostos;
    }

    @Override
    public void run(){
        for (edat_actual = edat_inici_treball; edat_actual < edat_fi_treball; edat_actual ++){
            for (int mes = 0; mes < 12; mes++) {
                cobra();
                pagaImpostos();
                
            }
        }


    }
}
