import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JSON_FileReader {

    private String fileName;
    private JSONObject jsonFormat;

    public JSON_FileReader(String fileName) { this.fileName = fileName; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public JSONObject getJsonFormat() { return jsonFormat; }

    public void setJsonFormat(JSONObject jsonFormat) { this.jsonFormat = jsonFormat; }

    public JSONObject readJsonFile() throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object parsedFile = parser.parse( new FileReader( getFileName() ) );
        JSONObject jsonObject = (JSONObject) parsedFile;
        setJsonFormat(jsonObject);
        return jsonFormat;
    }

    public void resetData() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter( getFileName() );
        writer.print("");
        writer.close();

    }

    public static final Logger logger = LogManager.getLogger( JSON_FileReader.class );

    public static void main(String[] args) throws IOException, ParseException
    {

        JSON_FileReader systemFile = new JSON_FileReader("system.json");
        systemFile.readJsonFile();
        logger.info(systemFile.getJsonFormat());
        systemFile.resetData();
    }
}
