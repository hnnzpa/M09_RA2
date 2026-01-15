import java.util.Scanner;

public class Coet {
    Motor[] motors = {
            new Motor("0"),
            new Motor("1"),
            new Motor("2"),
            new Motor("3")
    };

    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            for (Motor motor : motors) {
                motor.setPotencia(p);
            }
        } else {
            System.out.println("Error: El valor ha de ser un número entre el 0 i el 10");
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public static void main(String[] args) {

        Coet coet = new Coet();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        coet.passaAPotencia(n);
        coet.arranca();

        while (true) {
            n = scanner.nextInt();

            coet.passaAPotencia(n);
            System.out.printf("Passant a potència %d\n", n);
            if (n == 0) {
                break;
            }
        }
        scanner.close();
    }

}
