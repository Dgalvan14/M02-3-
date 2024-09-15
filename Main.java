import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        // creating a scanner to allow our user to input a binary string
        Scanner userInput = new Scanner(System.in);
        // this will be used when trying to validate input
        boolean isValid = false;
        // this will store the users binary string
        String binaryString;
        // this will store the final decimal value
        int decimalValue = 0;

        //this loop will continue until a valid binary string is entered
        while (!isValid) {
            // getting user input
            System.out.println("Enter a binary number: ");
            binaryString = userInput.next();

            //using a try catch to run the code to conver the user input into a decimal and display the error message to the user if they enter an invalid string
            try {
                decimalValue = BinaryConverter.bin2Dec(binaryString);
                isValid = true;
            } catch (BinaryFormatException errorMessage) {

                System.out.println(errorMessage.getMessage());
                System.out.println("Please enter a valid binary number only 1s and 0s are allowed.");
            }
        }
        // closing our scanner to prevent memory leak
        userInput.close();

        // printing result
        System.out.println("Decimal value: " + decimalValue);
    }

    public static class BinaryFormatException extends Exception {
        public BinaryFormatException(String errorMessage) {
            super(errorMessage);
        }
    }
    
    // this method is used to convert the binary string to a decimal
    
    public class BinaryConverter {
    
        public static int bin2Dec(String binaryString) throws BinaryFormatException {
    
            /*This section of the code goes through the binary digit and ensure that it is valid by making sure it is either a 1 or a 0 if it is not it throws our error and indicates which
             * digit is at fault for the error
             */
            for (int i = 0; i < binaryString.length(); i++) {
                char character = binaryString.charAt(i);
                if(character != '0' && character != '1') {
                    throw new BinaryFormatException("Error: Character " + character + " at position " + (i + 1) + " is invalid." );
                }
            }
    
            // these values are used to get the lenght of our binary string and to store the decimal value of our binary string
            int decimalValue = 0;
            int length = binaryString.length();
    
            // for loop to go through each digit of the string
            for (int i = 0; i < length; i++ ) {
                // similar to the above section getting the char of the character at i in the binary string
                char digit = binaryString.charAt(i);
                // returns the int value for the char using unicode 
                int digitValue = Character.getNumericValue(digit);
                // this calculates the which power of 2 we need to multiply the digit by given its position in the string
                int powerOfTwo = (int) Math.pow(2, (length - 1 - i));
                // multiplying the digit value byt the corisponding power of two and then adding that to the total decimal value
                decimalValue += digitValue * powerOfTwo;
            }
    
            return decimalValue;
        }
    }
}
