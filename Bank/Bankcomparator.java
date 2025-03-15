package Bank;

import java.util.Comparator;

public class Bankcomparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.name.compareTo(b2.name);
    }
}
