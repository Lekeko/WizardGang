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
    List<Integer> map = new ArrayList<>();
    private int mapHeight;
    private int mapWidth;
    private shiro player = null;
    public File jsonFile;
    int curentLevel = 2;
    Actor gun = new Gun();
    Actor[] bulletAmmo = {
            new GunShowcase(),
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase(), 
            new bulletShowcase()
        };
    public level()
    {
        super(800, 800, 1);
        
        setMap();//chose what map should be played
        setFields();//read the map specifics and structure
        processMap();//saving the default location of all objects and tiles and instantiating them
        moveCamera(0);

        addObject(border,halfWidth,halfHeight);
        
        
    
        for (int i = 0; i < 8; i++){
            if(i == 0){
                addObject(bulletAmmo[i], 25 + ((i + 1) * 50), 80);
            }else
            addObject(bulletAmmo[i], 35 + ((i + 1) * 50), 80);
            
        };
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
        
        try {
            // create an ObjectMapper instance to read JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // read the JSON file and parse it into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            // extract the "width" and "height" values from the root JSON object
            mapWidth = jsonNode.get("width").asInt();
            mapHeight = jsonNode.get("height").asInt();
            // extract the "layers" array from the root JSON object
            JsonNode layersNode = jsonNode.get("layers");
            // get the "data" array from the first layer in the "layers" array
            JsonNode dataNode = layersNode.get(0).get("data");
            // convert the "data" array to a String
            String layersData = dataNode.toString();
            // remove square brackets and split the string by commas
            String[] numbersArray = layersData.replaceAll("[\\[\\]]", "").split(",");
            // parse each number string as an integer and add it to the list
            for (String numStr : numbersArray) {
                map.add(Integer.parseInt(numStr.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int nivel = 0;
    private void processMap(){
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                int kind = map.get(i*mapWidth+j);
                Actor actor = null;
                
                
                switch (kind){
                    
                    case 1 ->  {actor = new shiro(); }
                    case 2 ->  {actor = new grass_corner_right();}
                    case 3 ->  {actor = new grass_left();}
                    case 4 ->  {actor = new outer_grass_corner_right();}
                    case 5 ->  {actor = new enemy();}
                    case 6 ->  {actor = new dirt();}
                    case 7 ->  {actor = new grass_right();}
                    case 8 ->  {actor = new grass();}
                    case 9 ->  {actor = new stone();}
                    case 10 -> {actor = new outer_grass_corner_left();}
                    case 11 -> {actor = new grass_corner_left();}
                    case 12 ->  {actor = new brick_corner_left(); }
                    case 13 ->  {actor = new enemy2(); }
                    case 14 ->  {actor = new brick_down(); }
                    case 15 ->  {actor = new brick(); }
                    case 16 ->  {actor = new brick_side_right(); }
                    case 17 ->  {actor = new brick_corner_right(); }
                    case 18 ->  {actor = new brick_ground(); }
                    case 19 ->  {actor = new brick_out_Dright(); }
                    case 21 ->  {actor = new brick_corner_Dright(); }
                    case 20 ->  {actor = new brick_corner_Dleft(); }
                    case 22 ->  {actor = new brick_out_right(); }
                    case 23 ->  {actor = new brick_out_left(); }
                    case 24 ->  {actor = new brick_side_left(); }
                    case 25 ->  {actor = new brick_out_Dleft(); }
                    
                    }
                
                
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