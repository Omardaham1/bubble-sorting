import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class BubbleSortAssignment {

    /**
     * Creates an array of random integers between 0 and 100.
     * 
     * @param arrayLength The length of the array to be created.
     * @return An array of random integers.
     */
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // 101 is used to include 100
        }
        return array;
    }

    /**
     * Writes an array to a file, with each line containing one integer.
     * 
     * @param array     The array to be written.
     * @param filename  The name of the file to write to.
     */
    public static void writeArrayToFile(int[] array, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (int num : array) {
                fileWriter.write(num + "\n");
            }
            System.out.println("Array written to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads an array from a file, where each line contains one integer.
     * 
     * @param filename  The name of the file to read from.
     * @return The array read from the file.
     */
    public static int[] readFileToArray(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }
            scanner.close();

            int[] array = new int[count];
            scanner = new Scanner(new File(filename));
            for (int i = 0; i < count; i++) {
                array[i] = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
            }
            System.out.println("Array read from file: " + filename);
            return array;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return null;
        }
    }

    /**
     * Sorts an array in-place using Bubble Sort.
     * 
     * @param array The array to be sorted.
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Handles user keyboard input.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter array length:");
        int arrayLength = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        int[] array = createRandomArray(arrayLength);
        System.out.println("Random array created.");

        System.out.println("Enter filename to write array:");
        String filename = scanner.nextLine();

        writeArrayToFile(array, filename);

        System.out.println("Sorting array using Bubble Sort...");
        bubbleSort(array);
        System.out.println("Array sorted.");

        System.out.println("Enter filename to read sorted array:");
        String readFilename = scanner.nextLine();

        writeArrayToFile(array, readFilename); // Write sorted array to file

        int[] readArray = readFileToArray(readFilename);
        if (readArray != null) {
            System.out.println("Sorted array read from file:");
            for (int num : readArray) {
                System.out.println(num);
            }
        }

        scanner.close();
    }
}