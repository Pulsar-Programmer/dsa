//Double comments are for the developer and not intended to be read for documentation.
///Triple comments are supplemental comments written throughout code intended to be read.
/** Doc comments are attached to entities explicating their function and are intended to be read, also. */

public class Driver {
    public static void main(String[] args) throws Exception {
        var scanner = new java.util.Scanner(System.in);
        var playing = true;
        while(playing){
            ///We request the user to enter an expression and then show the converted expression in both prefix and postfix.
            System.out.println("Enter an expression:");
            final var expr = scanner.nextLine();
            Fixer fixer = new Fixer(expr);
            System.out.println(fixer);

            ///We request the user ask if they want to see the demos of the evaluation.
            System.out.println("Would you like to show demos of the evaluation? State `true` or `false`.");
            final var show_demo = scanner.nextBoolean(); scanner.nextLine();
            System.out.println("------------------Prefix Evaluation------------------");
            System.out.println("Final: " + fixer.evaluate_pre(show_demo));
            System.out.println("------------------Postfix Evaluation------------------");
            System.out.println("Final: " + fixer.evaluate_post(show_demo));
            
            System.out.println("> Verify all is correct, and then press enter to test again. Alternatively, write `stop` to stop.");
            if(scanner.nextLine().equals("stop")) break;
        }
        scanner.close();
    }
}
