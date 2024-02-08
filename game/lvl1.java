import greenfoot.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class lvl1 extends level
{
    public void setMap(){
        jsonFile = new File("level2.json");
    }//25,25 map will fit 800 800
}
