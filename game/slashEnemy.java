import greenfoot.*;
public class slashEnemy extends bullet
{
    public slashEnemy(){
        scalar=3;
        setImage(scaleSprite(getImage(), scalar));
        boom=new slashEffect();
    }
    public void act()
    {
        super.act();
    }
}
