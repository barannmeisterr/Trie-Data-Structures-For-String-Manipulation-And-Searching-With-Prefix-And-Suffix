import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
	//-----------------------------------------------------
		// Title: Main class
		// Author: Arda Baran
		// ID: 19172802022
		// Section: 2
		// Assignment: 4
		// Description:This class inserts all words to the trie then uses Command Line Interface(CLI) to print the output
		//-----------------------------------------------------	
public static void main(String[] args) throws IOException {
        TrieDataStructure trie = new TrieDataStructure();
        Scanner sc = new Scanner(System.in);

        // Reads the filename
       
        String filename = sc.nextLine();

        // Reads words from the input file and insert them into the trie
        String filePath = "src/resources/"+filename;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    trie.insertWordToTheTrie(word.replaceAll("[^a-zA-Z]", ""));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        // Command loop to perform operations based on user input
        while (sc.hasNextLine()) { // Checks that if there is a valid input
            String command = sc.nextLine();
            String[] commandParts = command.split(" ");
            switch (commandParts[0].toLowerCase()) {
                case "search"://Search(String arg)
                    System.out.println(trie.Search(commandParts[1]));
                    break;
                case "autocomplete"://autoComplete(String prefix)
                    trie.autoComplete(commandParts[1]);
                    break;
                case "reverse"://reverseAutoComplete(String suffix)
                    trie.reverseAutoComplete(commandParts[1]);
                    break;
                case "full"://FullAutoComplete(String prefix, String suffix)
                    trie.FullAutoComplete(commandParts[1], commandParts[2]);
                    break;
                case "topk"://findTopK(int k)
                    trie.findTopK(Integer.parseInt(commandParts[1]));
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
