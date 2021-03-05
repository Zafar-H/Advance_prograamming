import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class LinearArrayMultiplication_L2
{
    public static final Logger logger = LogManager.getLogger(LinearArrayMultiplication_L2.class);

    public static void main(String args[])
    {
        String array1StringValue= System.getenv("firstArray");
        String array2StringValue= System.getenv("secondArray");

        String[] array1StringArray = array1StringValue.split(" ");
        String[] array2StringArray = array2StringValue.split(" ");

        int[] array1 = new int[array1StringArray.length];
        int[] array2 = new int[array2StringArray.length];

        for (int i = 0; i < array1StringArray.length; i++) {
            String num1 = array1StringArray[i];
            String num2 = array2StringArray[i];

            array1[i] = Integer.parseInt(num1);
            array2[i] = Integer.parseInt(num2);
        }


        //this is the start position of the array
        int startCount = 0;
        int endCount   = 3;

        //initializing the initial value
        int InitialProductValue = 0;
        logger.info("Initialisation START...");

        logger.info("Default Value for Product Array = [" + InitialProductValue + "]");
        logger.info("Initial Position of Array = [" + startCount + "]");
        logger.info("End Position of Array = [" + endCount + "]");

        logger.info("Initialisation END.....");

        //checking if the start and end are logically correct
        if(startCount >= endCount)
        {

            logger.info("Initial Position ->[" + startCount + "] is greater then or equal to the end position ->[" + endCount +"]");
            System.exit(-3);
        }


        //The length is computed dynamically
        int ArrayElementCount_1 = array1.length;
        int ArrayElementCount_2 = array2.length;

        logger.info("ElementCount Array 1 = [" + ArrayElementCount_1 + "]");
        logger.info("ElementCount Array 2 = [" + ArrayElementCount_2 + "]");


        //checking if both the arrays are of same length otherwise exit
        if(ArrayElementCount_1 != ArrayElementCount_2)
        {
            logger.error("The array lengths are different and hence exiting");
            System.exit(-1);
        }

        //checking if the initial position of the array is within the array size
        if(startCount > ArrayElementCount_1)
        {
            logger.info("Array initial position ->[" + startCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-300);
        }

        //checking if the end position of the array is within the array size
        if(endCount > ArrayElementCount_1)
        {
            logger.info("Array end position ->[" + endCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-301);
        }


        //this holds the product of 2 arrays
        int ArrayProduct[] = new int[ArrayElementCount_1];


        //Looping constuct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should e same
        for(int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++ )
        {
            //initialising the product array
            ArrayProduct[indexOfArray] = InitialProductValue;
            int ElementOfArray1 = array1[indexOfArray];
            int ElementOfArray2 = array2[indexOfArray];

            //displaying the value of array at the current position
            logger.info("Element value of array 1 in the position[" + indexOfArray + "] = " + ElementOfArray1);
            logger.info("Element value of array 2 in the position[" + indexOfArray + "] = " + ElementOfArray2);

            //computing products
            int productOfElements = ElementOfArray1 * ElementOfArray2;
            ArrayProduct[indexOfArray] = productOfElements;

            logger.info("Product value at position [" + indexOfArray + "] = " + ArrayProduct[indexOfArray]);
        }

    }
}
