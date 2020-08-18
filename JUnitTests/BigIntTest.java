import Exceptions.InvalidInputException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;



import java.io.File;
import java.math.BigInteger;


import static org.junit.Assert.*;

/**
 * Detailed Test cases for {@link BigInt} class.
 *
 * {a,b,c,d,e,f,g E Z}
 */
public class BigIntTest
{


	/**
	 * BigInt objects.
	 * {@code b3} holds the results
	 */
	private BigInt b1, b2, b3;


	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Ignore
	@Test
	public void testConstructorError()
	{
		thrown.expect(InvalidInputException.class);
		thrown.expectMessage("Empty String");
		b1 = new BigInt("");
	}
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

	@Test
	public void testMultiply3()
	{
		b1 = new BigInt("98762394869817235487615239476918237468765345" +
				"23457236457629384756928735478152384751827346918273469845" +
				"23457123568162358476523497659238475987236495692873459865817234" +
				"2345782348756297346598762349875698787152384658756123654756817234" +
				"234587651872365487659712346597812346765213387465871236546");
		b2 = new BigInt("98765432109876542310987654231098765432109873496982345" +
				"2345676293487569827345789846987456" +
				"456987945698749609830495987239847872364876234534587628374676834568345" +
				"34587623874676654651423654652347868345837645745645645645465456" +
				"4568768347623465723465723654765823746587345876348576834756384756345345" +
				"3458763847568763765723654765762387687");
		assertEquals(new BigInteger(b1.toString()).multiply(new BigInteger(b2.toString())).toString(),b1.multiply(b2).toString());
	}


	@Test
	public void testMultiply23()
	{
		b1 = new BigInt("9486987498273459872346957862349857629834756928374234598769283745" +
				"2345762934759872346578698768712354876587698723469876978698723456587653485" +
				"3245769876918234123412349876823745234523458769287458972639487569827346958762983745" +
				"2345723465897234058923475023456230487569786587658165234765763248756987234695872345" +
				"2435764786587698734659876987634985698769876345876987639487569876394875687653847534" +
				"534578687126387634876349856394856938475697869287346987634765876587152347659872345962345" +
				"345873465976786587658172347657638456873469587349587345" +
				"345987345786923876987629387459287364576987126346987612384769827364589762345234" +
				"52347869817236498769786192873469876876234987569872364957698762934569872634589762934876982374659872345" +
				"3452374856928347562984576234985769876127365487651234610234172304917834081723401834643764477478234" +
				"5765642677674747789234526398475665347892938476534789229834765347892189237474832901019" +
				"23874748392010923874" +
				"23471289374734829019238747483920192387474839201901283477483921009283747483" +
				"2010129384774839201019283747438920191283744783291001928374" +
				"1234718273761239876643782912983746643782912983746643782191283746673829191823" +
				"746463782199128374664378291912873464673821991827346643782191827346" +
				"978897897978345019874078698127346987608927340587027364985698723452345" +
				"123476978126938746987698769287346587698723645769726398476987123987469786127354876152378465817234" +
				"13469871236984769187234187877833793487978433783378908787012789012340897019823749877483920101293874874932" +
				"817234876189273649876678392197898726346786837261987698777777777777723874698716298374" +
				"12341629837649876987689761723089749816283746876129837469187236478463278199" +
				"1372846731928463719826473918263719826437291826473191234123434523452345" +
				"99999999999999999999999999999999999999999999999999999999999999999999");
		b2 = new BigInt("111111111111111111111111111111111111111111111111111111111111111111111" +
				"222222222222222222222222222222222222222222222222222222222222222222222222222222222223" +
				"333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
				"4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444" +
				"55555555555555555555555555555555555555555555555555555555555555555555555555555555555555" +
				"66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666" +
				"77777777777777777777777777777777777777777777777777777777777777777777777777777777777777" +
				"8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888" +
				"999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
				"8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888" +
				"6666666666666666666666666666666666666666666666666666666666666666666666666666666666666" +
				"4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444" +
				"22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222" +
				"11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		assertEquals(new BigInteger(b1.toString()).multiply(new BigInteger(b2.toString())).toString(),b1.multiply(b2).toString());
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

		String ans = "339233020499025569932407395888132962811197654277770852184742000153482414063776362574970454339661" +
				"7378689760721713666724770963591620744906691350393413818731584371936871935580061096146517495349656745073" +
				"8024949211694497530011839262986694100140345158765132871152234771023120211029926754061431810993086517127" +
				"9492902667900288768957647975342598876796513365397153638576945822694251251315325650015183780968177020551" +
				"6440130802749013867077751405198520105513140824267921818918784336725440689973424535950137072242834776680" +
				"7944298831117713485116657480641690786690945694606636497017377336875999026482983746542758910456150025984" +
				"2753400127119688715377115291721616574130381797035793175062535576365358645466268146885736955122059012251" +
				"2760448009580466461545506636883452651575129948455872879861718773111203126942328092009221853075447245752" +
				"0478069718992333848544687444196731018127117167552505313124194645773366658085210184836810482117525094327" +
				"4861724366987858170991052860448422326858164381084052154803042202052465316188759000621240367163789214892" +
				"6144808426110993496165620307013745562794919784862360233571917481992677748667475247348270924798002431589" +
				"4550475650581876435785572115061434196287052817796549496591986504325947132236776161572144748979692629602" +
				"8631556702940951336286263000478885646179242155548206029004987315829970185543046641349912897021404691691" +
				"7317814314203695235471006590379930499766223006910572093910014631168057648312658759865760397598277479264" +
				"1161767619142562315285628193808573953852629972650932274908028672055966203029555602572339316573471868205" +
				"5069902234831407697790248134284761920807307610891251435570186716061221670948578207640266252714098562875" +
				"3446097273431457209850782345057250769768869235342729449806481092490322678108545596351153371600899889130" +
				"8967746029337394085782951864789979460926685937115614037961382155466760426358849046442855453987110903310" +
				"5277070998943749501125825607160338533590887398038552571319239838904501202292120565153918476844408087724" +
				"1322186514705530087908113556909335155680350821492945440182937193580219205645449481360504048261978791511" +
				"7626837675423731854217429427870356835467228105707738119287046504598210595413375093840895075916806700881" +
				"6892070635869505838720650846327209064976566553145455875194344996379885757844392027980646061964219848696" +
				"1144197263456523211570895355994247335295287155844323517899935506397752522885419969530783795446308132117" +
				"0182172871445270642602361539348607566451734400791971439408747663291456413805040907825570799211035876965" +
				"3529010906565061412147247278185152851313135165011254343767352368135303055936335008302897761590347673346" +
				"5904066321233795397980378675419132158294380789750832278229918663274114797442447154340597620761345117643" +
				"4009349419791892337840881140266932525707144415390961230046797512176460012157564564086442986357799776456" +
				"3013688805630173234902428218935554535705327744847127815979366896236989823417972781969680416748078556012" +
				"7093087851979126433527000855712280629876747449144218199471804499717346564712167649956084014855282778185" +
				"1261470678077644134771655309056428876961502848691845696126309049287900658052457212612822462905058909223" +
				"6717646587178463307288703525301986579585250401219462470273883493106876250565114560455277985835333949858" +
				"5302247901412316888365552488228983807354252648227983192778980421014318383501123625763363349379342278873" +
				"2083938472002097102691128163208284150824953290139075840084548595944130227261256668130936789580071261904" +
				"1825487017962533018602589020074823833139893747848347693088958455435528864029197264092571434809472066911" +
				"7337652256878110076021735358304965762103331912099539318462214557216955241021619484264485110459355138584" +
				"2537812406470882816131589364276991062691924718415840814008254390262623721507672163482879677636123971692" +
				"8600562863552425222687600854531862320330267578804151428988260656313668699619689412101434501288251628728" +
				"8065893293042773794239999550444115023874476617997339155388129850634863531691961546006695777920543791571" +
				"7551601610557539641883736072994057994869135368571606794258489336178404568883892030155735922975523822326" +
				"7066341235017416559462000941177618086343044357452611864912794597670123140196066563624531520001664309153" +
				"0211354105787129761132566103845932459747113282526732464176104557408634385709649764380411473515288131448" +
				"5727229030452074133673178578201921585755978789482443122685653634528446314294780365266474160367500416698" +
				"91701705399772229538162252843297576322068922959618852360399918267599533019839785846370344345933" +
				"16518969531898332816465230890075015834746682030868831601870709180269920973157572264700516521668507682" +
				"1154711796860080097839618307250646343602599449894681696533050080559598934370133594570467513873463128142" +
				"2333290424023381676564207935897732851562465199249880323871904982812757994225604082660632" +
				"427268018998507594326812097810304201271607066099117771230590665979822487042390809687261683695551881069112026" +
				"0392498549757036030455604683377943301543271852323210315423958255634154977256984993226227939159531802666" +
				"0932856927897855374113592899971856445347822674715514552757926220026437706403881243097000286283730667" +
				"9714431894264086193179156737393397515230654332003212565502175976346387422616588232904265676549590629234" +
				"119482421324424052658004548247127415998100227317163852313352165236309012781983236008288059022";
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
		b1 = new BigInt(new File("src\\TextFiles\\dividend.txt"));
		b2 = new BigInt(new File("src\\TextFiles\\divisor.txt"));
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

		String ans = "99408522422120768340405256213119647787327349609063713388824944519692338063652123221035246267224525" +
				"8795589240311541564622563957314405023137148584760477140139131628974735989864919208754019256" +
				"5017089141174908363521453192527434142153086732924366052529267706116964799481322569877217334916626165465" +
				"41883500653413023717773124700330058338899456293281840862241725177029751120618539154381" +
				"8319174509213575885730353904189795579366861120930262637223289934274598245701042308780536232332322038917" +
				"32517370131260010658806445064515459897162627406959594279176493726477652862348857746915" +
				"4928036626479130349824998903951564305955947624020492331027972324909742783351893637007631924187738012555" +
				"61973321404007519092689364559266477554082747247273797477143333846903916996634973085595" +
				"5645190118780381095738586662316333977400502803632221008921059859052376569702651483957646399454553805205" +
				"69888240983311853334202874399391780578063832507793492286079842852922017031432522913210" +
				"8690099289470787554295642425916129962690644601236612618474079869825031241638480714036377601848327872161" +
				"11929228649183006038264287162281365595004475266514740088692550003946692371080970462813" +
				"7794733426602313410167243054220943545721885429041082492564000603328466474055102942713375295685639165572" +
				"19430560295875084345400914721877771492990178489590045224052763353857830659721682872806" +
				"5420086142873214910458033451779048891627098866091692605041841115597732509051466365448473140108065489793" +
				"74271837058631535603987291544301015035531902257542726791698864561955862825649736284585" +
				"6892266248620736965328676571599832493001179834582012038204559138238416127670775170316909564222122388618" +
				"00673789771536969187753742735643339058681967980379731195247627762182372010624980446935" +
				"2590873282010658258441347362765834644316072952369668500463097426444204157147261747294927308703178930383" +
				"99860778093978152404002388373635691317986266249376545830996601244919188065511680466267" +
				"5585324752158317267298717787355487520036508567317871700274542462575652446146678387349943384133862577795" +
				"42169709658161217384715623736799490131334651127709803606645194985654948062910885504106" +
				"0792941951146834758229858918089712147685858763105576795117401361889989745201975953126860664651907075970" +
				"94782404256133258946641407614469731519636046904618875797935229145313306948391559698536" +
				"2041154391543550503508344261029280396922283713320737228329856076582139246371999605604591026797065982421" +
				"54715688955362205194174317435836636975524941209457271737666617882986993336533130640351" +
				"4318246571495871574763858917046800929585950730223940311642983469200009350116150231836442005747954142068" +
				"41026487042127628441782419008504643186707003263312246680249386942466380877969399318372" +
				"1692488098874820291060353491278438611894806217256521873572882154586564872902099981322244805497727118476" +
				"27711723749543179689751825458065838033454911619039092925561042111716171635158582946671" +
				"7265283327810740447645464675352881913313020076208672335909488614829910960807418687609926772190859196979" +
				"38875005669981927378421050516535756813648264053477921735394782837785404576650706399761" +
				"3574279668807396385470352011665450267733720539518955229396306437549011721620718303663917670263840050772" +
				"40469408926213217748727606219940408244201161334520626752028199808691470058900657356901" +
				"1573986369482742236144440339397818625106344447775257477046316482576287735395392220356487041298924373060" +
				"29413464995349904047731815459233707010511142914730333714043713835979091666654162977382" +
				"2928744627484454122062820966821501584529274354003363007074265036301334130694336377283076844022361042235" +
				"37456547023737911191129458486199097914909784477854108390424652371123154546808266492069" +
				"9105248412723840884114875278137775935930996799946761246512644993596708333713213734427781426886276907530" +
				"07312313729600941347356082220441963402794903867620180384269134519159451447387971520188" +
				"7559514635461759298081250417079210460828408832297125736393559389792545693821965145446765342003299892987" +
				"55379601810058016632774084920126451105953445984140276428911709851402652816228035112376" +
				"0291506967709317979123875602249191271723633069442859885918989889201518779874875922929695921917350589863" +
				"64861270483109007814629416231041700752143827106837777261401251609736131955978784757901" +
				"2867938720421123809734359455393054179796369334978447812520316464921248162687764591094118865166139419141" +
				"65786318586823205948755058865730117597351328671983233425846277024642727929651509966005" +
				"1779780722646969161602052376582237581929126309808369635610497340776265890972882615626329086086465388159" +
				"58376095817004086556776051268285251024857324627728679320641846127629734522212309966838" +
				"6252601376407255484373741824293254921212325038763748621499297812633551978594480727261672143337705671532" +
				"45629776557853548582061043966339758093290811947617304651362967841722781145410484184794" +
				"8784271362330538136323313736093710956570870408789407759651492616089971002460811846439522095633304302868" +
				"37158874671814707686875513181149867758900815719522741320792484504515238684999135827417" +
				"1654578884217007314998265635811444944228474796642299970227325751738336003109458848653204786718101514250" +
				"42925158379507909011896768105841542790029913864378308205624795829477528130086845752955" +
				"0837229403874778857563162674565231235085170731275265962347549989819347855799331017820853564287172111317" +
				"62932512687966448180170614963706488017367973652770682139539286431440358373189003979227" +
				"6850744410595780069614189392112856830585064360524991305321220725189294588776105955072419505072015208760" +
				"74912416081839691559933207716895773250051375464511809367242129361601234276438917629764" +
				"7414504125410447408139916598565035433906194935122775890093299264798913145306845396857515061615827161019" +
				"53582006759132484492480684866561860789023499147065456584916738383001518463851493939802" +
				"9567592200034553117850371994465889362408867065963916275404410640833413238629086881298342183249788625022" +
				"59031377511640231691925291697151246030682421089868770678290554778207313013726812672630" +
				"8993573232628006715166861239569627811036442711127085637120887112982764233234763182001001346020252405909" +
				"18118160048402256361553027583737693638333132185818800357759368874100643010642787583083" +
				"2189655819956064241239589566757487256399258523504178980525175052004639389761882322982215242213700349914" +
				"11148020798361655465906576805550174911504995962166001632416291443924042752217329304189" +
				"3274686370541562453009814069802927637871171703853336572731133932940606470993526935501516863992119512410" +
				"62813371225779736400933518094243541297517191698612065306033048414902180882044699059726" +
				"1568856208598445606000134708618610279049751682038346462092266693749158060595856154979367092787494860272" +
				"84588298797623141914194981946743831973510895015176596975238819066708029229077269486005" +
				"2332950472664950456959792156675059081139789662674899576350126109689531972397293342461075402175454883211" +
				"08877198759440391088965262734258970741230330362334762933409098684456451695012632320535" +
				"9022884907749707549038853949275431342053128286652925832507857088344553348707485367712331168390079909825" +
				"35773529632298409133139042113831083449999115370228130170836803322336480536683799697944" +
				"5848852706999408862992526572508345888999615836972575915166907524697628328370282844661411922391416124098" +
				"07327338797053274831389458328155631140735980985289561505068317310031041571574864099202" +
				"7144008737655273761428241115948871181329702339763899111314801094672650661982968594196014639581638907964" +
				"43205421379497529254816350016580615461687364178233058656001849716096355204909884132679" +
				"4844362873992903961985617930758808231751830664528077146994446511674032734425331336436107048483210705293" +
				"63932661089048809744487144471569402832487640294144937107504779634326592468450282370736" +
				"1303521469007498292594734891516553247792102092043743111779007666891005288212396949130601599740433523038" +
				"72051482432736244556324919987136259839918893796430204497644907302887893514852896904295" +
				"1949712806183078628688144090546109444750618814075193798010173116122681148428862542592290967173935670811" +
				"60540008443254248322918900665460473396195560644812116894196902460505168799325493357282" +
				"8037047326214134005576122085537864545186520755135737044295361052467799240199098977265791155032839959074" +
				"18142673183706056730504520762519174290698935851557782349737081529255977642786394623586" +
				"1082079161283359074702452043831771677798173673969493208020412605778825186384872726520012635911268050853" +
				"16796866443493752581636246248919685069565770219427041357386982691165651358663910820103" +
				"2705832146492029196772155803042988031964837207869888329065692628009903885386352149868244708760809066521" +
				"99465789657850095478130254119099722386792223352724763170688945333586268871489931799767" +
				"3606951600434961729565198230887295785633806816407673321790743034164987696158299018490307571351456740313" +
				"40178170156186089338556741792063118597133656134730964299606324710656631736192286891075" +
				"0595928712973365290481044489400054926109419614499057369116491347103642054000929774606258243001770801366" +
				"41289646756342559440593685824037072676564901247150387173384005435502593876395866946240" +
				"6751404279889963964377204015404125373621143254145830948807064127363407334789877239678018706419503459224" +
				"09010705496006828342509388289404961261829956476608201575469364738472569837614197434773" +
				"9506275254623417353111106094132451755849955684135938232016979520780267536736170904178644738827648029786" +
				"49937244831517615566043435246862724671699043127871040366339932417841698761488566584775" +
				"3471813756000864140854112605617018854244768870729130939720362607647107886656525230515354758816433394886" +
				"06134759387312050015153457780467006061981084536355267314239560932789639247920667167890" +
				"3979043882996733324673180730074795390530911793488956356403147843497973075488941495611458168235799390878" +
				"54653187588840278558610295042202167555602460864789413728072037739335053300523744606479" +
				"0920804120119580245033491347620443404559408757172478305844056758343951783273605321669055834529576101730" +
				"11138665072920291594410115604363207518728822663534452127016679886176292594214546638642" +
				"6904126920443969698665459129101729124727066461776050690139223664207957573161322337500554716354763761323" +
				"54452155202126726872720398956129774322043005071279591791335426424654698332697989423485" +
				"2148339036585613726598271210685143722855524975727774038939783613633363321211873845743566563827401413235" +
				"14444664813745371041806305178316400468400236095077214612987816211393543051363048873933" +
				"2027268387304268801094343589380393159848255551930824107851933690302642791672802930464685240890207019259" +
				"27356583239546110629238866279344602068281425303090691245935980524146978276234364048878" +
				"7713018147750891322246403460532998684462272269594596871706472078393844929520803925491786249262763218762" +
				"42073592193652328394016667876132680533189723221955824992859683196145109912672917041054" +
				"8216761018885973769001733049070206371094144393732172515878637240446969848416825349758758356702713174797" +
				"18900524897936301457608685893739577653178211817270159664875302248331216788652199283134" +
				"9616365951063274321939530976910088839193955411105254841170564543770718404045188127172589384130903694720" +
				"89641150325363960840800725662151824364236350524505203338189540268245452707011273098965" +
				"6070316967812651027490373699734237745258664405729401831267743607070383984373059799874881226985232366604" +
				"44863269341706844181685528735497141463247465016467480064706660848721156741699430301184" +
				"7944988813491391173581544678510158242190614370020368116883223740747242282510917525282652812084194616753" +
				"60561288709019594619193974498902581806948923468099516852791474395981548251622470939716" +
				"6389982497207481062594485467365807021925198172633556932741179049713689179837926944566487270690821592242" +
				"49078019841499561801403174532885838385881003285460300963085581499756813451301497104247" +
				"1706598413809272199822568165024754442477872389553705492601911723370466985526576486953631372826687789338" +
				"50805774986509772815564924494875029385344055326000087815281177171379948329760494122202" +
				"3457530018035479632180588192506363305792228394733501713410287795967924629476757558753844062976372983690" +
				"17212339156147341507523219857249365719174839437838767250411091768056315792707080506495" +
				"4016667849009320363100397494176971900624956103820220179512052469766925265044480786312170185121824104754" +
				"12172185222980776957982541231591884319647253782094728174691652514292403219590990302370" +
				"3969881411699126504593726673432257530534914394352612444954332292294987704977265373787426620524411304104" +
				"55228587832692822522225194204113573756181363671026425111668474435428133354182395121668" +
				"8796249087923354576093920985250705294435161860728609777956381051227205799559700806975533964020576443655" +
				"11278431519565139812900774932880297532567189763493765112909295465494946509824888573941" +
				"1148714375279828883725666091992454205984218389848714384339501209355676119001285527691335669155308795900" +
				"34776463231179203396242590107958818894145308234683361225465064575183271250120110032490" +
				"4567858375151619943705598636378212818442285584261006273803907567952728016701700915645461886431857259638" +
				"50407495104546344754615400526729775670928195777508630039132818184577527363530707500681" +
				"7075150167522536728783163661950842044454691388748531111801856925448216178843328282547510598675276348884" +
				"76590235121308151157193384182269506404324634612232253336726620110552825964440649883277" +
				"9578086420779720632992139080869469618681390984561980861848407606103516286149898542065067300419179648846" +
				"94451400799732626348908286562573920536094772564960903434182104201702622823533530908691" +
				"7407757936383973783181240514057604373128224786404794312182365312201114365106988069898142519970134135764" +
				"54285146441160822664708907273942326202598112717588108957346951268078584410755030283777" +
				"5438485554905523903529082477620244992783715080777129853516964296814331673153127386075628845577072206164" +
				"03271406895215089128669241764230616885651814092546548241895646224573119601233009994634" +
				"0801668744380809657140091932187598718947137046685765673360754334131986330927141866968623453286886408473" +
				"87122113665188039969412857803414019658774649899874136966152126296204188228186614535209" +
				"0811174336992437983150282335014402310651001659907458631102407175355006155778170437376946869655231912225" +
				"25244320097485771637645496228140590618482767315937476054824221913384158361994542981549" +
				"5185303574206932837595685430469826993075442595353413074673257365921999282713053387198901962761717601653" +
				"76528105185788593570683144875632880179743994587963133359081965564394831152309959018859" +
				"8166994991163637068791514369589855248046739527109330238558481167233797125510833825377957881652633622613" +
				"36859976310804623291675293441668057058600432139411762529848830409730655254567974873499" +
				"2535477913563824255809038337569866311000693683245465441489791355315118890427775677380511742374698854439" +
				"21271819681752930942157750981830943807427028553603976414437680542974694591242858662389" +
				"6825495568419144364402952712560781617357337438824118639083675374989056240184819831311832309234942861000" +
				"21220089729211123920809595882381068571938712523564319769955656503844823228897688705025" +
				"0752130923340863937552235224164040435661012083727062843755427404940389193825605380909520128348574285509" +
				"72644687679922291622838360021682558670247260532185598317270771336232274785886138913704" +
				"1356694921261647266212095404359997618558239832948779625715278096898762610326024139672790608133269087727" +
				"82937813936162433277692966948886799545134332520820412554824876657909388055459799132867" +
				"3787789706679812343388890499536177475832690773255333188492249848332107600364552891842384507212552911238" +
				"40500501598695222784255706173793163926539870670740449692393692088732098043564551359884" +
				"2555240454684118343222146435639851793059114135373542327050138046202179448286963750297936126001900610924" +
				"09145740577588158552120144649288210033837330635444539585225718206760266126102428365786" +
				"1233739311752778143373433986279412898580162589959881443818543118586938204670131964458891507279044953917" +
				"13229579302806087126749905319834132813503628234101251965448974096372781936880799345110" +
				"8390128843027334858723994997928289507134443824205557108071445717275227860557777383319945670071186220262" +
				"30957471027570676563416440950231321004174246037519006474790308494746761053030047058745" +
				"6742305832648886507681042601462321880728381201868703137581289389047850740876838738111579470249728702771" +
				"50628055747907675047477260180151190061491314443368810671969445909727609752273654197152" +
				"6262686613679280165099803733426456957785605371262701097844935028032530474008362419073901556411776328619" +
				"12079239833347931098433305960232360038489294035353582518774035511536536647500263084195" +
				"5571171390167451315471003425746154736171763300841126418012312327362781652364483723965411418579496813043" +
				"04017705156092778679601976820994798119544869152115962426254489646341690381125633184760" +
				"1772151415945696409316520302331055166747932136615130536375509498982943769185635483836849367688523934479" +
				"43986649229144759002546207515485879312998231425833878097308127322246101567361648196093" +
				"7949122019456360594887659371248991481662943";
		long start = System.currentTimeMillis();
		b2 = b1.pow(25);
		System.out.println((System.currentTimeMillis() - start) + "\nb = "
				+ b1.toString().length() + " digits"+" b^25: " + ans.length() + " digits");
		assertEquals(ans,b2.toString());
	}

	@Test
	public void testExponentV6()
	{
		b1 = new BigInt("2");
		assertEquals("1024",b1.pow(10).toString());
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
		long st = System.currentTimeMillis();
		b2 = b1.pow(4);
		System.out.println(System.currentTimeMillis() - st);
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

	//@Ignore
	@Test
	public void testhgf()
	{
		b1 = new BigInt("20002978345692735692387456298742" +
				"293847592874569287456928734569283745692387456927834562345" +
				"234856239487629734568237456823745827651037421345" +
				"23458236459273465827346587615238476155648173465972356987235682765" +
				"23475628347582734578623452345623456" +
				"3456345635674567456745673456345623452345" +
				"234523464567456745674678467457457457653763" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"763673673567456746678467846734562542345234523453464567" +
				"4567467846785895986789678967896789678967896789678967896789" +
				"6789679854736324756576547654756476547543634254235432" +
				"654375648765865465432432143215453765576865" +
				"75475436543543254326544876597687678737732678347732477623" +
				"36746623871234677632677413676213466732487987923" +
				"4787237878234787234887327667235677667764676723" +
				"78677278782359789235852134565213478747890978007897235626890782070789234" +
				"97787298236662553245763287890978023879082147527723468707890235" +
				"32632473283873623497802978027890238723863147623332613460876087" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"7778767277237724667326723673127326327324727278978927236723" +
				"763672872349782978234673245623467347890238471562341" +
				"23418236451872345817348716523847658917235475187354123451334");
		long st = System.currentTimeMillis();
		int pow = 32;
		b2 = b1.pow(pow);
		System.out.println(System.currentTimeMillis() - st);
		//String num = b2.toString();
		System.out.printf("a = %d digits, a^%d: %d digits\n",b1.length(),pow,b2.length());
		assertEquals(new BigInteger(b1.toString()).pow(32).toString(),b2.toString());
	}

	@Test
	public void testABS()
	{
		assertEquals("1",BigInt.ONE.toString());
//		b1 = new BigInt("-12345");
//		//System.out.println(b1.absValue());
//		assertEquals("12345",b1.absValue().toString());
	}

	@Test
	public void testModifiedCompareForLongValue()
	{
		b1 = new BigInt("234567890987654321");
		b2 = new BigInt("234567890987654321");
		assertEquals(0,b1.compareTo(b2));
	}

	@Test
	public void testModifiedCompareForLongValue1()
	{
		b1 = new BigInt(Long.toString(Long.MAX_VALUE));
		b2 = new BigInt(Long.toString(Long.MIN_VALUE));
		assertEquals(1,b1.compareTo(b2));
	}

	@Test
	public void testModifiedCompareForLongValue2()
	{
		b1 = new BigInt(Long.toString(Long.MIN_VALUE));
		b2 = new BigInt(Long.toString(Long.MAX_VALUE));
		assertEquals(-1,b1.compareTo(b2));
	}

	@Test
	public void testModifiedCompareForLongValue3()
	{
		b1 = new BigInt(Long.toString(Long.MAX_VALUE-1));
		b2 = new BigInt(Long.toString(Long.MAX_VALUE));
		assertEquals(-1,b1.compareTo(b2));
	}

	@Test
	public void testOdd()
	{
		b1 = new BigInt("284355128375487123547612347860237845876235487");
		assertEquals(1,b1.parity());
	}
	@Test
	public void testEven()
	{
		b1 = new BigInt("28435512837548712354761234786023784587623548");
		assertEquals(0,b1.parity());
	}

	@Test
	public void testOdd1()
	{
		b1 = new BigInt("-284355128375487123547612347860237845876235487");
		assertEquals(1,b1.parity());
	}
	@Test
	public void testEven1()
	{
		b1 = new BigInt("-28435512837548712354761234786023784587623548");
		assertEquals(0,b1.parity());
	}

	@Test
	public void testSignumConstructor()
	{
		b1 = new BigInt(-1,"76347634638734");
		b2 = new BigInt(-1,"87387387934978342879342");
		b3 = b1.add(b2);
		assertEquals("-87387388011325977518076",b3.toString());
	}

	@Test
	public void testSignumConstructor1()
	{
		b1 = new BigInt(-1,"6666666666666");
		assertEquals("6666666666666",b1.absValue().toString());
	}

	@Test
	public void testShiftLeft()
	{
		b1 = new BigInt("76");
		assertEquals("152",b1.shiftLeft(1).toString());
	}

	@Test
	public void testShiftLeft1()
	{
		b1 = new BigInt("76");
		assertEquals("304",b1.shiftLeft(2).toString());
	}

	@Test
	public void testShiftLeft2()
	{
		b1 = new BigInt("76");
		assertEquals("608",b1.shiftLeft(3).toString());
	}

	@Test
	public void testShiftRight()
	{
		b1 = new BigInt("152");
		assertEquals("76",b1.shiftRight(1).toString());
	}

	@Test
	public void testShiftRight1()
	{
		b1 = new BigInt("608");
		assertEquals("76",b1.shiftRight(3).toString());
	}

	@Test
	public void testGcd()
	{
		b1 = new BigInt("119");
		b2 = new BigInt("544");
		b3 = b1.gcd(b2);
		assertEquals("17",b3.toString());
	}

	@Test
	public void testDivdeRemainder()
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
		System.out.println(b1.divideAndRemainder(b2)[1]);

	}

	@Test
	public void testDivdeAndRemainder()
	{
		b1 = new BigInt("11");
		b2 = new BigInt("4");
		BigInt[] results = b1.divideAndRemainder(b2);
		assertEquals(results[0].toString(),"2");
		assertEquals(results[1].toString(),"3");
	}


	@Test
	public void testWithinLing()
	{
		b1 = new BigInt("123456789");
		System.out.println(b1.isWithinLong());
	}

	@Test
	public void testModPow()
	{
		b1 = new BigInt("11");
		b2 = new BigInt("13");
		b3 = new BigInt("19");
		System.out.println(b1.modPow(b2,b3).toString());
	}

}