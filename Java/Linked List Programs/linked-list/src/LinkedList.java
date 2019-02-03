
public class LinkedList {

	public static void main(String[] args) {
        /* Start with the empty list. */  
		LinkedList list = new LinkedList();
		
		LinkedList.head = new Node(100);  
		Node second = new Node(200);
		Node third = new Node(300);  
		
		LinkedList.head.next = second; // Link first node with the second node  
		second.next = third; // Link first node with the second node  
		
		list.display();
	}
	
	/* This function prints contents of the linked list starting from head */  
	public void display() { 
		Node n = head;
		while(n != null) { 
			 System.out.print(n.data+" \n");  
			 n = n.next;
		}
	}

	static Node head;  // head of list 
	
	static class Node{ 
		int data;
		Node next;
			
		Node(int d){ 
			data = d;
			next = null;
		}
	}
	
	

}
