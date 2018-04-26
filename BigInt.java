package RevisedBigInt;

import RevisedBigInt.Exceptions.InvalidInputException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;


//Hare Krsna
public class BigInt
{

	private ArrayList<Integer> numberArray = new ArrayList<>();
	private boolean isCharged = false;

	BigInt(String userValue)
	{
		String absValue = verifySignAndLength(userValue);
		try {
			if (isInputValid(absValue))
				this.numberArray = storeInArrayList(absValue);
		} catch (InvalidInputException e) {
			e.getMessage();
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private BigInt(ArrayList<Integer> numberArray)
	{
		this.numberArray = numberArray;
	}

	private String verifySignAndLength(String value)
	{
		char signValue = value.charAt(0);
		if (value.length() == 1 && !Character.isDigit(value.charAt(0)))
			System.exit(-1);
		else if (signValue == '+' || signValue == '-') {
			setSign(signValue);
			return value.substring(1);
		}return value;
	}

	private boolean isInputValid(String value) throws InvalidInputException
	{
		for (int i = 0; i < value.length(); i++) {
			if (!Character.isDigit(value.charAt(i)))
				throw new InvalidInputException(value.concat(" ")
						.concat("Contains invalid Character"));
		}return true;
	}

	private void setSign(char signValue)
	{
		if (signValue == '-')
			isCharged = true;
	}

	private ArrayList<Integer> storeInArrayList(String value)
	{
		ArrayList<Integer> numberArray = new ArrayList<>();
		if (isCharged) {
			for (int i = 0; i < value.length(); i++)
				numberArray.add(Integer.parseInt(value.substring(i, i + 1)) * -1);
		} else {
			for (int i = 0; i < value.length(); i++)
				numberArray.add(Integer.parseInt(value.substring(i, i + 1)));
		}return numberArray;
	}

	BigInt add(BigInt other)
	{
		reverse(this.numberArray,other.numberArray,null);
		BigInt f = new BigInt(add(this.numberArray, other.numberArray));
		reverse(this.numberArray,other.numberArray,f.numberArray);
		return f;
	}

	private void reverse(ArrayList<Integer> numberArray,
	                     ArrayList<Integer> second, ArrayList<Integer> third)
	{
		Collections.reverse(numberArray);
		Collections.reverse(second);
		if(third != null) Collections.reverse(third);
	}

	private ArrayList<Integer> add(ArrayList<Integer> addend,
	                               ArrayList<Integer> auguend)
	{
		int carry = 0, tempSum;
		ArrayList<Integer> sum = new ArrayList<>();
		compareAndPadArrayList(addend, auguend);

		for (int i = 0; i < addend.size(); i++) {
			tempSum = addend.get(i) + auguend.get(i) + carry;
			if (tempSum >= 10 || tempSum <= -10) {
				carry = tempSum / 10;
				sum.add(tempSum % 10);
			} else {
				sum.add(tempSum);
				carry = 0;
			}
		}
		if (carry != 0) sum.add(carry);

		return sum;
	}

	BigInt subtract(BigInt other)
	{
		BigInt difference;
		reverse(this.numberArray,other.numberArray,null);
		difference = new BigInt(subtractionByCases(other));
		reverse(this.numberArray,other.numberArray,difference.numberArray);
		return difference;
	}

	private ArrayList<Integer> subtractionByCases(BigInt other)
	{
		ArrayList<Integer> answer = new ArrayList<>();
		if(this.numberArray.size() == 1 && other.numberArray.size() == 1){
			answer.add(this.numberArray.get(0) - other.numberArray.get(0));
		}
		//  A.size = B.size || A - B
		// Go ahead and do normal subtraction
		else if(!this.isCharged && !other.isCharged) {
			answer = subtractWithBorrow(this.numberArray, other.numberArray);
		}
		// A - (-B) = A + B
		// First make B positive then add then together
		else if(!this.isCharged ) {
			answer = add(this.numberArray, negate(other.numberArray));
		}
		// -A - (-B) = -A + B = B - A
		// Make B positive and keep A the same the subtract
		else if( other.isCharged) {
			answer = negate(subtractWithBorrow(negate(this.numberArray), negate(other.numberArray)));
		}
		// -A - B
		else  {
			answer = negate(add(negate(this.numberArray), other.numberArray));
		}
		return answer;
	}

	private ArrayList<Integer> subtractWithBorrow(ArrayList<Integer> minuend,
	                                              ArrayList<Integer> subtrahend)
	{
		compareAndPadArrayList(minuend,subtrahend);
		ArrayList<Integer> difference = new ArrayList<>();
		int tempSum, borrow = 0;

		for (int i = 0; i < minuend.size(); i++) {
			tempSum = (minuend.get(i) - borrow) - subtrahend.get(i);
			if (tempSum < 0) {
				difference.add(tempSum + 10);
				borrow = 1;
			} else {
				difference.add(tempSum);
				borrow = 0;
			}
		}
		if(difference.get(difference.size()-1) == 0)
			difference.remove(difference.size()-1);

		return difference;
	}

	private void compareAndPadArrayList(ArrayList<Integer> firstArray,
	                                    ArrayList<Integer> secondArray)
	{
		int second = secondArray.size(), first = firstArray.size();
		if (firstArray.size() < secondArray.size()) {
			padWithZeros(firstArray, second - first);
		} else padWithZeros(secondArray, first - second);
	}

	private void padWithZeros(ArrayList<Integer> array, int numberOfZeros)
	{
		int lastPos = array.size();
		for (int i = 0; i < numberOfZeros; i++)
			array.add(lastPos, 0);
	}

	private ArrayList<Integer> negate(ArrayList<Integer> numberArray)
	{
		ArrayList<Integer> negatedArray = new ArrayList<>();
		for (Integer aNumberArray : numberArray) {
			negatedArray.add(aNumberArray * -1);
		}
		return negatedArray;
	}

	private void finalCheckForNegativeNumbers()
	{
		for (Integer aNumberArray : this.numberArray) {
			if (aNumberArray < 0) {
				isCharged = true;
				break;
			}
		}
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		finalCheckForNegativeNumbers();
		if(isCharged) stringBuilder.append('-');

		for (Integer aNumberArray : this.numberArray)
			stringBuilder.append(Math.abs(aNumberArray));

		String finalString = stringBuilder.toString();

		if(finalString.matches("^[0]+$"))
			return finalString.substring(0,1);
		else
			return finalString.replaceFirst("^0*", "");
	}
}
