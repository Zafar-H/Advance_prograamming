import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class LinearArrayOperation_L2 {
    public static final Logger logger = LogManager.getLogger( LinearArrayOperation_L2.class );

    public static void main(String args[]) throws IOException, ParseException {

        String programArgument_1 = "default/system.json";
        String programArgument_2 = "default/transaction.json";

        //splitter constant literals
        logger.trace( "Specifying delimiter for segregation..." );
        String underscoreDelimiter = "_";
        String alphaNumericCondition = "^[a-zA-Z0-9]*$";

        logger.info( "Delimiter used for comments : [ " + underscoreDelimiter + " ] " );

        //Checking whether default files are specified...
        logger.trace( "Checking whether default system file is specified..." );
        if (programArgument_1 != null) {
            logger.trace( "Default system file is specified..." );
            logger.info( "Default system file : [ " + programArgument_1 + " ] " );
        } else {
            logger.error( "Exiting because default system file is not specified :(" );
            System.exit( -3 );
        }

        logger.trace( "Checking whether default transaction file is specified..." );
        if (programArgument_2 != null) {
            logger.trace( "Default transaction file is specified..." );
            logger.info( " default transaction file : [ " + programArgument_2 + " ] " );
        } else {
            logger.error( "Exiting because default transaction file is not specified, hence exiting!!!" );
            System.exit( -3 );
        }

        //Checking whether argument is passed
        //Finding the number of arguments
        logger.trace( "Checking whether argument is passed..." );
        logger.trace( "Finding the number of arguments..." );
        int argumentLength = args.length;
        int noArgument = 0;
        logger.info( "Argument count : [ " + argumentLength + " ] " );

        //Specifying expected number of argument
        logger.trace( "Specifying expected number of argument..." );
        int maxNumberOfArgument = 2;
        logger.info( "Expected number of argument : [ " + maxNumberOfArgument + " ] " );

        //Specifying the position of expected arguments
        logger.trace( "Specifying the position of expected arguments..." );
        int firstArgumentPosition = 0;
        int secondArgumentPosition = 1;
        logger.info( "Position of first expected argument : [ " + firstArgumentPosition + " ] " );
        logger.info( "Position of second expected argument : [ " + secondArgumentPosition + " ] " );


        //Get the number of arguments passed
        logger.trace( "Getting the number of arguments passed" );
        int numberOfArgument = argumentLength;
        int numberOne = 1;
        logger.info( "Number of arguments passed : [ " + numberOfArgument + " ] " );

        //If at least 1 arg is specified than override it with default file
        if (numberOfArgument == numberOne) {
            logger.trace( "Overriding default system file..." );
            programArgument_1 = args[firstArgumentPosition];
            logger.info( "Overridden argument file is : [ " + programArgument_1 + " ] " );
            logger.info( "Second argument is not specified hence using default file : [ " + programArgument_2 + " ] " );
        }

        //If there are more than 2 arguments, take the first 2 and ignore the rest. Override 2 args with default files
        if (numberOfArgument >= maxNumberOfArgument) {
            logger.trace( "Overriding the default files with files passed as argument..." );
            logger.trace( "Considering the first 2 arguments and ignoring the rest..." );
            programArgument_1 = args[firstArgumentPosition];
            programArgument_2 = args[secondArgumentPosition];
            logger.info( "Overridden argument files are : [ " + programArgument_1 + " , " + programArgument_2 + " ] " );
        }


        //Storing argument or default files in variable
        logger.trace( "Storing argument or default files in variable..." );
        File argumentSystemFile = new File( programArgument_1 );
        File argumentTransactionFile = new File( programArgument_2 );
        logger.info( "The files to be used in program is : [ " + argumentSystemFile + " , " + argumentTransactionFile + " ] " );

        //Validation check for the files passed as argument
        //validations for system.dat file
        //check if specified file exists or not
        logger.trace( "Validation check for the files used..." );
        logger.trace( "Validation check for system.dat file..." );
        logger.trace( "Checking if specified file exists or not..." );
        if (!argumentSystemFile.exists()) {
            logger.error( "File system.json does not exist!!!" );
            System.exit( -3 );
        }
        logger.info( "system.json file exists." );

        //checking permissions for system.json file
        //Checking the Read permission
        logger.trace( "Checking permissions for system.json file..." );
        logger.trace( "Checking the Read permission..." );
        if (!argumentSystemFile.canRead()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Read!!!" );
            System.exit( -402 );
        }
        logger.info( "Read permission is granted for system.json file." );

        //Checking the Write permission
        logger.trace( "Checking the Write permission..." );
        if (!argumentSystemFile.canWrite()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Write!!!" );
            System.exit( -401 );
        }
        logger.info( "Write permission is granted for system.json file." );

        //Checking the Execute permission
        logger.trace( "Checking the Execute permission..." );
        if (!argumentSystemFile.canExecute()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Execute!!!" );
            System.exit( -403 );
        }
        logger.info( "Execute permission is granted for system.json file." );

        //Checking if the file is empty
        logger.trace( "Checking if the file is empty..." );
        if (argumentSystemFile.length() == 0) {
            logger.error( " File is empty" );
            System.exit( -404 );
        }
        logger.info( "system.json File is not empty" );

        JSONParser parser = new JSONParser();

        Object programSystemFile = parser.parse( new FileReader( argumentSystemFile ) );
        JSONObject jsonObject = (JSONObject) programSystemFile;

        ArrayList <AbstractMap.SimpleEntry <String, String>> systemFileValues = new ArrayList <AbstractMap.SimpleEntry <String, String>>();
        Set <String> keys = jsonObject.keySet();

        //checking for comment keys...
        for (String key : keys) {
            if (!key.startsWith( underscoreDelimiter )) {
                systemFileValues.add( new AbstractMap.SimpleEntry( key, jsonObject.get( key ) ) );
            } else {
                logger.info( "The key [ " + key + " ] starts with [ " + underscoreDelimiter + " ] hence considering as a comment" );
            }

            if (!(key.matches( alphaNumericCondition ))) {
                logger.error( "Key : [ " + key + " ] is invalid, hence exiting!!!" );
                System.exit( -404 );
            }
        }

        for (int indexOfArray = 0; indexOfArray < systemFileValues.size(); indexOfArray++) {
            AbstractMap.SimpleEntry <String, String> map = systemFileValues.get( indexOfArray );
            logger.info( "key : " + map.getKey() + " Value : " + map.getValue() );

        }

        //String minCountString = (String)jsonObject.get("minCount");

        //Checking if mandatory values are specified
        logger.info( "Checking if all the mandatory values exists" );

        //Checking whether minimum arrayCount is specified
        logger.trace( "Checking whether minimum arrayCount is specified..." );
        String minArrayCountString = (String) jsonObject.get( "minArrayCount" );
        if (minArrayCountString == null) {
            logger.error( "Minimum array count is not specified" );
            System.exit( -404 );
        }
        logger.trace( "Minimum arrayCount is specified." );
        logger.info( "Minimum ArrayCount : [ " + minArrayCountString + " ] " );

        //Checking whether maximum arrayCount is specified
        logger.trace( "Checking whether maximum arrayCount is specified..." );
        String maxArrayCountString = (String) jsonObject.get( "maxArrayCount" );
        if (maxArrayCountString == null) {
            logger.error( "Maximum array count is not specified" );
            System.exit( -404 );
        }
        logger.trace( "maximum arrayCount is specified." );
        logger.info( "Maximum ArrayCount : [ " + maxArrayCountString + " ] " );

        //Checking whether arrayCount is specified
        logger.trace( "Checking whether arrayCount is specified..." );
        String arrayCountString = (String) jsonObject.get( "arrayCount" );
        if (arrayCountString == null) {
            logger.error( "Array count is not specified" );
            System.exit( -404 );
        }
        logger.trace( "ArrayCount is specified." );
        logger.info( "ArrayCount : [ " + arrayCountString + " ]" );

        //checking whether array count is within the min and max array count
        logger.trace( "Checking whether array count is within the min and max array count..." );
        int minArrayCount = Integer.parseInt( minArrayCountString );
        logger.trace( "Min count in integer: [" + minArrayCount + "]" );
        int maxArrayCount = Integer.parseInt( maxArrayCountString );
        logger.trace( "Max count in integer: [" + minArrayCount + "]" );
        int arrayCount = Integer.parseInt( arrayCountString );
        logger.trace( "Array count in integer: [" + minArrayCount + "]" );

        //Checking whether array count is in specified range...
        if (arrayCount < minArrayCount) {
            logger.error( "Minimum " + minArrayCount + " arrays required" );
            System.exit( -404 );
        }
        if (arrayCount > maxArrayCount) {
            logger.error( "Maximum " + maxArrayCount + " arrays can only be specified" );
            System.exit( -404 );
        }
        logger.info( "Array count is within the min and max array count." );

        //Checking whether start position of array is specified...
        logger.trace( "Checking whether start position of array is specified..." );
        String startCountString = (String) jsonObject.get( "startCount" );
        if (startCountString == null) {
            logger.error( "Arrays start count is not specified" );
            System.exit( -404 );
        }
        int startCount = Integer.parseInt( (String) jsonObject.get( "startCount" ) );
        logger.trace( "start position of array is specified." );
        logger.info( "Start position : [ " + startCount + " ] " );

        //Checking whether end position of array is specified...
        logger.trace( "Checking whether end position of array is specified..." );
        String endCountString = (String) jsonObject.get( "endCount" );
        if (endCountString == null) {
            logger.error( "Arrays end count is not specified" );
            System.exit( -404 );
        }
        int endCount = Integer.parseInt( (String) jsonObject.get( "endCount" ) );
        logger.trace( "end position of array is specified" );
        logger.info( "End position : [ " + endCount + " ] " );

        //validations for transaction.dat file
        //check if specified file exists or not
        logger.trace( "***********************************************************" );
        logger.trace( "Validations for transaction.dat file..." );
        logger.info( "Check if specified file exists or not..." );
        if (!argumentTransactionFile.exists()) {
            logger.error( "file does not exist!!!" );
            System.exit( -3 );
        }
        logger.info( "transaction.dat file exists." );

        //checking for permissions
        //Checking the Read permission
        logger.info( "Checking permissions transaction.dat file" );
        logger.trace( "Checking the Read permission..." );
        if (!argumentTransactionFile.canRead()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Read!!!" );
            System.exit( -402 );
        }
        logger.info( "Read permission is granted for transaction.dat file." );

        //Checking the Write permission
        logger.trace( "Checking the Write permission..." );
        if (!argumentTransactionFile.canWrite()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Write!!!" );
            System.exit( -401 );
        }
        logger.info( "Write permission is granted for transaction.dat file." );

        //Checking the Execute permission
        logger.trace( "Checking the Execute permission..." );
        if (!argumentTransactionFile.canExecute()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Execute!!!" );
            System.exit( -403 );
        }
        logger.info( "Execute permission is granted for transaction.dat file." );

        //Checking if the file is empty
        logger.trace( "Checking if the file is empty..." );
        if (argumentTransactionFile.length() == 0) {
            logger.error( " transaction.dat file is empty" );
            System.exit( -404 );
        }
        logger.info( "transaction.dat file is not empty" );
        logger.info( "***************************************************" );

        Object programTransactionFile = parser.parse( new FileReader( argumentTransactionFile ) );
        JSONObject jsonObject2 = (JSONObject) programTransactionFile;

        //HashMap<String, String> transactionFileValues1 = new HashMap<String, String>();
        //HashMap<String, String> arrayNameValues1 = new HashMap<String, String>();


        ArrayList <AbstractMap.SimpleEntry <String, String>> transactionFileValues = new ArrayList <AbstractMap.SimpleEntry <String, String>>();
        ArrayList <AbstractMap.SimpleEntry <String, Integer[]>> arrayNameValues = new ArrayList <AbstractMap.SimpleEntry <String, Integer[]>>();
        Set <String> transactionKeys = jsonObject2.keySet();
        //checking for comment keys...
        logger.trace( "Printing input variables and their values... " );
        for (String key : transactionKeys) {
            if (!key.startsWith( underscoreDelimiter )) {
                //transactionFileValues1.put(key, jsonObject2.get(key).toString());

                transactionFileValues.add( new AbstractMap.SimpleEntry( key, jsonObject2.get( key ) ) );
            }
            else {
                logger.info( "The key [ " + key + " ] starts with [ " + underscoreDelimiter + " ] hence considering as a comment" );
            }

            if (!(key.matches( alphaNumericCondition ))) {
                logger.error( "Key : [ " + key + " ] is invalid, hence exiting!!!" );
                System.exit( -404 );
            }

            //printing all the key value pairs
            if(key.matches( "Arrays" ) ){
                Object arrayNameValuePair = jsonObject2.get(key);
                JSONObject jsonObject3 = (JSONObject) arrayNameValuePair;

                Set <String> arrayNames = jsonObject3.keySet();

                for (String arrayName : arrayNames)
                {
                    if (!arrayName.startsWith( underscoreDelimiter )) {
                        //arrayNameValues1.put(arrayName, jsonObject3.get(arrayName).toString());
                        logger.info("$$$$$$$$$$$$$$$$$$4" +jsonObject3.get(arrayName));

                        arrayNameValues.add( new AbstractMap.SimpleEntry( arrayName, jsonObject3.get(arrayName) ) );
                    }
                    else{
                        logger.info( "The key [ " + arrayName + " ] starts with [ " + underscoreDelimiter + " ] hence considering as a comment" );
                    }
                    if (!arrayName.startsWith( underscoreDelimiter ) && !(arrayName.matches( alphaNumericCondition ))) {
                        logger.error( "Key : [ " + arrayName + " ] is invalid, hence exiting!!!" );
                        System.exit( -404 );
                    }
                }
            }
        }


        for (int indexOfArray = 0; indexOfArray < transactionFileValues.size(); indexOfArray++) {

            AbstractMap.SimpleEntry <String, String> map1 = transactionFileValues.get( indexOfArray );

            if ((map1.getKey()).matches("Arrays")) {
                logger.info( "key : " + map1.getKey());
                for (int innerIndexOfArray = 0; innerIndexOfArray < arrayNameValues.size(); innerIndexOfArray++)
                {
                    AbstractMap.SimpleEntry <String, Integer[]> map2 = arrayNameValues.get( innerIndexOfArray );
                    logger.info( "Array Name : [ " + map2.getKey()+ " ] Value : " +map2.getValue());
                    /*JSONArray getArray = arrayNameValues.getJSONArray("JArray1");
                    JSONArray array = new JSONArray();
                    array.add(map2.getValue());
                    int fEle = getArray.getJSONObject(array);*/
                }
            }
            else
            {
                logger.info( "key : [ " + map1.getKey() + " ] " + map1.getValue() + " ] ");
            }
        }

        //++++++++++++++++++++++++++++++++++++++++++++++++++
        logger.trace("++++++++++++++++++++++++++++++++++++++++++++++++++");
        /*HashMap<String, String> map11 = new HashMap<String, String>();
        for(Map.Entry mapElement : transactionFileValues1.entrySet()){
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();
            logger.trace(" Key : [ " + collectionKey + " ] " + " Value : [ " + collectionValue + " ] ");
        }*/
        logger.trace("++++++++++++++++++++++++++++++++++++++++++++++++++");


        //The length of array is computed dynamically
        logger.trace( "The length of array is computed dynamically..." );


        int[] expectedArrayElementCount = new int[arrayCount];
        int[] arrayElementCount = new int[arrayNameValues.size()];
        for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++) {
            expectedArrayElementCount[indexOfArray] = endCount;

        }
        logger.trace( "Checking if the arrays are of same length otherwise exit..." );
        logger.trace( "Checking if the initial position of the arrays are within the array size..." );
        logger.trace( "Checking if the end position of the arrays are within the array size..." );

        for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++) {
            //Checking if the arrays are of same length otherwise exit
            if (indexOfArray != arrayCount - 1)
                if (expectedArrayElementCount[indexOfArray] != expectedArrayElementCount[indexOfArray + 1]) {
                    logger.error( "The array lengths are different and hence exiting" );
                    System.exit( -1 );
                }
            //checking if the initial position of the arrays are within the array size
            if (startCount > expectedArrayElementCount[indexOfArray]) {
                logger.info( "Array initial position ->[" + startCount + "] is greater than or equal the size of the array ->[" + arrayElementCount[indexOfArray] );
                System.exit( -300 );
            }
            //checking if the end position of the arrays are within the array size
            if (endCount > expectedArrayElementCount[indexOfArray]) {
                logger.info( "Array end position ->[" + endCount + "] is greater than or equal the size of the array ->[" + arrayElementCount[indexOfArray] );
                System.exit( -301 );
            }
        }
        logger.trace( "Arrays are of same length" );
        logger.trace( "Initial position of the arrays are within the array size." );
        logger.trace( "End position of the arrays are within the array size." );

        /*for (int indexOfArray = startCount; indexOfArray < arrayCount; indexOfArray++) {
            logger.info( "ElementCount of Array " + (indexOfArray + 1) + " = [" + arrayElementCount[indexOfArray] + "]" );
        }*/




    }
}