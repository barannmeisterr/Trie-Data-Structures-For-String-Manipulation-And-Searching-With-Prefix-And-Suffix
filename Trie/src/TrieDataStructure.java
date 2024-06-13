public class TrieDataStructure {
	//-----------------------------------------------------
	// Title: TrieDataStructure class
	// Author: Arda Baran
	// Description: This class implements 26-way trie data structure and uses Linear Probing hashing in order to 
	//find the most occured strings in the trie data structure.
	//-----------------------------------------------------		
TrieNode root;
LinearProbing <String,TrieNode> hashing;
public TrieDataStructure() {
this.root = new TrieNode();
this.hashing=new LinearProbing<String, TrieNode>(327);
}
public void insertWordToTheTrie(String word) {
	//--------------------------------------------------------
	 // Summary: Inserts all characters of word string to the trie.
	 // Precondition: word is a string.
	 // Postcondition: The characters of word added to the trie.
	 //--------------------------------------------------------	
TrieNode curr = root;//curr variable is used while traversing on the trie.
for (int i = 0; i < word.length(); i++) {//in order to add all character of the word string,for loop is used.
char letter = Character.toLowerCase(word.charAt(i));//all characters converted to lower case version of the string
if (!curr.isContain(letter)) {//if any character of the word string is not present in the trie
 curr.addLetterToSlots(letter);//adds that character to the string
}
curr = curr.getNext(letter);//if the character is already present in the trie , curr temp value moves to next step.
  }
hashing.addUniqueWordToTableForOccurrences(word, curr);//adds the word to the hash table
 curr.setStored(true);//sets the word is stored.
 }
 public boolean Search(String arg) {
	//--------------------------------------------------------
	 // Summary: Searches arg string in the trie
	 // Precondition: arg is a string.
	 // Postcondition: returns true if arg parameter string is present in the trie.
	 //--------------------------------------------------------	 
	 TrieNode curr = root;
  for (int i = 0; i < arg.length(); i++) {
  char letter = Character.toLowerCase(arg.charAt(i));
   if (!curr.isContain(letter)) {
      return false;
     }
   curr = curr.getNext(letter);
    }
  return curr.isStored();
  }

 public boolean startsWith(String prefix) {
	//--------------------------------------------------------
	 // Summary: is any word starts with the given prefix
	 // Precondition: prefix is a string.
	 // Postcondition:  returns true if any stored word starts with a given prefix parameter.
	 //--------------------------------------------------------	 
	 TrieNode curr = root;
   for (int i = 0; i < prefix.length(); i++) {
    char letter = Character.toLowerCase(prefix.charAt(i));
     if (!curr.isContain(letter)) {
      return false;
     }
   curr = curr.getNext(letter);
    }
  return true;
   }
  public boolean endsWith(String suffix) {
	//--------------------------------------------------------
		 // Summary: is any word ends with the given suffix
		 // Precondition: suffix is a string.
		 // Postcondition: returns true if any stored word ends with a given suffix parameter.
		 //--------------------------------------------------------	 
  TrieNode curr = root;
  for (int i = suffix.length() - 1; i >= 0; i--) {
   char letter = Character.toLowerCase(suffix.charAt(i));
   if (!curr.isContain(letter)) {
     return false;
   }
  curr = curr.getNext(letter);
  }
 return true;
    }
  public void autoComplete(String prefix) {
	//--------------------------------------------------------
	  // Summary: Prints all words in the trie which starts with the given prefix by using autoCompleteHelper method
	  // Precondition: prefix is a string.
	  // Postcondition: all words that starts with the given prefix are added to the Singly Linked List data structure
	  //then printed as lexicographically.
	  //--------------------------------------------------------	  
	  if (!startsWith(prefix)) {
   System.out.println("No words");
    return;
    }
  TrieNode curr = root;
  for (int i = 0; i < prefix.length(); i++) {
 char letter = Character.toLowerCase(prefix.charAt(i));
   curr = curr.getNext(letter);
  }
 SinglyLinkedList result=new SinglyLinkedList();
  autoCompleteHelper(curr, prefix,result);
  result.display();
  }
 public SinglyLinkedList startsWithList(String prefix) {
		//--------------------------------------------------------
	  // Summary: adds all words in the trie which starts with the given prefix to the list then returns that list.
	 // Implemented due to detect full auto complete words in the trie.
	  // Precondition: prefix is a string.
	  // Postcondition: all words that starts with the given prefix are added to the list then returns that list
	  //--------------------------------------------------------	
	 if (!startsWith(prefix)) {
      System.out.println("No words");
      return null;
    }
   TrieNode curr = root;
     for (int i = 0; i < prefix.length(); i++) {
       char letter = Character.toLowerCase(prefix.charAt(i));
        curr = curr.getNext(letter);
      }
     SinglyLinkedList result=new SinglyLinkedList();
    autoCompleteHelper(curr, prefix,result);  
   return result;
 }
 public SinglyLinkedList endsWithList(String suffix) {
 //----------------------------------------------------------
 //Summary:adds all words in the trie which ends with the given suffix to the list then returns that list.	 
 //Precondition:suffix is a string
 //Postcondition:all words that ends with the given suffix are added to the list then returns that list	 
//--------------------------------------------------------------
	 SinglyLinkedList resultList = new SinglyLinkedList();
   reverseAutoCompleteHelper(root, suffix, "", resultList);
   return resultList;   
 }
   
  public void autoCompleteHelper(TrieNode node, String prefix,SinglyLinkedList resultList) {
		//--------------------------------------------------------
	  // Summary: Recursively finds all words that starts with the given prefix then adds these words to the list
	  // Precondition:node is TrieNode,prefix is string,resultList is SinglyLinkedList
	  // Postcondition: all words that starts with the given prefix are added to the Singly Linked List data structure
	  //by using recursive approach.
	  //--------------------------------------------------------	
	  if (node.isStored()) {
     resultList.addWordToList(prefix);
    }
    for (int i = 0; i < 26; i++) {
      if (node.slots[i] != null) {
      char nextCharacter = (char) (i + 'a');
    autoCompleteHelper(node.slots[i], prefix + nextCharacter,resultList);
      }
    }
    }
  public void reverseAutoCompleteHelper(TrieNode node, String suffix, String currentSuffix, SinglyLinkedList resultList) {
		//--------------------------------------------------------
	  // Summary: Recursively finds all words that ends with the given suffix then adds these words to the list
	  // Precondition: node is TrieNode,suffix is string,currentSuffix is string, resultList is SinglyLinkedList
	  // Postcondition:all words that ends with the given suffix are added to the Singly Linked List data structure
	  //by using recursive approach.
	  //--------------------------------------------------------	
	  if (node == null) { 
      return;
    }
     for (int i = 0; i < 26; i++) {
    TrieNode curr = node.slots[i];
     if (curr != null) {
    char nextCharacter = (char) (i + 'a');
  reverseAutoCompleteHelper(curr, suffix, currentSuffix + nextCharacter, resultList);
    }
   }
   if (node.isStored() && currentSuffix.endsWith(suffix)) {
   resultList.addWordToList(currentSuffix);
    }
    }
  
  public void reverseAutoComplete(String suffix) {
	//--------------------------------------------------------
	  // Summary: Prints all words in the trie which ends with the given suffix by using reverseAutoCompleteHelper method
	  // Precondition: suffix is a string.
	  // Postcondition: all words that ends with a given suffix are printed.
	  //--------------------------------------------------------

SinglyLinkedList resultList = new SinglyLinkedList();//list of words that ends with a given suffix.
  reverseAutoCompleteHelper(root, suffix, "", resultList);
  if (resultList.isEmpty()) {
   System.out.println("No words");        
  }else {
  resultList.display();
   }
 }
 public void FullAutoComplete(String prefix, String suffix) {
	//--------------------------------------------------------
	 // Summary: Finds all words in the trie that starts with the given prefix and ends with the given suffix
	 //then prints these words
	 // Precondition: prefix is a char and suffix is a string
	 // Postcondition:all words in the trie that starts with the given prefix and ends with the given suffix are printed.
	 //--------------------------------------------------------
 
SinglyLinkedList autoCompleteList = startsWithList(prefix);//prefix list
  SinglyLinkedList reverseAutoCompleteList = endsWithList(suffix);//suffix list
        // Check if either list is null or empty
if (autoCompleteList==null || reverseAutoCompleteList==null || autoCompleteList.isEmpty() || reverseAutoCompleteList.isEmpty()) {            
 return;
  }
 SinglyLinkedList fullAutoCompleteList = autoCompleteList.getCommonElementsBetweenTwoList(reverseAutoCompleteList);//common words between two lists
        // Check if the resulting list is empty
  if (fullAutoCompleteList == null || fullAutoCompleteList.isEmpty()) {
    System.out.println("No words");//if there is no element in the fullAutoComplete list,prints "No words"
   } else {
 fullAutoCompleteList.display();//displays the common elements
   }
  }
public void findTopK(int k) {
//-------------------------------------------------------------
//Summary:finds most occured k elements then prints them	
//Precondition:k is an integer.
//PostCondition:most k occured strings are printed lexicographically.	
//---------------------------------------------------------------	
	hashing.sortedTopKWords(k);
}

}