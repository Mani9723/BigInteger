import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * This only purpose of this class is to read a file and return the
 * numerical contents of the file
 *
 * @author Mani Shah
 * @version 1.0
 * @since 5/23/18
 */
public class BigIntFileReader
{
	/**
	 * Holds the string representation of the filepath
	 */
	private String filePath;
	/**
	 * Holds the contents of the file
	 */
	private String number;

	/**
	 * This constructor accepts a string arguments that represents the filePath.
	 * Initializes the instance variables and calls helper method to get data from the file.
	 * @param filePath - String
	 */
	public BigIntFileReader(String filePath)
	{
		this.filePath = filePath;
		number = openAndStoreContents();
	}

	/**
	 * Creates a Scanner object that reads the file and then stores that value
	 * in a StringBuilder variable.
	 * @return StringBuilder.toString()
	 */
	private String openAndStoreContents()
	{
		StringBuilder tempNumber = new StringBuilder();
		Scanner inputStream = readFromFile();
		while (inputStream.hasNext()) {
			tempNumber.append(inputStream.nextLine().trim());
		}
		inputStream.close();
		return tempNumber.toString();
	}

	/**
	 * Actually read the file and return the Scanner object
	 * @return Scanner object
	 */
	private Scanner readFromFile()
	{
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(getFilePath()));
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " +this.filePath);
			System.exit(0);
		}
		return inputStream;
	}

	/**
	 * Returns the filePath
	 * @return -String
	 */
	private String getFilePath()
	{
		return filePath;
	}

	/**
	 * Returns the numerical contents of the file
	 * @return - String
	 */
	public String getContents()
	{
		return number;
	}
}
