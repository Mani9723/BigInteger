package RevisedBigInt;

import RevisedBigInt.Exceptions.InvalidInputException;
import RevisedBigInt.Exceptions.DivideByZeroException;
import RevisedBigInt.Exceptions.NegativeExponentException;
import RevisedBigInt.FileReader.BigIntFileReader;

import java.io.File;
import java.lang.Math;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 *
 * This {@link BigInt} class allows for basic arithmetic on large integers.
 * Primitive data types such as int,double,float,long can hold only so much.
 *
 *
 *     <table summary = "Primitive Data Types" border = 1 cellpadding = "5">
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
 * {@link #multiply(BigInt)}multiplication, division2, and modulus.
 * There are a couple of comparison option such as {@link #isEqualTo(BigInt)} and {@link #compareTo(BigInt)}
 *
 * Overall, this class has been tested thoroughly using the JUnit4 Testing framework
 * on all methods with 100% coverage.
 *
 * @author Mani Shah
 * @version 2.1
 * @since 4/4/18
 */

//Hare Krsna
public class BigInt implements BigIntInterface
{

	/**
	 * This private instance ArrayList {@code #list} will hold the current
	 * arraylist that the BigInt object is referencing.
	 * It is not initialized to any size at the moment.
	 * I have opted not to use the ArrayList's minimum capacity feature.
	 */
	private ArrayList<Integer> list = new ArrayList<>();

	/**
	 * A private instance of a boolean {@code #isCharged} will
	 * hold the charge of the integer.
	 * This variable is the deciding factor in the arthemtic sitautions that this class
	 * handles.
	 */
	private boolean isCharged = false;

	/**
	 * String variable that holds the string representation of the BigInteger
	 * Useful when methods require string inputs since changing an arraylist 
	 * to string is time consuming.
	 */
	private String bigIntString;

	/**
	 * The Packacge Private main constructor accepts a string argument.
	 * Since a string can be of arbritary length, only the memory of
	 * the machine is the limiting factor.
	 * This constructor initializes the main arraylist {@link #list}
	 * and handles the situations where the user enters bad data.
	 *
	 * In the event of an exception, the constructor will catch
	 * the {@link InvalidInputException} and print a nice stackTrace
	 * and display the problematic spots in that particular execution of the code.
	 * It will then exit the program.
	 *
	 * Otherwise the program will continue.
	 *
	 * @param val - The input provided by the user.
	 */
	BigInt(String val)
	{
		this.bigIntString = verifySignAndMinLength(val);

		try {
			if (isInputValid(this.bigIntString))
				storeInArrayList(this.bigIntString);
		} catch (InvalidInputException e) {
			e.getMessage();
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * This constructor will take a File object as a parameter and retrieve the path
	 * of that object. Once the path has been verified, we can proceed normally to
	 * store the contents of the file into {@link #list}. {@link BigIntFileReader} class
	 * is used to read the contents of the file and store it in a string. That string
	 * is then returned through the toString method. The resulting string will then be
	 * converted into the proper arraylist.
	 * @param file - File Object representing the file to be read.
	 */
	BigInt(File file)
	{
		String path = file.getPath();
		BigIntFileReader fileContents = new BigIntFileReader(path);
		String number = fileContents.getContents();
		try {
			if(isInputValid(number))
				storeInArrayList(number);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Private constructor that acts as an lightweight constructor
	 * whose only purpose is to hold temporary array List until the operation
	 * on that arrayList has been created.
	 *
	 * @param numberArray - ArrayList that temporarily creates a BigInt object to be manipulated.
	 */
	private BigInt(ArrayList<Integer> numberArray)
	{
		this.list = numberArray;
	}

	/**
	 * Private method thatbypassess all checks because the input is valid
	 * and stores the string in an array list
	 * @param arrayList - string
	 * @param isValid - boolean
	 */
	@SuppressWarnings("unused")
	private BigInt(String arrayList, boolean isValid)
	{
		if(isValid){
			storeInArrayList(arrayList);
		}
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
			throw new InvalidInputException("Contains invalid Character");
		return true;
	}

	/**
	 * Sets the instance variable {@link #isCharged} if the given value is negative
	 * @param signValue - the first character of the given string.
	 */
	private void setSign(char signValue)
	{
		if (signValue == '-')
			this.isCharged = true;
	}

	/**
	 * Store the final approved String in the {@link #list}
	 * If negative the elements will be negated during parsing.
	 * @param value - String to be stored in the ArrayList
	 */
	private void storeInArrayList(String value)
	{
		int len = value.length();
		if (isCharged) {
			for (int i = 0; i < len; i++)
				this.list.add(Integer.parseInt(value.substring(i, i + 1)) * -1);
		} else {
			for (int i = 0; i < len; i++)
				this.list.add(Integer.parseInt(value.substring(i, i + 1)));
		}
	}

	/**
	 * Uses the {@link Collections} class's reverse method to reverse the ArrayLists
	 * It takes variable arguments of ArrayLists.
	 * @param numberArray - n ArrayLists to be reversed
	 */
	@SafeVarargs
	private final void reverse(ArrayList<Integer>...numberArray)
	{
		for(ArrayList arrayList : numberArray)
			Collections.reverse(arrayList);
	}

	/**
	 * Returns the Absolute value of this BigInteger
	 *
	 * @return BigInt
	 */
	BigInt absValue()
	{
		return this.isCharged ? new BigInt(this.negate(this.list)) : this;
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
		reverse(this.list,other.list);
		BigInt f = new BigInt(add(this.list, other.list));
		reverse(this.list,other.list,f.list);
		if(f.list.get(0)<0)
			f.isCharged = true;
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
				tempSum %=10;
			} else
				carry = 0;
			sum.add(tempSum);
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
		reverse(this.list,other.list);
		difference = new BigInt(subtractionByCases(other));
		reverse(this.list,other.list,difference.list);
		if(difference.list.get(0) < 0) difference.isCharged = true;
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
		if(this.list.size() == 1 && other.list.size() == 1)
			answer.add(this.list.get(0) - other.list.get(0));
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
		reverse(this.list,other.list);
		boolean lessThan = this.isLessThan(other);
		reverse(this.list,other.list);

		return lessThan ? negate(subtractAlgo(other.list, this.list))
				: subtractAlgo(this.list, other.list);
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
			return add(this.list, negate(other.list));
		else if( other.isCharged) {    // -A - (-B) = -A + B = B - A
			return negate(subtractAlgo(negate(this.list), negate(other.list)));
		} else   // -A - B = -(A + B)
			return negate(add(negate(this.list), other.list));
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
		int tempDiff, borrow = 0;

		for (int i = 0; i < minuend.size(); i++) {
			tempDiff = (minuend.get(i) - borrow) - subtrahend.get(i);
			if (tempDiff < 0) {
				tempDiff +=10;
				borrow = 1;
			} else {
				borrow = 0;
			}
			difference.add(tempDiff);
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
		product = mulCases(other);
		makeAbs(other);
		return product;
	}

	/**
	 * Divides the Multplication into cases.
	 * If it's single digits it just multiplies the two numbers and
	 * returns the result.
	 *
	 * Otherwise it uses Karatsuba multiplication algorithm to multiply the numbers.
	 * Sets the {@link #isCharged} to true so that the negative sign can be tacked
	 * in the final string representation of the object.
	 *
	 * @param other - Multiplicand object
	 * @return BigInt product
	 */
	private BigInt mulCases(BigInt other)
	{
		String product;
		if(this.list.size() == 1 && other.list.size() == 1)
			return new BigInt(Integer.toString(this.list.get(0)*other.list.get(0)));
		else
			product = karatsubaMultiplication(other);
		if(isOneNegative(other))
			return new BigInt("-" + product);
		return new BigInt(product);
	}

	/**
	 * First changes to absolute value.
	 *
	 * Sends to {@link #sendToKaratsuba(Karatsuba, String, String)}
	 * to calculate the product.
	 * @param other - BigInt Object
	 * @return product
	 */
	private String karatsubaMultiplication(BigInt other)
	{
		makeAbs(other);
		Karatsuba karatsuba = new Karatsuba();
		return sendToKaratsuba(karatsuba, this.bigIntString,other.bigIntString);
	}

	/**
	 * Uses the Karatsuba class to calculate the product
	 *
	 * If the string has the negative '-' character in the front, only the string
	 * after the sign is taken into the method.
	 *
	 * Everything is in base 10
	 *
	 * @param karatsuba - Karatsuba object
	 * @param s - Multiplicand
	 * @param s1 - Multiplier
	 * @return product
	 */
	private String sendToKaratsuba(Karatsuba karatsuba, String s, String s1)
	{
		String modifS = s.substring(1,s.length()), modifS1 = s1.substring(1,s1.length());
		if(s.charAt(0) == '-' && s1.charAt(0) == '-')
			return karatsuba.multiply(modifS,modifS1,10);
		else if(s.charAt(0) == '-')
			return karatsuba.multiply(modifS,s1,10);
		else if(s1.charAt(0)== '-')
			return karatsuba.multiply(s,modifS1,10);
		else return karatsuba.multiply(s,s1,10);
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
	@SuppressWarnings("unused")
	private ArrayList<Integer> multiplyByCases(BigInt other,
	                                           ArrayList<Integer> multiplicand,
	                                           ArrayList<Integer> multiplier)
	{
		ArrayList<Integer> product = new ArrayList<>();
		if(multiplicand.size()==1 && multiplier.size()==1)
			product.add(multiplicand.get(0)*multiplier.get(0));
		else {
			product = multiplicand.size() < multiplier.size() ?
					multiplyNaive(multiplier, multiplicand)
					: multiplyNaive(multiplicand, multiplier);
			if(!(this.isCharged && other.isCharged)&&
					(this.isCharged || other.isCharged)){
				product = negate(product);
			}
		}
		return product;
	}

	/**
	 * Multiplication algorithm
	 *
	 * Borrows a lot from the addition with some
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
	//@Deprecated
	private ArrayList<Integer> multiplyNaive(ArrayList<Integer> multiplicand, ArrayList<Integer> multiplier)
	{
		int carry = 0, tempProduct;
		ArrayList<Integer> product = new ArrayList<>(),firstProduct = new ArrayList<>(),
				partialSum = new ArrayList<>();
		for (int i = 0; i < multiplier.size(); i++) {
			if (i > 0) firstProduct = new ArrayList<>(addZerosToPartialSum(firstProduct, i));
			partialSum.clear();
			for (Integer currValue : multiplicand) {
				tempProduct = Math.abs(currValue * multiplier.get(i)) + carry;
				if (tempProduct >= 10) {
					carry = tempProduct / 10;
					firstProduct.add(tempProduct % 10);
				} else {
					firstProduct.add(tempProduct);
					carry = 0;
				}
			}
			if (carry != 0) firstProduct.add(carry);
			carry = 0;
			if (i == 0) {
				product = new ArrayList<>(firstProduct);
				firstProduct.clear();
			} else {
				partialSum = new ArrayList<>(product);
				product.clear();
				product = new ArrayList<>(add(firstProduct, partialSum));
				firstProduct.clear();
			}
		}
		return product;
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
	private ArrayList<Integer> addZerosToPartialSum(ArrayList<Integer> firstProduct, int zeros)
	{
		for(int i = 0; i < zeros; i++)
			firstProduct.add(i,0);
		return firstProduct;
	}

	/**
	 * Special Case for single digit multliplication that was only written to help
	 * the division method.
	 * This method is used to find the product that will ultimately
	 * be store in a LinkedHashMap.
	 * @param first - multiplicand
	 * @param second - multiplier
	 * @return the product as an arraylist
	 */
	private ArrayList<Integer> singleDigitMultCase(ArrayList<Integer> first, ArrayList<Integer> second)
	{
		int product = (first.get(0) * second.get(0));
		ArrayList<Integer> newProd = new ArrayList<>(2);
		if(product >= 10){
			newProd.add(product/10);
			newProd.add(product%10);
		} else newProd.add(product);
		Collections.reverse(newProd);
		return newProd;

	}

	/**
	 * Divides two arbritarily large BigInt object (this/other)
	 * Since this is not an integer division, the whole process
	 * become a bit complicated because you essentially have
	 * code so that the computer knows how to divided
	 * The algorithm is identical to the traditional
	 * long division approach because that is the most sensible
	 * way to tackle this problem at the moment.
	 * A number of helper methods are used to complete this process
	 *
	 * This takes case of cases where no division is required
	 * such as a/1 == a, if {@code a < b} == 0 since its integer division.
	 *
	 * Calls {@link #handleDivCases(BigInt,boolean)} that handles all cases
	 *
	 * @param other BigInt object - divisor
	 * @return this/other
	 */
	public BigInt divideBy(BigInt other)
	{
		validateDivisor(other);
		BigInt quotient = handleDivCases(other,false);
		if(isOneNegative(other) && (quotient.list.get(0) != 0))
			quotient.isCharged = true;
		return quotient;
	}

	/**
	 * Modulo operation.
	 * Will perform this%other.
	 * Uses the division algorithm to find the modulo.
	 * @param other - BigInt
	 * @return this%mod as BigInt
	 */
	public BigInt mod(BigInt other)
	{
		validateDivisor(other);
		BigInt mod = handleDivCases(other,true);
		if(isOneNegative(other)) {
			mod.isCharged = true;
		}
		return mod;
	}

	/**
	 * Checks whether the divisor value is 0.
	 * If this test passes, the division process
	 * moves forward.
	 * Handles the situation is a try-catch block.
	 * It will exit the program after printing the
	 * message and the stacktrace.
	 *
	 * @param other - Object to be test for 0
	 */
	private void validateDivisor(BigInt other)
	{
		try{
			checkDivisor(other);
		}catch (DivideByZeroException e){
			e.getMessage();
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Actually checks the arraylist if it only contains
	 * 0 value. If so, then it will throw a {@link DivideByZeroException}
	 * with a custom message.
	 * @param other - Object being tested
	 * @throws DivideByZeroException - Exception that handle zero case
	 */
	private void checkDivisor(BigInt other) throws DivideByZeroException
	{
		if(other.list.size()==1 && other.list.get(0) == 0)
			throw new DivideByZeroException("Cannot Divide By Zero "+ other.toString());
	}

	/**
	 * The goal is to handle cases where the dividend is less than the divisor.
	 * Since this is integer division, the result will be 0 so this will
	 * first negate any negative arraylists then compare the two arrayLists.
	 * If the this.list is less than other.list then the return
	 * String will be 0. That result will be passed through the {@link #BigInt(String)}
	 * constructor that accepts string arguments.
	 *
	 * Handles division cases where either or both (this or other) is negative
	 * For example:
	 * <table summary = "Cases" border = "1" cellPadding = "1">
	 *     <tr>
	 *         <td>a/-b</td>
	 *         <td>==</td>
	 *         <td>-c</td>
	 *     </tr>
	 *     <tr>
	 *         <td>-a/b</td>
	 *         <td>==</td>
	 *         <td>-c</td>
	 *     </tr>
	 *     <tr>
	 *         <td>-a/-b</td>
	 *         <td>==</td>
	 *</table>
	 * @param other - BigInt divisor
	 * @param mod - true if mod value was requested
	 * @return BigInt Object representing quotient
	 */
	@SuppressWarnings("EqualsBetweenInconvertibleTypes")
	private BigInt handleDivCases(BigInt other, boolean mod)
	{
		makeAbs(other);
		if(this.equals(0)) return new BigInt("0");
		else if(this.list.equals(other.list))
			return mod ? new BigInt("0") : new BigInt("1");
		else if(isLessThan(this.list,other.list) == -1)
			return mod ? new BigInt(this.list) : new BigInt("0");
		else
			return new BigInt(divideAlgo(this.list,other.list,mod));
	}

	/**
	 * Void method that sets the object to its
	 * Absolute value format.
	 * @param other BigInt object
	 */
	private void makeAbs(BigInt other)
	{
		if(this.isCharged)
			this.list = new ArrayList<>(negate(this.list));
		if(other.isCharged)
			other.list = new ArrayList<>(negate(other.list));
	}
//	/**
//	 * The goal is to handle cases where the dividend is less than the divisor.
//	 * Since this is integer division, the result will be 0 so this will
//	 * first negate any negative arraylists then compare the two arrayLists.
//	 * If the this.list is less than other.list then the return
//	 * String will be 0. That result will be passed through the {@link #BigInt(String)}
//	 * constructor that accepts string arguments.
//	 *
//	 * @param other - BigInt object
//	 * @return - "0" if true or "-1" if false
//	 */
//	private String handleZeroResult(BigInt other)
//	{
//		return other.toString();
//	}

//	/**
//	 * Handles division cases where either or both (this or other) is negative
//	 * For example:
//	 * <table border = "1" cellPadding = "1">
//	 *     <tr>
//	 *         <td>a/-b</td>
//	 *         <td>==</td>
//	 *         <td>-c</td>
//	 *     </tr>
//	 *     <tr>
//	 *         <td>-a/b</td>
//	 *         <td>==</td>
//	 *         <td>-c</td>
//	 *     </tr>
//	 *     <tr>
//	 *         <td>-a/-b</td>
//	 *         <td>==</td>
//	 *         <td>c</td>
//	 *     </tr>
//	 * </table>
//	 * @param other - BigInt other
//	 * @return +-a/+-b as an {@code ArrayList<Integer>}
//	 */
//	private ArrayList<Integer> handleNegDivCases(BigInt other)
//	{
//		return new ArrayList<>();
//	}

	/**
	 * The division algorithm.
	 *
	 * This algorithm replicates the long division proccess.
	 * My original intent was to divide by subtracting recursively.
	 * However, I ran into the problem of not being able to keep track of the
	 * number of times the recursive step was taken, which would have been the
	 * quotient and the remaining difference would have been the modulus value.
	 * And it would have horribly slow.
	 *
	 * So I used the long division approach.
	 * When doing long division, first the dividend has to be equal to or greater than
	 * the divisor. That process is handled only at the beginning by a separate method
	 * {@link #findDividend(ArrayList, ArrayList)}. After that you try to find
	 * the multiple that is equal to or less than the current dividend then get the
	 * difference. Then next number in  the dividend is brought "down" and becomes the
	 * value in the ones place. and that becomes the new dividend.
	 *
	 * That process is repeated until the end of the originial dividend is out of numbers.
	 *
	 * The obvious problem and the most time consuming is the part where you have
	 * to find the the multiple of the divisor that is less than or equal to the
	 * current dividend. Now, the only options for the quotient at each turn are
	 * 0-9 both inclusive.So originally you would have to multiply by 1,2,3...
	 * until the desired multiple is found. To keep repeating that process is a waste of computing power.
	 *
	 * The solution to that problem is to realize a key fact. Each time you are multiplying
	 * the divisor by 1-9 you get the same 9 set of values that you will be comparing the
	 * dividend with. So why not do it dynamically where those nine values are stored in
	 * some kind of table and all that needs to be done afterwards is to go through the table
	 * and see which value is the proper value.
	 *
	 * Another problem arises. What data structure to use for this table. One thing to keep
	 * in mind is that the numbers 0-9 represents the quotient of that particular dividend.
	 * So that number is necessary because its the answer. This fact actually narrows the
	 * choices for the correct data structure significantly because now the values are in the
	 * form of (quotient, multiple) or (key,value). That Observation limits the data
	 * strucures in the Map/Table area. Order is important so {@link LinkedHashMap} data structure
	 * was used to keep track of the quotient and the multiples.
	 *
	 * LinkedHashMap keeps the insertion order which is important because that makes
	 * the lookUp process have a constant time. Best case O(1), worst case O(9).
	 *
	 * So the only remaining part that is left is the rest of the process.
	 * Once the quotient is found that value is added to the arraylist that represents
	 * the quotient.
	 * The current dividend - product(divisor*quotient) == new dividend. THe next number is
	 * brought "down" and added at the end of the new dividend.
	 * Repeated until the original dividend is out of numbers.
	 *
	 * @param dividend - Dividend
	 * @param divisor - Divisor
	 * @param mod - true if modulus was requested
	 * @return quotient - ArrayList
	 */
	private ArrayList<Integer> divideAlgo(ArrayList<Integer> dividend, ArrayList<Integer> divisor,
	                                      boolean mod)
	{
		LinkedHashMap<Integer,ArrayList<Integer>> table = new LinkedHashMap<>(storeProductLHMap(divisor));

		ArrayList<Integer> quotient = new ArrayList<>(),currDividend, tempDivd, product;

		currDividend = new ArrayList<>(findDividend(divisor,dividend));
		int currIndexOrgDividend = currDividend.size()-1;

		while(currIndexOrgDividend < dividend.size()){
			product = new ArrayList<>(findQuotient(table,currDividend));
			quotient.add(product.get(product.size()-1));
			if(product.size()>1) product.remove(product.size() -1);
			tempDivd = new ArrayList<>(getNewDividend(currDividend,product));
			if(tempDivd.size()>1) removeLdZeroDiv(tempDivd);
			currIndexOrgDividend++;
			currDividend = new ArrayList<>(tempDivd);
			if(currIndexOrgDividend < dividend.size()) {
				if (currDividend.size()==1 && currDividend.get(0) == 0)
					currDividend.remove(0);
				currDividend.add(dividend.get(currIndexOrgDividend));
			}
		}
		return mod ? currDividend : quotient;
	}

	/**
	 * When you subtract a product from the partial dividend you get your new
	 * dividend. This method replicates that process.
	 * @param curr - Var Args of ArrayLists
	 * @return Current - Product of Divisor and quotient
	 */
	@SafeVarargs
	private final ArrayList<Integer> getNewDividend(ArrayList<Integer>... curr)
	{
		reverse(curr[0],curr[1]);
		ArrayList<Integer> newDiv = new ArrayList<>(subtractAlgo(curr[0],curr[1]));
		reverse(curr[0],curr[1],newDiv);
		return newDiv;
	}

	/**
	 * Is only called once during the begining of the division process.
	 * To make the process quicker, the search for the dividend starts with
	 * the first trial value of the dividend the same length as the divisor.
	 * This will guarantee that at most only two repetition will be needed.
	 *
	 * @param divisor - fixed arraylist
	 * @param dividend - flexible, values will be from the original dividend
	 *                    added until right one found
	 * @return the proper divided such that {@code dividend >= divisor}
	 */
	private ArrayList<Integer> findDividend(ArrayList<Integer> divisor, ArrayList<Integer> dividend)
	{
		int divisorLen = divisor.size();
		ArrayList<Integer> tempDividend = new ArrayList<>(dividend.subList(0,divisorLen));
		return isLessThan(divisor,tempDividend)==1
				? new ArrayList<>(dividend.subList(0,divisorLen+1))
				: tempDividend;
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
	 * The lookUp method that goes through the LinkedHashMap.
	 * The index represents the quotient and the ArrayList represents
	 * the product of the divisor and the index.
	 * @param divn - Dividend
	 * @param table - LinkedHashMap Table that stores the possible quotients
	 * @return ArrayList that represents product and the last value represents quotient.
	 */
	private ArrayList<Integer> findQuotient(LinkedHashMap<Integer,ArrayList<Integer>> table,
	                                        ArrayList<Integer> divn)
	{
		ArrayList<Integer> product = new ArrayList<>();
		int index = 1, result = 1;
		if(isLessThan(divn,table.get(1))==-1)
			product.add(0);
		else {
			while (result == 1 && index < 10) {
				result = isLessThan(divn, table.get(index));
				if (result == 1) ++index;
			}
			if (result == -1 || index == 10) --index;
			product = new ArrayList<>(table.get(index));
			product.add(index);
		}
		return product;
	}

	/**
	 * Creates the LinkedHashMap tables that contains the possible quotients
	 * and the product of (divisor*possible quotient).
	 * It reverses the divisor before multiplying because thats how
	 * the {@link #multiplyNaive(ArrayList, ArrayList)} algorithm was designed.
	 * Uses the {@link Collections} class to reverse the divisor to and from the
	 * original position.
	 *
	 * @param divisor - divisor that will be multiplied
	 * @return LinkedHashMap table the for the lookUp.
	 */
	private LinkedHashMap<Integer,ArrayList<Integer>> storeProductLHMap(ArrayList<Integer> divisor)
	{
		LinkedHashMap<Integer,ArrayList<Integer>> table = new LinkedHashMap<>();
		ArrayList<Integer> val = new ArrayList<>(), tempProd;
		ArrayList<Integer> divs = new ArrayList<>(divisor);
		Collections.reverse(divs);
		for(int i = 1; i<=9; i++) {
			val.add(i);
			tempProd = new ArrayList<>(getProduct(divs,val));
			Collections.reverse(tempProd);
			table.put(i,tempProd);
			val.remove(0);
		}
		return table;
	}

	/**
	 * Calculate divisor*(1-9), where 1-9 represents the quotient and the
	 * product represents the new dividend
	 * @param lists - varargs of ArrayLists.
	 * @return Product
	 */
	@SafeVarargs
	private final ArrayList<Integer> getProduct(ArrayList<Integer> ... lists)
	{
		//Karatsuba karatsuba = new Karatsuba();
		return lists[0].size()==1 && lists[1].size()==1
				? singleDigitMultCase(lists[0],lists[1])
				: multiplyNaive(lists[0],lists[1]); //karatsuba.toAList(karatsuba.multiply(toStrArrList(lists[0]), toStrArrList(lists[1]),10));
	}

	/**
	 * Calculates BigInt <tt>(this<sup>exponent</sup>)</tt>
	 * The exponent is of type int rather than a BigInt because
	 * that just makes life easier.
	 * Before doing anything serious, this method takes care of special
	 * cases where <tt>(this<sup>0</sup>)</tt> == 1
	 * and <tt>(this<sup>1</sup>)</tt> == this.
	 *
	 * Then it goes in a helper method that actually does the exponents.
	 *
	 * @param exponent - value of the exponent
	 * @return <tt>(this<sup>exponent</sup>)</tt>
	 */
	BigInt pow(int exponent)
	{
		validateExponent(exponent);
		BigInt expoResult;
		if(exponent == 1) expoResult = this;
		else {
			expoResult = new BigInt(expoBySquaring(this.bigIntString,exponent));
		}
		return expoResult;
	}

	/**
	 * Since this is a BigInteger class and not a BigDecimal class,
	 * all results must be an Integer so that leaves having negative
	 * exponents out the scope.
	 *
	 * Throws {@link NegativeExponentException} if exponent {@literal <0}
	 * @param exponent - value to be tested
	 */
	private void validateExponent(int exponent)
	{
		try{
			if(exponent<0) throw new NegativeExponentException("Exponent < 0");
		}catch (NegativeExponentException e){
			e.getMessage();
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Recursively finds the value of <tt>(this<sup>exponent</sup>)</tt>.
	 * Parameters accepts original arraylist, product arraylist and the current
	 * exponent value.
	 *
	 * The value of the exponent decreses each time the recursive method is called.
	 * It functions as a counting value.
	 *
	 * Base Case: if(expo==1) return the current product
	 *
	 * @param org - {@code this.list}
	 * @param newlist - this.list*<tt>(this.list<sub>i</sub>)</tt>.
	 *                i == current iteration of exponent
	 * @param exponent - exponent value int
	 * @return <tt>(this<sup>exponent</sup>)</tt>
	 */
	@Deprecated
	private BigInt recursiveExpo(ArrayList<Integer> org,ArrayList<Integer> newlist,
	                             int exponent) {
		if(exponent == 1)
			return new BigInt(newlist);
		else{
			ArrayList<Integer> product = new ArrayList<>(multiplyNaive(newlist,org));
			return recursiveExpo(org, product,--exponent);
		}
	}

	/**
	 *
	 * <p>exponent = 0                   return 1</p>
	 * <p>array * expo(a, --exponent)    if exponent is odd</p>
	 * <p>expo(array, exponent/2)^2      if exponent is even</p>
	 * @param expo - int
	 * @param bigIntString - string
	 * @return BigInt - expo
	 */
	private String expoBySquaring(String bigIntString,int expo)
	{
		Karatsuba karatsuba = new Karatsuba();
		if(expo == 0)
			return "1";
		else if(expo % 2 == 1)
			return karatsuba.multiply(bigIntString,expoBySquaring(bigIntString,--expo),10);
		else{
			String s = expoBySquaring(bigIntString,expo >>> 1);
			return karatsuba.multiply(s,s,10);
		}
	}

	/**
	 * {@literal if n is odd -> x(x^2)^(n-1/2)}
	 * {@literal if n is even -> (x^2)^(n/2)}
	 *
	 *
	 * @param org - al
	 * @param expo - duh!
	 * @return result
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private BigInt expoAddition(ArrayList<Integer> org, int expo)
	{
		int secondPow = (expo-1) >>> 1;
		ArrayList<Integer> result, firstResult, secondResult;
		if(expo%2 != 0 ){
			firstResult = new ArrayList<>(multiplyNaive(org,org));
			secondResult = new ArrayList<>(recursiveExpo(firstResult,firstResult,(secondPow)).list);
			result = multiplyNaive(secondResult,org);
			return new BigInt(result);
		}else{
			firstResult = new ArrayList<>(recursiveExpo(org,org,2).list);
			result = new ArrayList<>(recursiveExpo(firstResult,firstResult,expo>>>1).list);
			return new BigInt(result);
		}
	}

	/**
	 * This method is the one tha compares the length of the ArrayList.
	 * The helper method {@link #addZerosToPartialSum(ArrayList, int)}
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
	 * Package Private. The length of the Arraylist
	 * @return int length
	 */
	int getArListLen()
	{
		return this.list.size();
	}

	/**
	 * Converts the arrayList to a string using regex
	 * @param array - ArrayList
	 * @return string representation
	 */
	@SuppressWarnings("unused")
	private String toStrArrList(ArrayList<Integer> array)
	{
		return array.toString().replaceAll(",", "")
				.replaceAll("\\[","").replaceAll("]","")
				.replaceAll(" ","");
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

	/**
	 * Private version of {@link #isLessThan(BigInt)} that accepts two arraylists
	 * as argument rather than a BigInt object. Mostly to aid the the
	 * comparisons done during the arithmetic.
	 *
	 * Catches a {@link NullPointerException} if one the arraylists being compared is null.
	 * @param first
	 *  ArrayList
	 * @param second
	 *  ArrayList
	 * @return 1 {@code a > b}, 2 {@code a < b}, 0 {@code a==b}
	 */
	private int isLessThan(ArrayList<Integer> first, ArrayList<Integer> second)
	{
		try {
			if (first.equals(second)) return 0;
			else {
				return first.size() != second.size()
						? compareBasedOnLengthArrayList(first, second)
						: compareEachNumberForLoop(first, second);
			}
		}catch (NullPointerException e){
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Returns true if both objects are equal to each other
	 * @param other - BigInt object
	 * @return boolean
	 */
	boolean isEqualTo(BigInt other)
	{
		return this.list.equals(other.list);
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
		if(this.isCharged || other.isCharged) return handleNegCompCases(other);
		else if(this.list.size()==other.list.size())
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
	 * Calls {@link #compareEachNumberForLoop(ArrayList, ArrayList)} to compare
	 * each element
	 *
	 * @param other BigInt object
	 * @return int 1,-1
	 */
	private int compareEachNumber(BigInt other)
	{
		return compareEachNumberForLoop(this.list,other.list);
	}

	/**
	 * Only be called if a !=  b
	 * This method will be called if both arraylists are equal in length.
	 * It will return an int value as soon as the for loop discovers
	 * if  {@code a[i] < b[i]} or {@code a[i] > b[i]}.
	 * @param first - First ArrayList
	 * @param second - SecondArrayList
	 * @return {@code a > b} then 1 otherwise -1
	 */
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
	private int handleNegCompCases(BigInt other)
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
		if(this.list.size() == other.list.size()) return 0;
		return this.list.size() > other.list.size() ? 1 : -1;
	}

	/**
	 * Boolean Method
	 * @param other - BigInt
	 * @return true if at least one BigInt object is negative but not both.
	 */
	private boolean isOneNegative(BigInt other)
	{
		return !(this.isCharged && other.isCharged)
				&&(this.isCharged || other.isCharged);
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
	 * Converts array list to string without any checks because the input is correct.
	 * @return string
	 */
	private String straightToString()
	{
		StringBuilder s = new StringBuilder();
		for(Integer integer : this.list)
			s.append(integer);
		return s.toString();
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
		if (this.isCharged) stringBuilder.append('-');

		for (Integer aNumberArray : this.list)
			stringBuilder.append(Math.abs(aNumberArray));

		String finalString = stringBuilder.toString();
		return finalString.matches("^[0]+$") ? finalString.substring(0, 1)
				: finalString.replaceFirst("^0*", "");
	}
}