import greenfoot.*;
public class animatedEntity extends entity
{
    public GreenfootImage[][] imagini={};
    public boolean animate=false;
    public boolean crazyAnimation=false;
    public int currentFrame = 0;
    public int animateSpeed = 5;//the lower the faster the animation
    public int timeSinceLastFrame = 0;
    public int currentAnimation=0;
    public int direction=1;//1 right -1 left
    public animatedEntity(){
    }
    public void act()
    {
        if (animate||crazyAnimation){
            processFrame();
            if(currentFrame== imagini[currentAnimation].length){ 
                if(crazyAnimation){
                    getWorld().removeObject(this);
                }
                else{
                    currentFrame=0;
                }
            }
            else{
                setImage(imagini[currentAnimation][currentFrame]);
                setImage(scaleSprite(getImage(),3));
                if(direction==-1){//rotate if left
                    getImage().mirrorHorizontally();
                }
                super.act();   
            }
        }
        super.act();
    }
    private void processFrame(){
        if(timeSinceLastFrame < animateSpeed){
            timeSinceLastFrame++;
        }
        else
        {
            timeSinceLastFrame = 0;
            if(currentFrame < imagini[currentAnimation].length){
                currentFrame++;
            }
            else
            {
                currentFrame = 0;
            }
        }
    }
    public void changeAnimation(int animation){
        if(animation!=currentAnimation){
            currentAnimation=animation;
            timeSinceLastFrame = 0;
            currentFrame = 0;   
        }
    }
}