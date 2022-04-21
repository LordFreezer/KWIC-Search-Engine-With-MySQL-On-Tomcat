/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PAK;

import java.util.ArrayList;

/**
 *
 * @author eeeee
 */
public class MicroMiner {
	private Alphabetizer reference;
	// public static ArrayList<String> result;

	public MicroMiner(Alphabetizer az) {
		reference = az;
		// result = new ArrayList<String>();

	}

	/**
	 * OVERVIEW: Searches every shifted line in reference that starts with the first
	 * character in the keyword and displays the unshifted form of the line that
	 * starts with that keyword.
	 * 
	 * @param s The String keyword to search for
	 */
	public ArrayList<Integer> Search(String query) {
		// Separate keywords by space
		String[] keys = query.split(" ");

		// Create a list of results
		ArrayList<ArrayList<Integer>> results = new ArrayList();
		for (String s : keys) {
			results.add(linesThatMatch(s));
		}

		// Find the lines that every result has in common
		ArrayList<Integer> trueResults = results.get(0);
		for (int i = 1; i < results.size(); i++) {
			trueResults.retainAll(results.get(i));
		}
		return trueResults;
	}

	/**
	 * OVERVIEW: Finds the lines in a set that contain the specified keyword
	 * 
	 * @param s The string keyword to search for
	 * @return An int array representing the indices of the lines that contain that
	 *         keyword;
	 */
	public ArrayList<Integer> linesThatMatch(String s) {
		ArrayList<Integer> lines = new ArrayList();
		char c = s.charAt(0);
		int f = reference.whereIs(c);
		if (f == -1)
			return lines;
		while (reference.getChar(f, 0, 0) == c) {
			if (isKeyword(f, s))
				lines.add(reference.getParentIndex(f));
			f++;
		}
		return lines;
	}

	/**
	 * OVERVIEW: Displays the line at the specified index, ignoring circular shifts
	 * 
	 * @param i the index of the line to search for
	 */
	public void displayParentLine(int i) {
		int g = 0, h = 0;
		while (true) {
			if (reference.getParentChar(i, g, h) == 0) {
				h = 0;
				g++;
				System.out.print(' ');
			}
			if (reference.getParentChar(i, g, h) == 0) {
				break;
			}
			System.out.print(reference.getParentChar(i, g, h));
			h++;
		}
		System.out.println(" =>  " + reference.getUrl(i));
	}

	/**
	 * OVERVIEW: Determines if the line at the specified index starts with the
	 * specified String word
	 * 
	 * @param i The index of the line to search
	 * @param s The String keyword to search for
	 * @return whether or not that line starts with that keyword
	 */
	public boolean isKeyword(int i, String s) {
		int g = 0;
		String ref = "";
		while (reference.getChar(i, 0, g) != 0) {
			ref += (reference.getChar(i, 0, g));
			g++;
		}
		return ref.equals(s);
	}
}
