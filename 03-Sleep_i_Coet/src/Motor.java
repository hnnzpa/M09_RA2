
public class Motor extends Thread {

    public Motor(String name) {
        super(name);
    }

    private int potencia_actual = 0;
    private int potencia_objectiu;

    public void setPotencia(int p) {
        this.potencia_objectiu = p;

    }

    @Override
    public void run() {
        do {

            while (potencia_actual != potencia_objectiu) {
                System.out.println("Entrando en elwhile 2");
                if (potencia_objectiu > potencia_actual) {
                    System.out.printf("Motor %s: Incre. Objectiu: %d Actual: %d\n",
                            getName(), potencia_objectiu, potencia_actual);
                    int dorm = (Math.random() < 0.5) ? 1000 : 2000;
                    try {
                        sleep(dorm);
                        potencia_actual++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (potencia_objectiu < potencia_actual) {
                    System.out.printf("Motor %s: Decre. Objectiu: %d Actual: %d\n",
                            getName(), potencia_objectiu, potencia_actual);
                    int dorm = (Math.random() < 0.5) ? 1000 : 2000;
                    try {
                        sleep(dorm);
                        potencia_actual--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.printf("Motor %s: FerRes. Objectiu: %d Actual: %d\n", 
                                getName(), this.potencia_objectiu, this.potencia_actual);

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (potencia_objectiu == 0){
                        break;
                    }
                }
            }

        System.out.printf("Motor %s apagado\n", getName());

        }while (potencia_actual != 0);

        System.out.println("Final de run");
    }

}
