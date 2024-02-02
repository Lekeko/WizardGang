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
    String map;
    int mapHeight;
    int mapWidth;
    private vector2[][] tileCoordinates;
    shiro player = null;
    public level()
    {
        super(800, 800, 1,true);
        setFields();
        tileCoordinates=new vector2[mapHeight][mapWidth];
        vector2 playerCoordinates=new vector2();
        Actor player = null;
        for (int i=0; i<mapHeight; i++){
            for (int j=0; j<mapWidth; j++)
            {
                int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
               // System.out.println("\n"+kind+"\n");
                if (kind < 0) continue;
                if (kind == 2){//player is 2
                    playerCoordinates=new vector2(16+j*32, 16+i*32);
                    player=new shiro();
                }
                
                int tileX = 16 + j * 32;
                int tileY = 16 + i * 32;
                tileCoordinates[i][j]=new vector2(tileX,tileY);
                Actor actor = null;
                if (kind == 1) actor = new bricks();
                if(actor!=null){
                    addObject(actor, tileX, tileY);   
                }
                //offset 16 pixels to counter the inferiority of the engine
            }
        }
        addObject(player, playerCoordinates.x, playerCoordinates.y);
        Actor border = new border();
        addObject(border,border.getImage().getWidth()/2,getHeight()/2);
    }
    public void act(){
        player = (shiro) getObjects(shiro.class).get(0);
        if (player.getX()>getWidth()/2){
            moveCamera(1);
        }
        else if (player.getX()<getWidth()/2){
            moveCamera(-1);
        }
    }
    private static int getHeightFromJsonFile(File jsonFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            // Extract the value of "height" from the root JSON object
            int height = jsonNode.get("height").asInt();

            return height;
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Handle the error appropriately in your application
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
        offset=-player.speed*direction;
        for (int i=0; i<mapHeight; i++){
                for (int j=0; j<mapWidth; j++)
                {
                    int kind = "012345678".indexOf(""+map.charAt(i*mapWidth+j));
                    if (kind < 0) continue;
                    Actor actor = null;
                    
                    int tileX = 16 + j * 32;
                    int tileY = 16 + i * 32;
                    tileCoordinates[i][j].x+=offset;
                    if (kind == 1) actor = new bricks();
                    if(actor!=null){
                        List<Actor> objectsAtLocation = getObjectsAt(tileCoordinates[i][j].x, tileCoordinates[i][j].y , (Class<Actor>) actor.getClass());
                        if(!objectsAtLocation.isEmpty()&&!locationOnScreen(tileCoordinates[i][j])){
                            bricks singleBrick = (bricks) objectsAtLocation.get(0);
                            removeObject(singleBrick);
                        }
                        else if(!objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])){//if tile exists
                            bricks singleBrick = (bricks) objectsAtLocation.get(0);
                            singleBrick.setLocation(tileCoordinates[i][j].x, tileCoordinates[i][j].y); 
                        }
                        else if(objectsAtLocation.isEmpty()&&locationOnScreen(tileCoordinates[i][j])){//tile does not exist and is on screen then instantiate
                            addObject(actor, tileCoordinates[i][j].x, tileCoordinates[i][j].y); 
                        }
                        //if tile exist but is not on screen
                    }
                }
            }
    }

    public void setFields() {}

    public void nextLevel() {}
}
