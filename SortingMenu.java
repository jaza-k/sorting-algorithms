import java.util.*;

public class SortingMenu {

    // variables that are used in multiple functions declared here to avoid redundancy, private to limit their scopes
    private static Scanner input = new Scanner(System.in);
    private static Integer[] numbers = new Integer[10000];

    public static void main(String[] args) {
        int iterationCounter = 0;
        long sortStart = 0;
        long sortEnd = 0;

        int left  = 0;
        int right = 0;
        int low = 0;
        int high = 0;
   
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
                    mergeSort(numbers);
                    sortEnd = System.currentTimeMillis();
                    showResults(iterationCounter, sortStart, sortEnd);
                    break;
                case 5:
                    sortStart = System.currentTimeMillis();
                    quickSort(numbers, left, right);
                    sortEnd = System.currentTimeMillis();
                    showResults(iterationCounter, sortStart, sortEnd);
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

    // insertion sort uses two portions in which the elements from the unsorted portion are correctly inserted into the sorted portion
    public static void insertionSort(Integer[] numbers, int iterationCounter, long sortStart, long sortEnd) { // uses incremental approach
        int numLength = numbers.length;
        int temp = 0;
       
        sortStart = System.currentTimeMillis();
        for (int i = 1; i < numLength - 1; i++) { // iterate thru the array starting from the second and not first element
            temp = numbers[i]; // the element that is currently being inserted
            int j = i - 1;

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

    // merge sort divides array into two halves & recursively calls itself to further subdivide, sort, then re-merge
    public static Integer[] mergeSort(Integer[] numbers){
		Integer[] sorted = Arrays.copyOf(numbers, numbers.length);
	    if (sorted.length == 1){
	        return sorted;
	    }
	    int length = sorted.length/2;
	    Integer[] left = new Integer[length];
	    Integer[] right = new Integer[sorted.length - length];
	
	    for (int i = 0; i < length; i++)
	        left[i] = sorted[i];
	    for (int i = length; i < sorted.length; i++)
	        right[i-length] = sorted[i];
	
	    return merge(mergeSort(left),mergeSort(right));
	}
    public static Integer[] merge(Integer[] left, Integer[] right){
	    Integer[] sorted = new Integer[left.length+right.length];
	    int left_length = left.length;
	    int right_length = right.length;
	    
	    while (left_length > 0 && right_length > 0){
	        if (left[left.length - left_length] < right[right.length - right_length]){
	        	sorted[sorted.length -left_length-right_length] = left[left.length - left_length];
	            left_length--;
	        }
	        else{
	        	sorted[sorted.length - left_length-right_length] = right[right.length - right_length];
	        	right_length--;
	        }	       
	    }
	    while (left_length > 0){
	    	sorted[sorted.length - left_length] = left[left.length-left_length];
	        left_length--;
	    } 
	    while (right_length > 0){
	    	sorted[sorted.length - right_length] = right[right.length-right_length];
	        right_length--;
	    }
	    return sorted;
	}

    // quick sort picks an element as pivot and partitions the given array around the chosen pivot
    public static int partition(Integer[] numbers, int left, int right){
		int tempArr;
		int pivot = numbers[(left + right) / 2];
		
		while(left <= right){
			while(numbers[left] < pivot){
				left++;
			}
			while(numbers[right] > pivot){
				right--;
			}
			if(left <= right){
				tempArr = numbers[left];
				numbers[left] = numbers[right];
				numbers[right] = tempArr;
				left++;
				right--;
			}
		}
		return left;
	}
    
    public static void quickSort(Integer[] numbers, int left, int right) { // uses divide-and-conquer approach
        int indx = partition(numbers, left, right);
		
		if(left < indx -1){
			quickSort(numbers, left, indx - 1);
		}
		if(indx < right){
			quickSort(numbers, indx, right);
		}
    }
   
    public static void showResults(int iterationCounter, long sortStart, long sortEnd) {
        System.out.print(Arrays.toString(numbers) + "\n\nArray sorted! This method took " + iterationCounter + " iterations. ");
        System.out.println("Time taken was " + (sortEnd - sortStart) + " ms.");
    }

}



