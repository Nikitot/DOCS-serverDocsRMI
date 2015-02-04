import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikitot on 18.11.14.
 */
public class Document {

    public boolean setDataDocument(String text, String name, String masterLogin) {
        name = name.replaceAll(" ", "");
        masterLogin = masterLogin.replaceAll(" ", "");

        //System.out.println("[UPDATE DOCUMENT] " + name + " " + masterLogin + ".txt");
        //System.out.println(text + "\n");
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileOutputStream("Documents/" + name + " " + masterLogin + ".txt"));
            pw.write(text);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean createDocument(String name, String masterLogin) {
        name = name.replaceAll(" ", "");
        masterLogin = masterLogin.replaceAll(" ", "");
        File file = new File("./Documents/" + name + " " + masterLogin + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<String> getAllDocsFromUser(String masterLogin) {
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        LineNumberReader lnr;
        try {
            lnr = new LineNumberReader(new BufferedReader(new FileReader("./Documents/" + masterLogin + ".ini")));

            while (true) {
                line = lnr.readLine();
                if (line != null)
                    lines.add(line);
                else
                    break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public String openDocument(String name, String masterLogin) {

        name = name.replaceAll(" ", "");
        masterLogin = masterLogin.replaceAll(" ", "");
        String document = "";

        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(new FileReader("Documents/" + name + " " + masterLogin + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return document;
        }

        try {
            while ((line = reader.readLine()) != null) {
                document = document + line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }
}
