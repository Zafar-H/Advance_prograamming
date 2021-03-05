import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class LinearArrayMultiplication_L3
{
    public static final Logger logger = LogManager.getLogger(LinearArrayMultiplication_L3.class);

    public static void main(String args[]) throws IOException {

        //storing program arguments in variable
        String programArgument_1= args[0];
        String programArgument_2= args[1];
        File argumentInputFile = new File(programArgument_1);
        File argumentOutputFile = new File(programArgument_2);

        //check if specified file exists or not
        if (!argumentInputFile.exists()){
            logger.error("file does not exist");
            System.exit(-3);
        }

        //checking for permissions
        //Checking the Read permission
        if (!argumentInputFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }

        //Checking the Write permission
        if (!argumentInputFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }

        //Checking the Execute permission
        if (!argumentInputFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }

        //Checking if the file is empty
        if (argumentInputFile.length() == 0)
        {
            logger.error(" File is empty");
            System.exit(-404);

        }


        //deleting leading and trailing white spaces
        FileReader fileReader = new FileReader(argumentInputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(argumentOutputFile);
        String line;
        while ((line = bufferedReader.readLine()) != null) {

            line = line.trim();
            //deleting extra whitespaces and the spaces before and after ":"
            line = line.replaceAll("\\s+", " ").replaceAll("\\s+:", ":").replaceAll(":+\\s", ":")+"\n";

            fileWriter.write(line);
        }
        fileReader.close();
        fileWriter.close();


        //taking variables from the file
        Scanner inputFile = new Scanner(argumentOutputFile);
        String[] currentFileVariable = new String[2];
        HashMap<String, String> inputFileVariables = new HashMap<String, String>();
        while(inputFile.hasNextLine())
        {
            currentFileVariable = inputFile.nextLine().split(":");
            inputFileVariables.put(currentFileVariable[0], currentFileVariable[1]);
        }


        //retrieving array variable from file
        String arrayStringValues_1= inputFileVariables.get("arrayValues_1");
        String arrayStringValues_2= inputFileVariables.get("arrayValues_2");

        String[] stringArray_1 = arrayStringValues_1.split(" ");
        String[] stringArray_2 = arrayStringValues_2.split(" ");

        int[] array1 = new int[stringArray_1.length];
        int[] array2 = new int[stringArray_2.length];


        //this is the start and end position of the array
        int startCount = Integer.parseInt(inputFileVariables.get("startCount"));
        int endCount   = Integer.parseInt(inputFileVariables.get("endCount"));

        //converting type of array [string to integer]
        for (int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++)
        {
            String currentStringArrayValue_1 = stringArray_1[indexOfArray];
            String currentStringArrayValue_2 = stringArray_2[indexOfArray];

            array1[indexOfArray] = Integer.parseInt(currentStringArrayValue_1);
            array2[indexOfArray] = Integer.parseInt(currentStringArrayValue_2);
        }

        //initializing the initial value
        int InitialProductValue = Integer.parseInt(inputFileVariables.get("InitialProductValue"));;
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


        //Looping construct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should same
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

