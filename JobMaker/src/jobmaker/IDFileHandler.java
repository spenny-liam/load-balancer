
package jobmaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IDFileHandler {
    
    //Gets current ID number and increments the file by 1 so the next time
    //ID is accurate.
    public int getCurrentIDNumber() {
        
        BufferedWriter out = null;
        try {

            // Read File Contents
            BufferedReader br = new BufferedReader(new FileReader("idFile.txt"));
            String storedID = "0";
            int storedIDNumber = 0;
            while ((storedID = br.readLine()) != null) {
                storedIDNumber = (Integer.parseInt(storedID == null ? "0" : storedID));
            }

            // Write File Contents, increment ID by 1
            out = new BufferedWriter(new FileWriter("idFile.txt", false));
            out.write(String.valueOf(storedIDNumber + 1));
            
            return storedIDNumber;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

}
