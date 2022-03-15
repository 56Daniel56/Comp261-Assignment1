package comp261.assig1;

import java.util.*;

/**
 * This is an implementation of a trie, used for the search box.
 */

public class Trie {
	TrieNode root = new TrieNode(); // the root node of the trie

	public Trie() {
	}

	/**
	 * Adds a given stop to the Trie.
	 */
	public void add(Stop stop) {
		// TODO
		// write the code to add a stop object into the trie



		// END of TODO
	}

	/**
	 * Returns all the stops whose names start with a given prefix.
	 */
	public List<Stop> getAll(String prefix) {
		// TODO
		// write the code to get all the stops whose names match the prefix.
		return null;



		// END of TODO
	}


	/**
	 * Represents a single node in the trie. It contains a collection of the
	 * stops whose names are exactly the traversal down to this node.
	 */
	private class TrieNode {
		List<Stop> data = new ArrayList<>();
		Map<Character, TrieNode> children = new HashMap<>();
	}
}