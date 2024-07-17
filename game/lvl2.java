import greenfoot.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class lvl2 extends level
{
    public void setMap(){
        jsonFile = new File("lvls/level2.json");
    }
    public void nextLevel(){
        level.lastLvl=3;
        Greenfoot.setWorld(new lvl3());
    }
}
