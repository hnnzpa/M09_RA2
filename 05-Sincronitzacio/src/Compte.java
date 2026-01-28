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

    public synchronized void ingresar(float quantitat) {
        quantitat += saldo;
        this.setSaldo(quantitat);
    }

    public synchronized void retirar(float quantitat) {
        quantitat = saldo - quantitat;
        this.setSaldo(quantitat);
    }


    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

}
