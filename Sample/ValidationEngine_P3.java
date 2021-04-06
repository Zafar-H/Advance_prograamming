import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import validation.FileValidation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ValidationEngine_P3 {
    public static final Logger logger = LogManager.getLogger(ValidationEngine_P3.class);

    public static class ProjectFiles
    {
        public String configFile = "config.json";
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        //
        String defaultSystemFilePath = "./";
        String systemFilePath = defaultSystemFilePath;
        String systemFileName = "system.json";

        //Checking whether default system file is specified...
        logger.trace( "Checking whether default system file is specified..." );
        if (systemFileName != null) {
            logger.trace( "Default system file is specified..." );
            logger.info( "Default system file : [ " + systemFileName + " ] " );
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
        int expectedNumberOfArgument = 1;
        logger.info( "Expected number of argument : [ " + expectedNumberOfArgument + " ] " );

        //Specifying the position of expected arguments
        logger.trace( "Specifying the position of expected arguments..." );
        int firstArgumentPosition = 0;
        logger.info( "Position of first expected argument : [ " + firstArgumentPosition + " ] " );

        //Get the number of arguments passed...
        logger.trace( "Getting the number of arguments passed" );
        int numberOfArgument = argumentLength;
        logger.info( "Number of arguments passed is : [ " + numberOfArgument + " ] " );

        //Taking expected number of arguments
        if( numberOfArgument != noArgument)
        {
            if( numberOfArgument >= expectedNumberOfArgument )
            {
                logger.trace( "Overriding the default file path with path passed as argument..." );
                logger.trace( "Considering the expected arguments and ignoring the rest..." );
                systemFilePath = args[firstArgumentPosition];
                logger.info("File path passed as argument is : [ " + systemFilePath + " ] ");
            }
        }

        //File level validation for system file...
        logger.trace("File level validation for the system file...");
        FileValidation.validateFile(systemFilePath, systemFileName);



        //declaring system file variable...
        File systemFile = new File( systemFileName );

        //Storing system file data in collection...
        HashMap<String, String> systemFileData = createSystemFileCollection(systemFile);
        //Printing system data stored in collection...
        systemFileCollectionData(systemFileData);

        //Taking default validations file specified in system file...
        logger.info("Taking default validations file path and name specified in system file...");
        String defaultValidationsFilePath = systemFileData.get("FILEDIR");
        String defaultValidationsFileName = systemFileData.get("validationFileName");
        logger.info("Default validation file path : [ " + defaultValidationsFilePath + " ] , File name : [ " + defaultValidationsFileName + " ] ");

        //File level validation for default validations file
        logger.info("File level validation for default validations file");
        FileValidation.validateFile(defaultValidationsFilePath, defaultValidationsFileName);

        //declaring default validations file
        File defaultValidationsFile = new File( defaultValidationsFilePath+defaultValidationsFileName );

        //Storing system file data in collection...
        logger.trace("******************");
        createDefaultValidationsFileCollection(defaultValidationsFile);
        //Printing system data stored in collection...
        //defaultValidationsFileCollectionData(defaultValidationsFileData);


    }

    static JSONObject jsonObjectCreator(Object  object) throws IOException, ParseException {
        //JSONParser parser = new JSONParser();
        Object parsedObject = object;
        JSONObject jsonObject = (JSONObject) parsedObject;

        return jsonObject;
    }

    static JSONObject jsonObjectCreator(File  file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object parsedFile = parser.parse( new FileReader( file ) );
        JSONObject jsonObject = (JSONObject) parsedFile;

        return jsonObject;
    }

    static HashMap<String, String> createSystemFileCollection(File file) throws IOException, ParseException {
        //Storing system file data in json object...
        JSONObject fileJsonObject = jsonObjectCreator(file);

        HashMap<String, String> fileData = new HashMap<>();
        for (Object key : fileJsonObject.keySet())
        {
            fileData.put(key.toString(), fileJsonObject.get(key).toString());
        }

        return fileData;
    }

    static void systemFileCollectionData(HashMap<String,String> collection)
    {
        logger.info("Printing system file variables...");
        for(Map.Entry mapElement : collection.entrySet())
        {
            String collectionKey = (String)mapElement.getKey();
            String collectionValue = (String)mapElement.getValue();

            logger.info("Key : [ " + collectionKey + " ] Value : [ " + collectionValue + " ] ");
        }
    }

    static void createDefaultValidationsFileCollection(File file) throws IOException, ParseException {
        //Storing system file data in json object...
        JSONObject fileJsonObject = jsonObjectCreator(file);

        for (Object firstKey : fileJsonObject.keySet())
        {
            JSONObject firstKeyValues = jsonObjectCreator(fileJsonObject.get(firstKey));
            for(Object secondKey : firstKeyValues.keySet())
            {
                JSONObject secondKeyValues = jsonObjectCreator(firstKeyValues.get(secondKey));
                for(Object thirdKey : secondKeyValues.keySet())
                {
                    JSONObject thirdKeyValues = jsonObjectCreator(secondKeyValues.get(thirdKey));
                    for(Object fourthKey : thirdKeyValues.keySet())
                    {

                    }
                }
            }
        }

        //return fileData;
    }

    static void defaultValidationsFileCollectionData()
    {

    }


}
