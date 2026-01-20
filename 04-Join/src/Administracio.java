public class Administracio extends Thread{
    private int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

    public Administracio(){
        for (int i = 0; i < poblacio_activa.length; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-"+i, 25000, 20, 65);
        }
    }

    private void marxa(){
        for (Treballador treballador : poblacio_activa) {
            treballador.start();
        }

        for (Treballador treballador : poblacio_activa){
            try{
                treballador.join();
                System.out.printf("%s -> edat: %d / total: %.2f\n", treballador.getName(), treballador.getEdat(), treballador.getCobrat());

            }catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.marxa();
    }
}
