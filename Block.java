import java.util.Date;

public class Block{
    
    public String hash;
    public String previousHash;
    
    private String data; // gonna be something simple (like a message)
    private long timeStamp; // as number of millliseconds since 1/1/1970 (unix epoch)

    // Block Constructor
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
    }
}