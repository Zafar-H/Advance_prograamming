package validation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

public class FileValidation {
    public static final Logger logger = LogManager.getLogger(FileValidation.class);

    public static void main(String[] args)
    {

    }

    public static void validateFile(String filePath, String fileName) {

        File file = new File( filePath+fileName );
        logger.info( "File level validation for file : [ " + fileName + " ] " );

        logger.trace( "Checking if file [ " + fileName + " ] exists or not..." );
        if (!file.exists()) {
            logger.error( "File [ " + fileName + " ] does not exist!!!" );
            System.exit( -3 );
        }
        logger.info( "[ " + fileName + " ] file exists." );

        //checking permissions for file
        //Checking the Read permission
        logger.trace( "Checking permissions for [ " + fileName + " ] file..." );
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
        logger.trace( "Checking if the file [ " + fileName + " ] is empty..." );
        if (file.length() == 0) {
            logger.error( " File [ " + fileName + " ] is empty" );
            System.exit( -404 );
        }
        logger.info( "File [ " + fileName + " ] is not empty" );
    }
}
