package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> A = new AListNoResizing<Integer>();
        BuggyAList<Integer> B = new BuggyAList<Integer>();
        //adding 4, 5, 6 and to both A and B
        A.addLast(4);
        B.addLast(4);
        A.addLast(5);
        B.addLast(5);
        A.addLast(6);
        B.addLast(6);

        assertEquals(A.size(), B.size());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> J = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                J.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int L_size = L.size();
                int J_size = J.size();
                assertEquals(L_size, J_size);
            } else if (operationNumber == 2) {
                if (L.size() > 0 && J.size() > 0) {
                    int L_last = L.getLast();
                    int J_last = J.getLast();
                    assertEquals(L_last, J_last);
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0 && J.size() > 0) {
                    assertEquals(L.removeLast(), J.removeLast());
                }
            }
        }
    }
}
