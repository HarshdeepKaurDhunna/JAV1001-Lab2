package labMethodsArrays;

import java.util.Arrays;

/**
 * @author Name:  Harshdeep Kaur Dhunna
 * Student number: A00246003 
 * Program description: JAV-1001 - 11329 - App Development for Android - 202201 - 001
 * Compile the whole package: javac -cp . .\labMethodsArrays\*.java  
 * Run the class containing Main Method:  java labMethodsArrays.ArrayTools
 */

import java.util.Scanner;

public class ArrayTools {
    // Make input bold
    public static String boldString = "\033[0;1m";
    // Make input plain
    public static String plainString = "\033[0;0m";

    /**
     * @method Main method
     * @param args
     * @input Collect a numeric value current unit of measurement from the user.
     * @see Display the new result after Conversion
     */
    public static void main(String[] args) {
        String inputVal;
        int shiftRange;

        // Scanner permits a user to read a number from the System.in
        Scanner userInput = new Scanner(System.in);
        System.out.print(" Enter a string to encrypt. " + boldString);
        inputVal = userInput.nextLine();

        System.out.print(plainString + "Enter a value to encrypt with. " +
                boldString);
                shiftRange = Integer.parseInt(userInput.nextLine());

        // Encrypting the entered value
        StringBuilder encryptOutput = caesarCipher(inputVal, shiftRange);
        System.out.println(plainString + "The encrypted string is " + boldString +
                encryptOutput.toString());

        // Decrypting the encrypted value
        StringBuilder dcryptOutput = caesarCipher(encryptOutput.toString(), -shiftRange);
        System.out.println(plainString + "Decrypting " + encryptOutput.toString() + " with -" +
                           boldString +   -shiftRange + " : " + dcryptOutput.toString());

        //Collects Array from user for testing
        System.out.println(plainString + "Enter the array for Testing: " + boldString);
        inputVal = userInput.nextLine();
        
        //method to convert string to array
        arrayTestingMethod(inputVal, userInput);

        //prevent memory leakage
        userInput.close();

    }

    /**
     * 
     * @param inputVal   string to Encrypted/Dcrypted
     * @param shiftRange numeric value to use to specify shift
     * @return Encrypted/Dcrypted String value using Caesar cipher algorithm
     */
    public static StringBuilder caesarCipher(String inputVal, int shiftRange) {
        StringBuilder outputVal = new StringBuilder();

        //set the modus
        shiftRange = shiftRange % 26;

        //loop through the input string to create a new string builder
        for (int i = 0; i < inputVal.length(); i++) {
            char encryptChar = inputVal.charAt(i);
            encryptChar = (char) (inputVal.charAt(i) + shiftRange);
            outputVal.append(encryptChar);
        }
        return outputVal;
    }

    /**
     * This method is to convert string to array and call other testing methods
     * @param inputVal Array entered by user
     * @param userInput scanner object to get value from user for searching
     * @throws NumberFormatException if user inputs any value except numeric value
     */
    public static void arrayTestingMethod(String inputVal, Scanner userInput) throws NumberFormatException {

        try {
            int inputValLength = inputVal.length();

            //Check if the entered is not empty and have array format
            if (inputValLength != 0 && inputVal.charAt(0) == '[' && inputVal.charAt(inputValLength - 1) == ']') {
                
                //Remove array bracets
                inputVal = inputVal.replaceAll("^.|.$", "");

                //Convert String into integer array using Java 8 Streams
                int[] inputValArray = Arrays.asList(inputVal.split(",")).stream()
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();

                //Call method to calculate average of arrays
                System.out.println(plainString + String.valueOf(averageArray(inputValArray)));

                //Get user input to search value in array
                System.out.println("Enter a value to search for. " + boldString);
                String valToSearch = userInput.nextLine();

                //Call method to search value of arrays
                System.out.println(plainString + searchArray(inputValArray, valToSearch));

                //Call method to reverse arrays
                System.out.println(plainString + "The reversed array  is ");
                System.out.println(boldString + reverseArray(inputValArray));

            } else {
                System.out.println("Enter the Valid Array");
            }
        } catch (Exception e) {

            //print exception while conversion
            System.out.println(e.getMessage() + " Enter the Valid Numeric Value");
        }

    }

    /**
     * This method is to calculate average of arrays
     * @param inputValArray integer array entered by user
     * @return Average
     */
    public static double averageArray(int[] inputValArray) {
        int inputValSum = 0;
        double avgVal = 0.0;

        //Loop through the array for addition of values
        for (int i : inputValArray)
            inputValSum += i;

        avgVal = inputValSum / inputValArray.length;
        return avgVal;
    }

    /**
     * This methos is too search a value in array
     * @param inputValArray
     * @param valToCompare
     * @return String stating if array contains value or not
     */
    public static String searchArray(int[] inputValArray, String valToCompare) {
        String outputVal = "The array does not contain " + boldString  + valToCompare;
        for (int i : inputValArray) {

            //convert int to string and compare with entered string
            if (String.valueOf(i).equals(valToCompare)) {
                outputVal = "The array contains " + boldString + valToCompare;
            }
        }
        return outputVal;
    }

    /**
     * 
     * @param inputValArray
     * @return
     */
    public static String reverseArray(int[] inputValArray) {
        int reverseArr[] = new int[inputValArray.length];
        int i = 0;

        //Reverse loop to get the array elements from last index
        for (int counter = inputValArray.length - 1; counter >= 0; counter--) {
            reverseArr[i] = inputValArray[counter];
            
            i++;// create array index of reverseArr[] 
        }
        return Arrays.toString(reverseArr);
    }
}
