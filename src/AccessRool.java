import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nikitot on 25.11.14.
 */


public class AccessRool {
    public ArrayList<String> writers = new ArrayList<String>();
    public ArrayList<String> documents = new ArrayList<String>();

    public boolean setWriter(String fullname, String login) {
        if (documents.size() > 0) {
            for (int i = 0; i < documents.size(); i++) {
                if (documents.get(i).equals(fullname)) {
                    writers.set(i, login);
                    System.out.println("[UPDATE WRITER] " + fullname + " " + login);
                    return true;
                } else {
                    documents.add(fullname);
                    writers.add(login);
                    System.out.println("[SET WRITER] " + fullname + " " + login);
                    return true;
                }
            }
        } else {
            documents.add(fullname);
            writers.add(login);
            //System.out.println("[SET FIRST WRITER] " + fullname + " " + login);
            return true;
        }
        return false;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public ArrayList<String> getDocuments() {
        return documents;
    }

    public ArrayList<String> getDocumentInformation(String cleanDocumentName) {
        ArrayList<String> lines = new ArrayList<String>();

        File folder = new File("./Documents/");

        final String mask = ".ini";
        String[] files = folder.list(new FilenameFilter() {

            @Override
            public boolean accept(File folder, String name) {
                if (name.toLowerCase().endsWith(mask)) return true;
                return false;
            }
        });

        for (int i = 0; i < files.length; i++) {
            String filename = files[i];
            try {
                LineNumberReader lnr;
                String newLine;
                lnr = new LineNumberReader(new BufferedReader(new FileReader("./Documents/" + filename)));
                while (true) {
                    newLine = lnr.readLine();
                    if (newLine == null)
                        break;
                    if (newLine.contains(cleanDocumentName)) {
                        String delims = "/";
                        String[] tokens = newLine.split(delims);
                        lines.add(filename.substring(0, filename.length() - 4) + "/" + tokens[2]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return lines;
    }

    public boolean setDocumentUserAccess(String documentName, String masterLogin, String login, boolean exclusiveAccess) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("Documents/" + login + ".ini", true), true);
            String line;
            LineNumberReader lnr;
            try {
                lnr = new LineNumberReader(new BufferedReader(new FileReader("Documents/" + login + ".ini")));

                while (true) {
                    line = lnr.readLine();
                    if (line == null)
                        break;
                    if (line.contains(masterLogin + "/" + documentName + "/1") || line.contains(masterLogin + "/" + documentName + "/0")) {
                        if (exclusiveAccess)
                            printStream.println(masterLogin + "/" + documentName + "/1");
                        else
                            printStream.println(masterLogin + "/" + documentName + "/0");
                    }
                    printStream.println(masterLogin + "/" + documentName + "/1");
                    break;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
