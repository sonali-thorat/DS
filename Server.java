import java.rmi.*;


public class Server {
       public static void main(String[] args) {
            try{
                     ServerImpl serverImpl = new ServerImpl();
                     Naming.rebind("Server" ,serverImpl); //register
           
                      System.out.println("Server Started...");
            }catch(Exception e){
                    System.out.println("Exception occure at server" +e.getMessage());
            }
       }
}



//how to run 
//   1 javac *.java
//   2 rmiregistry
//   3 java Server 
//   4 java Client 
