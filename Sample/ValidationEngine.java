import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ValidationEngine {

    public static final Logger logger = LogManager.getLogger(ValidationEngine.class);

    public static void main(String args[]) throws IOException, ParseException
    {
        String formFieldFile = "/home/zafar/mini-project/form_field.json";
        String fieldInputFile = "/home/zafar/mini-project/field_input.json";
        String fieldValidationsFile = "/home/zafar/mini-project/default_field_validations.json";
        String errorMessagesFile = "/home/zafar/mini-project/error_message.json";
        String errorMessagesSubstituteFile = "/home/zafar/mini-project/error_message_substitute.json";

        //splitter constant literals
        logger.trace( "Specifying delimiter for segregation..." );
        String underscoreDelimiter = "_";
        logger.info( "Delimiter used for comments : [ " + underscoreDelimiter + " ] " );


        //Checking whether default files are specified...
        logger.trace( "Checking whether default field validation file is specified..." );
        if (fieldValidationsFile != null) {
            logger.trace( "Default field validation file is specified..." );
            logger.info( "Default field validation file : [ " + fieldValidationsFile + " ] " );
        } else {
            logger.error( "Exiting because default system file is not specified :(" );
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
        int maxNumberOfArgument = 1;
        logger.info( "Expected number of argument : [ " + maxNumberOfArgument + " ] " );

        //Specifying the position of expected arguments
        logger.trace( "Specifying the position of expected arguments..." );
        int firstArgumentPosition = 0;
        logger.info( "Position expected argument : [ " + firstArgumentPosition + " ] " );

        //Get the number of arguments passed
        logger.trace( "Getting the number of arguments passed" );
        int numberOfArgument = argumentLength;
        int numberOne = 1;
        logger.info( "Number of arguments passed : [ " + numberOfArgument + " ] " );

        //If there are arguments more than expected, take the first and ignore the rest. Override args with default files
        if (numberOfArgument >= maxNumberOfArgument) {
            logger.trace( "Overriding the default file with file passed as argument..." );
            fieldValidationsFile = args[firstArgumentPosition];
            logger.info( "Overridden argument files are : [ " + fieldValidationsFile +  " ] " );
        }


        //Storing argument or default files in variable
        logger.trace( "Storing argument or default files in variable..." );
        File programFormFieldFile = new File( formFieldFile );
        File programFieldInputFile = new File( fieldInputFile );
        File programFieldValidationsFile = new File( fieldValidationsFile );
        File programErrorMessagesFile = new File( errorMessagesFile );
        File programErrorMessagesSubstituteFile = new File( errorMessagesSubstituteFile );
        logger.info( "The form field file to be used in program is : [ " + programFormFieldFile + " ] " );
        logger.info( "The field input data file to be used in program is : [ " + programFieldInputFile + " ] " );
        logger.info( "The field validation file to be used in program is : [ " + programFieldValidationsFile + " ] " );
        logger.info( "The error messages file to be used in program is : [ " + programErrorMessagesFile + " ] " );
        logger.info( "The error message substitution file to be used in program is : [ " + programErrorMessagesSubstituteFile + " ] " );


        //Validation check for the files to be used in program
        //Validation check for form field file
        validateFile(programFormFieldFile);

        //Validation check for field input file
        validateFile(programFieldInputFile);

        //Validation check for field validations file
        validateFile(programFieldValidationsFile);

        //Validation check for error messages file
        validateFile(programErrorMessagesFile);

        //Validation check for error messages substitute file
        validateFile(programErrorMessagesSubstituteFile);

        //Calling a method to parse a json file and storing data as jsonObject
        JSONObject fieldValidationsFileJsonObject = jsonObjectCreator(programFieldValidationsFile);

        int nullCondition = 0;
        int numberOfKeys = fieldValidationsFileJsonObject.keySet().size();
        if (numberOfKeys != nullCondition)
        {
            for(Object levelOneKey : fieldValidationsFileJsonObject.keySet())
            {
                logger.info(" [ " +levelOneKey+ " ] ");
                JSONObject levelTwoValues = jsonObjectCreator(fieldValidationsFileJsonObject.get(levelOneKey));
                int levelTwoKeysSize = levelTwoValues.keySet().size();
                if(levelTwoKeysSize != nullCondition)
                {
                    for(Object levelTwoKey : levelTwoValues.keySet()){
                        logger.info(" >>>>>>> [ " +levelTwoKey+ " ] ");
                        JSONObject levelThreeValues = jsonObjectCreator(levelTwoValues.get(levelTwoKey));
                        int levelThreeKeysSize = levelThreeValues.keySet().size();
                        if(levelThreeKeysSize != nullCondition)
                        {
                            for(Object levelThreeKey : levelThreeValues.keySet())
                            {
                                logger.info(" >>>>>>> >>>>>>> [ " +levelThreeKey+ " ] ");
                                if(levelThreeValues.get(levelThreeKey) != null && levelThreeValues.get(levelThreeKey).getClass().getName() == "org.json.simple.JSONObject")
                                {
                                    JSONObject levelFourValues = jsonObjectCreator(levelThreeValues.get(levelThreeKey));
                                    if(levelFourValues != null){
                                        int levelFourKeysSize = levelFourValues.keySet().size();
                                        if(levelFourKeysSize != nullCondition)
                                        {
                                            for(Object levelFourKey : levelFourValues.keySet())
                                            {
                                                logger.info(" >>>>>>> >>>>>>> >>>>>>> [ " +levelFourKey+ " ] ");

                                                if(levelFourValues.get(levelThreeKey) != null && levelFourValues.get(levelFourKey).getClass().getName() == "org.json.simple.JSONObject")
                                                {
                                                    JSONObject levelFiveValues = jsonObjectCreator(levelFourValues.get(levelThreeKey));
                                                    if(levelFiveValues != null){
                                                        int levelFiveKeysSize = levelFiveValues.keySet().size();
                                                        if(levelFiveKeysSize != nullCondition)
                                                        {
                                                            for(Object levelFiveKey : levelFiveValues.keySet())
                                                            {
                                                                logger.info(" >>>>>>> >>>>>>> >>>>>>> >>>>>>> [ " +levelFiveKey+ " ] Value : [ " + levelFiveValues.get(levelFiveKey) + " ] ");

                                                            }
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    logger.info(" >>>>>>> >>>>>>> >>>>>>> >>>>>>> [ " + levelFourValues.get(levelFourKey) + " ] ");
                                                }
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    logger.info(" >>>>>>> >>>>>>> >>>>>>> [ " + levelThreeValues.get(levelThreeKey) + " ] ");
                                }
                            }
                        }
                    }
                }
            }
        }






    }

    //Method for file validation...
    static void validateFile(File file) {

        //Delimiter used for comments
        String underscoreDelimiter = "_";

        logger.trace( "Checking if file [ " + file + " ] exists or not..." );
        if (!file.exists()) {
            logger.error( "File [ " + file + " ] does not exist!!!" );
            System.exit( -3 );
        }
        logger.info( "[ " + file + " ] file exists." );

        //checking permissions for file
        //Checking the Read permission
        logger.trace( "Checking permissions for [ " + file + " ] file..." );
        logger.trace( "Checking the Read permission..." );
        if (!file.canRead()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Read!!!" );
            System.exit( -402 );
        }
        logger.info( "Read permission is granted to the file." );

        //Checking the Write permission
        logger.trace( "Checking the Write permission..." );
        if (!file.canWrite()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Write!!!" );
            System.exit( -401 );
        }
        logger.info( "Write permission is granted to the file." );

        //Checking the Execute permission
        logger.trace( "Checking the Execute permission..." );
        if (!file.canExecute()) {
            logger.error( " FILE PERMISSION ERROR : Cannot Execute!!!" );
            System.exit( -403 );
        }
        logger.info( "Execute permission is granted to the file." );

        //Checking if the file is empty
        logger.trace( "Checking if the file [ " + file + " ] is empty..." );
        if (file.length() == 0) {
            logger.error( " File [ " + file + " ] is empty" );
            System.exit( -404 );
        }
        logger.info( "File is not empty" );
    }

    static JSONObject jsonObjectCreator(File  file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object parsedFile = parser.parse( new FileReader( file ) );
        JSONObject jsonObject = (JSONObject) parsedFile;

        return jsonObject;
    }

    static JSONObject jsonObjectCreator(Object  object) throws IOException, ParseException {
        //JSONParser parser = new JSONParser();
        Object parsedObject = object;
        JSONObject jsonObject = (JSONObject) parsedObject;

        return jsonObject;
    }
}

