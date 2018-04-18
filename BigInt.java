package RevisedBigInt;


import RevisedBigInt.Exceptions.InvalidInputException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;


//Hare Krsna
public class BigInt
{

    private ArrayList<Integer> numberArray = new ArrayList<Integer>();
    private boolean isCharged = false;

    public BigInt(String userValue)
    {
        String absValue = verifySignAndLength(userValue);
        try
        {
            if (isInputValid(absValue))
                this.numberArray = storeInArrayList(absValue);
        } catch (InvalidInputException e)
        {
            e.getMessage();
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private BigInt(ArrayList<Integer> numberArray)
    {
        this.numberArray = numberArray;
    }

    private String verifySignAndLength(String value)
    {
        char signValue = value.charAt(0);
        if (value.length() == 1 && !Character.isDigit(value.charAt(0)))
            System.exit(-1);
        else if (signValue == '+' || signValue == '-')
        {
            setSign(signValue);
            return value.substring(1);
        }
        return value;
    }

    private boolean isInputValid(String value) throws InvalidInputException
    {
        for (int i = 0; i < value.length(); i++)
        {
            if (!Character.isDigit(value.charAt(i)))
                throw new InvalidInputException(value.concat(" ").concat("Contains invalid Character"));
        }
        return true;
    }

    private void setSign(char signValue)
    {
        if (signValue == '-')
            isCharged = true;
    }

    private ArrayList<Integer> storeInArrayList(String value)
    {
        ArrayList<Integer> numberArray = new ArrayList<Integer>();
        if (isCharged)
        {
            for (int i = value.length() - 1; i >= 0; i--)
                numberArray.add(Integer.parseInt(value.substring(i, i + 1)) * -1);
        } else
        {
            for (int i = value.length() - 1; i >= 0; i--)
                numberArray.add(Integer.parseInt(value.substring(i, i + 1)));
        }
        return numberArray;
    }

    public BigInt add(BigInt other)
    {
        return new BigInt(add(this.numberArray, other.numberArray));
    }

    private ArrayList<Integer> add(ArrayList<Integer> addend, ArrayList<Integer> auguend)
    {
        int carry = 0;
        if (addend.size() != auguend.size()) compareArrayList(addend, auguend);
        ArrayList<Integer> sum = new ArrayList<Integer>();

        for (int i = 0; i < addend.size(); i++) {
            int tempSum = addend.get(i) + auguend.get(i) + carry;
            if (tempSum >= 10) {
                carry = tempSum / 10;
                sum.add(tempSum % 10);
            } else {
                sum.add(tempSum);
                carry = 0;
            }
        }
        if (carry > 0)
            sum.add(carry);
        return sum;
    }

    private void compareArrayList(ArrayList<Integer> addend, ArrayList<Integer> auguend)
    {
        int second = auguend.size(), first = addend.size();
        if (addend.size() < auguend.size())
        {
            padWithZeros(addend, second - first);
        } else padWithZeros(auguend, first - second);
    }

    private void padWithZeros(ArrayList<Integer> array, int numberOfZeros)
    {
        int lastPos = array.size();
        for (int i = 0; i < numberOfZeros; i++)
            array.add(lastPos, 0);
    }

    private void finalCheckForNegativeNumbers()
    {
        for (int i = 0; i < this.numberArray.size(); i++){
            if (this.numberArray.get(i) < 0) {
                isCharged = true;
                break;
            }
        }
    }

 
    @Override
    public String toString()
    {
        Collections.reverse(this.numberArray);
        StringBuilder stringBuilder = new StringBuilder();

        finalCheckForNegativeNumbers();
        

        if(isCharged) stringBuilder.append('-');
        for (Integer aNumberArray : this.numberArray)
            stringBuilder.append(Math.abs(aNumberArray));

        if(stringBuilder.length()==1 && stringBuilder.charAt(0) == '0')
            return stringBuilder.toString();

        return stringBuilder.toString().replaceFirst("^[0]+", "");
    }
}
