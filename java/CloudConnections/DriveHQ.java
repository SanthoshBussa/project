package CloudConnections;
import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.net.ftp.FTPClient;

public class DriveHQ {
    
    

    FTPClient client = new FTPClient();
    FileInputStream fis = null;
    boolean status;

    public boolean upload(File file,String fname) {
        try {
            System.out.println("file is  your file "+fname);
            System.out.println("file is  your file "+file);
            System.out.println("successpavie");
            client.connect("ftp.drivehq.com");
            client.login("cloudproxy1", "Cloudproxy@1");
            System.out.println("file is  your file "+fname);
            System.out.println("file is  your file "+file);
            client.enterLocalPassiveMode();
            fis = new FileInputStream(file);
            status = client.storeFile(" /SLA/" + fname, fis);
            client.logout();
            fis.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (status) {
            System.out.println("success");
            return true;
        } else {
            System.out.println("failed");
            return false;
        }
    }
}
