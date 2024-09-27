public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

class Node<T>{
    T data;
    Node next;

    // public Node<T>{
    //     data = null
    // }

    public Node<T> clone(){
        return new Node(data, next);
    }

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    public static Node interlace(Node n1, Node n2){
        
        Node construction = n1.clone();

        Node head = construction;

        Node runner_1 = n1;
        Node runner_2 = n2;
        
        var turn = false;

        while (true)
        {
            if(turn && runner_1.next != null){

                construction.next = runner_1.clone();
                runner_1 = runner_1.next;

            } else if (!turn && runner_2.next != null) {

                construction.next = runner_2.clone();
                runner_2 = runner_2.next;

            } else {
                break;
            }
            turn = !turn;
        }

        return head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }




    // public static Node reverse(Node original){
    //     Node construction = new Node(null, null);

        


    // }
}
