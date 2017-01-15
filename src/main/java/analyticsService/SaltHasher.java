package analyticsService;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class SaltHasher {

    private static String saltParam = "bebamanalytics";

    public static String hashString(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((key + saltParam).getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }
}
