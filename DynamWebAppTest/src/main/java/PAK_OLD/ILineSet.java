package PAK_OLD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Stephen Key
 */
public interface ILineSet {
	/**
	 * OVERVIEW: Returns the k'th character of the j'th word of the i'th line in the
	 * list
	 * 
	 * @param i The index of the line to search for
	 * @param j The index of the line to search for within that set
	 * @param k The index of the character to search for within that word
	 * @return The char value at that index
	 */
	public char getChar(int i, int j, int k);

	/**
	 * OVERVIEW: returns the number of lines in this set
	 * 
	 * @return an integer value of the size of the lines list
	 */
	public int lineCount();

	/**
	 * OVERVIEW: returns the word count of the line at the specified index
	 * 
	 * @param i The index of the line to search for
	 * @return The word count of that line
	 */
	public int wordCountAt(int i);

	/**
	 * OVERVIEW: Returns the length of the j'th word in the i'th line
	 * 
	 * @param i the index of the line to search for
	 * @param j the index of the word to search for within that line
	 * @return The length of that word
	 */
	public int charCountAt(int i, int j);

	/**
	 * OVERVIEW: Returns the url string of the line at the specified index
	 * 
	 * @param i The index of the line to search for
	 * @return The url string of the line at that index
	 */
	public String getUrl(int i);
}
