import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ValidationEngine_P2 {
    public static final Logger logger = LogManager.getLogger(ValidationEngine_P2.class);

    public static class ProjectFiles
    {
        public String configFile = "config.json";
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        //Storing reference files in a variable
        ValidationEngine_P2.ProjectFiles files = new ValidationEngine_P2.ProjectFiles();

        //Checking whether default files are specified...
        logger.trace( "Checking whether default config file is specified..." );
        if (files.configFile != null) {
            logger.trace( "Default config file is specified..." );
            logger.info( "Default system file : [ " + files.configFile + " ] " );
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

        //Get the number of arguments passed
        logger.trace( "Getting the number of arguments passed" );
        int numberOfArgument = argumentLength;
        logger.info( "Number of arguments passed : [ " + numberOfArgument + " ] " );

        //Taking expected number of arguments
        if( numberOfArgument != noArgument)
        {
            if( numberOfArgument >= expectedNumberOfArgument )
            {
                logger.trace( "Overriding the default files with files passed as argument..." );
                logger.trace( "Considering the expected arguments and ignoring the rest..." );
                files.configFile = args[firstArgumentPosition];
            }
        }

        //File level validation for the config file...
        logger.trace("File level validation for the config file...");
        validateFile(files.configFile);

        //storing config file in a variable
        File configFile = new File( files.configFile );

        //Specifying initial level of keys for jason data
        logger.trace("Specifying initial level of keys in jason data...");
        int initialLevel = 0;
        logger.info(" Initial level of keys in jason data is : [ " + initialLevel  + " ] ");

        //Specifying tree structure for File directoryform field file and adding root to it
        logger.trace("Specifying tree structure for config file and adding root to it...");
        Tree<String> configFileTree = new Tree<>();
        configFileTree.addRoot(configFile.getName());
        Tree.Node<String> configFileTreeRoot = configFileTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root node for the config file is : [ " + configFileTree.getRoot().getValue()  + " ] ");
        //Storing form field file data in tree structure
        configFileTree = treeCreation(configFile, initialLevel, configFileTree, configFileTreeRoot);
        configFileTree.displayTreeStructure();


        logger.info("***** Framework is ready... *****");

        String systemFile = configFileTree.search("PROJECT.SystemFile");
        logger.info(systemFile);





    }

    //Method for file validation...
    static void validateFile(String fileName) {

        File file = new File( fileName );
        logger.info( "File level validation for file : [ " + file + " ] " );

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

    static Tree<String> treeCreation(File file, int initialLevel, Tree<String> tree, Tree.Node<String> treeRoot) throws IOException, ParseException {
        //Storing file data as jsonObject
        logger.trace("Storing [ " + file + " ]'s data as jsonObject...");
        JSONObject fileJsonObject = jsonObjectCreator(file);
        //Obtaining number of keys present in first level of file
        int levelOneKeysSizeOfFile = fileJsonObject.keySet().size();
        logger.info("Number of keys present in first level of [ " + file + " ]'s data are : [ " + levelOneKeysSizeOfFile + " ] ");
        //Storing file data in tree structure
        logger.trace("Storing [ " + file + " ]'s data in tree structure...");
        //createTree(fileJsonObject, levelOneKeysSizeOfFile, initialLevel);

        Tree<String> treeValues = createTree(fileJsonObject, levelOneKeysSizeOfFile, initialLevel, tree, treeRoot);
        //treeValues.traverseDepthFirst();

        return treeValues;
    }

    static Tree<String> createTree(JSONObject jsonObject, int levelOneKeysSize, int initialLevel, Tree<String> tree, Tree.Node<String> treeRoot) throws IOException, ParseException {
        String jsonObjectDataType = "org.json.simple.JSONObject";
        int nullCondition = 0;
        initialLevel++;
        if(levelOneKeysSize != nullCondition)
        {
            for(Object Key : jsonObject.keySet())
            {
                Tree.Node<String> node = tree.addNode(treeRoot, Key.toString());
                if(jsonObject.get(Key) != null )
                {
                    if(jsonObject.get(Key).getClass().getName() == jsonObjectDataType)
                    {
                        JSONObject newJsonObject = jsonObjectCreator(jsonObject.get(Key));
                        int newJsonObjectKeySize = newJsonObject.keySet().size();

                        createTree(newJsonObject, newJsonObjectKeySize, initialLevel, tree, node);
                    }
                    else
                    {
                        Tree.Node<String> leafNode = tree.addNode(node, jsonObject.get(Key).toString());
                    }
                }
            }
        }

        return tree;
    }
}
