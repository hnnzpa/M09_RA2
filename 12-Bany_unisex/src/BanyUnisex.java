import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {

    private final Object BANY_BUIT = new Object();
    private final Object BANY_AMB_HOMES = new Object();
    private final Object BANY_AMB_DONES = new Object();


    private Object estatActual = BANY_BUIT;
    private int ocupants = 0;
    private final int CAPACITAT_MAX = 3;

    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);

    private ReentrantLock lockEstat = new ReentrantLock();

    public void entraHome() throws Exception{
        synchronized(BANY_AMB_HOMES) {
            while(estatActual == BANY_AMB_DONES){
                BANY_AMB_HOMES.wait();
            }
            if (estatActual != BANY_AMB_HOMES){
                estatActual = BANY_AMB_HOMES;}
            if(capacitat.tryAcquire()){
                try{
                    lockEstat.lock();
                    ocupants++;
                }finally{
                    lockEstat.unlock();
                }
            }
        }
    }

    public void entraDona() throws Exception{
        synchronized(BANY_AMB_DONES) {
            while(estatActual == BANY_AMB_HOMES){
                BANY_AMB_DONES.wait();
            }
            if (estatActual != BANY_AMB_DONES){
                estatActual = BANY_AMB_DONES;}{
            if(capacitat.tryAcquire()){
                try{ 
                    lockEstat.lock();
                    ocupants++;
                }finally{
                    lockEstat.unlock();
                }
            }
        }
    }
    

}
