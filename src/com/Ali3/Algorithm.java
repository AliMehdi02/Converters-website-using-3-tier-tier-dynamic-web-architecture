package com.Ali3;
public class Algorithm {

	// This class consists of the algorithm to convert from binary to decimal and hexadecimal.
	public String BinaryToHex(long Bin_Num) //Name of user defined method which takes Bin_Num as its parameter. containing algorithm to convert from binary to hexadecimal number.
	{
		//Below are variable declaration
		long inputtedbinary; 
		long remainder;
		String hexadecimal_num= ""; // this is string as hexadecimal number also has some alphabets 
		char hexadecimal[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; // array which contains characters that a hexadecimal number has
		inputtedbinary = Bin_Num; // here inputed number means the binary number inputed  by the user

		while(inputtedbinary > 0) // if the inputed number by the user is greater than 0 then while loop will run.
		{
			remainder = inputtedbinary % 16;  // it will divide the inputed number by 16 and output will be the remainder of the calculation.
			hexadecimal_num = hexadecimal[(int) remainder] + hexadecimal_num; // what evr the oupt of modulus then its index will find the char in hexadecimal in the array. 
			inputtedbinary = inputtedbinary / 16; // the inputed number is divided by 16 --> the while loop loops till inputed number becomes <= 0
		}
		return hexadecimal_num; // it returns hexadecimal number calculated above
	}

	public long BinaryToDecimal(int Bin_Num) //Name of user defined method which takes Bin_Num as its parameter. containing algorithm to convert from binary to decimal number.
	{
		//Below are variable declarations
		long inputtedbinary; 
		long Dec_Num = 0;
		long x = 1;
		long rem;

		inputtedbinary =  Bin_Num; // here inputed number means the binary number inputed  by the user
		while ( inputtedbinary != 0) // if the inputed number by the user is not = to 0 then while loop will run.
		{
			rem = inputtedbinary % 10; //the inputed number will be divided by 10 and give remainder as the output
			Dec_Num = Dec_Num + rem * x; // decimal number will be added to the remainder and multiplied by x
			x = x * 2; //x is multiplied by 2
			inputtedbinary /=  10; // inputed number is divided by 10 -->  the while loop loops till inputed number becomes = 0
		}
		return Dec_Num; //it returns decimal number calculated above
	}
}
