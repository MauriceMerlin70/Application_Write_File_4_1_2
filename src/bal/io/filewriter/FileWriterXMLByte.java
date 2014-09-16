package bal.io.filewriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWriterXMLByte {

    // private Writer fw=null;
    private static final Logger LOG = Logger.getLogger(FileWriterXMLByte.class.getName());

    int totalResults = 23;
    int itemsperPage = 10;
    int i = 0;
    int j = 0;

    public void doCreateFiles() throws MalformedURLException, IOException {
        
        
        File file = new File("./output");
        file.mkdir();

        //URL oracle = new URL("file:///D:/output/fileWriter_"+i+".xml");
        for (i = 1; i <= (totalResults / itemsperPage) + 1; ++i) {
            System.out.println("nextPage u" + i);
            File fileSub = new File("./output/sub" + i);
            fileSub.mkdir();
            LOG.info("sub Directory erstellt");
            
            // URL oracle = new URL("file:///D:/output/sub"+i+"/fileWriter_"+i+".xml");
            // es hollt mir die Files aus den Output Direcotrys mit den Sub Verzeichnissen
            URL oracle = new URL("file:///D:/output/sub" + i + "/fileWriter_" + j + ".xml");
            URLConnection urlConnection = oracle.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            for (; j < i * itemsperPage; j++) {
                if (j > totalResults) {

                    System.out.println("test");
                    break;

                }
                System.out.println("Filenummer " + (j));
                // Das müsste ich so modifizieren das es in sub Verzeichnisse schreibt
                //FileOutputStream outputStream = new FileOutputStream("fileWriter_" + j + ".xml");
                FileOutputStream outputStream = new FileOutputStream("./output/sub" + i + "/fileWriter_" + j + ".xml");

                int bytesRead = -1;
                int BUFFER_SIZE = 100;
                byte[] buffer = new byte[BUFFER_SIZE];

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

            }
        }

    }
}
