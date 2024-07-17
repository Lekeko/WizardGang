import greenfoot.*;
public class continueButton extends button
{
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            if(level.lastLvl==1){
                Greenfoot.setWorld(new lvl1());   
            }
            else if(level.lastLvl==2){
                Greenfoot.setWorld(new lvl2());   
            }
            else if(level.lastLvl==3){
                Greenfoot.setWorld(new lvl3());
            }
        }
    }
}
