import greenfoot.*;
public class thrownGun extends bullet
{
    
    public thrownGun(){
        boom=new BoomGun();
    }
    public void act()
    {
        getImage().rotate(30);
        super.act();
    }
}
