public class PrincipalEstricte {

    public static void main(String[] args) {
        
        Fil pepe = new Fil("Pepe", 1000, 1);
        Fil juan = new Fil("Juan", 1000, 1);

        juan.start();
        juan.setPriority(Thread.NORM_PRIORITY);
        pepe.start(); 
        pepe.setPriority(Thread.NORM_PRIORITY);

        System.out.println("Acaba thread main");
    }
    
}
