public class TrieNode {
	//-----------------------------------------------------
	// Title: TrieNode class
	// Author: Arda Baran
	// Description: This class implements node of trie data structure.Trie node has 26 slots because there are 26 letters in
	//the english alphabet so that I declared an array type of TrieNode and size of 26.I also declared boolean variable named 
	//isStored which returns true if the word is stored in the trie.
	//-----------------------------------------------------		
	TrieNode[] slots;
  boolean isStored;
  
  public TrieNode() {
  this.slots = new TrieNode[26];//slots are initialized with the size of english alphabet.
  this.isStored = false;//initialized as false
 }
//getter and setters
 public TrieNode[] getSlots() {
  return slots;
   }

 public void setSlots(TrieNode[] slots) {
 this.slots = slots;
 }
  
public boolean isStored() {
return isStored;
}

 public void setStored(boolean isStored) {
this.isStored = isStored;
 }

 public boolean isContain(char letter) {
	//--------------------------------------------------------
	 // Summary: is TrieNode structure contains the parameter letter   
	 // Precondition: letter is a char
	 // Postcondition: return true if TrieNode contains letter,else returns false
	 //--------------------------------------------------------	 
int index = letter - 'a';
 if (index < 0 || index >= 26) {
  return false;
  }
  return slots[index] != null;
 }

 public void addLetterToSlots(char letter) {
	//--------------------------------------------------------
	 // Summary: Adds a character to appropriate indexthslot 
	 // Precondition: letter is a char
	 // Postcondition: finds the index of the letter then adds that letter to the index of slot.
	 //--------------------------------------------------------
	 	 
  int index = letter - 'a';//index of letter
  if (index >= 0 && index < 26) {
 slots[index] = new TrieNode();//letter is added to the index^th slot.
 }
 }
 
 public TrieNode getNext(char letter) {
	//--------------------------------------------------------
	 // Summary: returns the next index of TrieNode.Just like Binary Search Tree.
	 // Precondition: letter is a char.
	 // Postcondition: Returns the next TrieNode.
	 //--------------------------------------------------------
int index = letter - 'a';
 if (index >= 0 && index < 26) {
  return slots[index];
  }
  return null;
  }
}
