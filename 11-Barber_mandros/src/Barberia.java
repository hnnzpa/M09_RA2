import java.util.LinkedList;
import java.util.List;

public class Barberia {

    private List<Client> cua = new LinkedList<Client>();
    private Integer numCadires;
    private Object condBarber = new Object();

    public Barberia(Integer nCad){
        numCadires = nCad;
    }

    public synchronized Client suguentClient(){
        if (cua.isEmpty()){
            return null; 
        }else {
            Client seguent = cua.getFirst();
            cua.removeFirst();
            return seguent;
        }
    }

    public void entraClient(Client client){
        if (cua.size() < numCadires){ // hi han cadires
            cua.addLast(client);
            condBarber.notifyAll(); // condicio despertar barber
        }
    }

    public void entraDeu(){
        for (int i = 0; i < 10; i++) {
            entraClient(new Client(i));
        }
    }

    public void run(){
        entraDeu();
        try{
            Thread.sleep(50);
        }catch (Exception e){
            e.getMessage();
        }
        entraDeu();

    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Mike");
        barber.start();
        barberia
    }
}
