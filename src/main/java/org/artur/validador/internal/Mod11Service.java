package org.artur.validador.internal;

	// Class that determines CPF/CNPJ validity
public class Mod11Service {

	public Mod11Service() {
		super();
	}

	//Test DV (digito verificador) for Mod11 test
		// Number: 123456789	d[0] = 9 , d[1] = 8 ... d[8] = 1
		// DV[1] must be: (SumOf(d[i] * (9 - (i % 8))) % 11) % 10
		// Only considers the digits in the string passed as argument
	public boolean isMod11Valid (String stringOfNumberToTest, int numberOfDVs) {
			// Data cleaning. Cannot clean " character because they mess with JSON format
				//Removes spaces
		stringOfNumberToTest = stringOfNumberToTest.trim();
		String auxString = new String();
				// Removes everything other than digits
		for(int i = 0; i < stringOfNumberToTest.length(); i++) {
			if (Character.isDigit(stringOfNumberToTest.charAt(i))) {
				auxString += stringOfNumberToTest.charAt(i);
			}
		}
		stringOfNumberToTest = auxString;
		
			// Exception Case for CPF/CNPJ validity
			// Equal numbers for all digits should be invalid. Ex: 222222222
		boolean allEqualFlag = true;
		for(int i = 0; i < stringOfNumberToTest.length() - numberOfDVs - 1; i++) {
			if(stringOfNumberToTest.charAt(i) != stringOfNumberToTest.charAt(i + 1)) {
				allEqualFlag = false;
				break;
			}	
		}
		if (allEqualFlag) {
			//System.out.println("All numbers are equal");
			return false;	// All numbers are equal
		}
		
			// Generic Case for CPF/CNPJ validity
		for(int j = numberOfDVs; j > 0; j--) {
			int acc = 0;
			for(int i = 0; i < stringOfNumberToTest.length() - j; i++) {
				int intElementPos = stringOfNumberToTest.length() - 1 - j - i;
				int intElement = Integer.valueOf(stringOfNumberToTest.charAt(intElementPos)) - 48;
				acc += intElement * (9 - (i % 8)); // CPF/CNPJ mod11 seems different from standard mod11
				//System.out.println("intElementPos = " + intElementPos + "; intElement = " + intElement 
				//		                              + "; charAt() = " + stringOfNumberToTest.charAt(intElementPos) 
				//		                              + "; acc[" + (i) + "] = " + acc);
			}
			System.out.println("acc = " + acc);
			acc = acc % 11;
			acc = acc % 10;
			//System.out.println("Valid DV[" + (2 - j) + "] = " + acc + "; Actual DV[" + (2 - j) + "] = " 
			//			+ (Integer.valueOf(stringOfNumberToTest.charAt(stringOfNumberToTest.length() - j)) - 48));
			if (acc != Integer.valueOf(stringOfNumberToTest.charAt(stringOfNumberToTest.length() - j)) - 48) {
				//System.out.println("DV doesn't match");
				return false; // DV doesn't match
			}	
		}
		
		return true; // All tests passed. DVs are valid
	}
	
}
