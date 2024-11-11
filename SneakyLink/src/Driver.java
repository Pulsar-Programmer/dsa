import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are also intended to be read. */

public class Driver {
    public static void main(String[] args) throws Exception {
        final File file = new File("src/countries_data.csv");
        Countries countries = Countries.loadFromFile(file);

        System.out.println(countries);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Database of Countries!");
        System.out.println("Select an Option then Enter Parameters:");
        while (true) {
            System.out.println("1) Get : Requires a Country Name");
            System.out.println("2) Remove : Requires a Country Key");
            System.out.println("3) Put : Requires a Country Name and Optional Parameters: [iso3, iso2, numeric_code, phone_code, capital, currency, currency_name, currency_symbol, tld, lang, region, region_id, subregion, subregion_id, latitude, longitude, emoji, emojiUnicode].");
            System.out.println("4) Quit");
            System.out.println("For the sake of speed, input only the fields you want by writing the paramameter and then what it equals.");
            System.out.println("Examples: 1 United States; or 3 American Samoa iso2=as currency_symbol=$");
            var reply = scanner.nextLine();
            var space_scanner = new Scanner(reply);
            space_scanner.useDelimiter(" ");
            var instruction = space_scanner.nextInt();
            if(instruction == 4){
                break;
            }
            String name = "";
            String next = space_scanner.next();
            while (!next.contains("=") && space_scanner.hasNext()) {
                name += next;
                next = space_scanner.next();
            }
            var key = new Key(name);
            if(instruction == 1){
                
                System.out.println("Data Retreived:");
                System.out.println(countries.get(key));
            }
            if(instruction == 2){
                System.out.println("Instruction Successful:");
                System.out.println(countries.remove(key));
            }
            var map = new HashMap<String, String>();
            while(space_scanner.hasNext()){
                var split = next.split("=");
                map.put(split[0], split[1]);
                next = space_scanner.next();
            }
            var value = 
            new Value(
                map.getOrDefault("iso3", "None"),
                map.getOrDefault("iso2", "None"),
                Integer.parseInt(map.getOrDefault("numeric_code", "0")),
                Integer.parseInt(map.getOrDefault("phone_code", "0")),
                map.getOrDefault("capital", "None"),
                map.getOrDefault("currency", "None"),
                map.getOrDefault("currency_name", "None"),
                map.getOrDefault("currency_symbol", "None"),
                map.getOrDefault("tld", "None"),
                map.getOrDefault("lang", "None"),
                map.getOrDefault("region", "None"),
                Integer.parseInt(map.getOrDefault("region_id", "0")),
                map.getOrDefault("subregion", "None"),
                Integer.parseInt(map.getOrDefault("subregion_id", "0")),
                map.getOrDefault("nationality", "None"),
                Float.parseFloat(map.getOrDefault("latitude", "0")),
                Float.parseFloat(map.getOrDefault("longitude", "0")),
                map.getOrDefault("emoji", "None"),
                map.getOrDefault("emojiUnicode", "None")
            );

            if(countries.put(new Entry(key, value))){
                System.out.println("Collision occurred!!!");
            }

            System.out.println("Current Configuration: " + countries);

            space_scanner.close();
        }
        scanner.close();
    }
}
