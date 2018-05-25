package RevisedBigInt;

//import org.junit.Ignore;
import org.junit.Test;

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
	//*******************************************************************************************
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
		b1 = new BigInt("100");
		b2 = new BigInt("-100");
		b3 = b1.mod(b2);
		assertEquals("0", b3.toString());
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
		b1 = new BigInt("-123");
		b2 = new BigInt("-123");
		b3 = b1.divideBy(b2);
		assertEquals("1",b3.toString());
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
	//94568792345667874387998734809908238767938798072390889776672378234789234//2652837458723568623948756928374958276
	//35648166846667761186467552295124648
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
		b1.
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
		b1 = new BigInt("945687923345678654324567865432567897654356789876543245678965" +
				"4324567890876542314567890887654324354376559786987658765346543243215246" +
				"53765587698769345667874387998734809969827359687236475862386387465238765298743659" +
				"2874659287659827436958769126456715237486593845762394856928374956827364538475" +
				"287356928734659827346598726394876592837456653243265476798698768764" +
				"5423423654697860987697752376127345182345975613487658127634082387679" +
				"38798072390889776672378234789234");
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


}