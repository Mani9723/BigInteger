package RevisedBigInt;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigIntTest
{

	private BigInt b1, b2, b3;

	@Test
	public void testAddZero()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddNoCarry()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("456");
		b3 = b1.add(b2);
		assertEquals("579", b3.toString());
	}

	@Test
	public void testAddCarry()
	{
		b1 = new BigInt("199999999");
		b2 = new BigInt("199999999");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddCarryTwo()
	{
		b1 = new BigInt("9876123546");
		b2 = new BigInt("987612355467434000");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddCarryThree()
	{
		b1 = new BigInt("76297436892");
		b2 = new BigInt("76297876");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarryFour()
	{
		b1 = new BigInt("7687187619897813458");
		b2 = new BigInt("6324567673108978079");
		b3 = b1.add(b2);
		String number = "14011755293006791537";
		assertEquals(number, b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarryFive()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321455";
		assertEquals(number, b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarrySix()
	{
		b2 = new BigInt("1");
		b1 = new BigInt("6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321457";
		assertEquals(number, b3.toString());
	}

	@Test
	public void testAddFirstNegative()
	{
		b1 = new BigInt("-1");
		b2 = new BigInt("+1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddSecondNegative()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddFirstLengthGreater()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testAddSecondLengthGreater()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("123");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testPaddingWithZeros()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testRemoveLeadingZeros()
	{
		b1 = new BigInt("0000000123");
		assertEquals("123", b1.toString());
	}

	@Test
	public void testAddTwoDifferentSigns()
	{
		b1 = new BigInt("-9878735");
		b2 = new BigInt("-9878735");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddTwoDifferentSignsTwo()
	{
		b1 = new BigInt("-100");
		b2 = new BigInt("100");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddAnotherCase()
	{
		b1 = new BigInt("-201");
		b2 = new BigInt("98712364978612934201");
		b3 = b1.add(b2);
		assertEquals("98712364978612934000", b3.toString());
	}

	///******************************************************************
	@Test
	public void testSubtractSingleDigits()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-1", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsV2()
	{
		b1 = new BigInt("-1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-3", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsV3()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("3", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsv4()
	{
		b1 = new BigInt("-1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	@Test
	public void testSubtractWithZeros()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV2()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV3()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV4()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV5()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV6()
	{
		b1 = new BigInt("9");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	@Test
	public void testSubtractOne()
	{
		b1 = new BigInt("19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	@Test
	public void testSubtractTwo()
	{
		b1 = new BigInt("-19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("-20", b3.toString());
	}

	@Test
	public void testSubtractThree()
	{
		b1 = new BigInt("-18");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("-9", b3.toString());
	}

	@Test
	public void testSubtractFour()
	{
		b1 = new BigInt("19");
		b2 = new BigInt("-19");
		b3 = b1.subtract(b2);
		assertEquals("38", b3.toString());
	}

	@Test
	public void testSubtractFive()
	{
		b1 = new BigInt("1778899");
		b2 = new BigInt("-1778899");
		b3 = b1.subtract(b2);
		assertEquals("3557798", b3.toString());
	}

	@Test
	public void testSubtractSix()
	{
		b1 = new BigInt("-1778899");
		b2 = new BigInt("-78899");
		b3 = b1.subtract(b2);
		assertEquals("-1700000", b3.toString());
	}

	@Test
	public void testSubtractSeven()
	{
		b1 = new BigInt("-1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("-1857798", b3.toString());
	}

	@Test
	public void testSubtractEight()
	{
		b1 = new BigInt("1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("1700000", b3.toString());
	}

	@Test
	public void testSubtractBorrowOne()
	{
		b1 = new BigInt("187");
		b2 = new BigInt("179");
		b3 = b1.subtract(b2);
		assertEquals("8", b3.toString());
	}

	@Test
	public void testSubtractTen()
	{
		// A - (-B) = A + B
		b1 = new BigInt("100");
		b2 = new BigInt("-101");
		b3 = b1.subtract(b2);
		assertEquals("201", b3.toString());
	}

	@Test
	public void testSubtractEleven()
	{
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("888888888889", b3.toString());
	}

	@Test
	public void testSubtractTwelve()
	{
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("16888888888887", b3.toString());
	}

	@Test
	public void testSubtractThirteen()
	{
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-888888888889", b3.toString());
	}

	@Test
	public void testSubtractFourteen()
	{
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-16888888888887", b3.toString());
	}

	@Test
	public void testFinalSubtract()
	{
		b1 = new BigInt("000");
		b2 = new BigInt("-100");
		b3 = b1.subtract(b2);
		assertEquals("100", b3.toString());
	}

//****************************************************************

	@Test
	public void testMultiplyOne()
	{
		b1 = new BigInt("2");
		b2 = new BigInt("2");
		b3 = b1.multiply(b2);
		assertEquals("4", b3.toString());
	}

	@Test
	public void testMultiplyTwo()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyThree()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("1");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyFour()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyFive()
	{
		b1 = new BigInt("-2");
		b2 = new BigInt("9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	@Test
	public void testMultiplySix()
	{
		b1 = new BigInt("2");
		b2 = new BigInt("-9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	@Test
	public void testMultiplySeven()
	{
		b1 = new BigInt("-8");
		b2 = new BigInt("-5");
		b3 = b1.multiply(b2);
		assertEquals("40", b3.toString());
	}

	@Test
	public void testMultiplyEight()
	{
		b1 = new BigInt("4");
		b2 = new BigInt("13");
		b3 = b1.multiply(b2);
		assertEquals("52", b3.toString());
	}

	@Test
	public void testMultiplyNine()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-10");
		b3 = b1.multiply(b2);
		assertEquals("-10", b3.toString());
	}

	@Test
	public void testMultiplyTen()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("23");
		b3 = b1.multiply(b2);
		assertEquals("2829", b3.toString());
	}

	@Test
	public void testMultiplyEleven()
	{
		b1 = new BigInt("-23");
		b2 = new BigInt("123");
		b3 = b1.multiply(b2);
		assertEquals("-2829", b3.toString());
	}

	@Test
	public void testMultiplyTwelve()
	{
		b1 = new BigInt("9998");
		b2 = new BigInt("8765");
		b3 = b1.multiply(b2);
		assertEquals("87632470", b3.toString());
	}

	@Test
	public void testMultiplyIdentity()
	{
		b3 = new BigInt("1").multiply(new BigInt("123456"));
		assertEquals("123456", b3.toString());
	}

	@Test
	public void testmult()
	{
		b3 = new BigInt("111111111111111111111111111111111111111")
				.multiply(new BigInt("2"));
		assertEquals("222222222222222222222222222222222222222", b3.toString());
	}

	@Test
	public void testEquality()
	{
		assertTrue(new BigInt("12345")
				.isEqualTo(new BigInt("12345")));
	}

	@Test
	public void testInequality()
	{
		assertFalse(new BigInt("12345")
				.isEqualTo(new BigInt("23456")));
	}

	@Test
	public void testInequalityTwo()
	{
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("23456")));
	}

	@Test
	public void testInequalityThree()
	{
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("12345")));
	}

	@Test
	public void testCompareTo()
	{
		b1 = new BigInt("12345");
		b2 = new BigInt("12345");
		assertEquals(0, b1.compareTo(b2));
	}

	@Test
	public void testNegativeMultiply()
	{
		b3 = new BigInt("-1000").multiply(new BigInt("-1"));
		assertEquals("1000", b3.toString());
	}

	@Test
	public void testSubtractZeroIdentity()
	{
		b3 = new BigInt("-1000").subtract(new BigInt("0"));
		assertEquals("-1000",b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityOne()
	{
		b3 = new BigInt("0").subtract(new BigInt("0"));
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityTwo()
	{
		b3 = new BigInt("0").subtract(new BigInt("0"));
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityThree()
	{
		b3 = new BigInt("0").subtract(new BigInt("-12345"));
		assertEquals("12345",b3.toString());
	}

	@Test
	public void testCompareNegativeInt()
	{
		assertEquals(-1,new BigInt("-12345")
				.compareTo(new BigInt("12345")));
	}
	@Test
	public void testCompareNegativeIntV1()
	{
		assertEquals(1,new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV1()
	{
		assertEquals(0,new BigInt("12345")
				.compareTo(new BigInt("12345")));
	}

	@Test
	public void testMagnitudeV2()
	{
		assertEquals(0,new BigInt("-12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV3()
	{
		assertEquals(1,new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV4()
	{
		assertEquals(-1,new BigInt("-123458")
				.compareTo(new BigInt("12345")));
	}

	@Test
	public void testMagnitudeV5()
	{
		assertEquals(-1,new BigInt("123458")
				.compareTo(new BigInt("133454")));
	}

	@Test
	public void testMagnitudeV6()
	{
		assertEquals(1,new BigInt("143458")
				.compareTo(new BigInt("133454")));
	}

	@Test
	public void testMagnitudeUnequalLen()
	{
		assertEquals(-1,new BigInt("1434")
				.compareTo(new BigInt("133454")));
	}

	@Test
	public void testMagnitudeUnequalLenV1()
	{
		assertEquals(1,new BigInt("1439872347656187236987345094568798794")
				.compareTo(new BigInt("139867767878879879324767234")));
	}

	@Test
	public void testMagnitudeUnequalLenV2()
	{
		assertEquals(-1,new BigInt("-12345")
				.compareTo(new BigInt("-12344")));
	}

	@Test
	public void testMagnitudeUnequalLenV3()
	{
		assertEquals(1,new BigInt("-12344")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeUnequalLenV4()
	{
		assertEquals(0,new BigInt("9999999")
				.compareTo(new BigInt("9999999")));
	}

	@Test
	public void testMagnitudeUnequalLenV5()
	{
		assertEquals(0,new BigInt("-9999999")
				.compareTo(new BigInt("-9999999")));
	}

	@Test
	public void smallTest()
	{
		b1 = new BigInt("-12345");
		b2 = new BigInt("1");
		assertEquals(-1,b1.compareTo(b2));
	}





}
