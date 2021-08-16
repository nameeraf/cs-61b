package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeTest {

    @Test
    public void testThreeAddThreeRemove() {

        LinkedListDeque<Integer> A = new LinkedListDeque<>();
        ArrayDeque<Integer> B = new ArrayDeque<>();

        A.addLast(4);
        A.addLast(5);
        A.addLast(6);

        B.addLast(4);
        B.addLast(5);
        B.addLast(6);

        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
    }

    @Test
    public void multipleParamTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<String>();
        ArrayDeque<Double> ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    public void testGet() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        ad1.addLast(0); // 0
        ad1.addLast(1); // 0 1
        ad1.addFirst(2); // 2 0 1
        ad1.addFirst(4); // 4 2 0 1

        int getZero = ad1.get(0);
        int getOne = ad1.get(1);
        int getTwo = ad1.get(2);
        int getThree = ad1.get(3);

        assertEquals(4, getZero);
        assertEquals(2, getOne);
        assertEquals(0, getTwo);
        assertEquals(1, getThree);
    }

    @Test
    public void testEqualsArrayArray() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addFirst(2);
        ad1.addFirst(4);

        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        ad2.addLast(0);
        ad2.addLast(1);
        ad2.addFirst(2);
        ad2.addFirst(4);

        assertTrue(ad1.equals(ad2));
    }

    @Test
    public void testEqualsArrayLinked() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addFirst(2);
        ad1.addFirst(4);

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(2);
        lld1.addFirst(4);

        assertTrue(ad1.equals(lld1));
    }

    @Test
    public void randomizedTest() {

        ArrayDeque<Integer> A = new ArrayDeque<>();
        LinkedListDeque<Integer> L = new LinkedListDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                A.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeA = A.size();
                assertEquals(sizeL, sizeA);
            } else if (L.size() == 0 || A.size() == 0) {
                // size <= 0
                continue;
            }
        }
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> A = new ArrayDeque<>();

        A.addLast(14); // 14
        A.addFirst(15); // 15 14
        A.addLast(16); // 15 14 16
        A.addFirst(17); // 17 15 14 16
        A.addFirst(18); // 18 17 15 14 16
        A.addLast(19); // 18 17 15 14 16 19
        A.addLast(20); // 18 17 15 14 16 19 20
        A.addFirst(21); // 21 18 17 15 14 16 19 20
        A.addLast(22); // 21 18 17 15 14 16 19 20 22

        int real = A.get(4);

        assertEquals(14, real);
    }
}
