
public class Motor extends Thread {

    public Motor(String name) {
        super(name);
    }

    private int potencia_actual = 0;
    private int potencia_objectiu = 0;

    public void setPotencia(int p) {
        this.potencia_objectiu = p;
    }

    private void dorm() {
        try {
            sleep((Math.random() < 0.5) ? 1000 : 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean faRes = false;
        while (!(potencia_actual == 0 && potencia_objectiu == 0)) {
            String msg;
            do {
                if (potencia_objectiu > potencia_actual) {
                    msg = "Incre.";
                    potencia_actual++;
                    faRes = false;
                } else if (potencia_objectiu < potencia_actual) {
                    msg = "Decre.";
                    potencia_actual--;
                    faRes = false;
                } else {
                    msg = "FerRes.";
                    dorm();
                    if (faRes)
                        continue;
                    faRes = true;
                }
                dorm();
                System.out.printf("Motor %s: %s Objectiu: %d Actual: %d\n", getName(), msg, potencia_objectiu,
                        potencia_actual);
            } while (!(potencia_actual == potencia_objectiu));
        }
    }

}
