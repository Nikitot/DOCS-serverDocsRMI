/**
 * SimpleServer.java
 * The server part of the SimpleTime application
 * @version 1.0
 */
//Import all needed packages
import java.rmi.*;
import java.rmi.registry.*;

public class SimpleServer
{
    // Registration for RMI serving.
    public static void main(String[] args)
    {
        try
        {
            //Set up security manager for the server part
            //System.setSecurityManager(new RMISecurityManager());
            //Create remote object
            ConnectRool sti = new ConnectRool(0); //создаем объект для локальной сети

            Registry localRegistry = LocateRegistry.createRegistry(1099); //создаем реестр для локальной сети

            localRegistry.rebind("//127.0.0.1/SimpleTime", sti);//регистрируем объект в реестре

            //Bind remote object into the RMI registry
            Naming.rebind("//127.0.0.1/SimpleTime", sti);
            //Echo a message
            System.out.println("Ready to serve");
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
}
