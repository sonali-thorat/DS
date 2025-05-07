import java.rmi.*;
import java.rmi.server.*;


public class ServerImpl extends UnicastRemoteObject implements ServerIntf {
      
     public ServerImpl() throws RemoteException {
      super(); // Explicitly call superclass constructor
  }
      public double addition(double num1 ,double num2) throws RemoteException{
         return num1 + num2;
      }
}
