import greenfoot.*;
public class slashEnemy extends bullet
{
    public slashEnemy(){
        scalar=3;
        enemyClass=shiro.class;
        setImage(scaleSprite(getImage(), scalar));
        boom=new slashEffect();
    }
    public void act()
    {
        super.act();
    }
}
