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
    private vector2[][] thingCoordinates;
    public String map;
    public int mapHeight;
    public int mapWidth;
    public shiro player = null;
    public File jsonFile;
    private int cameraSpeed=9;
    private int halfWidth=getWidth()/2;
    private int halfHeight=getHeight()/2;
    vector2 cameraLocation=new vector2(halfWidth,halfHeight);
    private Actor border = new border();
    public level()
    {
        super(800, 800, 1);
        
        setMap();//chose what map should be played
        setFields();//read the map specifics and structure
        processMap();//saving the default location of all objects and tiles
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
                player.setLocation(player.getX()-cameraSpeed*direction, player.getY());   
            }   
        }
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
                Actor actor = null;
                tileCoordinates[i][j].x+=offset;
                if (kind == 1) actor=new shiro();
                if (kind == 2) actor = new grass();
                if (kind == 3) actor = new dirt();
                if (kind == 5) actor = new grass_corner_left();
                if (kind == 6) actor = new grass_corner_right();
                if (kind == 7) actor = new outer_grass_corner_left();
                if (kind == 8) actor = new outer_grass_corner_right();
                if(actor!=null){
                    List<Actor> objectsAtLocation = getObjectsAt(tileCoordinates[i][j].x-offset, tileCoordinates[i][j].y , (Class<Actor>) actor.getClass());
                    if(!objectsAtLocation.isEmpty()&&!locationOnScreen(tileCoordinates[i][j])){//if tile exists but is not on the screen
                        Actor singleBrick = (Actor) objectsAtLocation.get(0);
                        removeObject(singleBrick);
                    }
                    else if(!objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])&&kind!=1){//if tile exists
                        Actor singleBrick = (Actor) objectsAtLocation.get(0);
                        singleBrick.setLocation(tileCoordinates[i][j].x, tileCoordinates[i][j].y); 
                    }
                    else if(objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])){//tile does not exists and is on screen then instantiate
                        if((kind==1&&player==null)||kind!=1){//since everything is moved based on the player location, the player should not be moved
                            addObject(actor, tileCoordinates[i][j].x, tileCoordinates[i][j].y);
                        }
                    }
                    //if tile exist but is not on screen
                }
            }
        }
        List<Actor> allObjects = getObjects(Actor.class);

        for (Actor actor : allObjects) {
            if(!(actor instanceof shiro)&&!(actor instanceof platform)&&!(actor instanceof border)){
                actor.setLocation(actor.getX() + offset, actor.getY());   
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
            //convert the "data" array to a String
            map = numbersStringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processMap(){
        tileCoordinates=new vector2[mapHeight][mapWidth];
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                tileCoordinates[i][j]=new vector2(16 + j * 32,16 + i * 32);
                //offset 16 pixels to counter the inferiority of the engine
            }
        }
    }
    public void nextLevel(){}//both set individually by each lvl
    public void setMap(){}
}