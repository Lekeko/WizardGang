import greenfoot.*;
public abstract class level extends World
{
    String[] map;
    public level()
    {    
        super(800, 800, 1);
        setFields();
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[i].length(); j++)
            {
                int kind = "cbpwmdksf".indexOf(""+map[i].charAt(j));
                if (kind < 0) continue;
                Actor actor = null;
                if (kind == 2) actor = new bricks();
                if (kind == 6) actor = new shiro();
                /*if (kind == 0) actor = new Player();
                if (kind == 1) actor = new Block();
                if (kind == 2) actor = new Platform2();
                if (kind == 3) actor = new Wall();
                if (kind == 4) actor = new Monster();
                if (kind == 5) actor = new Door();
                if (kind == 7) actor = new Score();
                if (kind == 8) actor = new Floater();*/
                if(actor!=null)addObject(actor, 16+j*32, 16+i*32);
                //offset 16 pixels to counter the inferiority of the engine
            }
        }
    }

    public void setFields() {}

    public void nextLevel() {}
}
