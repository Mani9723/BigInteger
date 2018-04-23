package RevisedBigInt;


import RevisedBigInt.Exceptions.InvalidInputException;

import java.lang.Math;
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
        try {
            if (isInputValid(absValue))
                this.numberArray = storeInArrayList(absValue);
        } catch (InvalidInputException e) {
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
        else if (signValue == '+' || signValue == '-') {
            setSign(signValue);
            return value.substring(1);
        }return value;
    }

    private boolean isInputValid(String value) throws InvalidInputException
    {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i)))
                throw new InvalidInputException(value.concat(" ").concat("Contains invalid Character"));
        }return true;
    }

    private void setSign(char signValue)
    {
        if (signValue == '-')
            isCharged = true;
    }

    private ArrayList<Integer> storeInArrayList(String value)
    {
        ArrayList<Integer> numberArray = new ArrayList<Integer>();
        if (isCharged) {
            for (int i = 0; i < value.length(); i++)
                numberArray.add(Integer.parseInt(value.substring(i, i + 1)) * -1);
        } else {
            for (int i = 0; i < value.length(); i++)
                numberArray.add(Integer.parseInt(value.substring(i, i + 1)));
        }return numberArray;
    }
    private ArrayList<Integer> negate(ArrayList<Integer> numberArray)
    {
        ArrayList<Integer> negatedArrayList = new ArrayList<Integer>();
        for (int i = 0; i < numberArray.size(); i++)
            negatedArrayList.add(numberArray.get(i) * -1);
        return negatedArrayList;
    }

    public BigInt add(BigInt other)
    {
        reverse(this.numberArray,other.numberArray,null);
        BigInt f = new BigInt(add(this.numberArray, other.numberArray));
        reverse(this.numberArray,other.numberArray,f.numberArray);
        return f;
    }

    private void reverse(ArrayList<Integer> numberArray,
                         ArrayList<Integer> second, ArrayList<Integer> third)
    {
        Collections.reverse(numberArray);
        Collections.reverse(second);
        if(third != null) Collections.reverse(third);
    }
    private ArrayList<Integer> add(ArrayList<Integer> addend, ArrayList<Integer> auguend)
    {
        int carry = 0, tempSum;
        if (addend.size() != auguend.size()) compareArrayList(addend, auguend);
        ArrayList<Integer> sum = new ArrayList<Integer>();

        for (int i = 0; i < addend.size(); i++) {
            tempSum = addend.get(i) + auguend.get(i) + carry;
            if (tempSum >= 10 || tempSum <= -10) {
                carry = tempSum / 10;
                sum.add(tempSum % 10);
            } else {
                sum.add(tempSum);
                carry = 0;
            }
        }if (carry > 0 || carry < 0)
        sum.add(carry);

        return sum;
    }

    public BigInt subtract(BigInt other)
    {
        reverse(this.numberArray,other.numberArray,null);
        BigInt f = new BigInt(subtract(this.numberArray,other.numberArray));
        reverse(this.numberArray,other.numberArray,f.numberArray);
        return f;
    }

    private ArrayList<Integer> subtract(ArrayList<Integer> minuend, ArrayList<Integer> subtrahend)
    {
        int borrow = 10, carry = 0, tempDiff =0, firstVal, secondVal;
        ArrayList<Integer> difference = new ArrayList<Integer>();
        if (minuend.size() != subtrahend.size()) compareArrayList(minuend, subtrahend);

        if(minuend.size()==1 && subtrahend.size()==1) { difference.add(minuend.get(0) - subtrahend.get(0)); }
        else if(subtrahend.get(0) < 0){ difference = add(minuend,subtrahend); }
        else {
            for (int i = 0; i < minuend.size(); i++) {
                firstVal = minuend.get(i); secondVal = subtrahend.get(i);
                if((firstVal < 0 && secondVal > 0) || secondVal < 0) {
                    difference = add(minuend, negate(subtrahend));
                    difference = negate(difference);
                }
                else if(Math.abs(firstVal) < Math.abs(secondVal)){
                    tempDiff = (firstVal+borrow - carry) - secondVal;
                    carry = 1;
                }else if(firstVal<secondVal){
                    tempDiff = (firstVal-carry) - secondVal;
                }else
                    tempDiff = firstVal - secondVal;
                difference.add(tempDiff);
            }
        }
        return difference;
    }
    private void compareArrayList(ArrayList<Integer> firstArray, ArrayList<Integer> secondArray)
    {
        int second = secondArray.size(), first = firstArray.size();
        if (firstArray.size() < secondArray.size()) {
            padWithZeros(firstArray, second - first);
        } else padWithZeros(secondArray, first - second);
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
        StringBuilder stringBuilder = new StringBuilder();
        finalCheckForNegativeNumbers();

        if(isCharged) stringBuilder.append('-');

        for (Integer aNumberArray : this.numberArray)
            stringBuilder.append(Math.abs(aNumberArray));

        String finalString = stringBuilder.toString();

        if(stringBuilder.toString().matches("^[0]+$"))
            return finalString.substring(0,1);

        return finalString.replaceFirst("^[0]+", "");
    }
}
