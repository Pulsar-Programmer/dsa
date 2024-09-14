//Double comments are for the developer and not intended to be read for documentation.
///Triple comments are supplemental comments written throughout code intended to be read.
/** Doc comments are attached to entities explicating their function. */

public class Driver {
    public static void main(String[] args) throws Exception {
        var scanner = new java.util.Scanner(System.in);
        var playing = true;
        while(playing){
            System.out.println("Enter an expression:");
            final var expr = scanner.nextLine();
            Fixer fixer = new Fixer(expr);
            System.out.println(fixer);
        }
        scanner.close();
    }
}
