import java.util.*;

public class SortingMenu {

    // variables that are used in multiple functions declared here to avoid redundancy, private to limit their scopes
    private static Scanner input = new Scanner(System.in);
    private static Integer[] numbers = new Integer[10000];

    private static Integer[] helper;

    private static int number;

    private static Integer[] values;

    public static void main(String[] args) {
        int iterationCounter = 0;
        long sortStart = 0;
        long sortEnd = 0;

   
        generateNumbers(numbers); // calls method which will process dataset (the static array)

        System.out.println("Dataset created.\n");
        System.out.println("Select a sorting method:\n\n(1) bubble sort\n(2) insertion sort\n(3) selection sort\n(4) merge sort\n(5) quick sort\n");
        int methodSelect = getInput();

        if (methodSelect <= 5) {
            switch(methodSelect) {
                case 1:
                    bubbleSort(numbers, iterationCounter, sortStart, sortEnd);
                    break;
                case 2:
                    insertionSort(numbers, iterationCounter, sortStart, sortEnd);
                    break;
                case 3:
                    selectionSort(numbers, iterationCounter, sortStart, sortEnd);
                    break;
                case 4:
                    sortStart = System.currentTimeMillis();
                    sort(numbers, values, helper, number);
                    sortEnd = System.currentTimeMillis();
                    showResults(iterationCounter, sortStart, sortEnd);
                    break;
                case 5:
                    quickSort(numbers, iterationCounter, sortStart, sortEnd);
                    break;
            }
        }
        else {
            System.out.println("Enter a method's number to select it:");
            getInput();
        }
        input.close();
    }

    public static int getInput() throws InputMismatchException { // method that returns the user choice, w/ a robust input-checking system
        while (true) {
            try {
                return input.nextInt();
            }
            catch(InputMismatchException e) {
                input.next();
                System.out.println("Enter a method's number to select it:");
            }
        }
    }

    public static void generateNumbers(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) { // simple for loop to populate the Integer array that's passed, in numerical order
            numbers[i] = i;
        }
        Collections.shuffle(Arrays.asList(numbers)); // randomizes the order of the numbers stored in the array
    }

    // bubble sort works by repeatedly swapping adjacent elements if they are in the wrong order
    public static void bubbleSort(Integer[] numbers, int iterationCounter, long sortStart, long sortEnd) { // uses incremental approach
        int numLength = numbers.length;
        int temp = 0; // temporary variable that will serve as a safe haven for the first value during a swap

        sortStart = System.currentTimeMillis();
        for (int i = 0; i < numLength; i++) {
            for (int j = 1; j < (numLength - 1); j++) {
                if (numbers[j-1] > numbers[j]) { // if number is smaller, swap numbers[j] and numbers[j-1]
                    temp = numbers[j-1];
                    numbers[j-1] = numbers[j];
                    numbers[j] = temp;
                    iterationCounter++;
                }
            }
        }
        sortEnd = System.currentTimeMillis();
       
        showResults(iterationCounter, sortStart, sortEnd);
    }

    // insertion sort removes an element from array, compares it w/ the largest value and then places it accordingly
    public static void insertionSort(Integer[] numbers, int iterationCounter, long sortStart, long sortEnd) { // uses incremental approach
        int numLength = numbers.length;
        int temp = 0;
       
        sortStart = System.currentTimeMillis();
        for (int i = 1; i < numLength - 1; i++) {
            temp = numbers[i]; // item to insert
            int j = i -1;

            // if value happens to be larger, insert item (temp)
            while (j >= 0 && numbers[j] > temp) {
                numbers[j + 1] = numbers[j];
                j--;
                iterationCounter++;
            }
            numbers[j + 1] = temp; // upon leaving loop, j + 1 is the index where item belongs
        }
        sortEnd = System.currentTimeMillis();
   
        showResults(iterationCounter, sortStart, sortEnd);
    }

    // selection sort repeatedly finds minimum element and places it at the beginning
    public static void selectionSort(Integer[] numbers, int iterationCounter, long sortStart, long sortEnd) { // uses incremental approach
        int min = 0;
        int minIndex = 0;
        int numLength = numbers.length;

        sortStart = System.currentTimeMillis();
        for (int i = 0; i < numLength; i++) {
            min = numbers[i];
            minIndex = i;
            for (int j = i + 1; j < numLength; j++) { // find minimum
                if (numbers[j] < min) {
                    min = numbers[j];
                    minIndex= j;
                    iterationCounter++;
                }
            }
            numbers[minIndex] = numbers[i]; // swap values
            numbers[i] = min;
        }
        sortEnd = System.currentTimeMillis();
   
        showResults(iterationCounter, sortStart, sortEnd);
    }







    public static void sort(Integer[] numbers, Integer[] values, Integer[] helper, int number) {
        numbers = values;
        number = values.length;
        helper = new Integer[number];
        mergeSort(0, number - 1);
    }
    // merge sort divides array into two halves & recursively calls itself to further subdivide, sort, then re-merge
    public static void mergeSort(int low, int high) { // uses divide-and-conquer approach
         // check if low is smaller than high, if not then the array is sorted
         if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergeSort(low, middle);
            // Sort the right side of the array
            mergeSort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }
    public static void merge(int low, int middle, int high) {
        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.
    }





    // quick sort picks an element as pivot and partitions the given array around the chosen pivot
    public static void quickSort(Integer[] numbers, int iterationCounter, long sortStart, long sortEnd) { // uses divide-and-conquer approach
        sortStart = System.currentTimeMillis();
       
        // TO-DO *****************************************************
       
        sortEnd = System.currentTimeMillis();
        showResults(iterationCounter, sortStart, sortEnd);
    }
   
    public static void showResults(int iterationCounter, long sortStart, long sortEnd) {
        System.out.print(Arrays.toString(numbers) + "\n\nArray sorted! This method took " + iterationCounter + " iterations. ");
        System.out.println("Time taken was " + (sortEnd - sortStart) + " ms.");
    }

}