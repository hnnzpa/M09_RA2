import java.util.LinkedList;
import java.util.List;

public class Barberia extends Thread{

    private List<Client> cua = new LinkedList<Client>();
    private Integer numCadires;
    public Object condBarber = new Object();

    public Barberia(Integer nCad){
        numCadires = nCad;
    }

    public synchronized Client seguentClient(){
        if (cua.isEmpty()){
            System.out.println("Nungú a la espera");
            return null; 
        }else {
            System.out.println("Li toca al client " + cua.getFirst().getNom());
            return cua.removeFirst();
        }
    }

    public synchronized void entraClient(Client client){
        if (cua.size() < numCadires){ 
            cua.addLast(client);
            System.out.println("Client " + client.getNom() + " en espera.");
            this.notifyAll(); 
        }else {
            System.out.println("No queden cadires, client " + client.getNom() + " se'n va.");
        }
    }

    public void entraDeu(){
        for (int i = 0; i < 10; i++) {
            entraClient(new Client(i));
            try{
                sleep(500);
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    public void run(){
        entraDeu();
        try{
            sleep(10000); // esprem 10 segons per a que entrin els clients
        }catch (Exception e){
            e.getMessage();
        }
        entraDeu();

    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Mike", barberia);
        barber.start();
        barberia.start();
    }
}
