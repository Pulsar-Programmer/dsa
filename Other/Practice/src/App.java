import java.util.Optional;

import javax.swing.tree.TreeNode;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        var amt = lsi(new int[]{0, 1, 2, 3, 4, 5, 5, 4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2 ,2 ,3, 1}, 0, 0, 0);
        System.out.println(amt);
    }





    public static int lsi(int[] list, int i, int amt, int max){
        if (i + 1 == list.length) {
            return Math.max(amt, max);
        }
        var new_amt = list[i + 1] >= list[i] ? amt + 1 : 1;
        var new_max = Math.max(amt, max);
        return lsi(list, i+1, new_amt, new_max);
    }



    public static int lss(String s1, String s2, int max){
        var new_s1 = s1.substring(1);
        var new_s2 = s2.substring(1);
        return Math.max(lss(new_s1, s2), lss(s1, new_s2));
    }






    public static boolean isBST(TNode<Integer> root, int min, int max){
        if(root == null){
            return true;
        }
        boolean is_bst = true;
        if(root.left != null){
            is_bst &= root.left.val < root.val;
        }
        if(root.right != null){
            is_bst &= root.right.val > root.val;
        }
        return is_bst && isBST(root.left) && isBST(root.right);
    }


    
}

class TNode<T>{
    public T val;
    public TNode<T> left;
    public TNode<T> right;
}
