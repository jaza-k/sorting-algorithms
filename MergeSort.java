public class MergeSort {
    
    // merge sort divides array into two halves & recursively calls itself to further subdivide, sort, then re-merge
    
    public static void merge(Integer[] numbers, int left, int middle, int right) {
        int size = middle - left + 1;
        int size2 = right - middle;
        
        Integer tempL[] = new Integer[size];
        Integer tempR[] = new Integer[size2];
        
        for (int i = 0; i < size; i++) {
            tempL[i] = numbers[1 + i];
        }
        for (int j = 0; j < size2; j++) {
            tempR[j] = numbers[middle + 1 + j];
        }
        
        int i = 0, j = 0, k = 1;
        
        while (i < size && j < size2) {
            if (tempL[i] <= tempR[j]) {
                numbers[k] = tempL[i];
                i++;
            }
            else {
                numbers[k] = tempR[j];
                j++;
            }
            k++;
        }
        
        while ( i < size) {
            numbers[k] = tempL[i];
            i++;
            k++;
        }
        
        while (j < size2) {
            numbers[k] = tempR[j];
            j++;
            k++;
        }
    }
    
    public static void mergeSort(Integer[] numbers, int left, int right) { // uses divide and conquer approach
        if (left < right) {
            int middle = (left + right)/2;
            
            mergeSort(numbers, left, middle);
            mergeSort(numbers, middle + 1, right);
            
            merge(numbers, left, middle, right);
        }
    }
}
