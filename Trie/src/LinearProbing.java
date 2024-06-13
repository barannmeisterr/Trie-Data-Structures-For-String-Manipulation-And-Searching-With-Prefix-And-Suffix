import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
public class LinearProbing<Key, Value> {
	//-----------------------------------------------------
	// Title: LinearProbing class
	// Author: Arda Baran
	// Description:This class implements Linear Probing Hash Table.
	//-----------------------------------------------------		
int size;
Key[] keys;
Value [] vals;
int[] occurrences;
    @SuppressWarnings("unchecked")
public LinearProbing(int size) {
this.size = size;
this.keys = (Key[]) new Object[size];
this.vals = (Value[]) new Object[size];
this.occurrences = new int[size];
}
public boolean isSameKey(String word1, String word2) {
	//--------------------------------------------------------
	 // Summary: are word1 and word2 same.
	 // Precondition: word1 is a string and word2 is a string
	 // Postcondition: returns true if word1 and word2 are same.
	 //--------------------------------------------------------	
String[] parts1 = word1.split(" ");
String[] parts2 = word2.split(" ");
for (String part1 : parts1) {
for (String part2 : parts2) {
if (part1.equalsIgnoreCase(part2)) {
return true;
}
}
}
return false;
}
public int hash(Key key) {
	//--------------------------------------------------------
	 // Summary: Key is hashed,each key in the linear probing has unique index
	 // Precondition: key is a type of Key.
	 // Postcondition: returns the hash value.
	 //--------------------------------------------------------	
if (key instanceof String) {//if key is a type of string
String k = ((String) key).toLowerCase();
return (k.hashCode() & 0x7fffffff) % size;
}
return (key.hashCode() & 0x7fffffff) % size;
}
public void addUniqueWordToTableForOccurrences(Key key, Value val) {
	//--------------------------------------------------------
	 // Summary: adds unique key value pair to the hash table by using linear probing
	 // Precondition: key is a Key and val is a Value
	 // Postcondition: key val pair are added to the hash table and occurences of keys are updated.
	 //--------------------------------------------------------	
String insertedKey = key.toString().toLowerCase();//converts key to the lovercase due to obtain desired output in the assignment
 int i;
for (i = hash(key); keys[i] != null; i = (i + 1) % size) {
String currKeys = keys[i].toString().toLowerCase();
if (isSameKey(insertedKey, currKeys)) {//if the inserted key is not unique which means already presents in the hash table
occurrences[i]++;//increments occurences of the key
return;//returns without doing nothing
}
}
keys[i] = key;//key is added to the hash table
vals[i] = val;//val is added to the hash table
occurrences[i]++;//occurences of the key incremented
}
public void sortedTopKWords(int k) {
	//--------------------------------------------------------
	 // Summary: finds and prints the most occured k words in the hash table by using priority queue.
	 // Precondition: k is an integer
	 // Postcondition: most occured k words are printed lexicographically with case sensitive
	 //--------------------------------------------------------	
// priority queue to keep track of the top k words
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
public int compare(Integer i, Integer j) {
// Comparing the  occurrences of the words int the hash table
int cmp = Integer.compare(occurrences[j], occurrences[i]);
// If occurrences are the same, compare keys lexicographically ignoring case
if (cmp == 0) {
 return ((String) keys[i]).compareToIgnoreCase((String) keys[j]);
}
return cmp;
}
});
 // Adds all indexes to the priority queue
for (int i = 0; i < size; i++) {
if (keys[i] != null) {
pq.offer(i);
}
}
// Retrieve the top k words with the most occurrences by using arraylist
List<String> topKWords = new ArrayList<>();
for (int count = 0; count < k && !pq.isEmpty(); count++) {
int maxIndex = pq.poll();
topKWords.add((String) keys[maxIndex]);
}
// Sort the top k words lexicographically, ignoring case sensitive
topKWords.sort(String::compareToIgnoreCase);

// Prints the sorted top k words
for (int i = 0; i < topKWords.size(); i++) {
System.out.print(topKWords.get(i).toLowerCase());
 if (i < topKWords.size() - 1) {
System.out.print(", ");
 }
 }
}
}