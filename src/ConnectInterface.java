/**
 * SimpleTime.java
 * Definition of the SimplTime interface.
 * @version 1.0
 */
//Import needed packages

import java.rmi.*;
import java.util.ArrayList;
import java.util.List;

public interface ConnectInterface extends Remote {
    boolean createUser(String login, String password) throws RemoteException;
    boolean checkUser(String login, String password) throws RemoteException;
    boolean createDocument(String name, String masterLogin, String text) throws RemoteException;
    String getDocument(String name, String masterLogin) throws RemoteException;
    ArrayList<String> getAllDocsFromUser(String masterLogin) throws RemoteException;
    boolean updateDocument(String text, String name, String masterLogin) throws RemoteException;
    boolean setWriter(String fullname, String masterLogin) throws RemoteException;
    boolean writeStatus(String docname, String masterLogin) throws RemoteException;
    ArrayList<String> getDocumentInfo(String clearDocName)throws RemoteException;
}