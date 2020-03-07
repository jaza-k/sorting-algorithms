public class QuickSort {
    
    // quick sort picks an element as pivot and partitions the given array around the chosen pivot
    
    public static int partition(Integer numbers[], int low, int high) { 
        int pivot = numbers[high];  
        int i = (low - 1); // index of smaller element 
        
        for (int j = low; j < high; j++) { 
            if (numbers[j] < pivot) { // if the current element is smaller than the pivot 
                i++; 
                // swap numbers[i] and numbers[j] 
                int temp = numbers[i]; 
                numbers[i] = numbers[j]; 
                numbers[j] = temp; 
            } 
        } 
        // swap numbers[i+1] and pivot 
        int temp = numbers[i + 1]; 
        numbers[i + 1] = numbers[high]; 
        numbers[high] = temp; 
  
        return i + 1; 
    }

    public static int quickSort(Integer numbers[], int low, int high) { 
        int iterationCounter = 0;
        if (low < high) { 
            int partitionIndx = partition(numbers, low, high); // the partitioning index is now in the right place in numbers[]
  
            // uses recursive approach to sort elements before partition & after partition
            quickSort(numbers, low, partitionIndx-1); 
            quickSort(numbers, partitionIndx+1, high); 
            iterationCounter++;
        }
        return iterationCounter;
    }
}
