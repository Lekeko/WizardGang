import greenfoot.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class lvl3 extends level
{
    public void setMap(){
        jsonFile = new File("lvls/level3.json");
    }
    public void nextLevel(){
        Greenfoot.setWorld(new lvl1());
    }
}
