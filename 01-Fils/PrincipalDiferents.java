public class PrincipalDiferents {

    public static void main(String[] args) {
        
        Fil pepe = new Fil("Pepe", 1000, 0);
        Fil juan = new Fil("Juan", 1000, 0);

        pepe.start();
        pepe.setPriority(Thread.MAX_PRIORITY);
        juan.start();
        juan.setPriority(Thread.MIN_PRIORITY);

        System.out.println("Acaba thread main");

    }
    
}
