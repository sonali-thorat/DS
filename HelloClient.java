package corba-hello;
// HelloClient.java
import HelloApp.*;
import org.omg.CORBA.*;
import java.nio.file.*;

public class HelloClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            String ior = new String(Files.readAllBytes(Paths.get("Hello.ref")));
            org.omg.CORBA.Object objRef = orb.string_to_object(ior);
            Hello helloRef = HelloHelper.narrow(objRef);

            System.out.println("Client received: " + helloRef.sayHello());
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
}



//1.idlj -fall Hello.idl
// This generates HelloApp/ folder with helper classes.

// 2. Compile Java Files -  javac *.java HelloApp.java
// 3.Start CORBA Naming Service (optional) -tnameserv -ORBInitialPort 1050


// 4. Run the Server - java HelloServer -ORBInitialPort 1050 -ORBInitialHost localhost


//5.Run the Client - java HelloClient -ORBInitialPort 1050 -ORBInitialHost localhost