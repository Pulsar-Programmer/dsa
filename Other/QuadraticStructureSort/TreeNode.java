

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

    public TreeNode get(Comparable num){
        if(num.equals(value)){
            return this;
        }
        var selected = num.compareTo(value) < 0 ? left : right;
        if(selected == null) {
            return null;
        } else{
            return selected.get(num);
        }
    }

    // public TreeNode<Integer> remove(TreeNode<Integer> tn){
    //     TreeNode found = null;
    //     TreeNode parent = this;
    //     Stack to_investigate = new Stack();
    //     while(found == null){
    //         var selection = left != null && left.value < tn.value ? left : right;

    //         if(parent.left != null && parent.left.value == tn.value) found = parent.left;
    //         if(parent.right != null && parent.right == tn) found = parent.right;
            
    //     }
    //     return found;
    // }

    // public TreeNode discard_left(){
        

    // }
}
