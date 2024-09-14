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
    private static String infix_to_postfix(String infix){
        ///We create an expression and stack.
        String expr = new String();
        Stack<Character> stack = new Stack<>();
        ///We loop through each symbol.
        for(var _i = 0; _i < infix.length(); _i++){
            var i = infix.charAt(_i);
            ///We check which symbol it is.
            if(i == '('){
                ///We simply push to the stack if it is a parentheses.
                stack.push(i);
            } else if(is_operand(i)){
                ///We simply add the literal to the expression.
                expr += i;
            } else if(i == ')'){
                ///We pop everything until we end the stack or encounter a beginning to the end and add to the expression.
                while(!stack.isEmpty()){
                    final var val = stack.pop();
                    if(val == '(') break;
                    expr += val;
                }
            } else if(is_operator(i)){
                ///The operator is only welcome to be checked and later placed in the expression if the stack has contents and that contents is an operator.
                final var is_welcome = !stack.isEmpty() && is_operator(stack.peek());
                ///The operator on the stack must have greater than or equal precedence to the one we are seeing now and qualify as welcome.
                if(is_welcome && precedence_of(stack.peek()) >= precedence_of(i)){
                    ///We then can pop the value on the stack and add it to the expression.
                    final var val = stack.pop();
                    expr += val;
                }
                ///We add the operator to the stack.
                stack.push(i);
            }
        }
        ///We add all the stack items onto the expression.
        for(var _i = 0; _i < stack.size(); _i++) expr += stack.pop();
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
        operator == '%' ||
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
            case '%':
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
