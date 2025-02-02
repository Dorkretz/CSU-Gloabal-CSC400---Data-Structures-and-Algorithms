public class MissingNumber {
    public static int findMissingNumber(int[] arr, int n) {
        int expectedSum = (n + 1) * (n + 2) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 1, 4};
        int n = arr.length;
        System.out.println("Missing number: " + findMissingNumber(arr, n));
    }
}