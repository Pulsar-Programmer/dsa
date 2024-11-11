import java.io.File;
import java.util.Scanner;

//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are also intended to be read. */

public class Driver {
    public static void main(String[] args) throws Exception {
        final File file = new File("src/countries_data.csv");
        Countries countries = Countries.loadFromFile(file);

        
    }
}
