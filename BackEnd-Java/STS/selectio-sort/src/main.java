
public class main {

	public static void main(String[] args) {
		/**
		 *  In selection sort algorithm, we search for the lowest element and arrange it to the proper location. 
		 *  We swap the current element with the next lowest number.  complexity O(1)
		 */

		int[] arr1 = {9,14,3,2,43,11,58,22};  
        System.out.println("Before Selection Sort");
        
        for(int i:arr1){  
            System.out.print(i+" ");  
        } 
        
        System.out.println();  
        
        selectionSort(arr1);//sorting array using selection sort  
         
        System.out.println("After Selection Sort");  
        for(int i:arr1){  
            System.out.print(i+" ");  
        } 
		
	}
	
	
	
	/**
	 * The selection sort algorithm works in a very simple way. It maintains two subarray for the given array.

		 *The subarray is already sorted.
		 *And the second subarray is unsorted.

		With every iteration of selection sort, an element is picked from the unsorted subarray and moved to the sorted subarray.
	 */
	
	
	public static void selectionSort(int[] arr) { 
		
		for(int i=0;i <arr.length; i++) { 
			int index = i;
			
			for(int j=i;j < arr.length; j++) { 
				if(arr[j] < arr[index]) { 
					//searching for lowest index  
					index = j;
				}
			}
			
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
	}
	

}
