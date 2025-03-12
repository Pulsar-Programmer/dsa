
import java.util.Optional;

/** CharNode (this will have the char frequency, left and right)*/
public class CharNode implements Comparable<CharNode> {
    public Optional<Character> character;
    /** This will act as the priority. */
    public int frequency;
    // public int code;
    public Optional<CharNode> left, right;



    public CharNode(char character, int frequency) {
        this.character = Optional.of(character);
        this.frequency = frequency;
        left = Optional.empty();
        right = Optional.empty();
    }

    public CharNode(int frequency, CharNode left, CharNode right) {
        this.character = Optional.empty();
        this.frequency = frequency;
        this.left = Optional.of(left);
        this.right = Optional.of(right);
    }


    /**
     * The compareTo method in the CharNode class if the frequencies are the same then it should
give priority (for the minheap) to characters vs non characters and also letters that come first in
the alphabet should come first in the priority queue.
     */
    @Override
    public int compareTo(CharNode other) {
        if(other.frequency != this.frequency){
            return this.frequency - other.frequency;
        }
        if(this.character.isEmpty()){
            return 1;
        }
        if(other.character.isEmpty()){
            return -1;
        }
        
        return this.character.get() - other.character.get();
    }

    @Override
    public String toString() {
        return "CharNode [character=" + character + ", frequency=" + frequency + ", left=" + left + ", right=" + right
                + "]";
    }

    




    
}
