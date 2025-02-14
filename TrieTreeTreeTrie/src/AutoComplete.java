import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutoComplete{
    
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter the path of the file: ");
        String path = stdin.nextLine();
        File file = new File(path);
        Dictionary dictionary = 
        file.toString().endsWith(".txt") ? load_txt(file) : load_csv(file);
        skibidi: while (true) { 
            System.out.println(
                "What would you like to do?\n1. Add a Key\n2. Delete a Key\n3. Autocomplete a Key\n4. Compress\n5. Print In Order\n6. Exit"
            );
            int choice = stdin.nextInt(); stdin.nextLine();
            switch(choice){
                case 1 -> {
                    System.out.println("Enter the Key to Add: ");
                    dictionary.root.addKey(stdin.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter the Key to Delete: ");
                    dictionary.root.deleteKey(stdin.nextLine());
                }
                case 3 -> {
                    System.out.println("Enter the Key to Autocomplete: ");
                    var auto = dictionary.root.auto(stdin.nextLine());
                    if(auto.isEmpty()){
                        System.out.println("No such word!");
                    }
                    auto.forEach(System.out::println);
                }
                case 4 -> {
                    dictionary.root.compress();
                    System.out.println("Compressed successfully!");
                }
                case 5 -> System.out.println(dictionary.root);
                case 6 -> {
                    System.out.println("Exiting...");
                    break skibidi;
                }
                default -> System.out.println("Invalid Choice");
            }
        }
        stdin.close();
    }

    public static Dictionary load_txt(File file) throws FileNotFoundException{
        TNode self = new TNode();
        Scanner scanner = new Scanner(file);
        scanner.tokens().forEach((t)->{
            self.addKey(t);
        });
        scanner.close();
        return new Dictionary(self);
    }

    public static Dictionary load_csv(File file) throws FileNotFoundException{
        TNode self = new TNode();
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        scanner.tokens().forEach((t)->{
            self.addKey(t);
        });
        scanner.close();
        return new Dictionary(self);
    }
    
}
