package RevisedBigInt;

import RevisedBigInt.Exceptions.InvalidInputException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;

/**
 *
 * This {@link BigInt} class allows for basic arithmetic on large integers.
 * Primitive data types such as int,double,float,long can hold only so much.
 * <p>
 *     <table border = 1 cellpadding = "5">
 *         <tr>
 *             <th>Type</th>
 *             <th>Size</th>
 *             <th>Range</th>
 *         </tr>
 *         <tr>
 *             <td>Byte</td>
 *             <td>16 Bits</td>
 *             <td>-128 .. 127</td>
 *         </tr>
 *         <tr>
 *             <td>short</td>
 *             <td>16 bits</td>
 *             <td>-32,768 .. 32,767</td>
 *         </tr>
 *         <tr>
 *             <td>int</td>
 *             <td>32 bits</td>
 *             <td>-2,147,483,648 .. 2,147,483,647</td>
 *         </tr>
 *         <tr>
 *             <td>long</td>
 *             <td>64 bits</td>
 *             <td>-9,223,372,036,854,775,808 .. 9,223,372,036,854,775,807</td>
 *         </tr>
 *         <tr>
 *             <td>float</td>
 *             <td>32 bits</td>
 *             <td>3.40282347 x 1038, 1.40239846 x 10-45</td>
 *         </tr>
 *         <tr>
 *             <td>double</td>
 *             <td>64 bits</td>
 *             <td>1.7976931348623157 x 10308, 4.9406564584124654 x 10-324</td>
 *         </tr>
 *     </table>
 *
 * </p>
 *
 * When dealing numbers that are arbitrarily lengthy those data types are not helpful
 * and will create more problems then necessary.
 * In situations where encryption is required, it is the norm
 * to work with numbers that are hundreds of bits long.
 * Those types of situation will benefit from using the {@link BigInt} class.
 *
 * Java library already provides a comprehensive class called {@link java.math.BigInteger}.
 * However, this was a small scale project that I wanted to implement myself and try to practice
 * good design.
 *
 * This class provides methods that allow for basic arithmetic on arbritarily large integers.
 * Only {x | x E Z}.
 *
 * This class provides {@link #add(BigInt)}, {@link #subtract(BigInt)},
 * {@link #multiply(BigInt)}multiplication, division*, and modulus*.
 * There are a couple of comparison option such as {@link #isEqualTo(BigInt)} and {@link #compareTo(BigInt)}
 *
 * Overall, this class has been tested thoroughly using the JUnit4 Testing framework
 * on all methods with 100% coverage.
 *
 *------*: refers to features that are under development.
 * @author Mani Shah
 * @version 2.1
 * @since 4/4/18
 */

//Hare Krsna
public class BigInt
{
	/**
	 * This private instance ArrayList {@link #numberArray} will hold the current
	 * arraylist that the BigInt object is referencing.
	 * It is not initialized to any size at the moment.
	 * I have opted not to use the ArrayList's minimum capacity feature.
	 */
	private ArrayList<Integer> numberArray = new ArrayList<>();

	/**
	 * A private instance of a boolean {@link #isCharged} will
	 * hold the charge of the integer.
	 * This variable is the deciding factor in the arthemtic sitautions that this class
	 * handles.
	 */
	private boolean isCharged = false;

	/**
	 * The Packacge Private main constructor accepts a string argument.
	 * Since a string can be of arbritary length, only the memory of
	 * the machine is the limiting factor.
	 * This constructor initializes the main arraylist {@link #numberArray}
	 * and handles the situations where the user enters bad data.
	 *
	 * In the event of an exception, the constructor will catch
	 * the {@link InvalidInputException} and print a nice stackTrace
	 * and display the problematic spots in that particular execution of the code.
	 * It will then exit the program.
	 *
	 * Otherwise the program will continue.
	 *
	 * @param userValue - The input provided by the user.
	 */
	BigInt(String userValue)
	{
		String absValue = verifySignAndMinLength(userValue);

		try {
			if (isInputValid(absValue))
				this.numberArray = new ArrayList<>(storeInArrayList(absValue));
		} catch (InvalidInputException e) {
			e.getMessage();
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Private constructor that acts as an lightweight constructor
	 * whose only purpose is to hold temporary arraylist until the operation
	 * on that arrayList has been created.
	 *
	 * @param numberArray - ArrayList that temporarily creates a BigInt object to be manipulated.
	 */
	private BigInt(ArrayList<Integer> numberArray)
	{
		this.numberArray = numberArray;
	}

	/**
	 * This method simply looks for a positve or a negative sign in front of the user input
	 * and initializes the boolean instance variable if the sign is negative.
	 *
	 * It will exit the program if the length is 1 and not that character is not an integer.
	 *
	 * @param value  - String value to be tested for validity
	 * @return the String without any "+" or "-" signs
	 */
	private String verifySignAndMinLength(String value)
	{
		char signValue = value.charAt(0);
		if (value.length() == 1 && !Character.isDigit(value.charAt(0)))
			System.exit(-1);
		else if (signValue == '+' || signValue == '-') {
			setSign(signValue);
			return value.substring(1);
		}return value;
	}

	/**
	 * It will determine if all the characters in the user-given string are Integers.
	 * In the case that it is not; {@link InvalidInputException} will be thrown with
	 * a message.
	 *
	 * @param value - String value to be tested
	 * @return boolean indicating the success or failure of the test
	 * @throws InvalidInputException - Handles the exception.
	 */
	private boolean isInputValid(String value) throws InvalidInputException
	{
		for (int i = 0; i < value.length(); i++) {
			if (!Character.isDigit(value.charAt(i)))
				throw new InvalidInputException(value.concat(" ")
						.concat("Contains invalid Character"));
		}return true;
	}

	/**
	 * Sets the instance variable {@link #isCharged} if the given value is negative
	 * @param signValue - the first character of the given string.
	 */
	private void setSign(char signValue)
	{
		if (signValue == '-') isCharged = true;
	}

	/**
	 * Store the final approved String in the {@link #numberArray}
	 * If negative the elements will be negated during parsing.
	 * @param value - String to be stored in the ArrayList
	 * @return the final ArrayList that will be assigned to {@link #numberArray}
	 */
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

	/**
	 * Uses the {@link Collections} class's reverse method to reverse the ArrayLists
	 * There are three parameters. The first two parameters are required whereas the
	 * third arraylist can be null. The method will handle the null input.
	 *
	 * @param numberArray - ArrayList
	 * @param second - ArrayList
	 * @param third - ArrayList. This can be null.
	 */
	private void reverse(ArrayList<Integer> numberArray, ArrayList<Integer> second,
	                     ArrayList<Integer> third)
	{
		Collections.reverse(numberArray);
		Collections.reverse(second);
		if(third != null) {
			Collections.reverse(third);
		}
	}

	/**
	 * Add method.
	 *
	 * This method will add any two arbritarily large integers in the set {x | x E Z}.
	 * The strings to be added are passed around through {@link BigInt} objects.
	 *
	 * Adding is commutative so simply A + B will do.
	 * If the result is negative then  the {@link #isCharged} value is set and the result will
	 * be negated.
	 *
	 * The resulting ArrayLists are first reversed since additions are done from right to left.
	 * Then the sum is produced through helper method {@link #add(BigInt)} and stored in a temporary
	 * BigInt object.
	 *
	 * All of the ArrayLists are reversed to their original positions and the sum is returned through
	 * the {@link #toString()} method.
	 *
	 * @param other -  BigInt object representing the auguend
	 * @return the sum of this and other in an ArrayList.
	 */
	BigInt add(BigInt other)
	{
		reverse(this.numberArray,other.numberArray,null);
		BigInt f = new BigInt(add(this.numberArray, other.numberArray));
		reverse(this.numberArray,other.numberArray,f.numberArray);
		return f;
	}

	/**
	 * The helper method that will compute the sum of two large integers.
	 *
	 * It uses a helper method {@link #compareAndPadArrayList(ArrayList, ArrayList)}
	 * that will balance out the difference in sizes
	 * between the two ArrayList otherwise it will throw an {@link IndexOutOfBoundsException}
	 * since one of the ArrayList is requested access to an element that does not exist.
	 * So the smaller ArrayList will be padded with zeros on the front to avoid that error.
	 *
	 * The new ArrayLists will be summed and handles carry.
	 *
	 * @param addend - Number being added
	 * @param auguend - Number being added to
	 *
	 * @return The sum in an ArrayList
	 */
	private ArrayList<Integer> add(ArrayList<Integer> addend, ArrayList<Integer> auguend)
	{
		int carry = 0, tempSum;
		ArrayList<Integer> sum = new ArrayList<>();
		if(addend.size()!=auguend.size()) compareAndPadArrayList(addend, auguend);

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

	/**
	 * This method will subtract any two arbritarily large integers.
	 * As with the add method it will first reverse and then call
	 * another helper method that will divide the two numbers based
	 * on whether the numbers are positive or negative. The result will
	 * be stored in a temporary BigInt object.
	 *
	 * @param other - BigInt object that represents the subtrahend
	 * @return the difference
	 */
	BigInt subtract(BigInt other)
	{
		BigInt difference;
		reverse(this.numberArray,other.numberArray,null);
		difference = new BigInt(subtractionByCases(other));
		reverse(this.numberArray,other.numberArray,difference.numberArray);
		return difference;
	}

	/**
	 * Since subtraction is not commutative and not associative, its better
	 * to handle it by cases.
	 *
	 * <p>
	 *     <table border = 1 cellpadding = "5">
	 *         <tr>
	 *             <th>Cases</th>
	 *         </tr>
	 *         <tr>
	 *             <td>A.size = B.size || A - B</td>
	 *         </tr>
	 *         <tr>
	 *             <td>A - (-B) = A + B</td>
	 *         </tr>
	 *         <tr>
	 *             <td>-A - (-B) = -A + B = B - A</td>
	 *         </tr>
	 *         <tr>
	 *             <td>-A - B = -(A + B)</td>
	 *         </tr>
	 *     </table>
	 *
	 * </p>
	 *
	 * The cases will simplify the process by using addition to subtract
	 * Even though both of them are using the same algorithm the time spent is
	 * the same. Its better because addition is slightly faster.
	 * @param other
	 * @return
	 */
	private ArrayList<Integer> subtractionByCases(BigInt other)
	{
		ArrayList<Integer> answer = new ArrayList<>();
		if(this.numberArray.size() == 1 && other.numberArray.size() == 1)
			answer.add(this.numberArray.get(0) - other.numberArray.get(0));
		else if(!this.isCharged && !other.isCharged)  //  A.size = B.size || A - B
			answer = subtractWithBorrow(this.numberArray, other.numberArray);
		else if(!this.isCharged )  // A - (-B) = A + B
			answer = add(this.numberArray, negate(other.numberArray));
		else if( other.isCharged)  	// -A - (-B) = -A + B = B - A
			answer = negate(subtractWithBorrow(negate(this.numberArray), negate(other.numberArray)));
		else   // -A - B = -(A + B)
			answer = negate(add(negate(this.numberArray), other.numberArray));
		return answer;
	}

	private ArrayList<Integer> subtractWithBorrow(ArrayList<Integer> minuend, ArrayList<Integer> subtrahend)
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

	BigInt multiply(BigInt other)
	{
		BigInt product;
		reverse(this.numberArray,other.numberArray,null);
		product = new BigInt(multiplyByCases(other,this.numberArray,other.numberArray));
		reverse(this.numberArray,other.numberArray,product.numberArray);
		return product;

	}

	private ArrayList<Integer> multiplyByCases(BigInt other,
	                                           ArrayList<Integer> multiplicand,
	                                           ArrayList<Integer> multiplier)
	{
		ArrayList<Integer> product = new ArrayList<>();
		if(multiplicand.size()==1 && multiplier.size()==1)
			product.add(multiplicand.get(0)*multiplier.get(0));
		else {
			if (multiplicand.size() < multiplier.size())
				product = actuallyMultiply(multiplier, multiplicand);
			else
				product = actuallyMultiply(multiplicand, multiplier);
			if(!(this.isCharged && other.isCharged)&&
					(this.isCharged || other.isCharged)){
				product = negate(product);
			}
		}
		return product;
	}

	private ArrayList<Integer> actuallyMultiply(ArrayList<Integer> multiplicand, ArrayList<Integer> multiplier)
	{
		int carry = 0, tempProduct;
		ArrayList<Integer> product = new ArrayList<>(), firstProduct = new ArrayList<>(),
				partialSum = new ArrayList<>();

		for(int i = 0 ; i < multiplier.size();i++) {
			if(i>0) firstProduct = new ArrayList<>(addZerosToTheFront(firstProduct,i));
			partialSum.clear();
			for (Integer multiplied : multiplicand) {
				tempProduct = Math.abs(multiplied * multiplier.get(i)) + carry;
				if (tempProduct >= 10) {
					carry = tempProduct / 10;
					firstProduct.add(tempProduct % 10);
				} else {
					firstProduct.add(tempProduct);
					carry = 0;
				}
			}
			if(carry!=0) firstProduct.add(carry); carry = 0;
			if(i==0) {
				product = new ArrayList<>(firstProduct);
				firstProduct.clear();
			} else {
				partialSum = new ArrayList<>(product);
				product.clear();
				product = new ArrayList<>(add(firstProduct, partialSum));
				firstProduct.clear();
			}
		}return product;
	}

	private ArrayList<Integer> addZerosToTheFront(ArrayList<Integer> firstProduct, int zeros)
	{
		for(int i = 0; i < zeros; i++)
			firstProduct.add(i,0);
		return firstProduct;
	}

	private void compareAndPadArrayList(ArrayList<Integer> firstArray, ArrayList<Integer> secondArray)
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

	boolean isEqualTo(BigInt other)
	{
		return this.numberArray.equals(other.numberArray);
	}

	int compareTo(BigInt other)
	{
		return this.isEqualTo(other) ? 0 : separateAndCompare(other);
	}
	private int separateAndCompare(BigInt other)
	{
		if(this.isCharged || other.isCharged) return handleNegativeCases(other);
		else if(this.numberArray.size()==other.numberArray.size())
			return compareEachNumber(other);
		else return compareBasedOnLength(other);
	}

	private int compareBasedOnLength(BigInt other)
	{
		return getLen(other) == 1 ? 1 : -1;
	}
	private int compareEachNumber(BigInt other)
	{
		ArrayList<Integer> first = new ArrayList<>(this.numberArray);
		ArrayList<Integer> second = new ArrayList<>(other.numberArray);
		int len = first.size();
		for(int i = 0; i < len ; i++) {
			if (first.get(i) > second.get(i)) return 1;
			else if(second.get(i) > first.get(i)) break;
		}return -1;
	}
	private int handleNegativeCases(BigInt other)
	{
		if(this.isCharged && !other.isCharged) return -1;
		else if(!this.isCharged && other.isCharged) return 1;
		else return compareEachNumber(other);
	}
	private int getLen(BigInt other)
	{
		return this.numberArray.size() > other.numberArray.size() ? 1 : -1;
	}
	private ArrayList<Integer> negate(ArrayList<Integer> numberArray)
	{
		ArrayList<Integer> negatedArray = new ArrayList<>();
		for (Integer aNumberArray : numberArray)
			negatedArray.add(aNumberArray * -1);
		return negatedArray;
	}

	private void checkForNegativeNumbers()
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
		checkForNegativeNumbers();
		if(isCharged) stringBuilder.append('-');

		for (Integer aNumberArray : this.numberArray)
			stringBuilder.append(Math.abs(aNumberArray));

		String finalString = stringBuilder.toString();

		return finalString.matches("^[0]+$") ?
				finalString.substring(0,1) : finalString.replaceFirst("^0*", "");
	}
}
