import greenfoot.*;
public class thrownGun extends bullet
{
    public void thrownGun(){
        hSpeed=30;
    }
    public void act()
    {
        try{
            getImage().rotate(30);
            if(this.isTouching(platform.class)){
                getWorld().removeObject(this);  
            }
            super.act();
        }catch(Exception e){}
    }
}
