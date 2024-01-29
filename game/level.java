import greenfoot.*;
public abstract class level extends World
{
    String[] map;
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
    }

    public void setFields() {}

    public void nextLevel() {}
}
