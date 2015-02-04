/**
 * ConnectRool.java
 * The impplementation of SimpleTime interface in a remote object.
 * @version 1.0
 */
//Import all needed packages

import com.sun.org.apache.xpath.internal.SourceTree;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public final class ConnectRool extends UnicastRemoteObject implements ConnectInterface {
    User user = new User();
    Document document = new Document();
    AccessRool accessRool = new AccessRool();

    // Implementation of the interface
    public boolean createUser(String login, String password) throws RemoteException {
        return user.createUser(login, password);
    }

    public boolean checkUser(String login, String password) throws RemoteException {
        return user.checkPass(login, password);
    }

    public boolean createDocument(String name, String masterLogin, String text) throws RemoteException {
        accessRool.setDocumentUserAccess(name, masterLogin, masterLogin, true);
        return document.createDocument(name, masterLogin);
    }

    public String getDocument(String name, String masterLogin) throws RemoteException {
        return document.openDocument(name, masterLogin);
    }

    public ArrayList<String> getAllDocsFromUser(String login) throws RemoteException {
        return document.getAllDocsFromUser(login);
    }

    public boolean writeStatus(String docname, String masterLogin) throws RemoteException {
        docname = docname.replaceAll(" ", "");
        masterLogin = masterLogin.replaceAll(" ", "");

        List<String> writers = accessRool.getWriters();
        List<String> documents = accessRool.getDocuments();

        //System.out.println("[STATUS REQUEST] |" + docname + "| |" + masterLogin + "|");
        if (!documents.isEmpty())
            for (int i = 0; i < documents.size(); i++) {
                if (masterLogin.equals(writers.get(i))
                        && docname.equals(documents.get(i))) {
                    return true;
                }
            }
        return false;

    }

    public boolean updateDocument(String text, String name, String masterLogin) throws RemoteException {
        return document.setDataDocument(text, name, masterLogin);
    }


    public boolean setWriter(String fullname, String masterLogin) throws RemoteException {
        return accessRool.setWriter(fullname, masterLogin);
    }

    public ArrayList<String> getDocumentInfo(String clearDocName)throws RemoteException{
        return accessRool.getDocumentInformation(clearDocName);
    }

    // Must implement constructor to throw RemoteException
    public ConnectRool(int port) throws RemoteException {
        super(port);
    }


}
