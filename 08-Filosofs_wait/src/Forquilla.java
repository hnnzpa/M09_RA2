public class Forquilla {
    private int numero; 
    private int propietari;
    private final int LLIURE = -1;  

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public boolean isLliure() {
        return propietari == LLIURE;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    public synchronized boolean agafar(int filosof) {
        if (propietari == LLIURE) {
        propietari = filosof;
        return true; 
        }
        return false;
    }
    
    public synchronized boolean deixar(int filosof) {
        if (propietari == filosof) {
            propietari = LLIURE;
            notifyAll();
            return true;
        }
        return false;
    }
}