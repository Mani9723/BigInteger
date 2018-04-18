package RevisedBigInt;

import RevisedBigInt.Exceptions.InvalidInputException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigIntTest
{
	private BigInt b1,b2,b3;

	@Test
	public void testAddZero() throws Exception
	{
		b1 = new BigInt("-0");
		b2 = new BigInt("0");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddNoCarry() throws Exception
	{
		b1 = new BigInt("123");
		b2 = new BigInt("456");
		b3 = b1.add(b2);
		assertEquals("579",b3.toString());
	}

	@Test
	public void testAddFirstNegative() throws Exception
	{
		b1 = new BigInt("-1");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddSecondNegative() throws Exception
	{
		b1 = new BigInt("1");
		b2 = new BigInt("-1");
		b3 = b1.add(b2);
		assertEquals("0",b3.toString());
	}

	@Test
	public void testAddFirstLengthGreater() throws Exception
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}
	@Test
	public void testAddSecondLengthGreater() throws Exception
	{
		b1 = new BigInt("1");
		b2 = new BigInt("123");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}

	@Test
	public void testPaddingWithZeros() throws Exception
	{
		b1 = new BigInt("123");
		b2 = new BigInt("1");
		b3 = b1.add(b2);
		assertEquals("124",b3.toString());
	}

	@Test
	public void testRemoveLeadingZeros() throws Exception
	{
		b1 = new BigInt("0000000123");
		assertEquals("123",b1.toString());
	}
}