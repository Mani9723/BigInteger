package RevisedBigInt;

/**
 * Defines a general interface that specifies certain methods that must exist
 */
public interface BigIntInterface
{
	/**
	 * Calculates a+b = c, where {a,b,c | a E Z, b E Z, c E Z}
	 * @param other - BigInt
	 * @return this + other
	 */
	BigInt add(BigInt other);

	/**
	 Calculates a-b = c, where {a,b,c | a E Z, b E Z, c E Z}
	 * @param other - BigInt
	 * @return this - other
	 */
	BigInt subtract(BigInt other);

	/**
	 Calculates a*b = c, where {a,b,c | a E Z, b E Z, c E Z}
	 * @param other - BigInt
	 * @return this * other
	 */
	BigInt multiply(BigInt other);

	/**
	 Calculates a/b = c, where {a,b,c | a E Z, b E Z, c E Z}
	 * @param other - BigInt
	 * @return this / other
	 */
	BigInt divideBy(BigInt other);

	/**
	 Calculates a%b = c, where {a,b,c | a E Z, b E Z, c E Z}
	 * @param other - BigInt
	 * @return this % other
	 */
	BigInt mod(BigInt other);

}