
import java.util.Stack;

public class TreeNode<T> {
    private TreeNode left;
    private TreeNode right;
    private T value;
    

    public TreeNode(T val){
        value = val;
        left = null;
        right = null;
    }

    public TreeNode(T val, TreeNode left, TreeNode right){
        value = val;
        this.left = left;
        this.right = right;
    }

    public int size(){
        return 1 + (left == null ? 0 : left.size()) + (right == null ? 0 : right.size());
    }

    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height());
    }

    public TreeNode remove(TreeNode tn){
        TreeNode found = null;
        TreeNode parent = this;
        Stack to_investigate = new Stack();
        while(found == null){
            if(parent.left != null && parent.left == tn) found = parent.left;
            if(parent.right != null && parent.right == tn) found = parent.right;
            
        }
        return found;
    }
}
