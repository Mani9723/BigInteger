package RevisedBigInt;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigIntTest
{

	private BigInt b1,b2,b3;

	@Test
	public void testAddZero()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddNoCarry()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("456");
		b3 = b1.add(b2);
		assertEquals("579",b3.toString());
	}

	@Test
	public void testAddCarry()
	{
		b1 = new BigInt("199999999");
		b2 = new BigInt("199999999");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum),b3.toString());
	}
	@Test
	public void testAddCarryTwo()
	{
		b1 = new BigInt("9876123546");
		b2 = new BigInt("987612355467434000");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum),b3.toString());
	}

	@Test
	public void testAddCarryThree()
	{
		b1 = new BigInt("76297436892");
		b2 = new BigInt("76297876");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum),b3.toString());
	}
	//@Ignore
	@Test
	public void testAddCarryFour()
	{
		b1 = new BigInt("7687187619897813458");
		b2 = new BigInt("6324567673108978079");
		b3 = b1.add(b2);
		String number = "14011755293006791537";
		assertEquals(number,b3.toString());
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
		assertEquals(number,b3.toString());
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
		assertEquals(number,b3.toString());
	}

	@Test
	public void testAddFirstNegative()
	{
		b1 = new BigInt("-1");
		b2 = new BigInt("+1");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddSecondNegative()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-1");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddFirstLengthGreater()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}
	@Test
	public void testAddSecondLengthGreater()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("123");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}

	@Test
	public void testPaddingWithZeros()
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}

	@Test
	public void testRemoveLeadingZeros()
	{
		b1 = new BigInt("0000000123");
		assertEquals("123",b1.toString());
	}

	@Test
	public void testAddTwoDifferentSigns()
	{
		b1 = new BigInt("-9878735");
		b2 = new BigInt("-9878735");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum),b3.toString());
	}

	@Test
	public void testAddTwoDifferentSignsTwo()
	{
		b1 = new BigInt("-100");
		b2 = new BigInt("100");
		b3 = b1.add(b2);
		long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
		assertEquals(Long.toString(sum),b3.toString());
	}

	@Test
	public void testAddAnotherCase()
	{
		b1 = new BigInt("-201");
		b2 = new BigInt("98712364978612934201");
		b3 = b1.add(b2);
		assertEquals("98712364978612934000",b3.toString());
	}

	@Test
	public void testSubtractSingleDigits()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("2");
		b3 = b1.subtract(b2);
		assertEquals("-1",b3.toString());
	}@Test
public void testSubtractSingleDigitsV2()
{
	b1 = new BigInt("-1");
	b2 = new BigInt("2");
	b3 = b1.subtract(b2);
	assertEquals("-3",b3.toString());
}@Test
public void testSubtractSingleDigitsV3()
{
	b1 = new BigInt("1");
	b2 = new BigInt("-2");
	b3 = b1.subtract(b2);
	assertEquals("3",b3.toString());
}@Test
public void testSubtractSingleDigitsv4()
{
	b1 = new BigInt("-1");
	b2 = new BigInt("-2");
	b3 = b1.subtract(b2);
	assertEquals("1",b3.toString());
}

	@Test
	public void testSubtractWithZeros()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractWithZerosV2()
	{
		b1 = new BigInt("0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractWithZerosV3()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.subtract(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractWithZerosV4()
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testSubtractWithZerosV5()
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-0");
		b3 = b1.subtract(b2);
		assertEquals("1",b3.toString());
	}

	@Test
	public void testSubtractWithZerosV6()
	{
		b1 = new BigInt("9");
		b2 = new BigInt("-9");
		b3 = b1.subtract(b2);
		assertEquals("18",b3.toString());
	}

	@Test
	public void testSubtractOne()
	{
		b1 = new BigInt("19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("18",b3.toString());
	}

	@Test
	public void testSubtractTwo()
	{
		b1 = new BigInt("-19");
		b2 = new BigInt("1");
		b3 = b1.subtract(b2);
		assertEquals("-20",b3.toString());
	}

	@Test
	public void testSubtractThree()
	{
		b1 = new BigInt("-19");
		b2 = new BigInt("-1");
		b3 = b1.subtract(b2);
		assertEquals("-18",b3.toString());
	}
	@Test
	public void testSubtractFour()
	{
		b1 = new BigInt("19");
		b2 = new BigInt("-19");
		b3 = b1.subtract(b2);
		assertEquals("38",b3.toString());
	}
	@Test
	public void testSubtractFive()
	{
		b1 = new BigInt("1778899");
		b2 = new BigInt("-1778899");
		b3 = b1.subtract(b2);
		assertEquals("3557798",b3.toString());
	}
	@Test
	public void testSubtractSix()
	{
		b1 = new BigInt("-1778899");
		b2 = new BigInt("-78899");
		b3 = b1.subtract(b2);
		assertEquals("-1700000",b3.toString());
	}
	@Test
	public void testSubtractSeven()
	{
		b1 = new BigInt("-1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("-1857798",b3.toString());
	}
	@Test
	public void testSubtractEight()
	{
		b1 = new BigInt("1778899");
		b2 = new BigInt("78899");
		b3 = b1.subtract(b2);
		assertEquals("1700000",b3.toString());
	}
	@Test
	public void testSubtractBorrowOne()
	{
		b1 = new BigInt("187");
		b2 = new BigInt("179");
		b3 = b1.subtract(b2);
		assertEquals("8",b3.toString());
	}

	@Test
	public void testSubtractTen()
	{
		b1 = new BigInt("100");
		b2 = new BigInt("-100");
		b3 = b1.subtract(b2);
		assertEquals("200",b3.toString());
	}



}
