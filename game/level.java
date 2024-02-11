import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;

public abstract class level extends World
{//:)
    GreenfootSound sound;
    private int offset=0;
    private int cameraSpeed=15;
    private int halfWidth=getWidth()/2;
    private int halfHeight=getHeight()/2;
    private int mapHeight;
    private int mapWidth;
    public int curentLevel = 1;
    private vector2 cameraLocation=new vector2(halfWidth,halfHeight);
    private List<List<Long>> map = new ArrayList<>();
    public shiro player = null;
    public Actor border = new border();
    public File jsonFile;
    public static World lastMap= new lvl1();
    
    public boolean activateDoor=false;
    
    public Actor[] bulletAmmo = {
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
        
        for (int i = 0; i < 8; i++){
            if(i == 0){
                addObject(bulletAmmo[i], 35 + ((i + 1) * 50), 80);
            }else
            addObject(bulletAmmo[i], 35 + ((i + 1) * 50), 80);
            
        };
        addObject(border,halfWidth,halfHeight);
        
    }
    
    public void act(){
        setPaintOrder(
            bulletShowcase.class,
            GunShowcase.class,
            border.class,
            speciatBackground.class,
            knife.class,
            Gun.class,
            Boom.class,
            bullet.class,
            shiro.class,
            jumpParticles.class,
            collision.class,
            platform.class,
            backgroundTiles.class

        );
        //setPaintOrder(knife.class);
        
        cameraLocation.x=player.x;
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
                player.setLocation(halfWidth,player.y);
                offset=halfWidth-cameraLocation.x;
            }
            else{
                player.setLocation(player.x+offset, player.y);   
            }   
        }
        List<entity> allObjects = getObjects(entity.class);

        for (entity actor : allObjects) {
            if (actor instanceof red&&((red)actor).isOnScreen){
                nextLevel();
            }
            actor.x+=offset;
            if(locationOnScreen(new vector2(actor.x,actor.y))){
                actor.isOnScreen=true;
            }
            else{
                actor.isOnScreen=false;
            }
            if(activateDoor&&actor instanceof barier){
                removeObject(actor);
            }
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
            for (int i = 0; i < layersNode.size(); i++) {
                // get the "data" array from the current layer in the "layers" array
                JsonNode dataNode = layersNode.get(i).get("data");
                // convert the "data" array to a String
                String layersData = dataNode.toString();
                // remove square brackets and split the string by commas
                String[] numbersArray = layersData.replaceAll("[\\[\\]]", "").split(",");
                // parse each number string as a long and add it to the list
                List<Long> layer = new ArrayList<>();
                for (String numStr : numbersArray) {
                    layer.add(Long.parseLong(numStr.trim()));
                }
                map.add(layer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void processMap(){
        for (List<Long> innerList : map){
            for (int i=0; i<mapHeight; i++){
                for (int j=0; j<mapWidth; j++)
                {
                    Long kind = innerList.get(i*mapWidth+j);
                    Actor actor = null;
                    
                    switch (kind.intValue()){ 
                    case 1 ->  {actor = new outer_grass_corner_left(); }
                    case 2 ->  {actor = new plank(); }
                    case 3 ->  {actor = new all_wood(); }
                    case 4 ->  {actor = new grass_right(); }
                    case 5 ->  {actor = new log(); }
                    case 6 ->  {actor = new fence(); }
                    case 7 ->  {actor = new tutorialMove(); }
                    case 8 ->  {actor = new log2(); }
                    case 9 ->  {actor = new stone(); }
                    case 10 ->  {actor = new plank2_back(); }
                    case 11 ->  {actor = new plank_back(); }
                    case 12 ->  {actor = new grass(); }
                    case 13 ->  {actor = new grass_corner_left(); }
                    case 14 ->  {actor = new tutorialGun(); }
                    case 15 ->  {actor = new shiro(); }
                    case 16 ->  {actor = new grassLand(); }
                    case 17 ->  {actor = new log2_back(); }
                    case 18 ->  {actor = new colored(); }
                    case 19 ->  {actor = new all_wood2(); }
                    case 20 ->  {actor = new tree2(); }
                    case 21 ->  {actor = new tutorialZ(); }
                    case 22 ->  {actor = new window(); }
                    case 23 ->  {actor = new tutorialKnife(); }
                    case 24 ->  {actor = new enemy(); }
                    case 25 ->  {actor = new tutorialX(); }
                    case 26 ->  {actor = new outer_grass_corner_right(); }
                    case 27 ->  {actor = new plank2(); }
                    case 28 ->  {actor = new grass_corner_right(); }
                    case 29 ->  {actor = new tutorial1(); }
                    case 30 ->  {actor = new tree1(); }
                    case 31 ->  {actor = new tree3(); }
                    case 32 ->  {actor = new dirt(); }
                    case 33 ->  {actor = new enemy2(); }
                    case 34 ->  {actor = new walled_light(); }
                    case 35 ->  {actor = new grass_left(); }
                    case 36 ->  {actor = new log_back(); }
                    case 37 ->  {actor = new wall_house(); }
                    case 38 ->  {actor = new brick_out_left(); }
                    case 39 ->  {actor = new door(); }
                    case 40 ->  {actor = new brick_corner_left(); }
                    case 41 ->  {actor = new brick_corner_right(); }
                    case 42 ->  {actor = new standed_light(); }
                    case 43 ->  {actor = new brick_out_right(); }
                    case 44 ->  {actor = new brick_out_Dright(); }
                    case 45 ->  {actor = new brick_side_left(); }
                    case 46 ->  {actor = new white_block(); }
                    case 47 ->  {actor = new brick_corner_Dleft(); }
                    case 48 ->  {actor = new brick_corner_Dright(); }
                    case 49 ->  {actor = new pillar(); }
                    case 50 ->  {actor = new brick_ground();}
                    case 51 ->  {actor = new brick(); }
                    case 52 ->  {actor = new brick_out_Dleft(); }
                    case 53 ->  {actor = new brick_side_right(); }
                    case 54 ->  {actor = new brick_down(); }
                    case 55 ->  {actor = new miniBoss(); }
                    case 56 ->  {actor = new flag1(); }
                    case 57 ->  {actor = new flag2(); }
                    case 58 ->  {actor = new flag3(); }
                    case 59 ->  {actor = new carpet(); }
                    case 60 ->  {actor = new trump(); }
                    case 61 ->  {actor = new red(); }
                    case 62 ->  {actor = new barier(); }
                   
                    
                    
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
    }
    
    public void nextLevel(){}//both set individually by each lvl
    public void setMap(){}
}