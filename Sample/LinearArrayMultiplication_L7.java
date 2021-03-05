import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.*;

public class LinearArrayMultiplication_L7
{
    public static final Logger logger = LogManager.getLogger(LinearArrayMultiplication_L7.class);

    public static void main(String args[]) throws IOException {

        BasicConfigurator.configure();
        //Creating variables to stores system.dat and 8transaction.dat
        logger.trace("Assigning default system.dat and transaction.dat files to variable...");
        String programArgument_1 = "default/system.dat";
        String programArgument_2 = "default/transaction.dat";

        //Checking whether default files are specified...
        logger.trace("Checking whether default files are specified...");
        if (programArgument_1 != null)
        {
            logger.info(" default system file : [ " + programArgument_1 + " ] ");
        }
        else
        {
            logger.error("Exiting because default system.dat file is not specified :(");
            System.exit(-3);
        }

        if (programArgument_2 != null)
        {
            logger.info(" default system file : [ " + programArgument_2 + " ] ");
        }
        else
        {
            logger.error("Exiting because default system.dat file is not specified :(");
            System.exit(-3);
        }

        //splitter constant literals
        logger.trace("splitter constant literals");
        String commaDelimiter = ",";
        String spaceDelimiter = " ";
        String colonDelimiter = ":";
        logger.info("Delimiter used for segregating array values : [ " + commaDelimiter + " ] " );
        logger.info("Delimiter used for segregating key value pairs : [ " + colonDelimiter + " ] " );


        //Checking the number of arguments
        int argumentLength = args.length;

        int maxNumberOfArgument = 2;
        int firstArgumentPosition = 0;
        int secondArgumentPosition = 1;


        //Checking whether the arguments are specified to the program
        logger.info("Using the default files : [ " + programArgument_1 + " , " + programArgument_2 + " ] ");

        //get the number of arguments passed
        logger.trace("Getting the number of arguments passed");
        int numberOfArgument = argumentLength;
        int numberOne = 1;
        logger.info("Number of arguments passed : [ " + numberOne + " ] ");

        //if at least 1 arg is specified than override it with default file
        if (numberOfArgument == numberOne)
        {
            logger.trace("If only one argument is specified than overriding default system.dat file...");
            programArgument_1 = args[firstArgumentPosition];
            logger.info("Overridden argument file is : [ " + programArgument_1 + " ] ");
            logger.info("Second argument is not specified hence using default file : [ " + programArgument_2 + " ] ");
        }

        //if there are more than 2 args, take the first 2 and ignore the rest. Override 2 args with default files
        if ( numberOfArgument >= maxNumberOfArgument )
        {
            logger.trace("Overriding the default files with files passed as argument...");
            logger.trace("if there are more than 2 arguments, considering the first 2 and ignoring the rest");
            programArgument_1 = args[firstArgumentPosition];
            programArgument_2 = args[secondArgumentPosition];
            logger.info("Overridden argument files are : [ " + programArgument_1 + " , " + programArgument_2 + " ] ");
        }


        //storing either file from argument or default files in variable
        File argumentSystemFile = new File(programArgument_1);
        File argumentTransactionFile = new File(programArgument_2);
        logger.info("Arguments stored in file successfully");


        //Validation check for the files passed as argument
        //validations for system.dat file
        //check if specified file exists or not
        logger.trace("Validation check for the files passed as argument...");
        logger.trace("Validation check system.dat file...");
        logger.trace("Checking if specified file exists or not...");
        if (!argumentSystemFile.exists()){
            logger.error("file system.dat does not exist");
            System.exit(-3);
        }
        logger.info( "File not empty" );

        //checking permissions for system.dat file
        //Checking the Read permission
        logger.trace("checking permissions for system.dat file...");
        logger.trace("Checking the Read permission...");
        if (!argumentSystemFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }

        //Checking the Write permission
        logger.trace("Checking the Write permission...");
        if (!argumentSystemFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }

        //Checking the Execute permission
        logger.trace("Checking the Execute permission...");
        if (!argumentSystemFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }
        logger.info("  system.data File Permission Checked");

        //Checking if the file is empty
        logger.trace("Checking if the file is empty...");
        if (argumentSystemFile.length() == 0)
        {
            logger.error(" File is empty");
            System.exit(-404);
        }
        logger.info( "system.dat File not empty" );

        //deleting leading and trailing white spaces of system.dat file
        logger.trace("deleting leading and trailing white spaces..");
        FileReader fileReader = new FileReader(argumentSystemFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {

            line = line.trim();
            //deleting extra whitespaces and the spaces before and after ":" in system.dat
            line = line.replaceAll("\\s+", spaceDelimiter).replaceAll("\\s+" + colonDelimiter, colonDelimiter).replaceAll(colonDelimiter + "+\\s", colonDelimiter)+"\n";
            lines.add(line);
        }
        fileReader.close();
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(argumentSystemFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(String eachLine : lines)
        {
            bufferedWriter.write(eachLine);
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        logger.info( "system.dat file formatted successfully" );

        //validations for transaction.dat file
        //check if specified file exists or not
        logger.trace("validations for transaction.dat file...");
        logger.trace("check if specified file exists or not...");
        if (!argumentTransactionFile.exists()){
            logger.error("file does not exist");
            System.exit(-3);
        }
        logger.trace("File is not empty");

        //checking for permissions
        //Checking the Read permission
        logger.trace("checking permissions transaction.dat file");
        logger.trace("Checking the Read permission...");
        if (!argumentTransactionFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }

        //Checking the Write permission
        logger.trace("Checking the Write permission...");
        if (!argumentTransactionFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }

        //Checking the Execute permission
        logger.trace("Checking the Execute permission...");
        if (!argumentTransactionFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }
        logger.info("  system.data File Permission Checked");

        //Checking if the file is empty
        logger.trace("Checking if the file is empty...");
        if (argumentTransactionFile.length() == 0)
        {
            logger.error(" File is empty");
            System.exit(-404);

        }
        logger.info("File is not empty");

        //deleting leading and trailing white spaces of transaction.dat
        logger.trace("deleting leading and trailing white spaces of transaction.dat...");
        FileReader transactionFileReader = new FileReader(argumentTransactionFile);
        BufferedReader transactionBufferedReader = new BufferedReader(transactionFileReader);

        List<String> transactionFileLines = new ArrayList<String>();
        String transactionFileLine;
        while ((transactionFileLine = transactionBufferedReader.readLine()) != null) {

            transactionFileLine = transactionFileLine.trim();
            //deleting extra whitespaces and the spaces before and after ":" in transaction.dat file
            transactionFileLine = transactionFileLine.replaceAll("\\s+", spaceDelimiter).replaceAll("\\s+" +colonDelimiter, colonDelimiter).replaceAll(colonDelimiter + "+\\s", colonDelimiter)+"\n";
            transactionFileLines.add(transactionFileLine);
        }
        transactionFileReader.close();
        transactionBufferedReader.close();
        FileWriter transactionFileWriter = new FileWriter(argumentTransactionFile);
        BufferedWriter transactionBufferedWriter = new BufferedWriter(transactionFileWriter);
        for(String eachLine : transactionFileLines)
        {
            transactionBufferedWriter.write(eachLine);
        }
        transactionBufferedWriter.flush();
        transactionBufferedWriter.close();

        //deleting leading and trailing white spaces of system.dat
        logger.trace("deleting leading and trailing white spaces of system.dat...");
        FileReader systemFileReader = new FileReader(argumentSystemFile);
        BufferedReader systemBufferedReader = new BufferedReader(systemFileReader);

        List<String> systemFileLines = new ArrayList<String>();
        String systemFileLine;
        while ((systemFileLine = systemBufferedReader.readLine()) != null) {

            systemFileLine = systemFileLine.trim();
            //deleting extra whitespaces and the spaces before and after ":" in system.dat file
            systemFileLine = systemFileLine.replaceAll("\\s+", " ").replaceAll("\\s+:", ":").replaceAll(":+\\s", ":")+"\n";
            systemFileLines.add(systemFileLine);
        }
        systemFileReader.close();
        systemBufferedReader.close();
        FileWriter systemFileWriter = new FileWriter(argumentSystemFile);
        BufferedWriter systemBufferedWriter = new BufferedWriter(systemFileWriter);
        for(String eachLine : systemFileLines)
        {
            systemBufferedWriter.write(eachLine);
        }
        systemBufferedWriter.flush();
        systemBufferedWriter.close();
        logger.info("  File formatted successfully");

        //taking variables from the file
        logger.trace("taking variables from the file...");
        Scanner systemFile = new Scanner(argumentSystemFile);
        Scanner transactionFile = new Scanner(argumentTransactionFile);

        String[] currentSystemFileVariable = new String[2];
        HashMap<String, String> inputSystemFileVariables = new HashMap<String, String>();
        while(systemFile.hasNextLine())
        {
            currentSystemFileVariable = systemFile.nextLine().split(colonDelimiter);
            inputSystemFileVariables.put(currentSystemFileVariable[0], currentSystemFileVariable[1]);

        }

        String[] currentTransactionFileVariable = new String[2];
        ArrayList<String> arrayValuesString=new ArrayList<String>();
        HashMap<String, String> inputTransactionFileVariables = new HashMap<String, String>();
        while(transactionFile.hasNextLine())
        {
            currentTransactionFileVariable = transactionFile.nextLine().split(colonDelimiter);
            logger.info(currentTransactionFileVariable[1]);
            inputTransactionFileVariables.put(currentTransactionFileVariable[0], currentTransactionFileVariable[1]);

            //storing array values separately
            if(currentTransactionFileVariable[0].startsWith("ELEMENTS"))
            {
                arrayValuesString.add(currentTransactionFileVariable[1]);
            }

            //storing operation values separately
            if(currentTransactionFileVariable[0].startsWith("ELEMENTS"))
            {
                arrayValuesString.add(currentTransactionFileVariable[1]);
            }
        }



        //checking whether minimum and arrayCount is specified
        logger.trace("checking whether minimum and arrayCount is specified...");
        String minArrayCountString = inputSystemFileVariables.get("minArrayCount");
        if(minArrayCountString == null)
        {
            logger.error("Minimum array count is not specified");
            System.exit(-404);
        }
        logger.trace("minimum and arrayCount is specified");

        //checking whether maximum arrayCount is specified
        logger.trace("checking whether maximum arrayCount is specified...");
        String maxArrayCountString = inputSystemFileVariables.get("maxArrayCount");
        if(maxArrayCountString == null)
        {
            logger.error("Maximum array count is not specified");
            System.exit(-404);
        }
        logger.trace("maximum arrayCount is specified");

        //checking whether arrayCount is specified
        logger.trace("checking whether arrayCount is specified...");
        String arrayCountString = inputSystemFileVariables.get("arrayCount");
        if(arrayCountString == null)
        {
            logger.error("Array count is not specified");
            System.exit(-404);
        }
        logger.trace("arrayCount is specified...");


        //checking whether array count is within the min and max array count
        logger.trace("checking whether array count is within the min and max array count...");
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
        logger.trace("array count is within the min and max array count");


        //checking for array names
        logger.trace("checking for array names...");
        String arrayNamesString = inputTransactionFileVariables.get("arrayNames");
        if(arrayNamesString == null)
        {
            logger.error("Array names are not specified");
            System.exit(-404);
        }
        logger.trace("array names are specified");


        //check check whether start position of array are specified
        logger.trace("check check whether start position of array is specified...");
        String startCountString = inputSystemFileVariables.get("startCount");
        if(startCountString == null)
        {
            logger.error("Arrays start count is not specified");
            System.exit(-404);
        }
        logger.trace("start position of array is specified");

        //check check whether end position of array is specified
        logger.trace("check check whether end position of array is specified...");
        String endCountString = inputSystemFileVariables.get("endCount");
        if(endCountString == null)
        {
            logger.error("Arrays end count is not specified");
            System.exit(-404);
        }
        logger.trace("end position of array is specified");

        //this is the start and end position of the array
        logger.trace("this is the start and end position of the array...");
        int startCount = Integer.parseInt(inputSystemFileVariables.get("startCount"));
        int endCount   = Integer.parseInt(inputSystemFileVariables.get("endCount"));
        logger.info("Start position : [ " + startCount + " ] ");
        logger.info("End position : [ " + endCount + " ] ");

        //converting string values of array to integer
        String arrayNamesList = inputTransactionFileVariables.get("arrayNames");
        logger.info("Array names are [ " + arrayNamesList + " ]" );
        logger.trace("Converting string values of array to integer...");
        String[] arrayNamesArray = arrayNamesList.split(commaDelimiter);
        int namesEndCount = arrayNamesArray.length-1;
        HashMap<String, String> inputArrayNameValue = new HashMap<String, String>();
        for(int indexOfArray=startCount; indexOfArray<=namesEndCount; indexOfArray++ )
        {
            inputArrayNameValue.put(arrayNamesArray[indexOfArray] ,arrayValuesString.get(indexOfArray));
        }

        //converting each array values to integer and storing in integer array
        logger.trace("converting each array values to integer and storing in integer array...");
        String arrayStringValues;
        String[] arrayStringValuesArray;
        HashMap<String, Object[]> arrayNameValueList = new HashMap<String, Object[]>();

        for(Map.Entry mapElement : inputArrayNameValue.entrySet())
        {
            arrayStringValues = (String) mapElement.getValue();
            arrayStringValuesArray = arrayStringValues.split(commaDelimiter);
            int loopEndCount = arrayStringValuesArray.length-1;
            String arrayName = (String)mapElement.getKey();
            ArrayList<Integer> arrList=new ArrayList<Integer>();
            for (int indexOfArray=startCount; indexOfArray <= loopEndCount; indexOfArray++)
            {
                arrList.add(Integer.parseInt(arrayStringValuesArray[indexOfArray]));

            }
            Object[] array = arrList.toArray();
            arrayNameValueList.put(arrayName, array);
        }

        //The length is computed dynamically
        logger.trace("The length is computed dynamically...");
        int[] arrayElementCount = new int[endCount];
        for (int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++)
        {
            arrayElementCount[indexOfArray] = endCount;
        }


        logger.trace("Checking if the arrays are of same length otherwise exit...");
        logger.trace("Checking if the initial position of the arrays are within the array size...");
        logger.trace("Checking if the end position of the arrays are within the array size...");
        for (int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++)
        {
            //checking if the arrays are of same length otherwise exit
            if ( indexOfArray != endCount-1)
                if (arrayElementCount[indexOfArray] != arrayElementCount[indexOfArray+1])
                {
                    logger.error("The array lengths are different and hence exiting");
                    System.exit(-1);
                }

            //checking if the initial position of the arrays are within the array size
            if(startCount > arrayElementCount[indexOfArray])
            {
                logger.info("Array initial position ->[" + startCount + "] is greater than or equal the size of the array ->[" + arrayElementCount[indexOfArray]);
                System.exit(-300);
            }
            //checking if the end position of the arrays are within the array size
            if(endCount > arrayElementCount[indexOfArray])
            {
                logger.info("Array end position ->[" + endCount + "] is greater than or equal the size of the array ->[" + arrayElementCount[indexOfArray]);
                System.exit(-301);
            }
        }
        logger.trace("Arrays are of same length");
        logger.trace("Initial position of the arrays are within the array size");
        logger.trace("End position of the arrays are within the array size");

        for (int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++)
        {
            logger.info("ElementCount of Array " +(indexOfArray+1)+ " = [" + arrayElementCount[indexOfArray] + "]");
        }


        //this holds the transaction or operations of 4 arrays
        int ArrayProduct[] = new int[endCount];
        int ArraySum[] = new int[endCount];
        int ArrayDifference[] = new int[endCount];

        //Looping construct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should same
        for(int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++ )
        {
            int[] ElementOfArray = new int[arrayCount];
            for (int innerIndex = startCount; innerIndex < arrayCount; innerIndex++)
            {
                ElementOfArray[innerIndex] = (int)arrayNameValueList.get(arrayNamesArray[innerIndex])[indexOfArray];
                logger.info("Element value of array "+(innerIndex+1)+" in the position[" + indexOfArray + "] = " +ElementOfArray[innerIndex] );
            }
            int innerIndex2_EndCount = ElementOfArray.length;
            int productOfElements = 1;
            int sumOfElements = 0;
            int differenceOfElements = 0;
            String arrayOperation = inputTransactionFileVariables.get("ArrayOperation");
            switch(arrayOperation)
            {
                case "Addition" :
                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        sumOfElements += ElementOfArray[innerIndex2];
                    }
                    ArraySum[indexOfArray] = sumOfElements;
                    logger.info("Sum value at position [" + indexOfArray + "] = " + ArraySum[indexOfArray]);
                    break;
                case "Subtraction" :
                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        differenceOfElements -= ElementOfArray[innerIndex2];
                    }
                    ArrayDifference[indexOfArray] = differenceOfElements;
                    logger.info("Difference value at position [" + indexOfArray + "] = " + ArrayDifference[indexOfArray]);
                    break;
                case "Multiplication" :
                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        productOfElements *= ElementOfArray[innerIndex2];
                    }
                    ArrayProduct[indexOfArray] = productOfElements;
                    logger.info("Product value at position [" + indexOfArray + "] = " + ArrayProduct[indexOfArray]);
                    break;
                default:
                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        productOfElements *= ElementOfArray[innerIndex2];
                        sumOfElements += ElementOfArray[innerIndex2];
                        differenceOfElements -= ElementOfArray[innerIndex2];
                    }
                    ArrayProduct[indexOfArray] = productOfElements;
                    ArraySum[indexOfArray] = sumOfElements;
                    ArrayDifference[indexOfArray] = differenceOfElements;

                    logger.info("Product value at position [" + indexOfArray + "] = " + ArrayProduct[indexOfArray]);
                    logger.info("Sum value at position [" + indexOfArray + "] = " + ArraySum[indexOfArray]);
                    logger.info("Difference value at position [" + indexOfArray + "] = " + ArrayDifference[indexOfArray]);
            }




        }
    }
}


