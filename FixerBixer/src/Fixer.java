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

    private static String infix_to_postfix(String infix){
        //TODO
        return "";
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
