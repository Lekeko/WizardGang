import greenfoot.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class lvl2 extends level
{
    public void setFields()
    {
        File jsonFile = new File("untitled.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            // Extract the "width" and "height" values from the root JSON object
            mapWidth = jsonNode.get("width").asInt();
            mapHeight = jsonNode.get("height").asInt();

            // Extract the "layers" array from the root JSON object
            JsonNode layersNode = jsonNode.get("layers");
            // Extract the "data" array from the first layer in the "layers" array
            JsonNode dataNode = layersNode.get(0).get("data");

            // Convert the "data" array to a String
            map = dataNode.toString();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately in your application
        }
        /*map = new String[] { "                     pspppppppppppppppppppp",
                             "d m           c    m     ",
                             "ppp            pppppppppp",
                             "                         ",
                             "                  k      p",
                             "     pp                  ",
                             "                        p",
                             "             pppppppppp  ",
                             "                        p",
                             "   f    pp               ",
                             "               m                 p",
                             "    m                    ",
                             "             pppp     m                  p",
                             "            pp           ",
                             "    m                m  p",
                             "         w        w                ppppp",
                             "      p                 p       p",
                             "     pp   p                 p",
                             "    pp    p          pp p  p",
                             "   pp     p     pppp      p",
                             "p  p      p             pp",
                             "pppppppppppp  ppppppppppp",
                             "                        p",
                             "                         ",
                             "ppppppppppppppppppppppppppppppppppppppppppp",};*/
    }//25,25 map will fit
}
