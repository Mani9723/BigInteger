package RevisedBigInt;



public class BigIntMain
{
    public static void main(String[] args)
    {
        BigInt b3;
        BigInt b1 = new BigInt("-9999999999979");
        BigInt b2 = new BigInt("989");
        b3 = b1.add(b2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
