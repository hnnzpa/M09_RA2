import java.util.Random;

public class Home extends Thread{
    private BanyUnisex bany;
    private Random rand = new Random();

    public Home(String name, BanyUnisex bany) {
        super(name);
        this.bany = bany;
    }

    public void utilitzaLavabo(){
        int sec = rand.nextInt(2000, 3000);
        try{
            sleep(sec);
            System.out.printf("%s ha acabat d'usar el bany%n", this.getName());
        }catch (Exception e){ e.getMessage();}
    }


    public void run(){
        System.out.printf("%s vol entrar al bany%n", this.getName());
        bany.entraHome();
        utilitzaLavabo();
        bany.surtHome();
    }
}
