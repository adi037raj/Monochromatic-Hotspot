package monochromatic;

import java.util.Comparator;

class PatternComperator implements Comparator<Patterns> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Patterns a, Patterns b) {
        if (a.llr < b.llr) {
            return 1;
        }
        if(a.llr == b.llr) return 0;
        return -1;
    }
}
