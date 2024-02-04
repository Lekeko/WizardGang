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
    private vector2[][] tileCoordinates;
    public String map;
    public int mapHeight;
    public int mapWidth;
    public shiro player = null;
    public File jsonFile;
     
    
    public level()
    {
        super(800, 800, 1);
        
        setMap();
        setFields();
        tileCoordinates=new vector2[mapHeight][mapWidth];
        vector2 playerCoordinates=new vector2();
        Actor player = null;
        
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
                int tileX = 16 + j * 32;
                int tileY = 16 + i * 32;
                if (kind == 1){//player is 2
                    playerCoordinates=new vector2(tileX, tileY);
                    player=new shiro();
                }
                tileCoordinates[i][j]=new vector2(tileX,tileY);
                Actor actor = null;
                if (kind == 3) actor = new dirt();
                if (kind == 2) actor = new grass();
                if (kind == 5) actor = new grass_corner_left();
                if (kind == 6) actor = new grass_corner_right();
                if (kind == 7) actor = new outer_grass_corner_left();
                if (kind == 8) actor = new outer_grass_corner_right();
                if(actor!=null){
                    addObject(actor, tileX, tileY);   
                }
                //offset 16 pixels to counter the inferiority of the engine
            }
        }
        addObject(player, playerCoordinates.x, playerCoordinates.y);
        //
        addObject(border,border.getImage().getWidth()/2,getHeight()/2);
    }
    Actor border = new border();
    public void act(){
        
        setPaintOrder(border.class);
        
        player = (shiro) getObjects(shiro.class).get(0);
        if (player.getX()>getWidth()/2){
            moveCamera(1);
        }
        else if (player.getX()<getWidth()/2){
            moveCamera(-1);
        }
        
    }
    private boolean locationOnScreen(vector2 location){
        if(location.x>player.getX()-getWidth()/2&&location.x<player.getX()+getWidth()/2){
            return true;
        }
        return false;
    }
    private void moveCamera(int direction){//1 right -1 left
        player.setLocation(player.getX()-player.speed*direction, player.getY());
        offset=-player.speed *direction;
        for (int i=0; i<mapHeight; i++){
                for (int j=0; j<mapWidth; j++)
                {
                    int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
                    Actor actor = null;
                    tileCoordinates[i][j].x+=offset;
                    if (kind == 3) actor = new dirt();
                    if (kind == 2) actor = new grass();
                    if (kind == 5) actor = new grass_corner_left();
                    if (kind == 6) actor = new grass_corner_right();
                    if (kind == 7) actor = new outer_grass_corner_left();
                    if (kind == 8) actor = new outer_grass_corner_right();
                    if(actor!=null){
                        List<Actor> objectsAtLocation = getObjectsAt(tileCoordinates[i][j].x, tileCoordinates[i][j].y , (Class<Actor>) actor.getClass());
                        if(!objectsAtLocation.isEmpty()&&!locationOnScreen(tileCoordinates[i][j])){//if tile exists but is not on the screen
                            platform singleBrick = (platform) objectsAtLocation.get(0);
                            removeObject(singleBrick);
                        }
                        else if(!objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])){//if tile exists
                            platform singleBrick = (platform) objectsAtLocation.get(0);
                            singleBrick.setLocation(tileCoordinates[i][j].x, tileCoordinates[i][j].y); 
                        }
                        else if(objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])){//tile does not exists and is on screen then instantiate
                            addObject(actor, tileCoordinates[i][j].x, tileCoordinates[i][j].y); 
                        }
                        //if tile exist but is not on screen
                    }
                }
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
            // Convert the "data" array to a String
            map = numbersStringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextLevel() {}
    public void setMap(){}
}
