package corba-hello;

import HelloApp.*;
import org.omg.CORBA.*;

public class HelloImpl extends HelloPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public String sayHello() {
        return "Hello from CORBA server!";
    }
}
