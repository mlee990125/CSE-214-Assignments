import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Name: Michael Lee 112424954
 *
 */
public class SpellCheck {
	public static int prime = 100003; 
	final String path = "dictionary.txt";
	public static CharHashTable dictHash = new CharHashTable();
	
	/*
	 * While reading each line of the dictionary from dictionary.txt, it uses a hasFunction on each line
	 * to find an index corresponding to that line. Then that line is put into an array from the class 
	 * CharHashTable using the put method. 
	 */
	public SpellCheck() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				int index = hashFunction(line);
				dictHash.put(index, line);
				// 'line' is a valid word
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}		
	}
	/*
	 * The length of the return array should be the same as the number of words in 'sentence'.
	 * The i-th element of the return array is the substitute candidate for the i-th word in the sentence.
	 * If the i-th word is a valid word (i.e., not a typo), then the array should be empty.
	 */
	
	public int hashFunction(String word) {
		
		int hashCode = word.hashCode() > 0 ? word.hashCode() : (int)Math.abs(word.hashCode());
		return hashCode % prime;
	}
	
	/*
	 * The main function of the spell check. For each sentence that is passed, this function is called. An array of arraylist 
	 * of size of the words is created and if the word is invalid (not in the dictionary), it is checked by the method checkTypo.
	 * Each correct possibility of the word is then added to the arraylist. The isValid is the function that uses the HashTable mechanic
	 * by checking the index of the word (using hashFunction) with the corresponding index in dictHash. As nearly every index contains one
	 * entry of a word, it is O(1) time complexity.
	 */
	public ArrayList<String>[] spellCheck(String sentence) {
		String[] words = sentence.split("\\s"); // 'words' is the list of words in 'sentence'
		int len = words.length;
		ArrayList<String> alArr[] = new ArrayList[len];
		for(int i = 0; i < len; i ++) {
			String word = words[i].toLowerCase();
			if(isValid(word)) {
				continue;
			}
			alArr[i] = checkTypo(word);
		}
		


		
		
		return alArr;
	}
	
	public boolean isValid(String word) {
		
		int index = hashFunction(word);
		return dictHash.contains(index, word);
	}
	/*
	 * Using four methods aosc, rosc, rposc, stac, all correct possibilities of the misspelled 
	 * words are stored in an arraylist. 
	 */
	public ArrayList<String> checkTypo(String word) {
		ArrayList<String> al = new ArrayList<>();
		aosc(al, word);
		rosc(al, word);
		rposc(al, word);
		stac(al, word);


		
		return al;
		
	}
	
	public void stac(ArrayList<String> al, String word) {
		String correction;
		for(int i = 0; i < word.length() - 1; i++) {
			for(int j = 1; j < word.length(); j++) {
				correction = swap(word, i, j);
				if(isValid(correction)) {
					if(!al.contains(correction)) al.add(correction);
				}
			}
		}
		
	}
	
	private String swap(String s, int i, int j) {
		char ch[] = s.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return new String(ch);
	}
	
	public void rposc(ArrayList<String> al, String word) {
		String correction;
		for(int i = 0; i < 26; i ++) {
			char c = (char)((i % 26)+97);
			for(int j = 0; j < word.length(); j++) {
				correction = word.substring(0, j) + c + word.substring(j+1);
				if(isValid(correction)) {
					if(!al.contains(correction)) al.add(correction);
				}
			}
		}
	}
	
	public void aosc(ArrayList<String> al, String word) {
		String correction;
		for(int i = 0; i < 26; i++) {
			char c = (char)((i % 26) + 97);
			for(int j = 0; j <= word.length(); j++) {
				correction = word.substring(0, j) + c + word.substring(j);
				if(isValid(correction)) {
					if(!al.contains(correction)) al.add(correction);
				}
			}
		}
	}
	
	public void rosc(ArrayList<String> al, String word) {
		String correction;
		for(int i = 0; i < word.length(); i++) {
			correction = word.substring(0, i) + word.substring(i + 1);
			if(isValid(correction)) {
				if(!al.contains(correction)) al.add(correction);
			}
		}
	}
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"s", "paint the banel", "shee is a riend", "kangaru"};
		// Feel free to change the following printout routine
		for(String sent : sentences) {
			String[] words = sent.split("\\s");
			ArrayList<String>[] ret = sc.spellCheck(sent);
			if(ret == null) continue;
			String cand = "";
			for(int i = 0; i < ret.length; i++) {
				if(ret[i] == null) {
					System.out.print(words[i] + " ");
					continue;
				}
				Iterator<String> it = ret[i].iterator();
				while(it.hasNext())
					cand += (it.next() + ",");
				System.out.print("(" + cand + ") ");
				cand = "";
			}
			System.out.println();
		}
		System.out.println(System.nanoTime() - startTime);
		for(int i = 0; i < sc.dictHash.al.length; i++) {
			//System.out.println(sc.dictHash.al[i].size());
		}

	}

}
/*
 * the class responsible for keeping the dictionary in index corresponding to its hashCode.
 * It has a contains method that is O(1) and a put method that is O(1). Collision is very rare 
 * as the memory allocation makes up for the simple time complexity. 
 */
class CharHashTable {
	ArrayList<String>[] al;
	final int MAX_SIZE = SpellCheck.prime;
	public CharHashTable() {
		al = new ArrayList[MAX_SIZE];
		for(int i = 0; i < al.length; i++) {
			al[i] = new ArrayList<String>();
		}
	}
	
	public void put(int index, String s) {
		al[index].add(s);
	}
	
	public boolean contains(int index, String word) {
		long startTime = System.nanoTime();
		for(int i = 0; i < al[index].size(); i++) {
			if(al[index].get(i).equals(word)) {
				//System.out.println(System.nanoTime() - startTime);
				return true;
			}
			
		}
		//System.out.println(System.nanoTime() - startTime);
		return false;
	}
}
