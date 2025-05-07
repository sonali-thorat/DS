package corba-hello;

import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import java.io.FileWriter;

public class HelloServer {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HelloImpl helloImpl = new HelloImpl();
            helloImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            Hello href = HelloHelper.narrow(ref);

            String ior = orb.object_to_string(href);
            FileWriter fw = new FileWriter("Hello.ref");
            fw.write(ior);
            fw.close();

            System.out.println("Server ready and waiting...");
            orb.run();
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
        }
    }
}


