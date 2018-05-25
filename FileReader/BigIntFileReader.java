package RevisedBigInt.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.Scanner;

public class BigIntFileReader
{
	private String filePath;
	private String number;

	public BigIntFileReader(String filePath)
	{
		this.filePath = filePath;
		number = openAndStoreContents();
	}

	private String openAndStoreContents()
	{
		String tempNumber = "";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(getFilePath()));
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " +
					this.filePath);
			System.exit(0);
		}
		while (inputStream.hasNext()) {
			tempNumber += (inputStream.nextLine().trim());
		}
		inputStream.close();
		return tempNumber;
	}

	private String getFilePath()
	{
		return filePath;
	}

	@Override
	public String toString()
	{
		return number;
	}
}
