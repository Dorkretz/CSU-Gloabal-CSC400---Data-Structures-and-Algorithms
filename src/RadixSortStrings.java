import java.util.*;

public class RadixSortStrings {
    /**
     * Gets the maximum length of the strings in the array
     */
    private static int getMaxStringLength(String[] arr) {
        return Arrays.stream(arr).mapToInt(String::length).max().orElse(0);
    }

    /**
     * Performs radix sort on array of strings
     */
    public static void radixSort(String[] arr) {
        int maxLength = getMaxStringLength(arr);
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            countingSort(arr, pos);
        }
    }

    /**
     * Performs counting sort for selected position in each string
     */
    private static void countingSort(String[] arr, int pos) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[27]; // 26 letters + 1 for shorter words

        for (String str : arr) {
            int index = (pos < str.length()) ? (str.charAt(pos) - 'a' + 1) : 0;
            count[index]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int index = (pos < arr[i].length()) ? (arr[i].charAt(pos) - 'a' + 1) : 0;
            output[--count[index]] = arr[i];
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Method to test radix sorting
     */
    public static void main(String[] args) {
        String[] words = {"joke", "book", "back", "dig", "desk", "word", "fish", "ward", "dish", "wit", "deed", "fast", "dog", "bend"};
        System.out.println("Original Array: " + Arrays.toString(words));
        radixSort(words);
        System.out.println("Sorted Array: " + Arrays.toString(words));
    }
}

