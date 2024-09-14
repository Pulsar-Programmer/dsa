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
        return "Fixer {Postfix Expression: " + post + ", Prefix Expression: " + pre + "}";
    }

    /** Creates an instance of the Fixer class by converting the infix expression to both prefix and postfix. */
    public Fixer(String expr){
        this.post = infix_to_postfix(expr);
        this.pre = postfix_to_prefix(this.post);
    }

    /** Converts an infix expression to postfix. */
    private static String infix_to_postfix(String infix){
        ///We create an expression and stack.
        String expr = new String();
        Stack<Character> stack = new Stack<>();
        ///We loop through each symbol.
        for(var symbol : infix.toCharArray()){
            ///We check which symbol it is.
            if(symbol == '('){
                ///We simply push to the stack if it is a parentheses.
                stack.push(symbol);
            } else if(is_operand(symbol)){
                ///We simply add the literal to the expression.
                expr += symbol;
            } else if(symbol == ')'){
                ///We pop everything until we end the stack or encounter a beginning to the end and add to the expression.
                while(!stack.isEmpty()){
                    final var val = stack.pop();
                    if(val == '(') break;
                    expr += val;
                }
            } else if(is_operator(symbol)){
                ///The operator is only welcome to be checked and later placed in the expression if the stack has contents and that contents is an operator.
                final var is_welcome = !stack.isEmpty() && is_operator(stack.peek());
                ///The operator on the stack must have greater than or equal precedence to the one we are seeing now and qualify as welcome.
                if(is_welcome && precedence_of(stack.peek()) >= precedence_of(symbol)){
                    ///We then can pop the value on the stack and add it to the expression.
                    final var val = stack.pop();
                    expr += val;
                }
                ///We add the operator to the stack.
                stack.push(symbol);
            }
        }
        ///We add all the stack items onto the expression.
        while(!stack.isEmpty()) expr += stack.pop();
        return expr;
    }

    /** Converts Postfix to Prefix. */
    private static String postfix_to_prefix(String postfix){
        ///We ready our expression and stack.
        var expr = new String();
        Stack<String> stack = new Stack<>();

        ///We loop through each symbol.
        for(var symbol : postfix.toCharArray()){
            if(is_operand(symbol)){
                ///We push operands.
                stack.push(Character.toString(symbol));
            } else if(is_operator(symbol)){
                ///We use the operator to create a union of a pocket of prefix to be used in later compositions.
                var b = stack.pop();
                var a = stack.pop();
                String pre = symbol + a + b;
                stack.push(pre);
            }
        }

        ///We add all the stack items onto the expression.
        while(!stack.isEmpty()) expr += stack.pop();
        return expr;
    }
    
    /** Evaluates an expression in postfix. */
    int evaluate_post(boolean show_demo){
        var stack = new Stack<Double>();
        ///We loop through each symbol.
        for(var symbol : this.post.toCharArray()){
            if(is_operator(symbol)){
                final var b = stack.pop();
                final var a = stack.pop();
                //yessss!!! finally!! match expressions from Rust!!!!
                ///We match on each operator and give the result.
                final var result = switch(symbol){
                    case '+' -> { yield a + b; }
                    case '-' -> { yield a - b; }
                    case '*' -> { yield a * b; }
                    case '/' -> { yield (double)(a.intValue() / b.intValue()); }
                    case '%' -> { yield a % b; }
                    case '^' -> { yield Math.pow(a, b); }
                    default -> { yield 0; }
                };
                stack.push(result);
            } else if(is_operand(symbol)){
                ///We place the operand or literal on the stack for later processing.
                stack.push(Double.parseDouble(Character.toString(symbol)));
            }
            if(show_demo) System.out.println("Scanned Symbol: " + symbol + ", Stack: " + stack);
        }
        return stack.pop().intValue();
    }

    /** Evaluates an expression in prefix. */
    int evaluate_pre(boolean show_demo){
        var stack = new Stack<Double>();
        ///We loop through each symbol in reverse.
        for(var i = this.pre.length() - 1; i >= 0; i--){
            var symbol = this.pre.charAt(i);
            if(is_operator(symbol)){
                final var a = stack.pop();
                final var b = stack.pop();
                //yessss!!! finally!! match expressions from Rust!!!!
                ///We match on each operator and give the result.
                final var result = switch(symbol){
                    case '+' -> { yield a + b; }
                    case '-' -> { yield a - b; }
                    case '*' -> { yield a * b; }
                    case '/' -> { yield (double)(a.intValue() / b.intValue()); }
                    case '%' -> { yield a % b; }
                    case '^' -> { yield Math.pow(a, b); }
                    default -> { yield 0; }
                };
                stack.push(result);
            } else if(is_operand(symbol)){
                ///We place the operand or literal on the stack for later processing.
                stack.push(Double.parseDouble(Character.toString(symbol)));
            }
            if(show_demo) System.out.println("Scanned Symbol: " + symbol + ", Stack: " + stack);
        }
        return stack.pop().intValue();
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
