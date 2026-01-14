import java.util.Scanner;

public class Coet {
        Motor motor1 = new Motor("0");
        Motor motor2 = new Motor("1");
        Motor motor3 = new Motor("2");
        Motor motor4 = new Motor("3");

    public void passaAPotencia(int p){
        if (p >= 0 && p <=10){
            motor1.setPotencia(p);
            motor2.setPotencia(p);
            motor3.setPotencia(p);
            motor4.setPotencia(p);
        } else {
            System.out.println("Error: El valor ha de ser un número entre el 0 i el 10");
        }
    }
    
    public void arranca(){
        motor1.start();
        motor2.start();
        motor3.start();
        motor4.start();
    }

    public static void main(String[] args) {

        Coet coet = new Coet();
        coet.arranca();

        Scanner scanner = new Scanner(System.in);
        
        while (true){
            int n = scanner.nextInt();

            coet.passaAPotencia(n);
            System.out.printf("Passant a potència %d\n", n);
            if (n == 0){
                break;
            }
        }

        System.out.println("Termina main");
        scanner.close();
    }

}
