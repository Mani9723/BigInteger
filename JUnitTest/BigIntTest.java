package RevisedBigInt;

import org.junit.Test;
import java.io.File;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Detailed Test cases for {@link BigInt} class.
 *
 * {a,b,c,d,e,f,g E Z}
 */
public class BigIntTest {

	/**
	 * BigInt objects.
	 * {@code b3} holds the results
	 */
	private BigInt b1, b2, b3;


	/**
	 *  Test -a - b
	 */
	@Test
	public void testSub() {
		assertEquals("-988", new BigInt("1")
				.subtract(new BigInt("989")).toString());
	}

	/**
	 * Test |a|
	 */
	@Test
	public void testAbs() {
		b1 = new BigInt("167");
		assertEquals(b1.toString(), b1.absValue().toString());

	}

	/**
	 * Tests |-a|
	 */
	@Test
	public void testAbs1() {
		b1 = new BigInt("-167");
		assertEquals("167", b1.absValue().toString());

	}

	/**
	 * Test -0 + 0
	 */
	@Test
	public void testAddZero() {
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test a + b = c *Includes Carry
	 */
	@Test
	public void testAddNoCarry() {
		b1 = new BigInt("123");
		b2 = new BigInt("456");
		b3 = b1.add(b2);
		assertEquals("579", b3.toString());
	}

	/**
	 * Test a + b = c *Includes Carry
	 */
	@Test
	public void testAddCarry() {
		b1 = new BigInt("199999999");
		b2 = new BigInt("199999999");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	/**
	 * Test a + b = c (Carry; a.len {@code {@code {@code < } } } b.len)
	 */
	@Test
	public void testAddCarryTwo() {
		b1 = new BigInt("9876123546");
		b2 = new BigInt("987612355467434000");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	/**
	 * Test a + b = c (Carry; a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testAddCarryThree() {
		b1 = new BigInt("76297436892");
		b2 = new BigInt("76297876");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	/**
	 * Test a + b = c (Carry; a.len == b.len)
	 */
	@Test
	public void testAddCarryFour() {
		b1 = new BigInt("7687187619897813458");
		b2 = new BigInt("6324567673108978079");
		b3 = b1.add(b2);
		String number = "14011755293006791537";
		assertEquals(number, b3.toString());
	}

	/**
	 * Test a + (-b) = -c (Carry; a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testAddCarryFive() {
		b1 = new BigInt("1");
		b2 = new BigInt("-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321455";
		assertEquals(number, b3.toString());
	}

	/**
	 * Test a + b = c (Carry; a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testAddCarrySix() {
		b2 = new BigInt("1");
		b1 = new BigInt("6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321457";
		assertEquals(number, b3.toString());
	}

	/**
	 * Test -a - b = 0
	 */
	@Test
	public void testAddFirstNegative() {
		b1 = new BigInt("-1");
		b2 = new BigInt("+1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test a + (-b) = 0
	 */
	@Test
	public void testAddSecondNegative() {
		b1 = new BigInt("1");
		b2 = new BigInt("-1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test a - b = c (a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testAddFirstLengthGreater() {
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	/**
	 * Test a + b = c (a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testAddSecondLengthGreater() {
		b1 = new BigInt("1");
		b2 = new BigInt("123");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	/**
	 * Test Leading Zero Removal
	 * 000000a = a
	 */
	@Test
	public void testRemoveLeadingZeros() {
		b1 = new BigInt("0000000123");
		assertEquals("123", b1.toString());
	}

	/**
	 * Test -a + (-b) = -c
	 */
	@Test
	public void testAddTwoDifferentSigns() {
		b1 = new BigInt("-9878735");
		b2 = new BigInt("-9878735");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	/**
	 * Test -a + b = 0
	 */
	@Test
	public void testAddTwoDifferentSignsTwo() {
		b1 = new BigInt("-100");
		b2 = new BigInt("100");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	/**
	 * Test -a + b = c (a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testAddAnotherCase() {
		b1 = new BigInt("-201");
		b2 = new BigInt("98712364978612934201");
		b3 = b1.add(b2);
		assertEquals("98712364978612934000", b3.toString());
	}

	///******************************************************************

	/**
	 * Test a - b = -c
	 */
	@Test
	public void testSubtractSingleDigits() {
		b1 = new BigInt("1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-1", b3.toString());
	}

	/**
	 * Test -a - b = -c
	 */
	@Test
	public void testSubtractSingleDigitsV2() {
		b1 = new BigInt("-1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-3", b3.toString());
	}

	/**
	 * Test a - (-b) = c
	 */
	@Test
	public void testSubtractSingleDigitsV3() {
		b1 = new BigInt("1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("3", b3.toString());
	}

	/**
	 * Test -a - (-b) = c
	 */
	@Test
	public void testSubtractSingleDigitsv4() {
		b1 = new BigInt("-1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	/**
	 * Test 0 - 0 = 0
	 */
	@Test
	public void testSubtractWithZeros() {
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test 0 - (-0) = 0
	 */
	@Test
	public void testSubtractWithZerosV2() {
		b1 = new BigInt("0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test -0 - 0 = 0
	 */
	@Test
	public void testSubtractWithZerosV3() {
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test -0 - (-0) = 0
	 */
	@Test
	public void testSubtractWithZerosV4() {
		b1 = new BigInt("-0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * Test a - (-0) = a
	 */
	@Test
	public void testSubtractWithZerosV5() {
		b1 = new BigInt("1");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	/**
	 * Test 0 - a = -a
	 */
	@Test
	public void testSubtractWithZerosV5a() {
		b1 = new BigInt("0");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("-1", b3.toString());
	}

	/**
	 * Test a - (-a) = 2a
	 */
	@Test
	public void testSubtractWithZerosV6() {
		b1 = new BigInt("9");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	/**
	 * Test a - b = c ( a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testSubtractOne() {
		b1 = new BigInt("19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	/**
	 * Test -a - b = -c (a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testSubtractTwo() {
		b1 = new BigInt("-19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("-20", b3.toString());
	}

	/**
	 * Test -a - (-b) = -c (a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testSubtractThree() {
		b1 = new BigInt("-18");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("-9", b3.toString());
	}

	/**
	 * Test a - (-b) = c (a.len = b.len)
	 */
	@Test
	public void testSubtractFour() {
		b1 = new BigInt("19");
		b2 = new BigInt("-19");
		b3 = b1.subtract(b2);
		assertEquals("38", b3.toString());
	}

	/**
	 * Test a - (-b) = c
	 */
	@Test
	public void testSubtractFive() {
		b1 = new BigInt("1778899");
		b2 = new BigInt("-1778899");
		b3 = b1.subtract(b2);
		assertEquals("3557798", b3.toString());
	}

	/**
	 * -a - (-b) = -c (a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testSubtractSix() {
		b1 = new BigInt("-1778899");
		b2 = new BigInt("-78899");
		b3 = b1.subtract(b2);
		assertEquals("-1700000", b3.toString());
	}
	/**
	 * -a - b = -c (a.len {@code {@code > } } b.len)
	 */
	@Test
	public void testSubtractSeven() {
		b1 = new BigInt("-1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("-1857798", b3.toString());
	}

	@Test
	public void testSubtractEight() {
		b1 = new BigInt("1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("1700000", b3.toString());
	}
	/******** BORROW SUBTRACTION ********
	 /**
	 * Test a - b ({@code a {@code {@code < } } b})
	 */
	@Test
	public void testSubtractBorrowOne() {
		b1 = new BigInt("187");
		b2 = new BigInt("179");
		b3 = b1.subtract(b2);
		assertEquals("8", b3.toString());
	}
	/**
	 * Test a - (-b)  = c
	 */
	@Test
	public void testSubtractTen() {
		// A - (-B) = A + B
		b1 = new BigInt("100");
		b2 = new BigInt("-101");
		b3 = b1.subtract(b2);
		assertEquals("201", b3.toString());
	}

	/**
	 * a - b = c ( d == f8888..; e == g9999... and f {@code {@code > } } g)
	 */
	@Test
	public void testSubtractEleven() {
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("888888888889", b3.toString());
	}

	/**
	 * a - (-b) = c ( d == f8888..; e == g9999... and |f| {@code {@code > } } |g|)
	 */
	@Test
	public void testSubtractTwelve() {
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("16888888888887", b3.toString());
	}
	/**
	 * -a - (-b) = -c ( d == f8888..; e == g9999... and |f| {@code {@code > } } |g|)
	 */
	@Test
	public void testSubtractThirteen() {
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-888888888889", b3.toString());
	}
	/**
	 * -a - b = -c ( d == f8888..; e == g9999... and |f| {@code {@code > } } |g|)
	 */
	@Test
	public void testSubtractFourteen() {
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-16888888888887", b3.toString());
	}
	/**
	 * 0 - (-b) = c
	 */
	@Test
	public void testFinalSubtract() {
		b1 = new BigInt("000");
		b2 = new BigInt("-100");
		b3 = b1.subtract(b2);
		assertEquals("100", b3.toString());
	}

//****************************************************************

	/**
	 * a * b = c
	 */
	@Test
	public void testMultiplyOne() {
		b1 = new BigInt("2");
		b2 = new BigInt("2");
		b3 = b1.multiply(b2);
		assertEquals("4", b3.toString());
	}

	/**
	 * 0 * 0 = 0
	 */
	@Test
	public void testMultiplyTwo() {
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * 0 * 1 = 0
	 */
	@Test
	public void testMultiplyThree() {
		b1 = new BigInt("0");
		b2 = new BigInt("1");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * 1 * 0 = 0
	 */
	@Test
	public void testMultiplyFour() {
		b1 = new BigInt("1");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * -a * b = -c
	 */
	@Test
	public void testMultiplyFive() {
		b1 = new BigInt("-2");
		b2 = new BigInt("9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	/**
	 * a * -b = -c
	 */
	@Test
	public void testMultiplySix() {
		b1 = new BigInt("2");
		b2 = new BigInt("-9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	/**
	 * -a * -b = c
	 */
	@Test
	public void testMultiplySeven() {
		b1 = new BigInt("-8");
		b2 = new BigInt("-5");
		b3 = b1.multiply(b2);
		assertEquals("40", b3.toString());
	}

	/**
	 * a * b = c (a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testMultiplyEight() {
		b1 = new BigInt("4");
		b2 = new BigInt("13");
		b3 = b1.multiply(b2);
		assertEquals("52", b3.toString());
	}

	/**
	 * a * (-b) = -c (a.len {@code {@code < } } b.len)
	 */
	@Test
	public void testMultiplyNine() {
		b1 = new BigInt("1");
		b2 = new BigInt("-10");
		b3 = b1.multiply(b2);
		assertEquals("-10", b3.toString());
	}

	/**
	 * a * b = c (a.len {@code > } b.len)
	 */
	@Test
	public void testMultiplyTen() {
		b1 = new BigInt("123");
		b2 = new BigInt("23");
		b3 = b1.multiply(b2);
		assertEquals("2829", b3.toString());
	}

	/**
	 * a * b = -c (a.len {@code < } b.len)
	 */
	@Test
	public void testMultiplyEleven() {
		b1 = new BigInt("-23");
		b2 = new BigInt("123");
		b3 = b1.multiply(b2);
		assertEquals("-2829", b3.toString());
	}

	/**
	 * a * b = c
	 */
	@Test
	public void testMultiplyTwelve() {
		b1 = new BigInt("9998");
		b2 = new BigInt("8765");
		b3 = b1.multiply(b2);
		assertEquals("87632470", b3.toString());
	}

	/**
	 * a * 1 = a
	 */
	@Test
	public void testMultiplyIdentity() {
		b3 = new BigInt("1").multiply(new BigInt("123456"));
		assertEquals("123456", b3.toString());
	}

	/**
	 * a * b = c (a.len {@code > } b.len)
	 */
	@Test
	public void testmult() {
		b3 = new BigInt("111111111111111111111111111111111111111")
				.multiply(new BigInt("2"));
		assertEquals("222222222222222222222222222222222222222", b3.toString());
	}

	/**
	 * a ?= b
	 */
	@Test
	public void testEquality() {
		assertTrue(new BigInt("12345")
				.isEqualTo(new BigInt("12345")));
	}

	/**
	 * a ?= b
	 */
	@Test
	public void testInequality() {
		assertFalse(new BigInt("12345")
				.isEqualTo(new BigInt("23456")));
	}

	/**
	 * -a ?= b
	 */
	@Test
	public void testInequalityTwo() {
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("23456")));
	}

	/**
	 * -a ?= b ( |a| == |b|)
	 */
	@Test
	public void testInequalityThree() {
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("12345")));
	}

	/**
	 * a == b
	 */
	@Test
	public void testCompareTo() {
		b1 = new BigInt("12345");
		b2 = new BigInt("12345");
		assertEquals(0, b1.compareTo(b2));
	}

	/**
	 * -a * -1 = a
	 */
	@Test
	public void testNegativeMultiply() {
		b3 = new BigInt("-1000").multiply(new BigInt("-1"));
		assertEquals("1000", b3.toString());
	}

	/**
	 * -a - 0 = -a
	 */
	@Test
	public void testSubtractZeroIdentity() {
		b3 = new BigInt("-1000").subtract(new BigInt("0"));
		assertEquals("-1000", b3.toString());
	}

	/**
	 * 0 - 0 = 0
	 */
	@Test
	public void testSubtractZeroIdentityOne() {
		b3 = new BigInt("0").subtract(new BigInt("0"));
		assertEquals("0", b3.toString());
	}

	/**
	 * 0 - (-0) = 0
	 */
	@Test
	public void testSubtractZeroIdentityTwo() {
		b3 = new BigInt("0").subtract(new BigInt("-0"));
		assertEquals("0", b3.toString());
	}

	/**
	 * 0 - (-a) = -a
	 */
	@Test
	public void testSubtractZeroIdentityThree() {
		b3 = new BigInt("0").subtract(new BigInt("-12345"));
		assertEquals("12345", b3.toString());
	}

	/**
	 * -a ?= -B (|a| {@code < } |b|)
	 */
	@Test
	public void testCompareNegativeInt() {
		assertEquals(-1, new BigInt("-12345")
				.compareTo(new BigInt("12345")));
	}

	/**
	 * a ?= -a
	 */
	@Test
	public void testCompareNegativeIntV1() {
		assertEquals(1, new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	/**
	 * a ?= b
	 */
	@Test
	public void testMagnitudeV1() {
		assertEquals(0, new BigInt("12345")
				.compareTo(new BigInt("12345")));
	}

	/**
	 * -a ?= -b
	 */
	@Test
	public void testMagnitudeV2() {
		assertEquals(0, new BigInt("-12345")
				.compareTo(new BigInt("-12345")));
	}

	/**
	 * b ?= -b
	 */
	@Test
	public void testMagnitudeV3() {
		assertEquals(1, new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	/**
	 * -a ?= b
	 */
	@Test
	public void testMagnitudeV4() {
		assertEquals(-1, new BigInt("-123458")
				.compareTo(new BigInt("12345")));
	}

	/**
	 * a ?= b
	 */
	@Test
	public void testMagnitudeV5() {
		assertEquals(-1, new BigInt("123458")
				.compareTo(new BigInt("133454")));
	}

	@Test
	public void testMagnitudeV6() {
		assertEquals(1, new BigInt("143458")
				.compareTo(new BigInt("133454")));
	}

	/**
	 * a ?= b
	 */
	@Test
	public void testMagnitudeUnequalLen() {
		assertEquals(-1, new BigInt("1434")
				.compareTo(new BigInt("133454")));
	}

	/**
	 * a?=b
	 */
	@Test
	public void testMagnitudeUnequalLenV1() {
		assertEquals(1, new BigInt("1439872347656187236987345094568798794")
				.compareTo(new BigInt("139867767878879879324767234")));
	}

	/**
	 * -a ?= -b
	 */
	@Test
	public void testMagnitudeUnequalLenV2() {
		assertEquals(-1, new BigInt("-12345")
				.compareTo(new BigInt("-12344")));
	}
	/**
	 * -a ?= -b
	 */
	@Test
	public void testMagnitudeUnequalLenV3() {
		assertEquals(1, new BigInt("-12344")
				.compareTo(new BigInt("-12345")));
	}
	/**
	 * a ?= a
	 */
	@Test
	public void testMagnitudeUnequalLenV4() {
		assertEquals(0, new BigInt("9999999")
				.compareTo(new BigInt("9999999")));
	}
	/**
	 * -a ?= -a
	 */
	@Test
	public void testMagnitudeUnequalLenV5() {
		assertEquals(0, new BigInt("-9999999")
				.compareTo(new BigInt("-9999999")));
	}
	/**
	 * -a ?= 1
	 */
	@Test
	public void smallTest() {
		b1 = new BigInt("-12345");
		b2 = new BigInt("1");
		assertEquals(-1, b1.compareTo(b2));
	}
	/**
	 * max(a,a)
	 */
	@Test
	public void textMaximum() {
		b1 = new BigInt("12345678987654321");
		assertEquals(b1, b1.max(new BigInt("123456787654321")));
	}
	/**
	 * max(-a,b)
	 */
	@Test
	public void textMaximumV1() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b2, b1.max(b2));
	}
	/**
	 * max(a,-b)
	 */
	@Test
	public void textMaximumV2() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.max(b2));
	}
	/**
	 * max(-a,b)
	 */
	@Test
	public void textMaximumV3() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("9876823456787654321");
		assertEquals(b2, b1.max(b2));
	}
	/**
	 * max(-a,-b)
	 */
	@Test
	public void textMaximumV4() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.max(b2));
	}
	/**
	 * max(-a,-b)
	 */
	@Test
	public void textMaximumV5() {
		b1 = new BigInt("-87963847512345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.max(b2));
	}
	/**
	 * min(a,b)
	 */
	@Test
	public void textMinimum() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b2, b1.min(b2));
	}
	/**
	 * min(-a,b)
	 */
	@Test
	public void textMinimumV1() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b1, b1.min(b2));
	}
	/**
	 * min(a,-b)
	 */
	@Test
	public void textMinimumV2() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.min(b2));
	}
	/**
	 * min(-a,b)
	 */
	@Test
	public void textMinimumV3() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("9876823456787654321");
		assertEquals(b1, b1.min(b2));
	}

	/**
	 * min(-a,-b)
	 */
	@Test
	public void textMinimumV4() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.min(b2));
	}

	/**
	 * min(-a,-b)
	 */
	@Test
	public void textMinimumV5() {
		b1 = new BigInt("-87963847512345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.min(b2));
	}

	/**
	 * -a % b = -c
	 */
	@Test
	public void testDivideV1() {
		b1 = new BigInt("-11");
		b2 = new BigInt("2");
		b3 = b1.mod(b2);
		assertEquals("-1", b3.toString());
	}

	/**
	 * a/b = c
	 */
	@Test
	public void testDivideV2() {
		b1 = new BigInt("124567");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("1012", b3.toString());
	}

	/**
	 * a/b = c
	 */
	@Test
	public void testDivideV3() {
		b1 = new BigInt("10");
		b2 = new BigInt("2");
		b3 = b1.divideBy(b2);
		assertEquals("5", b3.toString());
	}

	/**
	 * a/a = 1
	 */
	@Test
	public void testDivideV4() {
		b1 = new BigInt("24356789654356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("1", b3.toString());
	}

	/**
	 * a/b = 0
	 */
	@Test
	public void testDivideV5() {
		b1 = new BigInt("24356784356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * -a/b = 0
	 */
	@Test
	public void testDivideV6() {
		b1 = new BigInt("-24356784356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());
	}

	/**
	 * a/-b = 0
	 */
	@Test
	public void testDivideV7() {
		b1 = new BigInt("723478");
		b2 = new BigInt("-876543423");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());

	}

	/**
	 * -a/a = -1
	 */
	@Test
	public void testDivideV8()
	{
		b1 = new BigInt("-123");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("-1",b3.toString());
	}

	/**
	 * a/-a = -1
	 */
	@Test
	public void testDivideV9()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("-123");
		b3 = b1.divideBy(b2);
		assertEquals("-1",b3.toString());
	}

	/**
	 * -a / b = -c
	 */
	@Test
	public void testDivideV10()
	{
		b1 = new BigInt("-122353");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("-994",b3.toString());
	}

	/**
	 * a / -b = -c
	 */
	@Test
	public void testDivideV11()
	{
		b1 = new BigInt("122353");
		b2 = new BigInt("-123");
		b3 = b1.divideBy(b2);
		assertEquals("-994",b3.toString());
	}

	/**
	 * a/b = c
	 */
	@Test
	public void testDivideV12()
	{
		b1 = new BigInt("94568792345667874387998734809969827359687236475862386387465238765298743659287465928765982" +
				"743695876912645671523748659384576239485692837495682736453847528735692873465982734659872639487659283745" +
				"675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "3564816684666776118646755229514786464840738701011754521049706936976035677572361259188393069174752" +
				"68236665240236820872123193546739705457065242636006942109112285663634952743548629044201227343026991172" +
				"69964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}

	/**
	 * a/-b = -c
	 */
	@Test
	public void testDivideV13()
	{
		b1 = new BigInt("9456879234566787438799873480996982735968723647586238638746523876529874365928746592876598" +
				"27436958769126456715237486593845762394856928374956827364538475287356928734659827346598726394876592837" +
				"45675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("-2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "-356481668466677611864675522951478646484073870101175452104970693697603567757236125918839306917475" +
				"26823666524023682087212319354673970545706524263600694210911228566363495274354862904420122734302699117" +
				"269964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}

	/**
	 * -a/-b = c
	 */
	@Test
	public void testDivideV14()
	{
		b1 = new BigInt("-945687923456678743879987348099698273596872364758623863874652387652987436592874659287659" +
				"82743695876912645671523748659384576239485692837495682736453847528735692873465982734659872639487659283" +
				"745675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("-2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "35648166846667761186467552295147864648407387010117545210497069369760356775723612591883930691747" +
				"52682366652402368208721231935467397054570652426360069421091122856636349527435486290442012273430269911" +
				"7269964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}

	/**
	 * a/b = c
	 */
	@Test
	public void testDivideV15()
	{
		b1 = new BigInt("945687923345678654324567865432567897654" +
				"356789876543245678965432456789087654231456" +
				"789088765432435437655978698765876534654324321524653765587698769" +
				"345667874387998734809969827359687236475862386387465" +
				"2387652987436592874659287659827436958769" +
				"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475"
				+	"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"45182345975613487658127634082387679387980723908"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475"
				+"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"126456715237486593845762394856928374956827364538475" +
				"28735692873465982734659872639487659283745665324326547" +
				"679869876876454234236546978609876977523761273" +
				"45182345975613487658127634082387679387980723908" +
				"89776672378234789234");
		b2 = new BigInt("278772367723676562345677427898790230980980789789764236778923" +
				"48790890034789674236898790789078976767478879067565682" +
				"48790890034789674236898790789078976767478879067565682" +
				"48790890034789674236898790789078976767478879067565682" +
				"48790890034789674236898790789078976767478879067565682" +
				"278772367723676562345677427898790230980980789789764236778923" +
				"48790890034789674236898790789078976767478879067565682" +
				"278772367723676562345677427898790230980980789789764236778923" +
				"278772367723676562345677427898790230980980789789764236778923" +
				"278772367723676562345677427898790230980980789789764236778923" +
				"48790890034789674236898790789078976767478879067565682" +"278772367723676562345677427898790230980980789789764236778923" +
				"278772367723676562345677427898790230980980789789764236778923" +
				"48790890034789674236898790789078976767478879067565682" +
				"48790890034789674236898790789078976767478879067565682" +
				"48790890034789674236898790789078976767478879067565682" +
				"3489756827345");
		b3 = b1.divideBy(b2);
//		String ans = "4728439616728393271622839327162839488271783949382716228394827162283945438271157283945443827162177" +
//				"1882798934938293826732716216076232688279384938467283393719399936740498491367984361823793119319373261" +
//				"93826493718296437329643829913718479384563228357618743296922881197428464187478413682269237563228357618" +
//				"74329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761" +
//				"874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761" +
//				"874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761" +
//				"87432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692376436784643673299136732993631974382964187283266216327383993493843822711711827348930493848876188063672591172987806743829063817041193839693990361954063228357618743296922881197428464187" +
//				"47841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923764367846436732991367329936319743829641872832662163273839934938438227117118273489304938488761880636725911729878067438290638170411938396939903619540632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432" +
//				"969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692376436784643673299136732993631974382964187283266216327383993493843822711711827348930493848876188063672591172987806743829063817041193839693990361954063228357618743296922881197428464187478413682269237563228357618" +
//				"74329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761" +
//				"874329692288119742846418747841368226923764367846436732991367329936319743829641872832662163273839934938438227117118273489304938488761880636725911729878067438290638170411938396939903619540632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692376436784643673299136732993631974382964187283266216327383993493843822711711827348930493848876188063672591172987806743829063817041193839693990361954063228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237643678464367329913673299363197438296418728326621632738399349384382271171182734893049384887618806367259117298780674382906381704119383969399036195406322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923" +
//				"76436784643673299136732993631974382964187283266216327383993493843822711711827348930493848876188063672591172987806743829063817041193839693990361954063228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237643678464367329913673299363197438296418728326621632738399349384382271171182734893049384887618806367259117298780674382906381704119383969399036195406322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923764367846436732991367329936319743829641872832662163273839934938438227117118273489304938488761880636725911729878067438290638170411938396939903619540632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692375632283576187432969228811974284641874784136822692376436784643673299136732993631974382964187283266216327383993493843822711711827348930493848876188063672591172987806743829063817041193839693990361954063228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237643678464367329913673299363197438296418728326621632738399349384382271171182734893049384887618806367259117298780674382906381704119383969399036195406322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923756322835761874329692288119742846418747841368226923772591172987806743829063817041193839693990361954063228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237563228357618743296922881197428464187478413682269237643678464367329913673299363197438296418728326621632738399349384382271171182734893049384887618806367259117298780674382906381704119383969399036195444888336189117394617";
		String ans = "33923302049902556993240739588813296281119765427777085218474200015348241406377636257497045433966173786897607217136667247709635916207449066913503934138187315843719368719355800610961465174953496567450738024949211694497530011839262986694100140345158765132871152234771023120211029926754061431810993086517127949290266790028876895764797534259887679651336539715363857694582269425125131532565001518378096817702055164401308027490138670777514051985201055131408242679218189187843367254406899734245359501370722428347766807944298831117713485116657480641690786690945694606636497017377336875999026482983746542758910456150025984275340012711968871537711529172161657413038179703579317506253557636535864546626814688573695512205901225127604480095804664615455066368834526515751299484558728798617187731112031269423280920092218530754472457520478069718992333848544687444196731018127117167552505313124194645773366658085210184836810482117525094327486172436698785817099105286044842232685816438108405215480304220205246531618875900062124036716378921489261448084261109934961656203070137455627949197848623602335719174819926777486674752473482709247980024315894550475650581876435785572115061434196287052817796549496591986504325947132236776161572144748979692629602863155670294095133628626300047888564617924215554820602900498731582997018554304664134991289702140469169173178143142036952354710065903799304997662230069105720939100146311680576483126587598657603975982774792641161767619142562315285628193808573953852629972650932274908028672055966203029555602572339316573471868205506990223483140769779024813428476192080730761089125143557018671606122167094857820764026625271409856287534460972734314572098507823450572507697688692353427294498064810924903226781085455963511533716008998891308967746029337394085782951864789979460926685937115614037961382155466760426358849046442855453987110903310527707099894374950112582560716033853359088739803855257131923983890450120229212056515391847684440808772413221865147055300879081135569093351556803508214929454401829371935802192056454494813605040482619787915117626837675423731854217429427870356835467228105707738119287046504598210595413375093840895075916806700881689207063586950583872065084632720906497656655314545587519434499637988575784439202798064606196421984869611441972634565232115708953559942473352952871558443235178999355063977525228854199695307837954463081321170182172871445270642602361539348607566451734400791971439408747663291456413805040907825570799211035876965352901090656506141214724727818515285131313516501125434376735236813530305593633500830289776159034767334659040663212337953979803786754191321582943807897508322782299186632741147974424471543405976207613451176434009349419791892337840881140266932525707144415390961230046797512176460012157564564086442986357799776456301368880563017323490242821893555453570532774484712781597936689623698982341797278196968041674807855601270930878519791264335270008557122806298767474491442181994718044997173465647121676499560840148552827781851261470678077644134771655309056428876961502848691845696126309049287900658052457212612822462905058909223671764658717846330728870352530198657958525040121946247027388349310687625056511456045527798583533394985853022479014123168883655524882289838073542526482279831927789804210143183835011236257633633493793422788732083938472002097102691128163208284150824953290139075840084548595944130227261256668130936789580071261904182548701796253301860258902007482383313989374784834769308895845543552886402919726409257143480947206691173376522568781100760217353583049657621033319120995393184622145572169552410216194842644851104593551385842537812406470882816131589364276991062691924718415840814008254390262623721507672163482879677636123971692860056286355242522268760085453186232033026757880415142898826065631366869961968941210143450128825162872880658932930427737942399995504441150238744766179973391553881298506348635316919615460066957779205437915717551601610557539641883736072994057994869135368571606794258489336178404568883892030155735922975523822326706634123501741655946200094117761808634304435745261186491279459767012314019606656362453152000166430915302113541057871297611325661038459324597471132825267324641761045574086343857096497643804114735152881314485727229030452074133673178578201921585755978789482443122685653634528446314294780365266474160367500416698917017053997722295381622528432975763220689229596188523603999182675995330198397858463703443459331651896953189833281646523089007501583474668203086883160187070918026992097315757226470051652166850768211547117968600800978396183072506463436025994498946816965330500805595989343701335945704675138734631281422333290424023381676564207935897732851562465199249880323871904982812757994225604082660632427268018998507594326812097810304201271607066099117771230590665979822487042390809687261683695551881069112026039249854975703603045560468337794330154327185232321031542395825563415497725698499322622793915953180266609328569278978553741135928999718564453478226747155145527579262200264377064038812430970002862837306679714431894264086193179156737393397515230654332003212565502175976346387422616588232904265676549590629234119482421324424052658004548247127415998100227317163852313352165236309012781983236008288059022";
		assertEquals(ans,b3.toString());
	}

	/**
	 * |a|
	 */
	@Test
	public void testAbs2()
	{
		assertEquals("1", new BigInt("-1").absValue().toString());
	}

	/**
	 * Test File Reader Constructor.
	 * a^b = c
	 */
	@Test
	public void testFileConstructor()
	{
		long startw = System.currentTimeMillis();
		b1 = new BigInt(new File("src\\RevisedBigInt\\TextFiles\\dividend.txt"));
		b2 = new BigInt(new File("src\\RevisedBigInt\\TextFiles\\divisor.txt"));
		System.out.println(System.currentTimeMillis()-startw);
		long start = System.currentTimeMillis();
		b3 = b1.add(b2);
		String ans = "73167176531330624919225119674426574742" +
				"355349194934969835203127745063262395783180169848" +
				"018694788518438586156078911294949545950173795833" +
				"195285320880551112540698747158523863050715693290" +
				"9632952274430435576689664895044524452316173185640" +
				"3098711121722383113622298934233803081353362766142" +
				"8280644448664523874930358907296290491560440772390" +
				"71381051585930796086670172427121883998797908792274" +
				"921901699720888093776657273330010533678812202354218097" +
				"51254540594752243525849077116705560136048395864467063" +
				"2441572215539753697817977846174064955149290862569321" +
				"9784686224828397224137565705605749026140797296865241" +
				"453510047482166370484403199890008895243450658541227588" +
				"666881164271714799244429282308634656748139191231628245" +
				"861786645835912456652947654568284891288314260769004224" +
				"2190226710556263211111093705442175069416589604080719840" +
				"3850962455444362981230987879927244284909188845801561660979" +
				"191338754992005240636899125607176060588611646710940507754100" +
				"22569831552000559357297257163626956188267042825248360082325" +
				"75304207529634504345065854122758866688187623876897346587638746" +
				"878716427171479924442928230863465674813919123162824586178664583591" +
				"24566529476545682848912883142607690042242190226710556263211111093" +
				"705442175069416589604080719840385096245544436298123098787992724" +
				"428490918884580156194085617896749154073177542348186018991063" +
				"041595713397628837358144605304401379350386703875881113409590845" +
				"1904951748112988280185365108411729322";
		assertEquals(ans,b3.toString());
		System.out.println(System.currentTimeMillis()-start);

	}

	/**
	 * Test a^b = c
	 */
	@Test
	public void testExponent()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(4);
		assertEquals("16",b2.toString());
	}

	/**
	 * Test a^b = c
	 */
	@Test
	public void testExponentV1()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(63);
		assertEquals("9223372036854775808",b2.toString());
	}

	/**
	 * Test a^0 = 1
	 */
	@Test
	public void testExponentV2()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(0);
		assertEquals("1",b2.toString());
	}

	@Test
	public void testExponentV5()
	{
		b1 = new BigInt("9997627346579629374589273469576239487567865876583156452" +
				"34527572134658976812736561523976234987569287345786452456256234758213" +
				"74659723498568716235476512475412384726938452693874234523459698765464" +
				"32456789876543124678972346957260384757234623475698723604578234583452" +
				"69348752984375872345678972346789654678976578245623457256753264518723" +
				"65487123649871692387502348570298346597658123454325697619238745987629" +
				"73475671253874587126938746978123412346759765761923874687165237469872" +
				"45762983475698762456334578687423677623878793129889823477764763242389" +
				"78943789879347895873495793847593847938749384998734957987938457987879" +
				"87090980934598345768762736547657623456453654365345423542354325432543");

		String ans = "994085224221207683404052562131196477873273496090637133888249445196923380636521232210352462672245258795589240311541564622563957314405023137148584760477140139131628974735989864919208754019256" +
				"501708914117490836352145319252743414215308673292436605252926770611696479948132256987721733491662616546541883500653413023717773124700330058338899456293281840862241725177029751120618539154381" +
				"831917450921357588573035390418979557936686112093026263722328993427459824570104230878053623233232203891732517370131260010658806445064515459897162627406959594279176493726477652862348857746915" +
				"492803662647913034982499890395156430595594762402049233102797232490974278335189363700763192418773801255561973321404007519092689364559266477554082747247273797477143333846903916996634973085595" +
				"564519011878038109573858666231633397740050280363222100892105985905237656970265148395764639945455380520569888240983311853334202874399391780578063832507793492286079842852922017031432522913210" +
				"869009928947078755429564242591612996269064460123661261847407986982503124163848071403637760184832787216111929228649183006038264287162281365595004475266514740088692550003946692371080970462813" +
				"779473342660231341016724305422094354572188542904108249256400060332846647405510294271337529568563916557219430560295875084345400914721877771492990178489590045224052763353857830659721682872806" +
				"542008614287321491045803345177904889162709886609169260504184111559773250905146636544847314010806548979374271837058631535603987291544301015035531902257542726791698864561955862825649736284585" +
				"689226624862073696532867657159983249300117983458201203820455913823841612767077517031690956422212238861800673789771536969187753742735643339058681967980379731195247627762182372010624980446935" +
				"259087328201065825844134736276583464431607295236966850046309742644420415714726174729492730870317893038399860778093978152404002388373635691317986266249376545830996601244919188065511680466267" +
				"558532475215831726729871778735548752003650856731787170027454246257565244614667838734994338413386257779542169709658161217384715623736799490131334651127709803606645194985654948062910885504106" +
				"079294195114683475822985891808971214768585876310557679511740136188998974520197595312686066465190707597094782404256133258946641407614469731519636046904618875797935229145313306948391559698536" +
				"204115439154355050350834426102928039692228371332073722832985607658213924637199960560459102679706598242154715688955362205194174317435836636975524941209457271737666617882986993336533130640351" +
				"431824657149587157476385891704680092958595073022394031164298346920000935011615023183644200574795414206841026487042127628441782419008504643186707003263312246680249386942466380877969399318372" +
				"169248809887482029106035349127843861189480621725652187357288215458656487290209998132224480549772711847627711723749543179689751825458065838033454911619039092925561042111716171635158582946671" +
				"726528332781074044764546467535288191331302007620867233590948861482991096080741868760992677219085919697938875005669981927378421050516535756813648264053477921735394782837785404576650706399761" +
				"357427966880739638547035201166545026773372053951895522939630643754901172162071830366391767026384005077240469408926213217748727606219940408244201161334520626752028199808691470058900657356901" +
				"157398636948274223614444033939781862510634444777525747704631648257628773539539222035648704129892437306029413464995349904047731815459233707010511142914730333714043713835979091666654162977382" +
				"292874462748445412206282096682150158452927435400336300707426503630133413069433637728307684402236104223537456547023737911191129458486199097914909784477854108390424652371123154546808266492069" +
				"910524841272384088411487527813777593593099679994676124651264499359670833371321373442778142688627690753007312313729600941347356082220441963402794903867620180384269134519159451447387971520188" +
				"755951463546175929808125041707921046082840883229712573639355938979254569382196514544676534200329989298755379601810058016632774084920126451105953445984140276428911709851402652816228035112376" +
				"029150696770931797912387560224919127172363306944285988591898988920151877987487592292969592191735058986364861270483109007814629416231041700752143827106837777261401251609736131955978784757901" +
				"286793872042112380973435945539305417979636933497844781252031646492124816268776459109411886516613941914165786318586823205948755058865730117597351328671983233425846277024642727929651509966005" +
				"177978072264696916160205237658223758192912630980836963561049734077626589097288261562632908608646538815958376095817004086556776051268285251024857324627728679320641846127629734522212309966838" +
				"625260137640725548437374182429325492121232503876374862149929781263355197859448072726167214333770567153245629776557853548582061043966339758093290811947617304651362967841722781145410484184794" +
				"878427136233053813632331373609371095657087040878940775965149261608997100246081184643952209563330430286837158874671814707686875513181149867758900815719522741320792484504515238684999135827417" +
				"165457888421700731499826563581144494422847479664229997022732575173833600310945884865320478671810151425042925158379507909011896768105841542790029913864378308205624795829477528130086845752955" +
				"083722940387477885756316267456523123508517073127526596234754998981934785579933101782085356428717211131762932512687966448180170614963706488017367973652770682139539286431440358373189003979227" +
				"685074441059578006961418939211285683058506436052499130532122072518929458877610595507241950507201520876074912416081839691559933207716895773250051375464511809367242129361601234276438917629764" +
				"741450412541044740813991659856503543390619493512277589009329926479891314530684539685751506161582716101953582006759132484492480684866561860789023499147065456584916738383001518463851493939802" +
				"956759220003455311785037199446588936240886706596391627540441064083341323862908688129834218324978862502259031377511640231691925291697151246030682421089868770678290554778207313013726812672630" +
				"899357323262800671516686123956962781103644271112708563712088711298276423323476318200100134602025240590918118160048402256361553027583737693638333132185818800357759368874100643010642787583083" +
				"218965581995606424123958956675748725639925852350417898052517505200463938976188232298221524221370034991411148020798361655465906576805550174911504995962166001632416291443924042752217329304189" +
				"327468637054156245300981406980292763787117170385333657273113393294060647099352693550151686399211951241062813371225779736400933518094243541297517191698612065306033048414902180882044699059726" +
				"156885620859844560600013470861861027904975168203834646209226669374915806059585615497936709278749486027284588298797623141914194981946743831973510895015176596975238819066708029229077269486005" +
				"233295047266495045695979215667505908113978966267489957635012610968953197239729334246107540217545488321108877198759440391088965262734258970741230330362334762933409098684456451695012632320535" +
				"902288490774970754903885394927543134205312828665292583250785708834455334870748536771233116839007990982535773529632298409133139042113831083449999115370228130170836803322336480536683799697944" +
				"584885270699940886299252657250834588899961583697257591516690752469762832837028284466141192239141612409807327338797053274831389458328155631140735980985289561505068317310031041571574864099202" +
				"714400873765527376142824111594887118132970233976389911131480109467265066198296859419601463958163890796443205421379497529254816350016580615461687364178233058656001849716096355204909884132679" +
				"484436287399290396198561793075880823175183066452807714699444651167403273442533133643610704848321070529363932661089048809744487144471569402832487640294144937107504779634326592468450282370736" +
				"130352146900749829259473489151655324779210209204374311177900766689100528821239694913060159974043352303872051482432736244556324919987136259839918893796430204497644907302887893514852896904295" +
				"194971280618307862868814409054610944475061881407519379801017311612268114842886254259229096717393567081160540008443254248322918900665460473396195560644812116894196902460505168799325493357282" +
				"803704732621413400557612208553786454518652075513573704429536105246779924019909897726579115503283995907418142673183706056730504520762519174290698935851557782349737081529255977642786394623586" +
				"108207916128335907470245204383177167779817367396949320802041260577882518638487272652001263591126805085316796866443493752581636246248919685069565770219427041357386982691165651358663910820103" +
				"270583214649202919677215580304298803196483720786988832906569262800990388538635214986824470876080906652199465789657850095478130254119099722386792223352724763170688945333586268871489931799767" +
				"360695160043496172956519823088729578563380681640767332179074303416498769615829901849030757135145674031340178170156186089338556741792063118597133656134730964299606324710656631736192286891075" +
				"059592871297336529048104448940005492610941961449905736911649134710364205400092977460625824300177080136641289646756342559440593685824037072676564901247150387173384005435502593876395866946240" +
				"675140427988996396437720401540412537362114325414583094880706412736340733478987723967801870641950345922409010705496006828342509388289404961261829956476608201575469364738472569837614197434773" +
				"950627525462341735311110609413245175584995568413593823201697952078026753673617090417864473882764802978649937244831517615566043435246862724671699043127871040366339932417841698761488566584775" +
				"347181375600086414085411260561701885424476887072913093972036260764710788665652523051535475881643339488606134759387312050015153457780467006061981084536355267314239560932789639247920667167890" +
				"397904388299673332467318073007479539053091179348895635640314784349797307548894149561145816823579939087854653187588840278558610295042202167555602460864789413728072037739335053300523744606479" +
				"092080412011958024503349134762044340455940875717247830584405675834395178327360532166905583452957610173011138665072920291594410115604363207518728822663534452127016679886176292594214546638642" +
				"690412692044396969866545912910172912472706646177605069013922366420795757316132233750055471635476376132354452155202126726872720398956129774322043005071279591791335426424654698332697989423485" +
				"214833903658561372659827121068514372285552497572777403893978361363336332121187384574356656382740141323514444664813745371041806305178316400468400236095077214612987816211393543051363048873933" +
				"202726838730426880109434358938039315984825555193082410785193369030264279167280293046468524089020701925927356583239546110629238866279344602068281425303090691245935980524146978276234364048878" +
				"771301814775089132224640346053299868446227226959459687170647207839384492952080392549178624926276321876242073592193652328394016667876132680533189723221955824992859683196145109912672917041054" +
				"821676101888597376900173304907020637109414439373217251587863724044696984841682534975875835670271317479718900524897936301457608685893739577653178211817270159664875302248331216788652199283134" +
				"961636595106327432193953097691008883919395541110525484117056454377071840404518812717258938413090369472089641150325363960840800725662151824364236350524505203338189540268245452707011273098965" +
				"607031696781265102749037369973423774525866440572940183126774360707038398437305979987488122698523236660444863269341706844181685528735497141463247465016467480064706660848721156741699430301184" +
				"794498881349139117358154467851015824219061437002036811688322374074724228251091752528265281208419461675360561288709019594619193974498902581806948923468099516852791474395981548251622470939716" +
				"638998249720748106259448546736580702192519817263355693274117904971368917983792694456648727069082159224249078019841499561801403174532885838385881003285460300963085581499756813451301497104247" +
				"170659841380927219982256816502475444247787238955370549260191172337046698552657648695363137282668778933850805774986509772815564924494875029385344055326000087815281177171379948329760494122202" +
				"345753001803547963218058819250636330579222839473350171341028779596792462947675755875384406297637298369017212339156147341507523219857249365719174839437838767250411091768056315792707080506495" +
				"401666784900932036310039749417697190062495610382022017951205246976692526504448078631217018512182410475412172185222980776957982541231591884319647253782094728174691652514292403219590990302370" +
				"396988141169912650459372667343225753053491439435261244495433229229498770497726537378742662052441130410455228587832692822522225194204113573756181363671026425111668474435428133354182395121668" +
				"879624908792335457609392098525070529443516186072860977795638105122720579955970080697553396402057644365511278431519565139812900774932880297532567189763493765112909295465494946509824888573941" +
				"114871437527982888372566609199245420598421838984871438433950120935567611900128552769133566915530879590034776463231179203396242590107958818894145308234683361225465064575183271250120110032490" +
				"456785837515161994370559863637821281844228558426100627380390756795272801670170091564546188643185725963850407495104546344754615400526729775670928195777508630039132818184577527363530707500681" +
				"707515016752253672878316366195084204445469138874853111180185692544821617884332828254751059867527634888476590235121308151157193384182269506404324634612232253336726620110552825964440649883277" +
				"957808642077972063299213908086946961868139098456198086184840760610351628614989854206506730041917964884694451400799732626348908286562573920536094772564960903434182104201702622823533530908691" +
				"740775793638397378318124051405760437312822478640479431218236531220111436510698806989814251997013413576454285146441160822664708907273942326202598112717588108957346951268078584410755030283777" +
				"543848555490552390352908247762024499278371508077712985351696429681433167315312738607562884557707220616403271406895215089128669241764230616885651814092546548241895646224573119601233009994634" +
				"080166874438080965714009193218759871894713704668576567336075433413198633092714186696862345328688640847387122113665188039969412857803414019658774649899874136966152126296204188228186614535209" +
				"081117433699243798315028233501440231065100165990745863110240717535500615577817043737694686965523191222525244320097485771637645496228140590618482767315937476054824221913384158361994542981549" +
				"518530357420693283759568543046982699307544259535341307467325736592199928271305338719890196276171760165376528105185788593570683144875632880179743994587963133359081965564394831152309959018859" +
				"816699499116363706879151436958985524804673952710933023855848116723379712551083382537795788165263362261336859976310804623291675293441668057058600432139411762529848830409730655254567974873499" +
				"253547791356382425580903833756986631100069368324546544148979135531511889042777567738051174237469885443921271819681752930942157750981830943807427028553603976414437680542974694591242858662389" +
				"682549556841914436440295271256078161735733743882411863908367537498905624018481983131183230923494286100021220089729211123920809595882381068571938712523564319769955656503844823228897688705025" +
				"075213092334086393755223522416404043566101208372706284375542740494038919382560538090952012834857428550972644687679922291622838360021682558670247260532185598317270771336232274785886138913704" +
				"135669492126164726621209540435999761855823983294877962571527809689876261032602413967279060813326908772782937813936162433277692966948886799545134332520820412554824876657909388055459799132867" +
				"378778970667981234338889049953617747583269077325533318849224984833210760036455289184238450721255291123840500501598695222784255706173793163926539870670740449692393692088732098043564551359884" +
				"255524045468411834322214643563985179305911413537354232705013804620217944828696375029793612600190061092409145740577588158552120144649288210033837330635444539585225718206760266126102428365786" +
				"123373931175277814337343398627941289858016258995988144381854311858693820467013196445889150727904495391713229579302806087126749905319834132813503628234101251965448974096372781936880799345110" +
				"839012884302733485872399499792828950713444382420555710807144571727522786055777738331994567007118622026230957471027570676563416440950231321004174246037519006474790308494746761053030047058745" +
				"674230583264888650768104260146232188072838120186870313758128938904785074087683873811157947024972870277150628055747907675047477260180151190061491314443368810671969445909727609752273654197152" +
				"626268661367928016509980373342645695778560537126270109784493502803253047400836241907390155641177632861912079239833347931098433305960232360038489294035353582518774035511536536647500263084195" +
				"557117139016745131547100342574615473617176330084112641801231232736278165236448372396541141857949681304304017705156092778679601976820994798119544869152115962426254489646341690381125633184760" +
				"177215141594569640931652030233105516674793213661513053637550949898294376918563548383684936768852393447943986649229144759002546207515485879312998231425833878097308127322246101567361648196093" +
				"7949122019456360594887659371248991481662943";
		long start = System.currentTimeMillis();
		b2 = b1.pow(25);
		System.out.println(System.currentTimeMillis() - start);
		assertEquals(ans,b2.toString());
	}
	@Test
	public void testExponentV6()
	{
		b1 = new BigInt("2");
		assertEquals("32",b1.pow(5).toString());
	}

	@Test
	public void testKaratsuba()
	{
		b1 = new BigInt("1234565432123456789098765432132435465768798097867564534231" +
				"78909876543213243546576879809786756453423132132435465768798097867564534" +
				"3213243546576879809786753213243546576879809786756453423164534231321" +
				"32432132435465768732132435465768798097867564534231932132435465768798" +
				"09786756453423180978675645342313546576879809786756453423123197862345234" +
				"3213243532132435465768798097867564534231465768798097867564534231" +
				"3321324353213243546576879809786756453423146576879809786756453423121" +
				"32132435465768321324354657687980978675645342317980978675645342313243" +
				"5465768798097867564534231");
		b2 = new BigInt("-812736453672187236453672187236456372180192384738290129837" +
				"564738928347564738298374656758493094586702934857483920394857" +
				"564783294783567429492478356473992473856742938564792835647297483564792" +
				"1563254736487325463727638172364548572734789572872347587087045667762345" +
				"234562347692837456786987245687304568703469872346782398475692783465782345" +
				"571235647865817235647865654756278346879693456987693475698769872346");
		long start = System.currentTimeMillis();
		b3 = b1.multiply(b2);
		System.out.println(System.currentTimeMillis()-start);
		assertEquals(new BigInteger(b1.toString())
						.multiply(new BigInteger(b2.toString())).toString(),b3.toString());
	}

	/**
	 * Test a^b = c
	 */
	@Test
	public void testExponentV3()
	{
		b1 = new BigInt("61692346587365487659127346987691834758765324875" +
				"872364981723694871628736487162534871652398476934875692873458" +
				"726384756283745687346582375872365917823469178263948716293847" +
				"691999912635847658765817236547865876581723654876587658761523" +
				"8658162354861528374651823645182364518275182374651283645128374" +
				"6512837465182734651827364518273645187236458172364518736451863" +
				"2451873465187376581562348571256345172356471234757187234173541" +
				"27354172354");
		b2 = b1.pow(4);
		String ans = "14485222119148599692759100060057520383038016902044135650" +
				"7144389331683856253167497754184392391155619542182717453446680" +
				"4437964395116974967166081568284461230634574938305333334782720" +
				"9790071623385443255667711551614080316392175231140310268077191" +
				"5190795361167735044748465813935878146742903652747539699871023" +
				"5787600965190161433682744079270925399553832908786056470776072" +
				"7209978941209536699549810501282392411570206302256433955635299" +
				"5382862673213438168166451632192025919228778411421521629844598" +
				"7864801019845112973807490412082475452351867414013348072704581" +
				"7656318710800273961947642590028481803306978254778317472957389" +
				"5867226562965230985203934443080899955835048703783138716482503" +
				"5990340424349290778199403240456585776251120839954856720385690" +
				"2716192641837105667758390396976451499700927865365560266081064" +
				"4728351382643147821031228334613768019870810142746921846693245" +
				"4819189900336947720116084813126373780605369818028892871345595" +
				"1567356082996246222246419155119042295749729041849333739007297" +
				"9119400620434183424061300844243077084910965745590490996334939" +
				"4786230618380226877405456883369379371768584052273736443151693" +
				"3340624683560975264192999034857364033102327100908743843326732" +
				"1304868088575841873484203212598827354546581643019584779723735" +
				"5130246619065288942016180848490062249804337452046925642826375" +
				"2224855413487318757648964467162363373141577469574406213958008" +
				"1986018052739876368588357371442151248918495426554421581721582" +
				"1613061997347334320879503509809073504335169209936146917398554" +
				"0423357494170525752878507773362987826697052696917951579100020" +
				"1030512485414898361833180625624058435188241600240242460958065" +
				"7592733672620497600992106064719062116773476351956522665004899" +
				"274834756082583179328559127639603354531856";
		assertEquals(ans,b2.toString());
	}
//	BigInteger b1 = new BigInteger("20002978345692735692387456298742" +
//				"293847592874569287456928734569283745692387456927834562345" +
//				"234856239487629734568237456823745827651037421345" +
//				"23458236459273465827346587615238476155648173465972356987235682765" +
//				"23475628347582734578623452345623456" +
//				"3456345635674567456745673456345623452345" +
//				"234523464567456745674678467457457457653763" +
//				"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +	"763673673567456746678467846734562542345234523453464567" +
//				"4567467846785895986789678967896789678967896789678967896789" +
//				"6789679854736324756576547654756476547543634254235432" +
//				"654375648765865465432432143215453765576865" +
//				"75475436543543254326544876597687678737732678347732477623" +
//				"36746623871234677632677413676213466732487987923" +
//				"4787237878234787234887327667235677667764676723" +
//				"78677278782359789235852134565213478747890978007897235626890782070789234" +
//				"97787298236662553245763287890978023879082147527723468707890235" +
//				"32632473283873623497802978027890238723863147623332613460876087" +
//				"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +"7778767277237724667326723673127326327324727278978927236723" +
//				"763672872349782978234673245623467347890238471562341" +
//				"23418236451872345817348716523847658917235475187354123451334");
//		//BigInt b2;
//		//b2 = b1.pow(15);
//		BigInteger b2;
//		long st = System.currentTimeMillis();
//		b2 = b1.pow(15);
//		String num = b2.toString();
//		System.out.println(System.currentTimeMillis() - st);
//		System.out.println(num + "\n:" + num.length());

}