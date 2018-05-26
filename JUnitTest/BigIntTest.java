package RevisedBigInt;

//import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class BigIntTest {

	private BigInt b1, b2, b3;

	@Test
	public void testSub() {
		assertEquals("-988", new BigInt("1")
				.subtract(new BigInt("989")).toString());
	}

	@Test
	public void testAbs() {
		b1 = new BigInt("167");
		assertEquals(b1.toString(), b1.absValue().toString());

	}

	@Test
	public void testAbs1() {
		b1 = new BigInt("-167");
		assertEquals("167", b1.absValue().toString());

	}

	@Test
	public void testAddZero() {
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddNoCarry() {
		b1 = new BigInt("123");
		b2 = new BigInt("456");
		b3 = b1.add(b2);
		assertEquals("579", b3.toString());
	}

	@Test
	public void testAddCarry() {
		b1 = new BigInt("199999999");
		b2 = new BigInt("199999999");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddCarryTwo() {
		b1 = new BigInt("9876123546");
		b2 = new BigInt("987612355467434000");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddCarryThree() {
		b1 = new BigInt("76297436892");
		b2 = new BigInt("76297876");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarryFour() {
		b1 = new BigInt("7687187619897813458");
		b2 = new BigInt("6324567673108978079");
		b3 = b1.add(b2);
		String number = "14011755293006791537";
		assertEquals(number, b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarryFive() {
		b1 = new BigInt("1");
		b2 = new BigInt("-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"-6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321455";
		assertEquals(number, b3.toString());
	}

	//@Ignore
	@Test
	public void testAddCarrySix() {
		b2 = new BigInt("1");
		b1 = new BigInt("6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
		b3 = b1.add(b2);
		String number =
				"6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321457";
		assertEquals(number, b3.toString());
	}

	@Test
	public void testAddFirstNegative() {
		b1 = new BigInt("-1");
		b2 = new BigInt("+1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddSecondNegative() {
		b1 = new BigInt("1");
		b2 = new BigInt("-1");
		b3 = b1.add(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testAddFirstLengthGreater() {
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testAddSecondLengthGreater() {
		b1 = new BigInt("1");
		b2 = new BigInt("123");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testPaddingWithZeros() {
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124", b3.toString());
	}

	@Test
	public void testRemoveLeadingZeros() {
		b1 = new BigInt("0000000123");
		assertEquals("123", b1.toString());
	}

	@Test
	public void testAddTwoDifferentSigns() {
		b1 = new BigInt("-9878735");
		b2 = new BigInt("-9878735");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddTwoDifferentSignsTwo() {
		b1 = new BigInt("-100");
		b2 = new BigInt("100");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum), b3.toString());
	}

	@Test
	public void testAddAnotherCase() {
		b1 = new BigInt("-201");
		b2 = new BigInt("98712364978612934201");
		b3 = b1.add(b2);
		assertEquals("98712364978612934000", b3.toString());
	}

	///******************************************************************
	@Test
	public void testSubtractSingleDigits() {
		b1 = new BigInt("1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-1", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsV2() {
		b1 = new BigInt("-1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-3", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsV3() {
		b1 = new BigInt("1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("3", b3.toString());
	}

	@Test
	public void testSubtractSingleDigitsv4() {
		b1 = new BigInt("-1");
		b2 = new BigInt("-2");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	@Test
	public void testSubtractWithZeros() {
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV2() {
		b1 = new BigInt("0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV3() {
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV4() {
		b1 = new BigInt("-0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV5() {
		b1 = new BigInt("1");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("1", b3.toString());
	}

	@Test
	public void testSubtractWithZerosV6() {
		b1 = new BigInt("9");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	@Test
	public void testSubtractOne() {
		b1 = new BigInt("19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("18", b3.toString());
	}

	@Test
	public void testSubtractTwo() {
		b1 = new BigInt("-19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("-20", b3.toString());
	}

	@Test
	public void testSubtractThree() {
		b1 = new BigInt("-18");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("-9", b3.toString());
	}

	@Test
	public void testSubtractFour() {
		b1 = new BigInt("19");
		b2 = new BigInt("-19");
		b3 = b1.subtract(b2);
		assertEquals("38", b3.toString());
	}

	@Test
	public void testSubtractFive() {
		b1 = new BigInt("1778899");
		b2 = new BigInt("-1778899");
		b3 = b1.subtract(b2);
		assertEquals("3557798", b3.toString());
	}

	@Test
	public void testSubtractSix() {
		b1 = new BigInt("-1778899");
		b2 = new BigInt("-78899");
		b3 = b1.subtract(b2);
		assertEquals("-1700000", b3.toString());
	}

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

	@Test
	public void testSubtractBorrowOne() {
		b1 = new BigInt("187");
		b2 = new BigInt("179");
		b3 = b1.subtract(b2);
		assertEquals("8", b3.toString());
	}

	@Test
	public void testSubtractTen() {
		// A - (-B) = A + B
		b1 = new BigInt("100");
		b2 = new BigInt("-101");
		b3 = b1.subtract(b2);
		assertEquals("201", b3.toString());
	}

	@Test
	public void testSubtractEleven() {
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("888888888889", b3.toString());
	}

	@Test
	public void testSubtractTwelve() {
		b1 = new BigInt("8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("16888888888887", b3.toString());
	}

	@Test
	public void testSubtractThirteen() {
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("-7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-888888888889", b3.toString());
	}

	@Test
	public void testSubtractFourteen() {
		b1 = new BigInt("-8888888888888");
		b2 = new BigInt("7999999999999");
		b3 = b1.subtract(b2);
		assertEquals("-16888888888887", b3.toString());
	}

	@Test
	public void testFinalSubtract() {
		b1 = new BigInt("000");
		b2 = new BigInt("-100");
		b3 = b1.subtract(b2);
		assertEquals("100", b3.toString());
	}

//****************************************************************

	@Test
	public void testMultiplyOne() {
		b1 = new BigInt("2");
		b2 = new BigInt("2");
		b3 = b1.multiply(b2);
		assertEquals("4", b3.toString());
	}

	@Test
	public void testMultiplyTwo() {
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyThree() {
		b1 = new BigInt("0");
		b2 = new BigInt("1");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyFour() {
		b1 = new BigInt("1");
		b2 = new BigInt("0");
		b3 = b1.multiply(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testMultiplyFive() {
		b1 = new BigInt("-2");
		b2 = new BigInt("9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	@Test
	public void testMultiplySix() {
		b1 = new BigInt("2");
		b2 = new BigInt("-9");
		b3 = b1.multiply(b2);
		assertEquals("-18", b3.toString());
	}

	@Test
	public void testMultiplySeven() {
		b1 = new BigInt("-8");
		b2 = new BigInt("-5");
		b3 = b1.multiply(b2);
		assertEquals("40", b3.toString());
	}

	@Test
	public void testMultiplyEight() {
		b1 = new BigInt("4");
		b2 = new BigInt("13");
		b3 = b1.multiply(b2);
		assertEquals("52", b3.toString());
	}

	@Test
	public void testMultiplyNine() {
		b1 = new BigInt("1");
		b2 = new BigInt("-10");
		b3 = b1.multiply(b2);
		assertEquals("-10", b3.toString());
	}

	@Test
	public void testMultiplyTen() {
		b1 = new BigInt("123");
		b2 = new BigInt("23");
		b3 = b1.multiply(b2);
		assertEquals("2829", b3.toString());
	}

	@Test
	public void testMultiplyEleven() {
		b1 = new BigInt("-23");
		b2 = new BigInt("123");
		b3 = b1.multiply(b2);
		assertEquals("-2829", b3.toString());
	}

	@Test
	public void testMultiplyTwelve() {
		b1 = new BigInt("9998");
		b2 = new BigInt("8765");
		b3 = b1.multiply(b2);
		assertEquals("87632470", b3.toString());
	}

	@Test
	public void testMultiplyIdentity() {
		b3 = new BigInt("1").multiply(new BigInt("123456"));
		assertEquals("123456", b3.toString());
	}

	@Test
	public void testmult() {
		b3 = new BigInt("111111111111111111111111111111111111111")
				.multiply(new BigInt("2"));
		assertEquals("222222222222222222222222222222222222222", b3.toString());
	}

	@Test
	public void testEquality() {
		assertTrue(new BigInt("12345")
				.isEqualTo(new BigInt("12345")));
	}

	@Test
	public void testInequality() {
		assertFalse(new BigInt("12345")
				.isEqualTo(new BigInt("23456")));
	}

	@Test
	public void testInequalityTwo() {
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("23456")));
	}

	@Test
	public void testInequalityThree() {
		assertFalse(new BigInt("-12345")
				.isEqualTo(new BigInt("12345")));
	}

	@Test
	public void testCompareTo() {
		b1 = new BigInt("12345");
		b2 = new BigInt("12345");
		assertEquals(0, b1.compareTo(b2));
	}

	@Test
	public void testNegativeMultiply() {
		b3 = new BigInt("-1000").multiply(new BigInt("-1"));
		assertEquals("1000", b3.toString());
	}

	@Test
	public void testSubtractZeroIdentity() {
		b3 = new BigInt("-1000").subtract(new BigInt("0"));
		assertEquals("-1000", b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityOne() {
		b3 = new BigInt("0").subtract(new BigInt("0"));
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityTwo() {
		b3 = new BigInt("0").subtract(new BigInt("0"));
		assertEquals("0", b3.toString());
	}

	@Test
	public void testSubtractZeroIdentityThree() {
		b3 = new BigInt("0").subtract(new BigInt("-12345"));
		assertEquals("12345", b3.toString());
	}

	@Test
	public void testCompareNegativeInt() {
		assertEquals(-1, new BigInt("-12345")
				.compareTo(new BigInt("12345")));
	}

	@Test
	public void testCompareNegativeIntV1() {
		assertEquals(1, new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV1() {
		assertEquals(0, new BigInt("12345")
				.compareTo(new BigInt("12345")));
	}

	@Test
	public void testMagnitudeV2() {
		assertEquals(0, new BigInt("-12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV3() {
		assertEquals(1, new BigInt("12345")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeV4() {
		assertEquals(-1, new BigInt("-123458")
				.compareTo(new BigInt("12345")));
	}

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

	@Test
	public void testMagnitudeUnequalLen() {
		assertEquals(-1, new BigInt("1434")
				.compareTo(new BigInt("133454")));
	}

	@Test
	public void testMagnitudeUnequalLenV1() {
		assertEquals(1, new BigInt("1439872347656187236987345094568798794")
				.compareTo(new BigInt("139867767878879879324767234")));
	}

	@Test
	public void testMagnitudeUnequalLenV2() {
		assertEquals(-1, new BigInt("-12345")
				.compareTo(new BigInt("-12344")));
	}

	@Test
	public void testMagnitudeUnequalLenV3() {
		assertEquals(1, new BigInt("-12344")
				.compareTo(new BigInt("-12345")));
	}

	@Test
	public void testMagnitudeUnequalLenV4() {
		assertEquals(0, new BigInt("9999999")
				.compareTo(new BigInt("9999999")));
	}

	@Test
	public void testMagnitudeUnequalLenV5() {
		assertEquals(0, new BigInt("-9999999")
				.compareTo(new BigInt("-9999999")));
	}

	@Test
	public void smallTest() {
		b1 = new BigInt("-12345");
		b2 = new BigInt("1");
		assertEquals(-1, b1.compareTo(b2));
	}

	@Test
	public void textMaximum() {
		b1 = new BigInt("12345678987654321");
		assertEquals(b1, b1.max(new BigInt("123456787654321")));
	}

	@Test
	public void textMaximumV1() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b2, b1.max(b2));
	}

	@Test
	public void textMaximumV2() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.max(b2));
	}

	@Test
	public void textMaximumV3() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("9876823456787654321");
		assertEquals(b2, b1.max(b2));
	}

	@Test
	public void textMaximumV4() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.max(b2));
	}

	@Test
	public void textMaximumV5() {
		b1 = new BigInt("-87963847512345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.max(b2));
	}

	@Test
	public void textMinimum() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b2, b1.min(b2));
	}

	@Test
	public void textMinimumV1() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("123456787654321");
		assertEquals(b1, b1.min(b2));
	}

	@Test
	public void textMinimumV2() {
		b1 = new BigInt("12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.min(b2));
	}

	@Test
	public void textMinimumV3() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("9876823456787654321");
		assertEquals(b1, b1.min(b2));
	}

	@Test
	public void textMinimumV4() {
		b1 = new BigInt("-12345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b2, b1.min(b2));
	}

	@Test
	public void textMinimumV5() {
		b1 = new BigInt("-87963847512345678987654321");
		b2 = new BigInt("-9876823456787654321");
		assertEquals(b1, b1.min(b2));
	}

	//@Ignore
	@Test
	public void testDivideV1() {
		b1 = new BigInt("-11");
		b2 = new BigInt("2");
		b3 = b1.mod(b2);
		assertEquals("-1", b3.toString());
	}

	//@Ignore
	@Test
	public void testDivideV2() {
		b1 = new BigInt("124567");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("1012", b3.toString());
	}

	@Test
	public void testDivideV3() {
		b1 = new BigInt("10");
		b2 = new BigInt("2");
		b3 = b1.divideBy(b2);
		assertEquals("5", b3.toString());
	}

	@Test
	public void testDivideV4() {
		b1 = new BigInt("24356789654356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("1", b3.toString());
	}

	@Test
	public void testDivideV5() {
		b1 = new BigInt("24356784356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testDivideV6() {
		b1 = new BigInt("-24356784356788765435678765434567876543245675");
		b2 = new BigInt("24356789654356788765435678765434567876543245675");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());
	}

	@Test
	public void testDivideV7() {
		b1 = new BigInt("723478");
		b2 = new BigInt("-876543423");
		b3 = b1.divideBy(b2);
		assertEquals("0", b3.toString());

	}

	@Test
	public void testDivideV8()
	{
		b1 = new BigInt("-123");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("-1",b3.toString());
	}

	@Test
	public void testDivideV9()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("-123");
		b3 = b1.divideBy(b2);
		assertEquals("-1",b3.toString());
	}
	@Test
	public void testDivideV10()
	{
		b1 = new BigInt("-122353");
		b2 = new BigInt("123");
		b3 = b1.divideBy(b2);
		assertEquals("-994",b3.toString());
	}

	@Test
	public void testDivideV11()
	{
		b1 = new BigInt("122353");
		b2 = new BigInt("-123");
		b3 = b1.divideBy(b2);
		assertEquals("-994",b3.toString());
	}
	@Test
	public void testDivideV12()
	{
		b1 = new BigInt("94568792345667874387998734809969827359687236475862386387465238765298743659287465928765982743695876912645671523748659384576239485692837495682736453847528735692873465982734659872639487659283745675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "35648166846667761186467552295147864648407387010117545210497069369760356775723612591883930691747526823666524023682087212319354673970545706524263600694210911228566363495274354862904420122734302699117269964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}

	@Test
	public void testDivideV13()
	{
		b1 = new BigInt("94568792345667874387998734809969827359687236475862386387465238765298743659287465928765982743695876912645671523748659384576239485692837495682736453847528735692873465982734659872639487659283745675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("-2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "-35648166846667761186467552295147864648407387010117545210497069369760356775723612591883930691747526823666524023682087212319354673970545706524263600694210911228566363495274354862904420122734302699117269964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}
	@Test
	public void testDivideV14()
	{
		b1 = new BigInt("-94568792345667874387998734809969827359687236475862386387465238765298743659287465928765982743695876912645671523748659384576239485692837495682736453847528735692873465982734659872639487659283745675237612734518234597561348765812763408238767938798072390889776672378234789234");
		b2 = new BigInt("-2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "35648166846667761186467552295147864648407387010117545210497069369760356775723612591883930691747526823666524023682087212319354673970545706524263600694210911228566363495274354862904420122734302699117269964294635100459535418204949345990";
		assertEquals(ans,b3.toString());
	}
	@Test
	public void testDivideV15()
	{
		b1 = new BigInt("945687923345678654324567865432567897654356789876543245678965432456789087654231456789088765432435437655978698765876534654324321524653765587698769" +
				"3456678743879987348099698273596872364758623863874652387652987436592874659287659827436958769126456715237486593845762394856928374956827364538475" +
				"2873569287346598273465987263948765928374566532432654767986987687645423423654697860987697752376127345182345975613487658127634082387679387980723908" +
				"89776672378234789234");
		b2 = new BigInt("2652837458723568623948756928374958276");
		b3 = b1.divideBy(b2);
		String ans = "3564816684248355875105607573260630918716186860044847548013992664620406893867248927095" +
				"671972831923320457176116452295967863358254340559150140721099029324374957836402828146958393053" +
				"2046489796410847545065164837511194879698020623259540545394815390198103518584867533037468159297" +
				"501297754392761566508403437281769711017879159019501009062567592982701312865503972993152747175" +
				"99017606724002716442664607106204242357491967327235";
		assertEquals(ans,b3.toString());
	}

	@Test
	public void testAbs2()
	{
		assertEquals("1", new BigInt("-1").absValue().toString());
	}

	@Test
	public void testFileConstructor()
	{
		long startw = System.currentTimeMillis();
		b1 = new BigInt(new File("C:\\Users\\Home\\Desktop\\IntelliJIDEA\\" +
						"IntelliJ Projects\\src\\RevisedBigInt\\TextFiles\\dividend.txt"));
		b2 = new BigInt(new File("C:\\Users\\Home\\Desktop\\IntelliJIDEA\\" +
						"IntelliJ Projects\\src\\RevisedBigInt\\TextFiles\\divisor.txt"));
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

	@Test
	public void testExponent()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(4);
		assertEquals("16",b2.toString());
	}

@Test
	public void testExponentV1()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(1);
		assertEquals("2",b2.toString());
	}
@Test
	public void testExponentV2()
	{
		b1 = new BigInt("2");
		b2 = b1.pow(0);
		assertEquals("1",b2.toString());
	}

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

	@Test
	public void testExponentV4()
	{
		long ss = System.currentTimeMillis();
		b1 = new BigInt("61692346587365487659127346987691834758765324875" +
				"872364981723694871628736487162534871652398476934875692873458" +
				"726384756283745687346582375872365917823469178263948716293847" +
				"691999912635847658765817236547865876581723654876587658761523" +
				"8658162354861528374651823645182364518275182374651283645128374" +
				"6512837465182734651827364518273645187236458172364518736451863" +
				"2451873465187376581562348571256345172356471234757187234173541" +
				"27354172354");
		System.out.println(System.currentTimeMillis()-ss);
		long start = System.currentTimeMillis();
		b2 = b1.pow(9);
		System.out.println(System.currentTimeMillis()-start);
		String ans = "12944390560451711090262974377695806736704714261734504929" +
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
				"721110935253628186699641307780192186300973253919661819673030329992776857046655714358978527301563287490" +
				"4353182093465473724856986626559583856465906469771" +
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
				"8950744868010918655" +
				"637173260930199456665097518213" +
				"6027524202648895270701413305267726554112479565878284129330888789964853315408199256" +
				"596191789664960780555517981106011496409930896856093771596215468544";
		assertEquals(ans,b2.toString());
	}

}