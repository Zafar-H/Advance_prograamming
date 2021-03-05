import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinearArrayMultiplication_L4
{
    public static final Logger logger = LogManager.getLogger(LinearArrayMultiplication_L4.class);

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
        ArrayList<String> arrayValuesString=new ArrayList<String>();
        HashMap<String, String> inputFileVariables = new HashMap<String, String>();
        while(inputFile.hasNextLine())
        {
            currentFileVariable = inputFile.nextLine().split(":");
            inputFileVariables.put(currentFileVariable[0], currentFileVariable[1]);

            //storing array values separately
            if(currentFileVariable[0].startsWith("ELEMENTS"))
            {
                arrayValuesString.add(currentFileVariable[1]);
            }
        }



        //checking whether minimum and arrayCount is specified
        String minArrayCountString = inputFileVariables.get("minArrayCount");
        if(minArrayCountString == null)
        {
            logger.error("Minimum array count is not specified");
            System.exit(-404);
        }
        //checking whether maximum arrayCount is specified
        String maxArrayCountString = inputFileVariables.get("maxArrayCount");
        if(maxArrayCountString == null)
        {
            logger.error("Maximum array count is not specified");
            System.exit(-404);
        }

        //checking whether arrayCount is specified
        String arrayCountString = inputFileVariables.get("arrayCount");
        if(arrayCountString == null)
        {
            logger.error("Array count is not specified");
            System.exit(-404);
        }


        //checking whether array count is within the min and max array count
        int minArrayCount = Integer.parseInt(minArrayCountString);
        int maxArrayCount = Integer.parseInt(maxArrayCountString);
        int arrayCount = Integer.parseInt(arrayCountString);
        if(arrayCount < minArrayCount)
        {
            logger.error("Minimum "+minArrayCount+" arrays required");
            System.exit(-404);
        }
        if(arrayCount > maxArrayCount)
        {
            logger.error("Maximum "+maxArrayCount+" arrays can only be specified");
            System.exit(-404);
        }


        //checking for array names
        String arrayNamesString = inputFileVariables.get("arrayNames");
        if(arrayNamesString == null)
        {
            logger.error("Array names are not specified");
            System.exit(-404);
        }


        //check check whether start and end position of array are specified
        String startCountString = inputFileVariables.get("startCount");
        if(startCountString == null)
        {
            logger.error("Arrays start count is not specified");
            System.exit(-404);
        }

        //check check whether start and end position of array are specified
        String endCountString = inputFileVariables.get("endCount");
        if(endCountString == null)
        {
            logger.error("Arrays end count is not specified");
            System.exit(-404);
        }

        //this is the start and end position of the array
        int startCount = Integer.parseInt(inputFileVariables.get("startCount"));
        int endCount   = Integer.parseInt(inputFileVariables.get("endCount"));

        //converting string values of array to integer
        String arrayNamesList = inputFileVariables.get("arrayNames");
        String[] arrayNamesArray = arrayNamesList.split(",");
        int namesEndCount = arrayNamesArray.length-1;
        HashMap<String, String> inputArrayNameValue = new HashMap<String, String>();
        for(int indexOfArray=startCount; indexOfArray<=namesEndCount; indexOfArray++ )
        {
            inputArrayNameValue.put(arrayNamesArray[indexOfArray] ,arrayValuesString.get(indexOfArray));
        }

        //converting each array values to integer and storing in integer array
        String arrayStringValues;
        String[] arrayStringValuesArray;
        HashMap<String, Object[]> finalArrayValueList = new HashMap<String, Object[]>();

        for(Map.Entry mapElement : inputArrayNameValue.entrySet())
        {
            arrayStringValues = (String) mapElement.getValue();
            arrayStringValuesArray = arrayStringValues.split(",");
            int loopEndCount = arrayStringValuesArray.length-1;
            String arrayName = (String)mapElement.getKey();
            //int[] arr = new int[arrayStringValuesArray.length];
            ArrayList<Integer> arr=new ArrayList<Integer>();
            for (int indexOfArray=startCount; indexOfArray<=loopEndCount; indexOfArray++)
            {
                arr.add(Integer.parseInt(arrayStringValuesArray[indexOfArray]));
            }
            Object[] array = arr.toArray();
            finalArrayValueList.put(arrayName, array);

        }

        //The length is computed dynamically
        int ArrayElementCount_1 = finalArrayValueList.get("firstArray").length;
        int ArrayElementCount_2 = finalArrayValueList.get("firstArray").length;
        int ArrayElementCount_3 = finalArrayValueList.get("firstArray").length;
        int ArrayElementCount_4 = finalArrayValueList.get("firstArray").length;

        logger.info("ElementCount Array 1 = [" + ArrayElementCount_1 + "]");
        logger.info("ElementCount Array 2 = [" + ArrayElementCount_2 + "]");
        logger.info("ElementCount Array 3 = [" + ArrayElementCount_3 + "]");
        logger.info("ElementCount Array 4 = [" + ArrayElementCount_4 + "]");


        //checking if the arrays are of same length otherwise exit
        if(ArrayElementCount_1 != ArrayElementCount_2 && ArrayElementCount_1 != ArrayElementCount_3 && ArrayElementCount_1 != ArrayElementCount_4 && ArrayElementCount_2 != ArrayElementCount_3 && ArrayElementCount_2 != ArrayElementCount_4 && ArrayElementCount_3 != ArrayElementCount_4)
        {
            logger.error("The array lengths are different and hence exiting");
            System.exit(-1);
        }


        //checking if the initial position of the arrays are within the array size
        if(startCount > ArrayElementCount_1)
        {
            logger.info("Array initial position ->[" + startCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-300);
        }

        //checking if the end position of the arrays are within the array size
        if(endCount > ArrayElementCount_1)
        {
            logger.info("Array end position ->[" + endCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-301);
        }

        //this holds the transaction or operations of 4 arrays
        int ArrayProduct[] = new int[ArrayElementCount_1];
        int ArraySum[] = new int[ArrayElementCount_1];
        int ArrayDifference[] = new int[ArrayElementCount_1];

        //Looping construct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should same
        for(int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++ )
        {
            //initialising the product array
            ArrayProduct[indexOfArray] = startCount;
            ArraySum[indexOfArray] = startCount;
            ArrayDifference[indexOfArray] = startCount;

            int ElementOfArray1 = (int)finalArrayValueList.get("firstArray")[indexOfArray];
            int ElementOfArray2 = (int)finalArrayValueList.get("secondArray")[indexOfArray];
            int ElementOfArray3 = (int)finalArrayValueList.get("thirdArray")[indexOfArray];
            int ElementOfArray4 = (int)finalArrayValueList.get("fourthArray")[indexOfArray];

            //displaying the value of array at the current position
            logger.info("Element value of array 1 in the position[" + indexOfArray + "] = " +ElementOfArray1 );
            logger.info("Element value of array 2 in the position[" + indexOfArray + "] = " +ElementOfArray2 );
            logger.info("Element value of array 3 in the position[" + indexOfArray + "] = " +ElementOfArray3 );
            logger.info("Element value of array 4 in the position[" + indexOfArray + "] = " +ElementOfArray4 );

            //computing products
            int productOfElements = ElementOfArray1 * ElementOfArray2 * ElementOfArray3 * ElementOfArray4;
            int sumOfElements = ElementOfArray1 + ElementOfArray2 + ElementOfArray3 + ElementOfArray4;
            int differenceOfElements = ElementOfArray1 - ElementOfArray2 - ElementOfArray3 - ElementOfArray4;
            ArrayProduct[indexOfArray] = productOfElements;
            ArraySum[indexOfArray] = sumOfElements;
            ArrayDifference[indexOfArray] = differenceOfElements;

            logger.info("Product value at position [" + indexOfArray + "] = " + ArrayProduct[indexOfArray]);
            logger.info("Sum value at position [" + indexOfArray + "] = " + ArraySum[indexOfArray]);
            logger.info("Difference value at position [" + indexOfArray + "] = " + ArrayDifference[indexOfArray]);
        }
    }
}
