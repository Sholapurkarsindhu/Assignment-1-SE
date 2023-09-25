import java.util.Scanner;

/**
 * Title: Fermat's Last Theorem Near Misses Finder
 * File Name: FermatsLastTheorem.java
 * External Files: None
 * Programmers: Sindhu
 * Email Address: 
 * Course Number: Software Engineering
 * Date Finished: 9/24/2023
 *
 * Explanation:
 * This program searches for "near misses" of Fermat's Last Theorem of the form (x, y, z, n, k) in the formula xn + yn = zn,
 * where x, y, z, n, k are positive integers. It asks the user for values of n and k, then systematically searches for
 * x, y, and z combinations that are "almost right." It calculates the smallest relative miss and prints the results
 * to the screen.
 *
 * Resources:
 * 
 */

public class FermatsLastTheorem {
    public final static Scanner STDIN_SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // Program title
        System.out.println("Fermat's Last Theorem Near Misses Finder\n");

        int n = 0, k = 0;

        // Input n within the specified range
        while (n <= 2 || n >= 12) {
            System.out.print("Enter integer n such that 2 < n < 12: ");
            n = STDIN_SCANNER.nextInt();
        }

        // Input k greater than 10
        while (k <= 10) {
            System.out.print("Enter upper limit k for x and y (k > 10): ");
            k = STDIN_SCANNER.nextInt();
        }

        int value = Integer.MAX_VALUE;
        double relativeValue = Integer.MAX_VALUE;

        // Loop through x and y combinations
        for (int x = 10; x <= k; x++) {
            for (int y = 10; y <= k; y++) {
                // Calculate xn + yn
                long lhs = (int) (Math.pow(x, n) + Math.pow(y, n));

                // Check for integer overflow
                if (lhs < 0) {
                    System.out.print("ERROR!! Choose lower values of n and k");
                    return;
                }

                // Calculate z using integer part of nth root
                int z = (int) Math.pow(lhs, 1.0 / n);
                int diff = (int) (lhs - Math.pow(z, n));

                // Check if (z+1)^n is closer
                if (Math.pow(z + 1, n) - lhs < diff) {
                    diff = (int) (Math.pow(z + 1, n) - lhs);
                    z++;
                }

                // Calculate relative difference
                double relDiff = 100.0 * diff / lhs;

                // Update if a smaller relative miss is found
                if (relDiff < relativeValue) {
                    value = diff;
                    relativeValue = relDiff;
                    System.out.printf("x = %d, y = %d, z = %d, value = %d, relative value = %.6f%%\n", x, y, z, diff, relDiff);
                }
            }
        }
    }
}
