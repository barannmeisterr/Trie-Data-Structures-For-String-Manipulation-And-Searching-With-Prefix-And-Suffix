public class ListNode {
	//-----------------------------------------------------
	// Title: ListNode class
	// Author: Arda Baran
	// Description: This class implements the node of Singly Linked List data structure
	//-----------------------------------------------------	
String word;
ListNode next;
public ListNode(String word) {
	this.word=word;
    this.next=null;
}
//getter and setters
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public ListNode getNext() {
	return next;
}
public void setNext(ListNode next) {
	this.next = next;
}
}
