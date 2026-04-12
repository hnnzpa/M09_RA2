import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {

    private final Object BANY_BUIT = new Object();
    private final Object BANY_AMB_HOMES = new Object();
    private final Object BANY_AMB_DONES = new Object();

    private Object estatActual = BANY_BUIT;
    private int ocupants = 0;
    private final int CAPACITAT_MAX = 3;

    public BanyUnisex() {
    }

    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);

    private ReentrantLock lockEstat = new ReentrantLock(true);

    public String getEstat(){
        String estat;
        if(estatActual == BANY_AMB_DONES){
            estat = "Dones";
        }else if (estatActual == BANY_AMB_HOMES){
            estat = "Homes";
        }else {
            estat = "Buit";
        }
        return estat;
    }

    public void entraHome() {
        while (true) {
            lockEstat.lock();

            try {
                if (estatActual != BANY_AMB_DONES) {
                    if (estatActual == BANY_BUIT) {
                        estatActual = BANY_AMB_HOMES;
                    }

                } else {
                    continue;
                }
            } finally {
                lockEstat.unlock();
            }

            if (capacitat.tryAcquire()) {
                lockEstat.lock();
                try {
                    ocupants++; // suma para tener la capacidad para los mensajes.
                    System.out.printf("Home entra al bany. Ocupants: %d, Estat: %s%n", ocupants, getEstat());
                    break;
                } finally {
                    lockEstat.unlock();
                }
            }

        }

    }

    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            System.out.printf("Home surt del bany. Ocupants: %d, Estat: %s%n", ocupants, getEstat());

        } finally {
            lockEstat.unlock();
        }
    }

    public void entraDona() {
        while (true) {
            lockEstat.lock();

            try {
                if (estatActual != BANY_AMB_HOMES) {
                    if (estatActual == BANY_BUIT) {
                        estatActual = BANY_AMB_DONES;
                    }

                } else {
                    continue;
                }
            } finally {
                lockEstat.unlock();
            }

            if (capacitat.tryAcquire()) {
                lockEstat.lock();
                try {
                    ocupants++; // suma para tener la capacidad para los mensajes.
                    System.out.printf("Dona entra al bany. Ocupants: %d, Estat: %s%n", ocupants, getEstat());
                    break;
                } finally {
                    lockEstat.unlock();
                }
            }

        }

    }

    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            System.out.printf("Dona surt del bany. Ocupants: %d, Estat: %s%n", ocupants, getEstat());

        } finally {
            lockEstat.unlock();
        }

    }

    

    public static void main(String[] args) {

        BanyUnisex bany = new BanyUnisex();
        for (int i = 0; i < 5; i++) {
            Home h = new Home("Home-" + i, bany);
            h.start();
            Dona d = new Dona("Dona-" + i, bany);
            d.start();
        }
    }

}
