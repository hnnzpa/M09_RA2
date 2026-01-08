public class DormAleatori extends Thread{

    private final long CREAT;
    
    public DormAleatori(String name){
        super(name);
        this.CREAT = System.currentTimeMillis();

    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            int num_iteracio = i; 
            long interval_aleatori = (long)(Math.random() * 1000);
            System.out.printf("%s(%d) a dormir %dms \t total \t %dms\n", this.getName(), num_iteracio, interval_aleatori, System.currentTimeMillis() - this.CREAT);
            try{
                sleep(interval_aleatori);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();

        System.err.println("--Fi de main --------------");
    }
}
