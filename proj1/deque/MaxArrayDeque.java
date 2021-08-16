package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> myComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        myComparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < size(); i += 1) {
            int comp = myComparator.compare(get(i), get(maxIndex));

            if (comp > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < size(); i += 1) {
            int comp = c.compare(get(i), get(maxIndex));

            if (comp > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
