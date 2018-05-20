package RevisedBigInt;

import RevisedBigInt.Exceptions.InvalidInputException;
import java.lang.Math;
//import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;

/**
 *
 * This {@link BigInt} class allows for basic arithmetic on large integers.
 * Primitive data types such as int,double,float,long can hold only so much.
 *
 *
 *     <table summary = "Primitive Data Types" border = 1 cellpadding = "5">
 *         <tr>
 *             <th>
 *                 Type
 *             </th>
 *             <th>
 *                 Size
 *             </th>
 *             <th>
 *                 Range
 *             </th>
 *         </tr>
 *         <tr>
 *             <td>
 *                 Byte
 *             </td>
 *             <td>
 *                 16 Bits
 *             </td>
 *             <td>
 *                 -128 .. 127
 *             </td>
 *         </tr>
 *         <tr>
 *             <td>
 *                 short
 *             </td>
 *             <td>
 *                 16 bits
 *             </td>
 *             <td>
 *                 -32,768 .. 32,767
 *             </td>
 *         </tr>
 *         <tr>
 *             <td>
 *                 int
 *             </td>
 *             <td>
 *                 32 bits
 *             </td>
 *             <td>
 *                 -2,147,483,648 .. 2,147,483,647
 *             </td>
 *         </tr>
 *         <tr>
 *             <td>
 *                 long
 *             </td>
 *             <td>
 *                 64 bits
 *             </td>
 *             <td>
 *                 -9,223,372,036,854,775,808 .. 9,223,372,036,854,775,807
 *             </td>
 *         </tr>
 *         <tr>
 *             <td>
 *                 float
 *             </td>
 *             <td>
 *                 32 bits
 *             </td>
 *             <td>
 *                 3.40282347 x 1038, 1.40239846 x 10-45
 *             </td>
 *         </tr>
 *         <tr>
 *             <td>
 *                 double
 *             </td>
 *             <td>
 *                 64 bits
 *             </td>
 *             <td>
 *                 1.7976931348623157 x 10308, 4.9406564584124654 x 10-324
 *             </td>
 *         </tr>
 *     </table>
 *
 *
 *
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
public class BigInt implements BigIntInterface
{
	/**
	 * This private instance ArrayList {@code #numberArray} will hold the current
	 * arraylist that the BigInt object is referencing.
	 * It is not initialized to any size at the moment.
	 * I have opted not to use the ArrayList's minimum capacity feature.
	 */
	private ArrayList<Integer> numberArray = new ArrayList<>();

	/**
	 * A private instance of a boolean {@code #isCharged} will
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

	private BigInt()
	{
		//DEFAULT
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
		}
		return value;
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
		if(!value.matches("^[0-9]+$"))
			throw new InvalidInputException(value.concat(" ")
					.concat("Contains invalid Character"));
		return true;
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
	 * Returns the Absolute value of this BigInteger
	 *
	 * @return BigInt
	 */
	BigInt absValue()
	{
		return this.isCharged ?
				new BigInt(this.negate(this.numberArray)) : this;

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
	 * Then the sum is produced through helper method {@link #add(ArrayList, ArrayList)}  and stored in a temporary
	 * BigInt object.
	 *
	 * All of the ArrayLists are reversed to their original positions and the sum is returned through
	 * the {@link #toString()} method.
	 *
	 * @param other -  BigInt object representing the auguend
	 * @return the sum of this and other in an ArrayList.
	 */
	public BigInt add(BigInt other)
	{
		reverse(this.numberArray,other.numberArray,null);
		BigInt f = new BigInt(add(this.numberArray, other.numberArray));
		reverse(this.numberArray,other.numberArray,f.numberArray);
		return f;
	}

	/**
	 * The helper method that will compute the sum of two large integers.
	 *
	 * It uses a helper method {@link #padArrayList(ArrayList, ArrayList)}
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
		if(addend.size()!=auguend.size()) padArrayList(addend, auguend);

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
	public BigInt subtract(BigInt other)
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
	 *
	 *
	 *     <table summary = "" border = 1 cellpadding = "5">
	 *         <tr>
	 *             <th>
	 *                 Cases
	 *             </th>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 A.size = B.size || A - B
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 A - (-B) = A + B
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 -A - (-B) = -A + B = B - A
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 -A - B = -(A + B)
	 *             </td>
	 *         </tr>
	 *     </table>
	 *
	 *
	 * The cases will simplify the process by using addition to subtract
	 * Even though both of them are using the same algorithm the time spent is
	 * the same. Its better because addition is slightly faster.
	 * The {@link #compareTo(BigInt)} will compare the values then decided
	 * how to subtract; whether A-B or B-A.
	 * {@link #negate(ArrayList)} will change the value based on the sign of the values.
	 *
	 * @param other - Represents the second BigInt object to be subtracted
	 * @return - The difference as an ArrayList
	 */
	private ArrayList<Integer> subtractionByCases(BigInt other)
	{
		ArrayList<Integer> answer = new ArrayList<>();
		if(this.numberArray.size() == 1 && other.numberArray.size() == 1)
			answer.add(this.numberArray.get(0) - other.numberArray.get(0));
		else if(this.isCharged || other.isCharged)
			answer = new ArrayList<>(negativeSubtractionCases(other));
		else{
			answer = new ArrayList<>(positiveSubtractionCases(other));
		}
		return answer;
	}

	/**
	 * Private helper method that handles cases where both A and B
	 * are positive. If A is less than B then -(B - A) otherwise
	 * it does the normal subtraction.
	 * @param other - BigInt object represents the second value
	 * @return difference
	 */
	private ArrayList<Integer> positiveSubtractionCases(BigInt other)
	{
		reverse(this.numberArray,other.numberArray,null);
		boolean lessThan = this.isLessThan(other);
		reverse(this.numberArray,other.numberArray,null);

		return lessThan ? negate(subtractAlgo(other.numberArray, this.numberArray))
				: subtractAlgo(this.numberArray, other.numberArray);
	}

	/**
	 * Handles the cases whether the values are negative either -A || -B
	 *
	 * @param other BigInt Object represent the second value
	 * @return difference
	 */
	private ArrayList<Integer> negativeSubtractionCases(BigInt other)
	{
		if(!this.isCharged )  // A - (-B) = A + B
			return add(this.numberArray, negate(other.numberArray));
		else if( other.isCharged) {    // -A - (-B) = -A + B = B - A
			return negate(subtractAlgo(negate(this.numberArray), negate(other.numberArray)));
		} else   // -A - B = -(A + B)
			return negate(add(negate(this.numberArray), other.numberArray));
	}

	/**
	 * This algorithm is similar to the {@link #add(ArrayList, ArrayList)} method
	 * where the only difference is how this algorithm handles the borrowing of numbers.
	 *
	 * As with the add method, the two ArrayLists are compared and equalized their length
	 * so that the method does not throw an IndexOutOfRange error and NullPointerError.
	 *
	 * The difference is stored in an ArrayList that will be destroyed upon the end of this method.
	 * Nothing else is different
	 *
	 * @param minuend - The first Value
	 * @param subtrahend - The second Value
	 * @return The difference as an ArrayList
	 */
	private ArrayList<Integer> subtractAlgo(ArrayList<Integer> minuend, ArrayList<Integer> subtrahend)
	{
		padArrayList(minuend,subtrahend);
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
		}if(difference.get(difference.size()-1) == 0)
		difference.remove(difference.size()-1);

		return difference;
	}

	/**
	 * This method will multiply two arbitrarily large integers
	 * {x,y | x E Z, y E Z}.
	 * As with the other methods, It will reverse and store the result in
	 * another temporary BigInt object.
	 *
	 * This method goes into a helper method {@link #multiplyByCases(BigInt, ArrayList, ArrayList)}
	 * that will separate the values into cases and then multiply them together.
	 *
	 *
	 * @param other represent second object
	 * @return product as BigInt object
	 */
	public BigInt multiply(BigInt other)
	{
		BigInt product;
		reverse(this.numberArray,other.numberArray,null);
		product = new BigInt(multiplyByCases(other,this.numberArray,other.numberArray));
		reverse(this.numberArray,other.numberArray,product.numberArray);
		return product;

	}

	/**
	 * This method divides the two numbers in cases to easily multiply them
	 * and send the modified values to the method that actually multiplies the
	 * two numbers.
	 *
	 * Since two numbers can be of variable length, it's better to perform the operation
	 * that takes the least amount of time. In other words if one of the
	 * integers has less digits than the other, the larger integer is multiplied
	 * by the one with the less digits, saving time and memory.
	 *
	 * Identities are quickly handles without the need to actually be multiplied.
	 * A * 1 = A
	 * A * 0 = 0
	 *
	 * THe final product will be negated if and only if either of the values are negative
	 * but not both.
	 *
	 * @param other - Object that represents the multiplier
	 * @param multiplicand - ArrayList
	 * @param multiplier- ArrayList
	 * @return The product as an ArrayList
	 */
	private ArrayList<Integer> multiplyByCases(BigInt other,
	                                           ArrayList<Integer> multiplicand,
	                                           ArrayList<Integer> multiplier)
	{
		ArrayList<Integer> product = new ArrayList<>();
		if(multiplicand.size()==1 && multiplier.size()==1)
			product.add(multiplicand.get(0)*multiplier.get(0));
		else {
			product = multiplicand.size() < multiplier.size() ?
					actuallyMultiply(multiplier, multiplicand)
					:actuallyMultiply(multiplicand, multiplier);
			if(!(this.isCharged && other.isCharged)&&
					(this.isCharged || other.isCharged)){
				product = negate(product);
			}
		}
		return product;
	}

	/**
	 * The actual multiplication algorithm. Borrows a lot from the addition with some
	 * cosmetic changes.
	 * I had to use traditional multiplication method because all the other
	 * algorithms such as the Karatsuba Algorithm relied on the fact that
	 * the numbers were represented in a pure form and not in an arraylist where
	 * each individual digits were a seperate element.
	 *
	 * That hindered me so I had to use the traditional method.
	 * Which led to me having to deal with partial sums.
	 * Since the traditional methods requires all the individual products
	 * to be added to create the final product, I had to replicate that process.
	 * I used only two main ArrayList and a third temporary one that will only get created
	 * as a medium to temporary hold another arrayList.
	 *
	 * Since ArrayList have two levels of copying namely, shallow and deep.
	 * It was important to note that if we simply assign two arraylist as such as: array = array2
	 * then its copy by reference and whatever you do the originial arraylist the second one will be
	 * replicating that action.
	 * So we have to use a deep copy can entirely assign all the elements to a new array.
	 *
	 * @param multiplicand - First ArrayList
	 * @param multiplier - Second ArrayList
	 * @return the product as ArrayList
	 */
	private ArrayList<Integer> actuallyMultiply(ArrayList<Integer> multiplicand, ArrayList<Integer> multiplier)
	{
		int carry = 0, tempProduct;
		ArrayList<Integer> product = new ArrayList<>(), firstProduct = new ArrayList<>(),
				partialSum = new ArrayList<>();

		for(int i = 0 ; i < multiplier.size();i++) {
			if(i>0) firstProduct = new ArrayList<>(addZerosToTheFrontOfPartialSum(firstProduct,i));
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

	/**
	 * This method simply adds zeros to the partial sum
	 * It will add zeros to the beginning(since the arraylist being passed
	 * is reversed).
	 *
	 * @param firstProduct - ArrayList to be padded
	 * @param zeros - The number of zeros to be added
	 * @return - Padded ArrayList
	 */
	private ArrayList<Integer> addZerosToTheFrontOfPartialSum(ArrayList<Integer> firstProduct, int zeros)
	{
		for(int i = 0; i < zeros; i++)
			firstProduct.add(i,0);
		return firstProduct;
	}


	/**
	 * Divides two arbritarily large BigInt object (this/other)
	 * Since this is not an integer division, the whole process
	 * become a bit complicated because you essentially have
	 * code so that the computer knows how to divided.
	 *
	 * The algorithm is identical to the traditional
	 * long division approach because that is the most sensible
	 * way to tackle this problem at the moment.
	 *
	 * A number of helper methods are used to complete this process
	 * @param other BigInt object - divisor
	 * @return this/other
	 */
	public BigInt divideBy(BigInt other)
	{
		BigInt quotient;
		if(this.isEqualTo(other)) quotient = new BigInt("1");
		else if(this.isLessThan(other))
			quotient = new BigInt("0");
		else
			quotient = new BigInt(divideAlgo(this.numberArray,other.numberArray));
		return quotient;
	}

	/**
	 * The division algorithm
	 * This algorithm emmulates the traditional long division approach.
	 * Since the original
	 *
	 * @param dividend - Dividend
	 * @param divisor - Divisor
	 * @return quotient - ArrayList
	 */
	private ArrayList<Integer> divideAlgo(ArrayList<Integer> dividend, ArrayList<Integer> divisor)
	{
		ArrayList<Integer> quotient = new ArrayList<>();
		ArrayList<Integer>  currDividend, tempDivd, product;
		int lenDivisor = divisor.size();
		int currIndexOrgDividend = 0;

		currDividend = new ArrayList<>(findDividend(divisor,dividend,lenDivisor));

		currIndexOrgDividend = currDividend.size()-1;

		while(currIndexOrgDividend < dividend.size()){
			product = new ArrayList<>(findQuoMult(currDividend,divisor));
			quotient.add(product.get(product.size()-1));
			if(product.size()>1) product.remove(product.size() -1);
			tempDivd = new ArrayList<>(subtractAlgo(currDividend,product));
			removeLdZeroDiv(tempDivd);
			currIndexOrgDividend++;
			currDividend = new ArrayList<>(tempDivd);
			if(currIndexOrgDividend < dividend.size())
				currDividend.add(dividend.get(currIndexOrgDividend));
		}
		return quotient;
	}

	/**
	 * Removes lading zeros that often results in after subtraction
	 * Mainly used in the divison process.
	 * @param tempDivd ArrayList without leading zeros
	 */
	private void removeLdZeroDiv(ArrayList<Integer> tempDivd) {
		int index = 0;
		int val = tempDivd.get(index);
		while(val == 0){
			tempDivd.remove(index);
			val = tempDivd.get(index);
		}
	}

	/**
	 *
	 * @param divisor
	 * @param dividend
	 * @param divisorLen
	 * @return
	 */
	private ArrayList<Integer> findDividend(ArrayList<Integer> divisor, ArrayList<Integer> dividend,
	                                        int divisorLen)
	{
		ArrayList<Integer> tempDividend = new ArrayList<>(dividend.subList(0,divisorLen));
		int result = 1;
		while(result == 1){
			result = isLessThan(divisor,tempDividend);
			if(result == 1){
				tempDividend = new ArrayList<>(dividend.subList(0,divisorLen + 1));
			}
		}
		return tempDividend;
	}

	/**
	 *
	 * @param divn
	 * @param divs
	 * @return
	 */
	private ArrayList<Integer> findQuoMult(ArrayList<Integer> divn, ArrayList<Integer> divs)
	{
		ArrayList<Integer> multiplier = new ArrayList<>(),
				product = new ArrayList<>();
		int result = 1, index = 0;
		if(isLessThan(divn,divs) == -1){
			product.add(0);
		}else {
			while (result == 1) {
				multiplier.clear();
				multiplier.add(getArrayValForMultiply(index));
				product = new ArrayList<>(actuallyMultiply(multiplier, divs));
				result = isLessThan(divn, product);
				index++;
				if (result == -1) {
					index -= 2;
					multiplier.clear();
					multiplier.add(getArrayValForMultiply(index));
					product = new ArrayList<>(actuallyMultiply(multiplier, divs));
				}
			}
			product.add(index + 1);
		}
		return product;
	}

	/**
	 * Since I implemented a division by multiplication algo for
	 * {@link #divideBy(BigInt)} I had to multiply the divisor
	 * by numbers 1-9 to get the right multiple.
	 * This method gets the value stored at the given index
	 *
	 * @param index  index of desired element
	 * @return 1 - 9 both inclusive
	 */
	private int getArrayValForMultiply(int index)
	{
		ArrayList<Integer> val =  new ArrayList<>(9);
		val.add(1); val.add(2); val.add(3); val.add(4); val.add(5);
		val.add(6); val.add(7); val.add(8); val.add(9);
		return val.get(index);
	}


	/**
	 * This method is the one tha compares the length of the ArrayList.
	 * The helper method {@link #addZerosToTheFrontOfPartialSum(ArrayList, int)}
	 * will actually add zeros.
	 * What this method does is that is finds the difference in length and
	 * passes that value along with the arraylist that needs to be padded.
	 * @param firstArray - ArrayList
	 * @param secondArray - ArrayList
	 */
	private void padArrayList(ArrayList<Integer> firstArray, ArrayList<Integer> secondArray)
	{
		int second = secondArray.size(), first = firstArray.size();
		if (firstArray.size() < secondArray.size()) {
			padWithZeros(firstArray, second - first);
		} else padWithZeros(secondArray, first - second);
	}

	/**
	 * This method will pad the arraylist that is not equal in length
	 * to the other arraylist.
	 * @param array - Array to be padded
	 * @param numberOfZeros - self explanatory
	 */
	private void padWithZeros(ArrayList<Integer> array, int numberOfZeros)
	{
		int lastPos = array.size();
		for (int i = 0; i < numberOfZeros; i++)
			array.add(lastPos, 0);
	}

	/**
	 * Returns true is {@code this < other}
	 * @param other BigInt object
	 * @return boolean
	 */
	private boolean isLessThan(BigInt other)
	{
		return this.compareTo(other) == -1;
	}

	private int isLessThan(ArrayList<Integer> first, ArrayList<Integer> second)
	{
		return first.size() != second.size() ? compareBasedOnLengthArrayList(first,second)
				: compareEachNumberForLoop(first,second);
	}

	/**
	 * Returns true if both objects are equal to each other
	 * @param other - BigInt object
	 * @return boolean
	 */
	boolean isEqualTo(BigInt other)
	{
		return this.numberArray.equals(other.numberArray);
	}

	/**
	 * Return maximum of this or other BigInt if
	 * {@code this > other} or {@code other > this}
	 * @param other - BigInt to be compared
	 * @return the larger BigInt Object
	 */
	BigInt max(BigInt other)
	{
		return this.isLessThan(other) ? other : this;
	}

	/**
	 * Return the minimum of this or other
	 *
	 * @param other - Value to be compared with
	 * @return the minimum BigInt
	 */
	BigInt min(BigInt other)
	{
		return this.isLessThan(other) ? this : other;
	}

	/**
	 * Compares the two ArrayList if {@link #isEqualTo(BigInt)} is false.
	 * Relies on a number of helper methods to compare since dividing into cases
	 * makes things easier.
	 *
	 * I decided to use ternary operator just for kicks.
	 * @param other - BigInt object
	 * @return int value: 0,1,-1
	 */
	int compareTo(BigInt other)
	{
		return this.isEqualTo(other) ? 0 : separatePosNegCompare(other);
	}

	/**
	 * This is helper method will separate the two values into different cases.
	 * Separate into negative cases; which will be taken into a different method.
	 * If the length are equal then it will call {@link #compareEachNumber(BigInt)}
	 * which will return the larger arraylist
	 *
	 * @param other - BigInt object
	 * @return int value 1,-1
	 */
	private int separatePosNegCompare(BigInt other)
	{
		if(this.isCharged || other.isCharged) return handleNegativeCases(other);
		else if(this.numberArray.size()==other.numberArray.size())
			return compareEachNumber(other);
		else return compareBasedOnLength(other);
	}

	/**
	 * This method will only be used if both arrays are positive.
	 * It will simply return an int value based on which arraylist is longer
	 * @param other BigInt object
	 * @return int value 1,-1
	 */
	private int compareBasedOnLength(BigInt other)
	{
		return getLen(other) == 1 ? 1 : -1;
	}

	/**
	 * This method takes ArrayList as a paramenter and is identical to
	 * {@link #compareBasedOnLength(BigInt)}
	 * @param first -ArrayList
	 * @param second -ArrayList
	 * @return 1 or -1
	 */
	private int compareBasedOnLengthArrayList(ArrayList first, ArrayList second)
	{
		return first.size()>second.size()? 1 : -1;
	}

	/**
	 * This method will be called if both arraylists are equal in length.
	 * It will return an int value as soon as the for loop discovers
	 * if  {@code a[i] < b[i]} or {@code a[i] > b[i]}.
	 * @param other BigInt object
	 * @return int 1,-1
	 */
	private int compareEachNumber(BigInt other)
	{
		return compareEachNumberForLoop(this.numberArray,other.numberArray);
	}

	private int compareEachNumberForLoop(ArrayList<Integer> first, ArrayList<Integer> second)
	{
		int len = first.size();
		for(int i = 0; i < len ; i++) {
			if (first.get(i) > second.get(i)) return 1;
			else if(second.get(i) > first.get(i)) break;
		}return -1;
	}
	/**
	 * This method handles comparison when either of the arraylist are negative.
	 * {@code -A < B}
	 * {@code A > -B}
	 *
	 * -A and -B then {@link #compareEachNumber(BigInt)} will be called.
	 *
	 * @param other BigInt object
	 * @return int 1,-1
	 */
	private int handleNegativeCases(BigInt other)
	{
		int lenResult = this.getLen(other);
		if(lenResult != 0) {
			if (this.isCharged && !other.isCharged) return -1;
			else if (!this.isCharged && other.isCharged) return 1;
			return (this.isCharged && lenResult == 1) ? -1 : 1;
		}else return compareEachNumber(other);
	}

	/**
	 * Return the int value indicating which arraylist is longer
	 * 1 if the first one
	 * -1 if the second one
	 * @param other BigInt object
	 * @return int 1,-1
	 */
	private int getLen(BigInt other)
	{
		if(this.numberArray.size() == other.numberArray.size()) return 0;
		return this.numberArray.size() > other.numberArray.size() ? 1 : -1;
	}

	/**
	 * This method will negate the Arraylist's each element so that operations
	 * are done properly.
	 * @param numberArray - Arraylist to be negated
	 * @return The negated arraylist
	 */
	private ArrayList<Integer> negate(ArrayList<Integer> numberArray)
	{
		ArrayList<Integer> negatedArray = new ArrayList<>();
		for (Integer aNumberArray : numberArray)
			negatedArray.add(aNumberArray * -1);
		return negatedArray;
	}

	/**
	 * This is a redundant method that will determine if there needs to be
	 * a negative sign on the final string representation of the BigInt object
	 * when being printed.
	 * It does not need to go through the entire array the worst case is that there
	 * are zeros in the begining; which really does not happen.
	 * 99% of the cases will only need to check the first value.
	 */
	private void checkForNegativeNumbers()
	{
		for (Integer aNumberArray : this.numberArray) {
			if (aNumberArray < 0) {
				isCharged = true;
				break;
			}
		}
	}

	/**
	 * Overrides the Object class's toString method and prints the string of BigInt.
	 * Uses Stringbuilder class rather than the standard + sign to concat the strings.
	 * It does a final check for negative numbers. That method should only go past the
	 * first element if that first element happens to be 0 otherwise 99% of the time
	 * it will only have to check the first element for a negative value.
	 * I decided to use regex to check if there were any leading zeros in the arraylist
	 * due to subtraction or addition
	 * @return String representation of the object
	 */
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
