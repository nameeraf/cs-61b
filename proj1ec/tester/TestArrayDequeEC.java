package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {

    @Test
    public void randomTest() {

        StudentArrayDeque<Integer> A = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> B = new ArrayDequeSolution<>();


        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                A.addFirst(randVal);
                B.addFirst(randVal);
                System.out.print("addFirst(" + randVal + ")");
                System.out.println();
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
                System.out.print("addLast(" + randVal + ")");
                System.out.println();
            } else if (operationNumber == 2) {
                // removeLast
                if (!A.isEmpty() && !B.isEmpty()) {
                    System.out.print("removeLast()");
                    System.out.println();
                    assertEquals(A.removeLast(), B.removeLast());
                }
            } else if (operationNumber == 3) {
                // removeFirst
                if (!A.isEmpty() && !B.isEmpty()) {
                    System.out.print("removeFirst()");
                    System.out.println();
                    Integer A_first = A.removeFirst();
                    Integer B_first = B.removeFirst();
                    assertEquals(A_first, B_first);
                }
            }
        }
    }
}