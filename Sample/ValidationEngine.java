import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ValidationEngine {

    public static final Logger logger = LogManager.getLogger(ValidationEngine.class);

    public static class ProjectFiles
    {
        public String PROJECTSystemFileName;
        public String PROJECTDefaultsFilename;

    }

    public static void main(String args[]) throws IOException, ParseException
    {
        //ValidationEngine.ProjectFiles files = new ValidationEngine.ProjectFiles();
        //files.PROJECTSystemFileName = "";
        //Storing reference files in a variable
        logger.trace("Storing reference files in a variable...");
        String systemFile = "/home/zafar/mini-project/system.json";
        String fieldInputFile = "/home/zafar/mini-project/field_input.json";
        String defaultValidationsFile = "/home/zafar/mini-project/default_field_validations.json";
        String customValidationsFile = "/home/zafar/mini-project/custom_validations.json";
        String errorMessagesFile = "/home/zafar/mini-project/error_message.json";
        String errorMessagesSubstituteFile = "/home/zafar/mini-project/error_message_substitute.json";


        //Checking whether default files are specified...
        logger.trace( "Checking whether default field validation file is specified..." );
        if (defaultValidationsFile != null) {
            logger.trace( "Default field validation file is specified..." );
            logger.info( "Default field validation file : [ " + defaultValidationsFile + " ] " );
        } else {
            logger.error( "Exiting because default system file is not specified :(" );
            System.exit( -3 );
        }

        //Specifying expected number of argument
        logger.trace( "Specifying expected number of argument..." );
        int maxNumberOfArgument = 1;
        logger.info( "Expected number of argument : [ " + maxNumberOfArgument + " ] " );

        //Finding the number of arguments
        logger.trace( "Finding the number of arguments passed..." );
        int argumentLength = args.length;
        logger.info( "Argument count : [ " + argumentLength + " ] " );


        //Specifying the position of expected arguments
        logger.trace( "Specifying the position of expected arguments..." );
        int firstArgumentPosition = 0;
        logger.info( "Position expected argument : [ " + firstArgumentPosition + " ] " );

        //Get the number of arguments passed
        logger.trace( "Getting the number of arguments passed" );
        int numberOfArgument = argumentLength;
        logger.info( "Number of arguments passed : [ " + numberOfArgument + " ] " );

        //If there are arguments more than expected, take the expected and ignore the rest. Override args with default files
        if (numberOfArgument >= maxNumberOfArgument) {
            logger.trace( "Overriding the default file with file passed as argument..." );
            defaultValidationsFile = args[firstArgumentPosition];
            logger.info( "Overridden argument files are : [ " + defaultValidationsFile +  " ] " );
        }


        //Storing argument or default files in variable
        logger.trace( "Storing argument or default files in variable..." );
        File programSystemFile = new File( systemFile );
        File programFieldInputFile = new File( fieldInputFile );
        File programDefaultValidationsFile = new File( defaultValidationsFile );
        File programCustomValidationsFile = new File( customValidationsFile );
        File programErrorMessagesFile = new File( errorMessagesFile );
        File programErrorMessagesSubstituteFile = new File( errorMessagesSubstituteFile );
        logger.info( "The form field file to be used in program is : [ " + programSystemFile + " ] " );
        logger.info( "The field input data file to be used in program is : [ " + programFieldInputFile + " ] " );
        logger.info( "The field validation file to be used in program is : [ " + programDefaultValidationsFile + " ] " );
        logger.info( "The custom validation file to be used in program is : [ " + programCustomValidationsFile + " ] " );
        logger.info( "The error messages file to be used in program is : [ " + programErrorMessagesFile + " ] " );
        logger.info( "The error message substitution file to be used in program is : [ " + programErrorMessagesSubstituteFile + " ] " );


        //Validation check for the files to be used in program
        logger.trace("Validation check for the files to be used in program...");
        //Validation check for form field file
        validateFile(programSystemFile);

        //Validation check for field input file
        validateFile(programFieldInputFile);

        //Validation check for default field validations file
        validateFile(programDefaultValidationsFile);

        //Validation check for custom field validations file
        validateFile(programCustomValidationsFile);

        //Validation check for error messages file
        validateFile(programErrorMessagesFile);

        //Validation check for error messages substitute file
        validateFile(programErrorMessagesSubstituteFile);

        //Specifying initial level of keys for jason data
        logger.trace("Specifying initial level of keys in jason data...");
        int initialLevel = 0;
        logger.info(" Initial level of keys in jason data is : [ " + initialLevel  + " ] ");

        //Specifying tree structure for json files and adding root node to each of them
        logger.trace("Specifying tree structure for json files and adding root node to each of them...");

        //Specifying tree structure for form field file and adding root to it
        logger.trace("Specifying tree structure for form field file and adding root to it...");
        Tree<String> systemFileDataTree = new Tree<>();
        systemFileDataTree.addRoot(programSystemFile.getName());
        Tree.Node<String> formFieldTreeRoot = systemFileDataTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root node for the form field file is : [ " + systemFileDataTree.getRoot().getValue()  + " ] ");
        //Storing form field file data in tree structure
        systemFileDataTree = treeCreation(programSystemFile, initialLevel, systemFileDataTree, formFieldTreeRoot);
        systemFileDataTree.displayTreeStructure();

        //Specifying tree structure for form input field file and adding root to it
        logger.trace("Specifying tree structure for form input field file and adding root to it...");
        Tree<String> formFieldInputTree = new Tree<>();
        formFieldInputTree.addRoot(programFieldInputFile.getName());
        Tree.Node<String> formFieldInputTreeRoot = formFieldInputTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root for the form field file is : [ " + formFieldInputTree.getRoot().getValue()  + " ] ");
        //Storing form field input file data in tree structure
        formFieldInputTree = treeCreation(programFieldInputFile, initialLevel, formFieldInputTree, formFieldInputTreeRoot);
        formFieldInputTree.displayTreeStructure();

        //Specifying tree structure for default form field validation file and adding root to it
        logger.trace("Specifying tree structure for default form field validation file and adding root to it...");
        Tree<String> defaultValidationTree = new Tree<>();
        defaultValidationTree.addRoot(programDefaultValidationsFile.getName());
        Tree.Node<String> formFieldValidationTreeRoot = defaultValidationTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root for the default form field file is : [ " + defaultValidationTree.getRoot().getValue()  + " ] ");
        //Storing default field validation file data in tree structure
        defaultValidationTree = treeCreation(programDefaultValidationsFile, initialLevel, defaultValidationTree, formFieldValidationTreeRoot);
        defaultValidationTree.displayTreeStructure();

        //Specifying tree structure for form field validation file and adding root to it
        logger.trace("Specifying tree structure for custom form field validation file and adding root to it...");
        Tree<String> customValidationTree = new Tree<>();
        customValidationTree.addRoot(programCustomValidationsFile.getName());
        Tree.Node<String> customFormFieldValidationTreeRoot = customValidationTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root for the custom form field file is : [ " + customValidationTree.getRoot().getValue()  + " ] ");
        //Storing custom field validation file data in tree structure
        customValidationTree = treeCreation(programCustomValidationsFile, initialLevel, customValidationTree, customFormFieldValidationTreeRoot);
        customValidationTree.displayTreeStructure();

        //Specifying tree structure for error messages file and adding root to it
        logger.trace("Specifying tree structure for error messages file and adding root to it...");
        Tree<String> errorMessageTree = new Tree<>();
        errorMessageTree.addRoot(programErrorMessagesFile.getName());
        Tree.Node<String> errorMessageTreeRoot = errorMessageTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root for the form field file is : [ " + errorMessageTree.getRoot().getValue()  + " ] ");
        //Storing error messages file data in tree structure
        errorMessageTree = treeCreation(programErrorMessagesFile, initialLevel, errorMessageTree, errorMessageTreeRoot);
        errorMessageTree.displayTreeStructure();

        //Specifying tree structure for error messages substitute file and adding root to it
        logger.trace("Specifying tree structure for error messages substitute file and adding root to it...");
        Tree<String> errorMessageSubstituteTree = new Tree<>();
        errorMessageSubstituteTree.addRoot(programErrorMessagesSubstituteFile.getName());
        Tree.Node<String> errorMessageSubstituteTreeRoot = errorMessageSubstituteTree.getRoot();
        logger.trace("Tree Structure specified.");
        logger.info("Root for the form field file is : [ " + errorMessageSubstituteTree.getRoot().getValue()  + " ] ");
        //Storing error messages substitute file data in tree structure
        errorMessageSubstituteTree = treeCreation(programErrorMessagesSubstituteFile, initialLevel, errorMessageSubstituteTree, errorMessageSubstituteTreeRoot);
        errorMessageSubstituteTree.displayTreeStructure();
    }

    //Method for file validation...
    static void validateFile(File file) {

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
        //treeValues.traverseDepthFirst(treeValues.getRoot(), "");
        //treeValues.traverseDepthFirst(treeValues.getRoot());
        //logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +treeValues.getRoot().getChildren().get(0).getValue());
    }

    /*static void createTree(JSONObject jsonObject, int levelOneKeysSize, int initialLevel) throws IOException, ParseException {
        String levelIdentifierSymbol = ">>>>";
        String jsonObjectDataType = "org.json.simple.JSONObject";
        int nullCondition = 0;
        String levelIdentifier = "";
        for(int i = 0; i < initialLevel; i++)
        {
            levelIdentifier = levelIdentifier + " " + levelIdentifierSymbol;
        }
        initialLevel++;
        if(levelOneKeysSize != nullCondition)
        {
            for(Object Key : jsonObject.keySet())
            {
                logger.info( levelIdentifier + " " + Key);
                if(jsonObject.get(Key) != null )
                {
                    if(jsonObject.get(Key).getClass().getName() == jsonObjectDataType)
                    {
                        JSONObject newJsonObject = jsonObjectCreator(jsonObject.get(Key));
                        int newJsonObjectKeySize = newJsonObject.keySet().size();
                        createTree(newJsonObject, newJsonObjectKeySize, initialLevel);
                    }
                    else
                    {
                        logger.info(levelIdentifier + " " +levelIdentifierSymbol+ " " +jsonObject.get(Key) );
                    }
                }
            }
        }
    }*/


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

