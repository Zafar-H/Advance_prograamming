import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.commons.lang.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class LinearArrayOperation
{
    public static final Logger logger = LogManager.getLogger(LinearArrayOperation.class);

    public static void main(String args[]) throws IOException
    {
        //Creating variables to stores system.dat and transaction.dat
        logger.trace("Assigning default system and transaction files to variable...");
        String programArgument_1 = "default/system.dat";
        String programArgument_2 = "default/transaction.dat";

        //Checking whether default files are specified...
        logger.trace("Checking whether default system file is specified...");
        if (programArgument_1 != null)
        {
            logger.trace("Default system file is specified...");
            logger.info("Default system file : [ " + programArgument_1 + " ] ");
        }
        else
        {
            logger.error("Exiting because default system file is not specified :(");
            System.exit(-3);
        }

        logger.trace("Checking whether default transaction file is specified...");
        if (programArgument_2 != null)
        {
            logger.trace("Default transaction file is specified...");
            logger.info(" default system file : [ " + programArgument_2 + " ] ");
        }
        else
        {
            logger.error("Exiting because default system.dat file is not specified :(");
            System.exit(-3);
        }

        //splitter constant literals
        logger.trace("Specifying delimiter for segregation...");
        String commaDelimiter = ",";
        String spaceDelimiter = " ";
        String colonDelimiter = ":";
        String dotDelimiter = ".";
        String hashDelimiter = "#";
        String keywordForArrayValues = "ELEMENTS.";
        String alphaNumericCondition = "^[a-zA-Z0-9]*$";
        String alphaNumericCondition_2 = "^[a-zA-Z0-9" + keywordForArrayValues + "]*$";

        logger.info("Delimiter used for segregating array values : [ " + commaDelimiter + " ] " );
        logger.info("Delimiter used for segregating key value pairs : [ " + colonDelimiter + " ] " );
        logger.info("Delimiter used for segregating variable name : [ " + dotDelimiter + " ] " );
        logger.info("Delimiter used for comments : [ " + hashDelimiter + " ] " );
        logger.info("Keyword to identify ArrayValues : [ " + keywordForArrayValues + " ] " );

        //Checking whether argument is passed
        //Finding the number of arguments
        logger.trace("Checking whether argument is passed...");
        logger.trace("Finding the number of arguments...");
        int argumentLength = args.length;
        int noArgument = 0;
        logger.info("Argument count : [ " + argumentLength + " ] ");

        //Specifying expected number of argument
        logger.trace("Specifying expected number of argument...");
        int maxNumberOfArgument = 2;
        logger.info("Expected number of argument : [ " + maxNumberOfArgument + " ] ");

        //Specifying the position of expected arguments
        logger.trace("Specifying the position of expected arguments...");
        int firstArgumentPosition = 0;
        int secondArgumentPosition = 1;
        logger.info("Position of first expected argument : [ " + firstArgumentPosition + " ] " );
        logger.info("Position of second expected argument : [ " + secondArgumentPosition + " ] " );


        //Get the number of arguments passed
        logger.trace("Getting the number of arguments passed");
        int numberOfArgument = argumentLength;
        int numberOne = 1;
        logger.info("Number of arguments passed : [ " + numberOfArgument + " ] ");

        //If at least 1 arg is specified than override it with default file
        if (numberOfArgument == numberOne)
        {
            logger.trace("Overriding default system file...");
            programArgument_1 = args[firstArgumentPosition];
            logger.info("Overridden argument file is : [ " + programArgument_1 + " ] ");
            logger.info("Second argument is not specified hence using default file : [ " + programArgument_2 + " ] ");
        }

        //If there are more than 2 arguments, take the first 2 and ignore the rest. Override 2 args with default files
        if ( numberOfArgument >= maxNumberOfArgument )
        {
            logger.trace("Overriding the default files with files passed as argument...");
            logger.trace("Considering the first 2 arguments and ignoring the rest...");
            programArgument_1 = args[firstArgumentPosition];
            programArgument_2 = args[secondArgumentPosition];
            logger.info("Overridden argument files are : [ " + programArgument_1 + " , " + programArgument_2 + " ] ");
        }


        //Storing argument or default files in variable
        logger.trace("Storing argument or default files in variable...");
        File argumentSystemFile = new File(programArgument_1);
        File argumentTransactionFile = new File(programArgument_2);
        logger.info("The program arguments are : [ " + argumentSystemFile + " , " + argumentTransactionFile + " ] ");

        //Validation check for the files passed as argument
        //validations for system.dat file
        //check if specified file exists or not
        logger.trace("Validation check for the files passed used...");
        logger.trace("Validation check for system.dat file...");
        logger.trace("Checking if specified file exists or not...");
        if (!argumentSystemFile.exists()){
            logger.error("File system.dat does not exist!!!");
            System.exit(-3);
        }
        logger.info( "system.dat file is not empty." );

        //checking permissions for system.dat file
        //Checking the Read permission
        logger.trace("Checking permissions for system.dat file...");
        logger.trace("Checking the Read permission...");
        if (!argumentSystemFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }
        logger.info("Read permission is granted for system.dat file.");

        //Checking the Write permission
        logger.trace("Checking the Write permission...");
        if (!argumentSystemFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }
        logger.info("Write permission is granted for system.dat file.");

        //Checking the Execute permission
        logger.trace("Checking the Execute permission...");
        if (!argumentSystemFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }
        logger.info("Execute permission is granted for system.dat file.");

        //Checking if the file is empty
        logger.trace("Checking if the file is empty...");
        if (argumentSystemFile.length() == 0)
        {
            logger.error(" File is empty");
            System.exit(-404);
        }
        logger.info( "system.dat File is not empty" );

        //deleting leading and trailing white spaces of system.dat file
        logger.trace("deleting leading and trailing white spaces..");
        FileReader fileReader = new FileReader(argumentSystemFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<String>();
        String line;
        logger.info("File content before formatting.");
        while ((line = bufferedReader.readLine()) != null) {
            logger.info(" [ " + line + " ] ");

            line = line.trim();
            //deleting extra whitespaces and the spaces before and after ":" in system.dat
            line = line.replaceAll("\\s+", spaceDelimiter).replaceAll("\\s+" + colonDelimiter, colonDelimiter).replaceAll(colonDelimiter + "+\\s", colonDelimiter)+"\n";
            lines.add(line);
        }
        fileReader.close();
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(argumentSystemFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        logger.info( "system.dat file formatted successfully." );
        logger.info("The file content after formatting...");
        for(String eachLine : lines)
        {
            logger.info(" [ " + eachLine.replaceAll("\\n+", "") + " ] ");
            bufferedWriter.write(eachLine);
        }
        bufferedWriter.flush();
        bufferedWriter.close();


        //Taking variables from system.dat file
        logger.trace("Taking variables from system.dat file...");
        FileReader systemFileReader = new FileReader(argumentSystemFile);
        BufferedReader systemBufferedReader = new BufferedReader(systemFileReader);

        String[] currentSystemFileVariable = new String[2];
        int currentSystemFileVariableLength = currentSystemFileVariable.length;
        HashMap<String, String> inputSystemFileVariables = new HashMap<String, String>();
        logger.trace("Ignoring the commented lines if exist... ");
        while ((line = systemBufferedReader.readLine()) != null)
        {
            if (!line.startsWith(hashDelimiter))
            {
                currentSystemFileVariable = line.split(colonDelimiter);

                if(currentSystemFileVariable.length != currentSystemFileVariableLength)
                {
                    logger.info("Value for key [ " + currentSystemFileVariable[0] + " ] is not specified, hence exiting!!!");
                    System.exit(-3);
                }
                else
                {
                    inputSystemFileVariables.put(currentSystemFileVariable[0], currentSystemFileVariable[1]);
                }

            }
            else
            {
                logger.info("The line : [ " + line + " ] starts with [ " + hashDelimiter + " ] hence considering it as comment.");
            }

        }
        systemFileReader.close();
        systemBufferedReader.close();

        //Checking whether key exist in collection
        logger.trace("Checking whether key exist in collection...");
        int collectionSize = inputSystemFileVariables.size();
        int noKeys = 0;
        if (collectionSize != noKeys)
        {
            logger.trace("The Keys are present in the collection.");
        }
        else
        {
            logger.error("No valid keys are present in the collection!!!");
            System.exit(-3);
        }

        //The values stored in collection are...
        logger.trace("Printing the key value pairs stored in collection if values exist and keys are alpha numeric...");
        for(Map.Entry mapElement : inputSystemFileVariables.entrySet())
        {
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();
            if(!collectionValue.isEmpty())
            {
                logger.trace(" Key : [ " + collectionKey + " ] " + " Value : [ " + collectionValue + " ] ");

                //Checking whether keys are alpha numeric...
                if (!collectionKey.matches(alphaNumericCondition)) {
                    logger.info("Key : [ " + collectionKey + " ] is invalid, hence exiting!!!");
                    System.exit(-404);
                }
            }
            else
            {
                logger.error("The value for key : [ " + collectionKey + " ] is not specified, hence exiting!!!");
                System.exit(-3);
            }
        }

        //Checking whether minimum arrayCount is specified
        logger.trace("Checking whether minimum arrayCount is specified...");
        String minArrayCountString = inputSystemFileVariables.get("minArrayCount");
        if(minArrayCountString == null)
        {
            logger.error("Minimum array count is not specified");
            System.exit(-404);
        }
        logger.trace("Minimum arrayCount is specified.");
        logger.info("Minimum ArrayCount : [ " + minArrayCountString + " ] ");

        //Checking whether maximum arrayCount is specified
        logger.trace("Checking whether maximum arrayCount is specified...");
        String maxArrayCountString = inputSystemFileVariables.get("maxArrayCount");
        if(maxArrayCountString == null)
        {
            logger.error("Maximum array count is not specified");
            System.exit(-404);
        }
        logger.trace("maximum arrayCount is specified.");
        logger.info("Maximum ArrayCount : [ " + maxArrayCountString + " ] ");

        //Checking whether arrayCount is specified
        logger.trace("Checking whether arrayCount is specified...");
        String arrayCountString = inputSystemFileVariables.get("arrayCount");
        if(arrayCountString == null)
        {
            logger.error("Array count is not specified");
            System.exit(-404);
        }
        logger.trace("ArrayCount is specified.");
        logger.info("ArrayCount : [ " + arrayCountString + " ] ");


        //checking whether array count is within the min and max array count
        logger.trace("Checking whether array count is within the min and max array count...");
        int minArrayCount = Integer.parseInt(minArrayCountString);
        int maxArrayCount = Integer.parseInt(maxArrayCountString);
        int arrayCount = Integer.parseInt(arrayCountString);

        //Checking whether array count is in specified range...
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
        logger.trace("Array count is within the min and max array count.");

        //Checking whether start position of array is specified...
        logger.trace("Checking whether start position of array is specified...");
        String startCountString = inputSystemFileVariables.get("startCount");
        if(startCountString == null)
        {
            logger.error("Arrays start count is not specified");
            System.exit(-404);
        }
        int startCount = Integer.parseInt(inputSystemFileVariables.get("startCount"));
        logger.trace("start position of array is specified.");
        logger.info("Start position : [ " + startCount + " ] ");

        //Checking whether end position of array is specified...
        logger.trace("Checking whether end position of array is specified...");
        String endCountString = inputSystemFileVariables.get("endCount");
        if(endCountString == null)
        {
            logger.error("Arrays end count is not specified");
            System.exit(-404);
        }
        int endCount   = Integer.parseInt(inputSystemFileVariables.get("endCount"));
        logger.trace("end position of array is specified");
        logger.info("End position : [ " + endCount + " ] ");


        //The length of array is computed dynamically
        logger.trace("The length of array is computed dynamically...");
        int[] arrayElementCount = new int[arrayCount];
        for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++)
        {
            arrayElementCount[indexOfArray] = endCount;
        }
        logger.trace("Checking if the arrays are of same length otherwise exit...");
        logger.trace("Checking if the initial position of the arrays are within the array size...");
        logger.trace("Checking if the end position of the arrays are within the array size...");
        for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++)
        {
            //Checking if the arrays are of same length otherwise exit
            if ( indexOfArray != arrayCount-1)
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

        for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++)
        {
            logger.info("ElementCount of Array " +(indexOfArray+1)+ " = [" + arrayElementCount[indexOfArray] + "]");
        }






        //validations for transaction.dat file
        //check if specified file exists or not
        logger.trace("***********************************************************");
        logger.trace("Validations for transaction.dat file...");
        logger.trace("Check if specified file exists or not...");
        if (!argumentTransactionFile.exists()){
            logger.error("file does not exist!!!");
            System.exit(-3);
        }
        logger.trace("transaction.dat file exists.");

        //checking for permissions
        //Checking the Read permission
        logger.trace("Checking permissions transaction.dat file");
        logger.trace("Checking the Read permission...");
        if (!argumentTransactionFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }
        logger.trace("Read permission is granted for transaction.dat file.");

        //Checking the Write permission
        logger.trace("Checking the Write permission...");
        if (!argumentTransactionFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }
        logger.trace("Write permission is granted for transaction.dat file.");

        //Checking the Execute permission
        logger.trace("Checking the Execute permission...");
        if (!argumentTransactionFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }
        logger.trace("Execute permission is granted for transaction.dat file.");

        //Checking if the file is empty
        logger.trace("Checking if the file is empty...");
        if (argumentTransactionFile.length() == 0)
        {
            logger.error(" transaction.dat file is empty");
            System.exit(-404);
        }
        logger.info("transaction.dat file is not empty");

        //deleting leading and trailing white spaces of transaction.dat
        logger.trace("Deleting leading and trailing white spaces of transaction.dat...");
        FileReader transactionFileReader = new FileReader(argumentTransactionFile);
        BufferedReader transactionBufferedReader = new BufferedReader(transactionFileReader);

        List<String> transactionFileLines = new ArrayList<String>();
        String transactionFileLine;
        logger.info("File content before formatting.");
        while ((transactionFileLine = transactionBufferedReader.readLine()) != null) {
            logger.info(" [ " + transactionFileLine + " ] ");
            transactionFileLine = transactionFileLine.trim();
            //deleting extra whitespaces and the spaces before and after ":" in transaction.dat file
            transactionFileLine = transactionFileLine.replaceAll("\\s+", spaceDelimiter).replaceAll("\\s+" +colonDelimiter, colonDelimiter).replaceAll(colonDelimiter + "+\\s", colonDelimiter)+"\n";
            transactionFileLines.add(transactionFileLine);

        }
        transactionFileReader.close();
        transactionBufferedReader.close();
        FileWriter transactionFileWriter = new FileWriter(argumentTransactionFile);
        BufferedWriter transactionBufferedWriter = new BufferedWriter(transactionFileWriter);
        logger.info( "transaction.dat file formatted successfully." );
        logger.info("The file content after formatting...");
        for(String eachLine : transactionFileLines)
        {
            logger.info(" [ " + eachLine.replaceAll("\\n+", "") + " ] ");
            transactionBufferedWriter.write(eachLine);
        }
        transactionBufferedWriter.flush();
        transactionBufferedWriter.close();

        //Taking variables from system.dat file
        logger.trace("Taking variables from transaction.dat file...");
        FileReader transactionFileReader_2 = new FileReader(argumentTransactionFile);
        BufferedReader transactionBufferedReader_2 = new BufferedReader(transactionFileReader_2);

        String[] currentTransactionFileVariable = new String[2];
        int currentTransactionFileVariableLength = currentTransactionFileVariable.length;
        HashMap<String, String> inputTransactionFileVariables = new HashMap<String, String>();
        logger.trace("Ignoring the commented lines if exist... ");
        while ((line = transactionBufferedReader_2.readLine()) != null)
        {
            if (!line.startsWith(hashDelimiter))
            {
                currentTransactionFileVariable = line.split(colonDelimiter);

                if(currentTransactionFileVariable.length != currentTransactionFileVariableLength)
                {
                    logger.info("Value for key [ " + currentTransactionFileVariable[0] + " ] is not specified, hence exiting!!!");
                    System.exit(-3);

                }
                else
                {
                    inputTransactionFileVariables.put(currentTransactionFileVariable[0], currentTransactionFileVariable[1]);
                }

            }
            else
            {
                logger.info("The line : [ " + line + " ] starts with [ " + hashDelimiter + " ] hence considering it as comment.");
            }
        }
        transactionFileReader_2.close();
        transactionBufferedReader_2.close();


        //Checking whether key exist in collection
        logger.trace("Checking whether key exist in collection...");
        int transactionCollectionSize = inputTransactionFileVariables.size();
        if (transactionCollectionSize != noKeys)
        {
            logger.trace("The Keys are present in the collection.");
        }
        else
        {
            logger.error("No valid keys are present in the collection!!!");
            System.exit(-3);
        }

        //The values stored in collection are...
        int countOfKeywordForArrayValues = 0;
        logger.trace("Printing the key value pairs stored in collection if values exist and keys are alpha numeric...");
        for(Map.Entry mapElement : inputTransactionFileVariables.entrySet())
        {
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();
            if(!collectionValue.isEmpty())
            {
                if (collectionKey.startsWith(keywordForArrayValues))
                {
                    countOfKeywordForArrayValues = countOfKeywordForArrayValues + 1;
                    //Checking whether key except the one starting with 'ELEMENTS' are alpha numeric
                    if (!collectionKey.matches(alphaNumericCondition_2)) {
                        logger.info("Key : [ " + collectionKey + " ] is invalid, hence exiting!!!");
                        System.exit(-404);
                    }
                }
                else
                {
                    //Checking whether key except the one starting with 'ELEMENTS' are alpha numeric
                    if (!collectionKey.matches(alphaNumericCondition)) {
                        logger.info("Key : [ " + collectionKey + " ] is invalid, hence exiting!!!");
                        System.exit(-404);
                    }
                }
                logger.trace(" Key : [ " + collectionKey + " ] " + " Value : [ " + collectionValue + " ] ");

            }
            else
            {
                logger.error("The value for key : [ " + collectionKey + " ] is not specified, hence exiting!!!");
                System.exit(-3);
            }
        }

        //Checking whether array names are specified
        logger.trace("Checking whether array names are specified...");
        String arrayNamesString = inputTransactionFileVariables.get("ArrayNames");
        if(arrayNamesString == null)
        {
            logger.error("Array names are not specified");
            System.exit(-404);
        }
        logger.trace("Array names are specified.");
        logger.info("Array names : [ " + arrayNamesString + " ] ");

        //Converting string values of array to integer...
        logger.trace("Converting string values of array to integer...");
        String[] arrayNamesArray = arrayNamesString.split(commaDelimiter);
        //Counting the names specified
        logger.trace("Counting the names specified...");
        int nameCount = arrayNamesArray.length;
        logger.info("Name count : [ " + nameCount + " ] ");

        //Count of variables starting with 'ELEMENT'
        logger.trace("Counting of variables name starting with 'ELEMENT'...");
        logger.info("Variables name count starting with 'ELEMENT' : [ " + countOfKeywordForArrayValues + " ] ");

        //Checking whether array operation is specified
        logger.trace("Checking whether array operation is specified...");
        String arrayOperationString = inputTransactionFileVariables.get("ArrayOperation");
        if(arrayOperationString == null)
        {
            logger.error("Array operation is not specified");
            System.exit(-404);
        }
        logger.trace("Array operation is specified.");
        logger.info("Array operation : [ " + arrayOperationString + " ] ");

        if (nameCount != countOfKeywordForArrayValues)
        {
            logger.error("Count of array values specified does not match the array count, hence exiting!!!");
            System.exit(-3);
        }
        logger.info("Count of array values specified matches the array count.");

        logger.trace("Comparing array name in arrayNames variable with name in the variable that starts with 'ELEMENTS' if matched storing array name and values in hashmap...");
        String[] arrayValues_StringArray;
        HashMap<String, Object[]> arrayNameValueList = new HashMap<String, Object[]>();
        for(Map.Entry mapElement : inputTransactionFileVariables.entrySet())
        {
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();

            if (collectionKey.startsWith(keywordForArrayValues))
            {
                arrayValues_StringArray = collectionValue.split(commaDelimiter);
                int loopEndCount2 = arrayValues_StringArray.length-1;
                ArrayList<Integer> arrList=new ArrayList<Integer>();
                for (int indexOfArray=startCount; indexOfArray <= loopEndCount2; indexOfArray++)
                {
                    arrList.add(Integer.parseInt(arrayValues_StringArray[indexOfArray]));
                }
                Object[] array = arrList.toArray();


                int containsFlag = 0;
                for (int indexOfArray=startCount; indexOfArray < nameCount; indexOfArray++)
                {
                    if(collectionKey.contains(arrayNamesArray[indexOfArray]))
                    {
                        arrayNameValueList.put(arrayNamesArray[indexOfArray], array);
                        containsFlag = containsFlag+1;
                        logger.info("Key : [ " + arrayNamesArray[indexOfArray] + " ] Value : " + arrList);
                    }

                }
                if (containsFlag == 0)
                {
                    logger.error("The key [ " + collectionKey + " ] does not match the array names, hence exiting!!!");
                    System.exit(-3);
                }

            }

        }

        //Specifying variables to store the transaction or operations of 4 arrays
        int ArrayProduct[] = new int[endCount];
        int ArraySum[] = new int[endCount];
        int ArrayDifference[] = new int[endCount];

        //Looping construct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should same
        logger.trace("Performing addition, subtraction or multiplication operation on Linear Arrays if specified, else performing all three operations. ");

        for(int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++ ){

            String arrayOperation = inputTransactionFileVariables.get("ArrayOperation");
            logger.info("Operation specified : [ " + arrayOperation + " ] hence performing [ " + arrayOperation + " ] operation...");
            int[] ElementOfArray = new int[arrayCount];
            int innerIndex2_EndCount = ElementOfArray.length;
            int productOfElements = 1;
            int sumOfElements = 0;
            int differenceOfElements = 0;

            switch(arrayOperation)
            {
                case "Addition" :
                    for(int innerIndexOfArray = startCount; innerIndexOfArray < arrayCount; innerIndexOfArray++){
                        ElementOfArray[innerIndexOfArray] = (int)arrayNameValueList.get(arrayNamesArray[innerIndexOfArray])[indexOfArray] ;
                        logger.info("Element value of " + arrayNamesArray[innerIndexOfArray] + " in the position [ " + indexOfArray + " ] = " +ElementOfArray[innerIndexOfArray] );
                    }

                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        sumOfElements += ElementOfArray[innerIndex2];
                    }
                    ArraySum[indexOfArray] = sumOfElements;

                    logger.info("Sum value at position [ " + indexOfArray + " ] = " + ArraySum[indexOfArray]);
                    break;

                case "Subtraction" :
                    for(int innerIndexOfArray = startCount; innerIndexOfArray < arrayCount; innerIndexOfArray++){
                        ElementOfArray[innerIndexOfArray] = (int)arrayNameValueList.get(arrayNamesArray[innerIndexOfArray])[indexOfArray] ;
                        logger.info("Element value of " + arrayNamesArray[innerIndexOfArray] + " in the position [ " + indexOfArray + " ] = " +ElementOfArray[innerIndexOfArray] );
                    }

                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        differenceOfElements -= ElementOfArray[innerIndex2];
                    }
                    ArrayDifference[indexOfArray] = differenceOfElements;

                    logger.info("Difference value at position [ " + indexOfArray + " ] = " + ArrayDifference[indexOfArray]);
                    break;

                case "Multiplication" :
                    for(int innerIndexOfArray = startCount; innerIndexOfArray < arrayCount; innerIndexOfArray++){
                        ElementOfArray[innerIndexOfArray] = (int)arrayNameValueList.get(arrayNamesArray[innerIndexOfArray])[indexOfArray] ;
                        logger.info("Element value of " + arrayNamesArray[innerIndexOfArray] + " in the position [ " + indexOfArray + " ] = " +ElementOfArray[innerIndexOfArray] );
                    }

                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        productOfElements *= ElementOfArray[innerIndex2];
                    }
                    ArrayProduct[indexOfArray] = productOfElements;

                    logger.info("Product value at position [ " + indexOfArray + " ] = " + ArrayProduct[indexOfArray]);
                    break;

                case "All" :
                    for(int innerIndexOfArray = startCount; innerIndexOfArray < arrayCount; innerIndexOfArray++){
                        ElementOfArray[innerIndexOfArray] = (int)arrayNameValueList.get(arrayNamesArray[innerIndexOfArray])[indexOfArray] ;
                        logger.info("Element value of " + arrayNamesArray[innerIndexOfArray] + " in the position [ " + indexOfArray + " ] = " +ElementOfArray[innerIndexOfArray] );
                    }

                    for (int innerIndex2 = startCount; innerIndex2 < innerIndex2_EndCount; innerIndex2++)
                    {
                        productOfElements *= ElementOfArray[innerIndex2];
                        sumOfElements += ElementOfArray[innerIndex2];
                        differenceOfElements -= ElementOfArray[innerIndex2];
                    }

                    ArrayProduct[indexOfArray] = productOfElements;
                    ArraySum[indexOfArray] = sumOfElements;
                    ArrayDifference[indexOfArray] = differenceOfElements;

                    logger.info("Product value at position [ " + indexOfArray + " ] = " + ArrayProduct[indexOfArray]);
                    logger.info("Sum value at position [ " + indexOfArray + " ] = " + ArraySum[indexOfArray]);
                    logger.info("Difference value at position [ " + indexOfArray + " ] = " + ArrayDifference[indexOfArray]);
                    break;

                default :
                    logger.error("Invalid Operation specified, hence exiting!!!");
                    System.exit(-3);
            }
        }
    }
}
