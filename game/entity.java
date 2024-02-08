import greenfoot.*;

public abstract class entity extends vector2
{
    public entity(){

    }
    public entity(int x,int y){
        this.x=x;
        this.x=y;
    }
    public void act()
    {
        setLocation(this.x, this.y);
    }
}
