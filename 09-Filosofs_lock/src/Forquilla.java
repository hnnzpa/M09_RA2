import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int numero; 
    private int propietari;
    private final int LLIURE = -1;
    private ReentrantLock bloqueig; 

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
        this.bloqueig = new ReentrantLock(true); 
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

    public boolean agafar(int filosof) {
        bloqueig.lock(); // Adquirir el lock
        try {
            if (propietari == LLIURE) {
                propietari = filosof;
                return true; 
            }
            return false;
        } finally {
            bloqueig.unlock(); 
        }
    }
    
    public boolean deixar(int filosof) {
        bloqueig.lock();
        try {
            if (propietari == filosof) {
                propietari = LLIURE;
                return true;
            }
            return false;
        } finally {
            bloqueig.unlock();
        }
    }
}