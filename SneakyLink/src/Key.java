import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Key {
    public String name;

    public Key(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        ///We create a list of primes and get the lowercase of the name.
        final List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11);
        final String lowercase = name.toLowerCase();

        ///Next, we loop through our primes and multiply our code by 2^[char at 1/5 through the word], 3^[char at 2/5 through the word], etc. such that it is unique.
        ///We also must mod to convert from BigInteger to integer.
        long code = 1;
        for(var i = 0; i < primes.size(); i++){
            final var elem = primes.get(i);
            final int index = (int)((double)i / (primes.size()-1) * (lowercase.length() - 1));
            final char character = lowercase.charAt(index);
            final int value = (int)character + 1 - 'a';
            code *= BigInteger.valueOf(elem).pow(Math.max(0, value)).mod(BigInteger.valueOf(2_147_483_647)).intValue();
        }

        return (int)code;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Key)obj).name);
    }
}
