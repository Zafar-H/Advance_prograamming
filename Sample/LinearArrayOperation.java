import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.commons.lang.*;

import java.io.*;
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
        logger.info("Delimiter used for segregating array values : [ " + commaDelimiter + " ] " );
        logger.info("Delimiter used for segregating key value pairs : [ " + colonDelimiter + " ] " );
        logger.info("Delimiter used for segregating variable name : [ " + dotDelimiter + " ] " );
        logger.info("Delimiter used for comments : [ " + hashDelimiter + " ] " );

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
        logger.info("Position of second expected argument : [ " + firstArgumentPosition + " ] " );


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


        //validations for transaction.dat file
        //check if specified file exists or not
        logger.trace("validations for transaction.dat file...");
        logger.trace("check if specified file exists or not...");
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
        logger.info( "system.dat file formatted successfully." );
        logger.info("The file content after formatting...");
        for(String eachLine : transactionFileLines)
        {
            logger.info(" [ " + eachLine.replaceAll("\\n+", "") + " ] ");
            transactionBufferedWriter.write(eachLine);
        }
        transactionBufferedWriter.flush();
        transactionBufferedWriter.close();

        //Taking variables from the file
        logger.trace("Taking variables from the file...");
        Scanner systemFile = new Scanner(argumentSystemFile);
        Scanner transactionFile = new Scanner(argumentTransactionFile);

        String[] currentSystemFileVariable = new String[2];
        String commentLine;
        HashMap<String, String> inputSystemFileVariables = new HashMap<String, String>();
        while(systemFile.hasNextLine())
        {
            if (!systemFile.nextLine().startsWith(hashDelimiter))
            {
                currentSystemFileVariable = systemFile.nextLine().split(colonDelimiter);
                inputSystemFileVariables.put(currentSystemFileVariable[0], currentSystemFileVariable[1]);
            }

        }

        //Collection level validation
        //Checking whether key exist in collection
        logger.trace("Checking whether key exist in collection..");
        int collectionSize = inputSystemFileVariables.size();
        int noKeys = 0;
        if (collectionSize != noKeys)
        {
            logger.trace("The value are stored in collection");
        }
        else
        {
            logger.error("No valid values present in collection!!!");
            System.exit(-3);
        }
        //The values stored in collection are...
        logger.info("Line starts with [ " + hashDelimiter + " ] Hence it is considered comment. ");
        logger.trace("The values stored in collection are...");
        for(Map.Entry mapElement : inputSystemFileVariables.entrySet())
        {
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();
            logger.trace(" Key : [ " + collectionKey + " ] " + " Value : [ " + collectionValue + " ] ");
            //Checking whether key exist


            //Checking whether key is alpha numeric
            /*if(!StringUtils.isAlphanumeric(collectionKey))
            {
                logger.info("Key is invalid, hence exiting!!!");
                System.exit(-404);
            }*/
        }

        //Checking whether minimum arrayCount is specified
        logger.trace("Checking whether minimum arrayCount is specified...");
        String minArrayCountString = inputSystemFileVariables.get("minArrayCount");
        if(minArrayCountString == null)
        {
            logger.error("Minimum array count is not specified");
            System.exit(-404);
        }
        logger.trace("Minimum arrayCount is specified");

        //Checking whether maximum arrayCount is specified
        logger.trace("Checking whether maximum arrayCount is specified...");
        String maxArrayCountString = inputSystemFileVariables.get("maxArrayCount");
        if(maxArrayCountString == null)
        {
            logger.error("Maximum array count is not specified");
            System.exit(-404);
        }
        logger.trace("maximum arrayCount is specified");

        //Checking whether arrayCount is specified
        logger.trace("Checking whether arrayCount is specified...");
        String arrayCountString = inputSystemFileVariables.get("arrayCount");
        if(arrayCountString == null)
        {
            logger.error("Array count is not specified");
            System.exit(-404);
        }
        logger.trace("ArrayCount is specified...");


        //checking whether array count is within the min and max array count
        logger.trace("Checking whether array count is within the min and max array count...");
        int minArrayCount = Integer.parseInt(minArrayCountString);
        int maxArrayCount = Integer.parseInt(maxArrayCountString);
        int arrayCount = Integer.parseInt(arrayCountString);
        logger.info("Minimum array count : [ " + minArrayCount + " ] ");
        logger.info("Maximum array count : [ " + maxArrayCount + " ] ");
        logger.info("Array count : [ " + arrayCount + " ] ");

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



    }
}



