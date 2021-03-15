import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Set;

public class LinearArrayOperation_L2
{
    public static final Logger logger = LogManager.getLogger(LinearArrayOperation_L2.class);

    public static void main(String args[]) throws IOException, ParseException {

        String programArgument_1 = "default/system.json";
        String programArgument_2 = "default/transaction.json";

        //splitter constant literals
        logger.trace("Specifying delimiter for segregation...");
        String underscoreDelimiter = "_";
        String alphaNumericCondition = "^[a-zA-Z0-9]*$";

        logger.info("Delimiter used for comments : [ " + underscoreDelimiter + " ] " );

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
            logger.info(" default transaction file : [ " + programArgument_2 + " ] ");
        }
        else
        {
            logger.error("Exiting because default transaction file is not specified, hence exiting!!!");
            System.exit(-3);
        }

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
        logger.info("The files to be used in program is : [ " + argumentSystemFile + " , " + argumentTransactionFile + " ] ");

        //Validation check for the files passed as argument
        //validations for system.dat file
        //check if specified file exists or not
        logger.trace("Validation check for the files used...");
        logger.trace("Validation check for system.dat file...");
        logger.trace("Checking if specified file exists or not...");
        if (!argumentSystemFile.exists()){
            logger.error("File system.json does not exist!!!");
            System.exit(-3);
        }
        logger.info( "system.json file exists." );

        //checking permissions for system.json file
        //Checking the Read permission
        logger.trace("Checking permissions for system.json file...");
        logger.trace("Checking the Read permission...");
        if (!argumentSystemFile.canRead())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Read!!!");
            System.exit(-402);
        }
        logger.info("Read permission is granted for system.json file.");

        //Checking the Write permission
        logger.trace("Checking the Write permission...");
        if (!argumentSystemFile.canWrite())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Write!!!");
            System.exit(-401);
        }
        logger.info("Write permission is granted for system.json file.");

        //Checking the Execute permission
        logger.trace("Checking the Execute permission...");
        if (!argumentSystemFile.canExecute())
        {
            logger.error(" FILE PERMISSION ERROR : Cannot Execute!!!");
            System.exit(-403);
        }
        logger.info("Execute permission is granted for system.json file.");

        //Checking if the file is empty
        logger.trace("Checking if the file is empty...");
        if (argumentSystemFile.length() == 0)
        {
            logger.error(" File is empty");
            System.exit(-404);
        }
        logger.info( "system.json File is not empty" );

        JSONParser parser = new JSONParser();

        Object programSystemFile = parser.parse(new FileReader(argumentSystemFile));
        JSONObject jsonObject = (JSONObject)programSystemFile;

        ArrayList<AbstractMap.SimpleEntry<String, String> > systemFileValues = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
        Set<String> keys = jsonObject.keySet();

        //checking for comment keys...
        for(String key :  keys)
        {
            if(!key.startsWith(underscoreDelimiter))
            {
                systemFileValues.add(new AbstractMap.SimpleEntry(key, jsonObject.get(key)));
            }
            else
            {
                logger.info("The key [ " + key + " ] starts with [ " + underscoreDelimiter + " ] hence considering as a comment"  );
            }

            if(!(key.matches(alphaNumericCondition)))
            {
                logger.error("Key : [ " + key + " ] is invalid, hence exiting!!!");
                System.exit(-404);
            }
        }

        for(int indexOfArray=0;indexOfArray<systemFileValues.size();indexOfArray++)
        {
            AbstractMap.SimpleEntry<String, String> map = systemFileValues.get(indexOfArray);
            logger.info("key : " + map.getKey() + " Value : " + map.getValue());

        }

        String minCountString = (String)jsonObject.get("minCount");







    }
}
