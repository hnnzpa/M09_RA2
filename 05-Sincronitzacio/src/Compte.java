public class Compte {
    private static Compte instancia; 

    private float saldo; 

    private Compte(){}

    public static Compte getInstance(){
        if (instancia == null){
            instancia = new Compte();
        }
        return instancia;
    }

    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

}
