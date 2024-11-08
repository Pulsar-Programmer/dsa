import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final File file = new File("data.csv");
        
        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        Scanner second = new Scanner(scanner.nextLine());
        second.useDelimiter(",");

        System.out.println(second.next());

        second.close();
        scanner.close();
    }
}
