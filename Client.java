import java.rmi.*;
import java.util.Scanner;

public class Client {
     public static void main(String[] args) {
       Scanner sc =new Scanner(System.in);


        try {   
            String serverURL ="rmi://localhost/Server";
            ServerIntf serverIntf  =(ServerIntf) Naming.lookup(serverURL);

            System.out.print("Enter 1st no:");
            double num1 =sc.nextDouble();

            System.out.print("Enter 2st no:");
            double num2 =sc.nextDouble();

           System.out.println("1st no:" + num1); 
           System.out.println("2st no:" + num2); 

           System.out.println("addition:" + serverIntf.addition(num1, num2)); 
 


        } catch (Exception e) {
           System.out.println("Exception Occured at Client" +e.getMessage());
        }
     }
}
