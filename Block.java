import java.util.Date;

public class Block{
    
    public String hash;
    public String previousHash;
    private String data; // gonna be something simple (like a message)
    private long timeStamp; // as number of millliseconds since 1/1/1970 (unix epoch)
    private int nonce;

    // Block Constructor
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); // we must set the other values first!
    }

    public String calculateHash(){
        String calculatedhash = StringUtil.applySha256( // passing in concatenated strings 
            previousHash + // hash of previous block
            Long.toString(timeStamp) + // converts long timeStamp into string
            data // payload of the block (already a string)
        );

        return calculatedhash;
    }

    public void mineBlock(int difficulty){ // takes int called difficulty (number of 0's must solve for)
        String target = new String(new char[difficulty]).replace('\0', '0'); // create string with difficulty * "0"
        while(!hash.substring(0, difficulty).equals(target)){
            ++nonce;
            hash = calculateHash();
        }
        System.out.println("Block Mined: " + hash);
    }
}