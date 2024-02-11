import greenfoot.*;
public class thrownGun extends bullet
{
    
    public thrownGun(){
        shouldFall = true;
        boom=new BoomGun();
    }
    public void act()
    {
        getImage().rotate(30);
        super.act();
    }
}
