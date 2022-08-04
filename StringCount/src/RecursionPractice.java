import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class RecursionPractice {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RecursionPractice() {
    }

    /**
     * Counts the number of a substrings in a given string.
     *
     * @param str
     *            {@code String} to be evaluated
     * @param sub
     *            {@code String} substring to be checked
     * @return Count of sub in str
     * @ensures sub = [total counts of sub in #str]
     */
    private static int strCount(String str, String sub) {
        int cnt = 0;
        if (str.indexOf(sub) == -1) {
            cnt = 0;
        } else {
            cnt = strCount(str.substring(str.indexOf(sub) + sub.length()), sub);
            cnt++;
        }
        return cnt;
    }

    /**
     * Groups integers in an array and checks whether some or all of them sum to
     * the target number.
     *
     * @param start
     *            start index for the array
     * @param nums
     *            number array
     * @param target
     *            number we are trying to sum to
     * @requires start >= 0
     */
    private static boolean groupSum(int start, int[] nums, int target) {
        boolean indeed = false;
        if (start >= nums.length) {
            indeed = target == 0 ? true : false;
        } else {
            indeed = groupSum(start + 1, nums, target - nums[start])
                    || groupSum(start + 1, nums, target) ? true : false;
        }
        return indeed;
    }

    private static String changeXY(String str) {
        final int start = 0;
        if (str.length() > 1) {
            String sub = str.substring(start + 1, start + 2);
            str = changeXY(sub);
        }
        if (str.charAt(start) == 'x') {
            str = str.replaceFirst("x", "y");
        }
        return str;
    }

    private static int countPairs(String str) {
        int count = 0;
        if (str.length() < 3) {
            count = 0;
        } else {
            if (str.charAt(0) == str.charAt(2)) {
                count++;
            }
            count += countPairs(str.substring(1, str.length()));
        }
        return count;
    }

    private static String stringClean(String str) {
        String sub = str;
        if (str.length() >= 2) {
            if (str.charAt(0) == str.charAt(1)) {
                sub = stringClean(str.substring(1, str.length()));
                sub += str.substring(0, 1);
            } else {
                sub = stringClean(str.substring(1, str.length()));
            }
        }
        return sub;
    }

    private static int countDigit(NaturalNumber n, int digit) {
        int count = 0;
        int ones = n.divideBy10();
        if (!n.isZero()) {
            count += countDigit(n, digit);
        }
        if (ones == digit) {
            count++;
        }
        n.multiplyBy10(ones);
        return count;
    }

    private static int countLabel(XMLTree t, String s) {
        final int left = 0;
        final int right = 1;
        int leftCnt = 0;
        int rightCnt = 0;
        int count = 0;

        if (t.numberOfChildren() > 0) {
            leftCnt = countLabel(t.child(left), s);
            rightCnt = countLabel(t.child(right), s);
        }
        if (t.label().equals(s)) {
            count = 1;
        }
        count += leftCnt + rightCnt;
        return count;
    }

    private static int sumOddIdx(NaturalNumber n) {
        int sum = 0;
        int oddIdx = n.divideBy10();
        int evenIdx = n.divideBy10();
        if (!n.isZero()) {
            sum = sumOddIdx(n);
        }
        sum += oddIdx;
        n.multiplyBy10(evenIdx);
        n.multiplyBy10(oddIdx);
        return sum;

    }

    private static int sumUp(NaturalNumber n) {
        int sum = 0;
        int last = n.divideBy10();
        int next = n.divideBy10();
        if (!n.isZero()) {
            sum = sumUp(n);
        }
        sum += last;
        n.multiplyBy10(next);
        n.multiplyBy10(last);
        return sum;
    }

    private static int countNodes(XMLTree t) {
        int cnt = 0;
        for (int i = 0; i < t.numberOfChildren(); i++) {
            cnt += countNodes(t.child(i));
        }
        cnt++;
        return cnt;
    }

    private static String stringCleaner(String str) {
        String clean = "";
        if (str.length() <= 1) {
            clean = str;
        } else {
            if (str.charAt(0) == str.charAt(1)) {
                clean += stringCleaner(str.substring(1, str.length()));
            } else {
                clean = str.substring(0, 1);
                clean += stringCleaner(str.substring(1, str.length()));
            }
        }
        return clean;
    }

    public static void mysteryFunction(NaturalNumber[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            NaturalNumber temp = new NaturalNumber2(arr[i]);
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    /*
     * If hi starts at zero contains will return that it is at zero and we can
     * continue to the next case If it is not at zero then we must check the
     * character immediately before it
     */
    private static int countHi(String str) {
        int cnt = 0;
        int idx = str.indexOf("hi");
        if (str.length() > 1) {
            if (idx == 0) {
                cnt = 1;
                cnt += countHi(str.substring(idx + 2, str.length()));
            } else if (idx > 0) {
                if (str.charAt(idx - 1) == 'x') {
                    cnt = 0;
                    cnt += countHi(str.substring(idx + 2, str.length()));
                } else {
                    cnt = 1;
                    cnt += countHi(str.substring(idx + 2, str.length()));
                }
            }
        }
        return cnt;
    }

    private static boolean stringEquals(String s1, String s2) {
        boolean equals = false;

        if (s1.length() == s2.length()) {
            if (s1.length() > 1) {
                if (s1.charAt(0) == s2.charAt(0)) {
                    equals = stringEquals(s1.substring(1, s1.length()),
                            s2.substring(1, s2.length()));
                }
            } else {
                if (s1.charAt(0) == s2.charAt(0) || s1.equals(s2)) {
                    equals = true;
                }
            }
        } else {
            equals = false;
        }
        return equals;
    }

    private static int countMax(XMLTree t) {
        int max = 0;

        if (t.isTag() && t.numberOfChildren() > 0) {
            for (int i = 0; i < t.numberOfChildren(); i++) {
                int thisFactor = countMax(t.child(i));
                if (t.numberOfChildren() > thisFactor) {
                    max = t.numberOfChildren();
                } else {
                    max = thisFactor;
                }
            }
        }
        return max;
    }

    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */

        if (p.isZero()) {
            n.setFromInt(1);
        } else {
            NaturalNumber remainder = p.divide(new NaturalNumber2(2));
            NaturalNumber sqr = new NaturalNumber2(n);
            if (remainder.compareTo(new NaturalNumber2(0)) == 0) {
                powerMod(n, p, m);
                n.multiply(new NaturalNumber2(n));
                n.divide(m);
            } else {
                n.multiply(sqr);
                n.divide(m);
                powerMod(n, p, m);
                n.multiply(sqr);
                n.divide(m);
            }
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a NN: ");
        NaturalNumber input = new NaturalNumber2(in.nextLine());
        out.print("Enter a power: ");
        NaturalNumber p = new NaturalNumber2(in.nextLine());
        NaturalNumber m = new NaturalNumber2(2);
        powerMod(input, p, m);
        out.println(input);

        in.close();
        out.close();
    }

}
