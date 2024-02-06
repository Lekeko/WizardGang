import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public abstract class level extends World
{
    private int offset=0;
    private int cameraSpeed=9;
    private int halfWidth=getWidth()/2;
    private int halfHeight=getHeight()/2;
    private vector2 cameraLocation=new vector2(halfWidth,halfHeight);
    private vector2[][] tileCoordinates;
    private Actor border = new border();
    private String map;
    private int mapHeight;
    private int mapWidth;
    private shiro player = null;
    public File jsonFile;
    public level()
    {
        super(800, 800, 1);
        
        setMap();//chose what map should be played
        setFields();//read the map specifics and structure
        processMap();//saving the default location of all objects and tiles and instantiating them
        moveCamera(0);

        addObject(border,halfWidth,halfHeight);
    }
    public void act(){
        
        setPaintOrder(border.class,shiro.class);
        
        player = (shiro) getObjects(shiro.class).get(0);
        cameraLocation.x=player.getX();
        if (cameraLocation.x>halfWidth){
            moveCamera(1);
        }
        else if (cameraLocation.x<halfWidth){
            moveCamera(-1);
        }
        
    }
    
    
    private boolean locationOnScreen(vector2 location){//true of the location is on screen else false
        if(location.x>cameraLocation.x-halfWidth&&location.x<cameraLocation.x+halfWidth){
            return true;
        }
        return false;
    }
    private void moveCamera(int direction){//1 right -1 left 0 just render
        offset=-cameraSpeed *direction;
        if(player!=null){
            if((cameraLocation.x>halfWidth&&cameraLocation.x+offset<halfWidth)||(cameraLocation.x<halfWidth&&cameraLocation.x+offset>halfWidth)){
                player.setLocation(halfWidth,player.getY());
                offset=halfWidth-cameraLocation.x;
            }
            else{
                player.setLocation(player.getX()+offset, player.getY());   
            }   
        }
        List<entity> allObjects = getObjects(entity.class);

        for (entity actor : allObjects) {
            actor.x+=offset;
        }
    }

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
            //convert the "data" array to a String
            map = numbersStringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processMap(){
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
                Actor actor = null;
                if (kind == 1) actor=new shiro();
                if (kind == 2) actor = new grass();
                if (kind == 3) actor = new dirt();
                if (kind == 5) actor = new grass_corner_left();
                if (kind == 6) actor = new grass_corner_right();
                if (kind == 7) actor = new outer_grass_corner_left();
                if (kind == 8) actor = new outer_grass_corner_right();
                if(actor!=null){
                    if(actor instanceof entity){
                        entity idk=(entity)actor;
                        idk.x=16 + j * 32;
                        idk.y=16 + i * 32;
                        addObject(idk,16 + j * 32, 16 + i * 32);
                    }//offset 16 pixels to counter the inferiority of the engine
                    else{
                        addObject(actor,16 + j * 32, 16 + i * 32);   
                    }
                }
            }

        }
    }
    public void nextLevel(){}//both set individually by each lvl
    public void setMap(){}
}