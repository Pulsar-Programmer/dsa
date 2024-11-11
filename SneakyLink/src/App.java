import java.io.File;
import java.util.Scanner;

//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are also intended to be read. */

public class App {
    public static void main(String[] args) throws Exception {
        final File file = new File("src/countries_data.csv");
        
        ///We create our Countries HashMap.
        Countries countries = Countries.with_capacity(250);

        ///We create a scanner from the file.
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); ///Once to remove name.
        scanner.nextLine(); ///Twice to remove headers.

        ///We scan through first each line.
        while (scanner.hasNext()) {
            ///Next we create another subscanner through that line, splitting on commas.
            Scanner second = new Scanner(scanner.nextLine());
            second.useDelimiter(",");

            ///Now, we deposit all of the entries into our map.
            Key key = new Key(second.next());
            Value value = new Value(second.next(), second.next(), second.nextInt(), second.nextInt(), second.next(), second.next(), second.next(), second.next(), second.next(), second.next(), second.next(), second.nextInt(), second.next(), second.nextInt(), second.nextFloat(), second.nextFloat(), second.next(), second.next());
            Entry entry = new Entry(key, value);

            countries.put(entry);

            second.close();
        }
        // System.out.println((int)'c');
        scanner.close();
    }
}
