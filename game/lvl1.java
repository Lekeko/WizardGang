import greenfoot.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class lvl1 extends level
{
    public void setFields()
    {
        File jsonFile = new File("untitled.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            //extract the "width" and "height" values from the root JSON object
            mapWidth = jsonNode.get("width").asInt();
            mapHeight = jsonNode.get("height").asInt();

            //extract the "layers" array from the root JSON object
            JsonNode layersNode = jsonNode.get("layers");
            //extract the "data" array from the first layer in the "layers" array
            JsonNode dataNode = layersNode.get(0).get("data");
            //extract and concatenate the numbers from the "data" array
            StringBuilder numbersStringBuilder = new StringBuilder();
            for (JsonNode numberNode : dataNode) {
                numbersStringBuilder.append(numberNode.asText());
            }
            // Convert the "data" array to a String
            map = numbersStringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//25,25 map will fit 800 800
}
