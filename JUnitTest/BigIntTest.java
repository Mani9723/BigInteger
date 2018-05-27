package RevisedBigInt;

import org.junit.Test;
import java.io.File;
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
	 * Test a^b = c
	 */
	@Test
	public void testExponentV2()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(0);
		assertEquals("1",b2.toString());
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

	/**
	 * Test a^b = c
	 */
	@Test
	public void testExponentV4()
	{
		b2 = new BigInt("61692346587365487659127346987691834758765324875" +
				"872364981723694871628736487162534871652398476934875692873458" +
				"726384756283745687346582375872365917823469178263948716293847" +
				"691999912635847658765817236547865876581723654876587658761523" +
				"8658162354861528374651823645182364518275182374651283645128374" +
				"6512837465182734651827364518273645187236458172364518736451863" +
				"2451873465187376581562348571256345172356471234757187234173541" +
				"27354172354");
		b1 = new BigInt("12944390560451711090262974377695806736704714261734504929" +
				"825946498525799947768827877338371615005511566952478128767935430" +
				"250059647987976294968927072832937366717262628349177302086748401" +
				"02129927447468372721289692096413210867180078161924699243935025076" +
				"607462763432857814500408244403474256271429566044467859292376571" +
				"36084152467846762671094153995106061047265245501783288990859524697" +
				"1805001654547639998096512327826839056828630774150893959424454681" +
				"392382237173398097042406719197606218255199962827860086488288489" +
				"406466736657539557188433177268177031819050028668679122949391199251" +
				"1545133646901802055595700630405386773185131020795300626216927674550" +
				"373531609036796146946033040079768168725880236326271085219043575041" +
				"81464016715010701051467720690841041985833199045819224756232573892330" +
				"852551445782786538199429495554990317979097864277897831716301878271347" +
				"17536390202455974872104051763820042078897613584064926687898975767330976" +
				"847476543241023713558738551800490252315847142491836544698782959042160" +
				"0172944093508135915339867410274714671012703510974064712970330104830" +
				"87718315892307968520175643995053380550028409158902151735404915982541" +
				"870789459532775489426701051991639424346994990230770853009684673907704616" +
				"4894798877575657137272733851481973050437334547616086688832558615457026858" +
				"0054359023817990439896229322133501333363861228186280945254392819441043" +
				"476338488334454172057478532615864390027999333395517749885718738718179852" +
				"0417125169616816233358187119778175921649613982282867919682114715606009796" +
				"5477506977962724824434911965712259016858047880733170802838492950418778543" +
				"63415217621146623949601899020384266005635378176508989171129463952946043491450" +
				"7211109352536281866996413077801921863009732539196618196730303299927768570" +
				"466557143589785273015632874904353182093465473724856986626559583856465906469771" +
				"596005007840268146357682971537632531094815349455706100301948988085388219425185" +
				"908145436913869834852596221113159738808020601230439614650055717622975370088712266061261600002883" +
				"3264954533761406852818477046316614310020347895149075559704" +
				"9024319374452887288518725141996493206821742960785734198207672531231355673680896" +
				"7785017676660494021665828622395114870" +
				"63494466236337997318862619954752817238381343876138415177328493025170377099399669" +
				"8770142299487807170519847984405182246660119715958736698477402085984084074412579551088" +
				"85717394896662204979789989132019690496438159644211447669543530730359" +
				"58867148150570645779071870673331160282104450305227229915238624121647575002628379" +
				"794963303129737481209751997932907210853616906484877412091" +
				"3937187920891227951144997748979594969275913917960093801310163486106982836563513121" +
				"663938176625515109636407893389361251485652758130363203090019067270662353261120667212834825262369792" +
				"208201738228516647816238982231911037015028614476" +
				"80245397973605207105919738756915072942039288305437150292020423691211712845429132761" +
				"90765070682211045620324835315598492497127949447103354504264664435060756" +
				"6799377178209336886487443386534118920373917020004188781238941" +
				"29878984127302985662996071378648384752180794169904242202024919458787482417729145562704821" +
				"99564419269187248224954457853408646030065499644578396244403524334217468450575974313288" +
				"9005513258952009800058499275980188130045802898262772555849" +
				"851867118310168849220117484010571415341068658758028506981943610547391723491383841096614976623879" +
				"16334316465381983276251857167534864186272367607618800663810627449124308100114406615953613169969" +
				"3865317105388360606559943946050941324051521464451791431175602310349077380032251236" +
				"8073720654432747309558549553841714821236658648180869746637" +
				"1643220457636620766893554585453347557788489664364643863221813965784491939351714555228716420" +
				"8950744868010918655637173260930199456665097518213" +
				"6027524202648895270701413305267726554112479565878284129330888789964853315408199256" +
				"596191789664960780555517981106011496409930896856093771596215468544");
		long st = System.currentTimeMillis();
		b3 = b1.divideBy(b2);
		System.out.println(System.currentTimeMillis() - st);
		assertEquals("1",b3.toString());
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