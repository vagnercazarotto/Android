
public class main {

	public static void main(String[] args) {
		
		/**
		 * Linear search is less used today because it is slower than binary search and hashing.
		 */
        int[] a1= {10,20,30,50,70,90};    
        int key = 50;    
        System.out.println(key+" is found at index: "+linearSearch(a1, key));  

	}
	
	
	public static int linearSearch(int[] arr, int key) { 
		for(int i=0; i < arr.length; i++) { 
			if(arr[i] == key) { 
				return i;
			}
		}
		return -1;
	}

}
