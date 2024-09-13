import java.util.Stack;

public class Fixer {
    private String pre;
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "Fixer [post=" + post + ", pre=" + pre + "]";
    }

    /** Creates an instance of the Fixer class by converting the infix expression to both prefix and postfix. */
    public Fixer(String expr){
        this.pre = infix_to_prefix(expr);
        this.post = infix_to_postfix(expr);
    }

    /** Converts an infix expression to postfix.*/
    public static String infix_to_postfix(String infix){ // Note: change to private
        String expr = new String();
        Stack<String> stack = new Stack<>();
        for(var _i = 0; _i < infix.length(); _i++){
            var i = infix.charAt(_i);
            if(i == ' ') continue;
            if(i == '('){
                stack.push(Character.toString(i));
            } else if(is_operand(i)){
                expr += i;
            } else if(i == ')'){
                while(true){
                    if(!stack.isEmpty()) break;
                    final var val = stack.pop();
                    if(val == "(") break;
                    expr += val;
                }
            } else if(is_operator(i)){
                while(true){
                    if(!stack.isEmpty()) break;
                    final var val = stack.pop();

                    if(!is_operator(val.charAt(0)) || precedence_of(i) >= precedence_of(val.charAt(0))){
                        stack.push(Character.toString(i));
                        break;
                    }
                    expr += val;
                }
            }
        }
        for(var elem : stack) expr += elem;
        return expr;
    }

    private static String infix_to_prefix(String infix){
        //TODO
        return "";
    }

    int evaluate_post(boolean show_demo){
        return 0;
    }

    int evaluate_pre(boolean show_demo){
        return 0;
    }
    

    



    
    /** Returns whether the character is an operator or not. */
    private static boolean is_operator(char operator){
        return operator == '+' ||
        operator == '-' ||
        operator == '*' ||
        operator == '/' ||
        operator == '^';
    }

    /** Returns whether the character is an operator or not. */
    private static boolean is_operand(char operand){
        return Character.isDigit(operand);
    }

    /** Returns the precedence of an Operator. The precedence is zero for addition and subtraction, one for multiplication and division, two for exponentation and logarithm, etc. . */
    private static int precedence_of(char operator){
        switch(operator){
            default:
            case '+':
            case '-':
            {
                return 0;
            }

            case '*':
            case '/':
            {
                return 1;
            }

            case '^':
            {
                return 2;
            }

        }
    }
}
