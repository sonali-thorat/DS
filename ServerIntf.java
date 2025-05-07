import java.rmi.*;


interface ServerIntf extends Remote{
        public double addition(double num1 ,double num2) throws RemoteException;  //if object not found thorw eception
}



