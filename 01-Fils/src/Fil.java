public class Fil extends Thread{

    private int delay;
    private int timer;

    public Fil(String nom){
        super(nom);
    }

    public Fil(String nom, int timer, int delay){
        super(nom);
        this.timer = timer;
        this.delay = delay;
    }

    @Override
    public void run(){
        for (int i = 1; i < timer; i++) {}

        for (int i = 1; i <10; i++) {
            System.out.printf("%s %d\n", this.getName(), i);
            try{
                sleep(delay);
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        System.out.printf("Acaba el fil: %s\n", this.getName());
    }
    
}
