package RevisedBigInt;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigIntTest
{

    protected BigInt b1,b2,b3;

    @Test
    public void testAddZero() throws Exception
    {
        b1 = new BigInt("-0");
        b2 = new BigInt("0");
        b3 = b1.add(b2);
        assertEquals("",b3.toString());
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
    public void testAddCarry() throws Exception
    {
        b1 = new BigInt("199999999");
        b2 = new BigInt("199999999");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }
    @Test
    public void testAddCarryTwo() throws Exception
    {
        b1 = new BigInt("9876123546");
        b2 = new BigInt("987612355467434000");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }

    @Test
    public void testAddCarryThree() throws Exception
    {
        b1 = new BigInt("76297436892");
        b2 = new BigInt("76297876");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }
    @Ignore
    @Test
    public void testAddCarryFour() throws Exception
    {
        b1 = new BigInt("7687187619897813458");
        b2 = new BigInt("6324567673108978079");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }
    @Ignore
    @Test
    public void testAddCarryFive() throws Exception
    {
        b1 = new BigInt("1");
        b2 = new BigInt("6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }
    @Ignore
    @Test
    public void testAddCarrySix() throws Exception
    {
        b2 = new BigInt("1");
        b1 = new BigInt("6324567673108978079435876239478567697298768754342314567899765432145678976543214567897654321456");
        b3 = b1.add(b2);
        long sum = Long.parseLong(b1.toString()) + Long.parseLong(b2.toString());
        assertEquals(Long.toString(sum),b3.toString());
    }

    @Test
    public void testAddFirstNegative() throws Exception
    {
        b1 = new BigInt("-1");
        b2 = new BigInt("1");
        b3 = b1.add(b2);
        assertEquals("",b3.toString());
    }

    @Test
    public void testAddSecondNegative() throws Exception
    {
        b1 = new BigInt("1");
        b2 = new BigInt("-1");
        b3 = b1.add(b2);
        assertEquals("",b3.toString());
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
