import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }






    public static <T> void bubble_stack(Stack<T> stack){
        Stack<T> comber = new Stack<T>();
        var swapped = true;
        while(swapped) {
            swapped = false;

            for(var i = 0; i < stack.size(); i++){
                
            }

        }
    }

    public static <T> void selection_stack(Stack<T> stack){
        Stack<T> comber = new Stack<T>();
    }
    
    public static <T> void insertion_stack(Stack<T> stack){

    }


    public static Queue<Integer> selection_queue(Queue<Integer> q){
        var to_return = new LinkedList<Integer>();
        while(q.isEmpty()){
            var max = Integer.MIN_VALUE;
            var max_idx = 0;
            for(var i = 0; i < q.size(); i++){
                var val = q.remove();
                if(val > max){
                    max = val;
                    max_idx = i;
                }
            }
        }


        return to_return;
    }




}
