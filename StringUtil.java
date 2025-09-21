import java.security.MessageDigest; // SHA-256

// Purpose: takes a string and applies SHA-256 algorithm, then returns the generated signature as a string
public class StringUtil{
    // Implement Sha256 to string and returns result
    public static String applySha256(String input){
        try {   // handle exceptions (hasing can throw errors like encoding or something unsupported)
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Use SHA-256 hashing algorithm
            // Apply sha256 to the input (String input)
            byte[] hash = digest.digest(input.getBytes("UTF-8")); // convert input string into byte array and then apply SHA-256 alogorithm)
            StringBuffer hexString = new StringBuffer(); // Contain the hash as a hexidecimal
            for(int i = 0; i < hash.length; ++i){ // goes through evey byte
                String hex = Integer.toHexString(0xff & hash[i]); // converts byte (-128 to 127) into unsigned integer (0-255)
                if(hex.length() == 1) // if hex string is only 1 character it will prepend it with '0' (so its consistent)
                    hexString.append('0');
                hexString.append(hex); // adds hex representation to hexString
            } // all 32 bytes of the hash should be converted into 64 hex characters
            return hexString.toString(); // final SHA-256 hash in hexadecimal format
        }
        catch(Exception e)  {
            throw new RuntimeException(e); // if anything goes wrong, throw exception
        }
    }
}