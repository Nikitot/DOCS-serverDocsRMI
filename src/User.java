import java.io.*;

/**
 * Created by Nikitot on 18.11.14.
 */
public class User {

   private boolean writeFile(String filename,String text){
       try {
           PrintStream printStream = new PrintStream(new FileOutputStream(filename, true), true);
           printStream.println(text);
           printStream.close();
           return true;
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }
   }

    public boolean createUser(String login, String password) { // нужна проверка на логины
            if (checkLogin(login) == -1) {

                try {
                    File fileInfo = new File("./Documents/" + login + ".ini");
                    fileInfo.createNewFile();
                    return (writeFile("datel.txt", login) && writeFile("datep.txt", password));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

            } else {
                return false;
            }
    }

    public int checkLogin(String login) {
        int loginNum = -1;
        String s;
        LineNumberReader lnr;
        try {
            lnr = new LineNumberReader(new BufferedReader(new FileReader("datel.txt")));

            while (true) {
                s = lnr.readLine();
                if (s == null)
                    break;

                if (s.contains(login)) {
                    loginNum = lnr.getLineNumber();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginNum;
    }

    public boolean checkPass(String login, String password) {

        int loginNum = checkLogin(login);
        if (loginNum != -1){

            int passNum = -1;
            String s;
            LineNumberReader lnr;
            try {
                lnr = new LineNumberReader(new BufferedReader(new FileReader("datep.txt")));

                while (true) {
                    s = lnr.readLine();
                    if (s == null)
                        break;

                    if (s.indexOf(password) != -1) {
                        passNum = lnr.getLineNumber();
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(passNum == loginNum){
                return true;
            }
            else{
                return false;
            }

        }

        return true;
    }
}
