import greenfoot.*;
import java.util.List;
public abstract class level extends World
{
    private int offset=0;
    String[] map;
    shiro player = null;
    public level()
    {    
        super(800, 800, 1,true);
        setFields();
        vector2 playerCoordinates=new vector2();
        Actor player = null;
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[i].length(); j++)
            {
                int kind = "cbpwmdksf".indexOf(""+map[i].charAt(j));
                if (kind < 0) continue;
                if (kind == 6){//player is 6 (k)
                    playerCoordinates=new vector2(16+j*32, 16+i*32);
                    player=new shiro();
                }
                Actor actor = null;
                if (kind == 2) actor = new bricks();
                if(actor!=null){
                    addObject(actor, 16+j*32, 16+i*32);   
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
            player.setLocation(player.getX()-player.speed, player.getY());
            offset-=player.speed;
            for (int i=0; i<map.length; i++){
                for (int j=0; j<map[i].length(); j++)
                {
                    int kind = "cbpwmdksf".indexOf(""+map[i].charAt(j));
                    if (kind < 0) continue;
                    Actor actor = null;
                    if (kind == 2) actor = new bricks();
                    if(actor!=null){
                        List<Actor> objectsAtLocation = getObjectsAt(16 + j * 32 + offset - 1, 16 + i * 32, (Class<Actor>) actor.getClass());
                        if (!objectsAtLocation.isEmpty()) {
                            bricks singleBrick = (bricks) objectsAtLocation.get(0);
                            singleBrick.setLocation(16 + j * 32 + offset, 16 + i * 32);
                            //offset 16 pixels to counter the inferiority of the engine
                        }
                    }
                }
            }
        }
        else if (player.getX()<getWidth()/2){
            player.setLocation(player.getX()+player.speed, player.getY());
            offset+=player.speed;
            for (int i=0; i<map.length; i++){
                for (int j=0; j<map[i].length(); j++)
                {
                    int kind = "cbpwmdksf".indexOf(""+map[i].charAt(j));
                    if (kind < 0) continue;
                    Actor actor = null;
                    if (kind == 2) actor = new bricks();
                    if(actor!=null){
                        List<Actor> objectsAtLocation = getObjectsAt(16 + j * 32 + offset - 1, 16 + i * 32, (Class<Actor>) actor.getClass());
                        if (!objectsAtLocation.isEmpty()) {
                            bricks singleBrick = (bricks) objectsAtLocation.get(0);
                            singleBrick.setLocation(16 + j * 32 + offset, 16 + i * 32);
                            //offset 16 pixels to counter the inferiority of the engine
                        }
                    }
                }
            }
        }
    }

    public void setFields() {}

    public void nextLevel() {}
}
