public class SinglyLinkedList {
	//-----------------------------------------------------
	// Title: SinglyLinkedList class
	// Author: Arda Baran
	// Description: this class implements Singly Linked List data structure.
	//-----------------------------------------------------		
ListNode root;
public SinglyLinkedList() {
	this.root=null;
}
public int getSize() {
	//--------------------------------------------------------
	 // Summary: finds the size of the list iteratieviliy
	 // Postcondition: returns the size of the list
	 //--------------------------------------------------------	
	int size=0;
	ListNode curr=root;
	while(curr!=null) {
		size++;
		curr=curr.getNext();
	}
	return size;	
}
public boolean isEmpty() {
	//--------------------------------------------------------
	 // Summary: is the list is empty
	 // Postcondition: returns true if the list is empty
	 //--------------------------------------------------------	
	return (getSize()==0);	
}
public ListNode addWordToList(ListNode n ,String key) {
	//--------------------------------------------------------
	 // Summary: adds the key to the list by recursively.
	 // Precondition: n is a ListNode and key is a string
	 // Postcondition: new node is added to the list
	 //--------------------------------------------------------	
	if(n==null) {
		return new ListNode(key);
	}
n.setNext(addWordToList(n.getNext(),key));
return n;
}
public void addWordToList(String key) {
	root=   addWordToList(root,key); 
}
public SinglyLinkedList getCommonElementsBetweenTwoList(SinglyLinkedList b) {
	//--------------------------------------------------------
	 // Summary: Finds the common elements between two different lists then returns the list of common elements between two lists.
	 // Precondition: b is a SinglyLinkedList 
	 // Postcondition: the common elements between two lists are added to the common list , then the function returns that 
	 //common list
	 //--------------------------------------------------------
	SinglyLinkedList common = new SinglyLinkedList();//list of common elements between two lists
    if (root == null || b.root == null) {
        return null;
    }
    ListNode curr1 = root;//root of first list
    ListNode curr2 = b.root;//root of second list
    // Iterate through the first list
    while (curr1 != null) {
        boolean foundInSecondList = false;
        ListNode temp = curr2;
        // Iterate through the second list in order to find a common element
        while (temp != null) {
            if (curr1.getWord().equals(temp.getWord())) {
                foundInSecondList = true;
                break;
            }
            temp = temp.next;
        }
        // If the element is found in both lists, add it to the common list
        if (foundInSecondList) {
            common.addWordToList(curr1.getWord());
        }
        curr1 = curr1.next;
    }
    return common;
}
public void display() {
	//--------------------------------------------------------
	 // Summary: list is printed.
	 // Postcondition: the elements in the list are printed lexicographically according to the desired output in the assignment.
	 //--------------------------------------------------------	
	if (root == null) {
        System.out.println("No word");
        return;
    }    
    ListNode curr = root;
    StringBuilder sb = new StringBuilder();
    while (curr != null) {
        sb.append(curr.getWord());
        if (curr.getNext() != null) {
            sb.append(", ");
        }
        curr = curr.getNext();
    }
    System.out.println(sb.toString());
}
}