import java.util.ArrayList;
import com.google.gson.GsonBuilder; // view blockchain as Json

public class BlockChain{

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {	
		// add blocks to the blockchain ArrayList:
		blockchain.add(new Block("This is the first block", "0"));		
		blockchain.add(new Block("Second block, INCOMING",blockchain.get(blockchain.size()-1).hash)); 
		blockchain.add(new Block("Last but not least, third block",blockchain.get(blockchain.size()-1).hash));
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
	}

    public static Boolean isChainValid(){ // will loop through all blocks in chain and compare hashes
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0'); // create string with difficulty * "0"


        // loop through block chain
        for(int i = 1; i < blockchain.size(); ++i){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // compare registered hash with the calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current hashes not equal :(");
                return false;
            }
            // compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ){
                System.out.println("Previous hashes not equal :(");
                return false;
            }
            // check if hash is solved
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}