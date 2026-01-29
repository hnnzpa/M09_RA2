public class Organitzador {

    public void inicia(){
        Esdeveniment esdeveniment = new Esdeveniment(5);
        Assistent[] assistents = new Assistent[10];
        for (int i = 0; i < assistents.length; i++) {
        assistents[i] = new Assistent("Assistent-"+i, esdeveniment);
        }
        for (Assistent assistent : assistents) {
            assistent.start();
        }
    }
   
    public static void main(String[] args) {
        Organitzador org = new Organitzador();
        org.inicia();
    }
}
