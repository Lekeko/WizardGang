import greenfoot.*;
public class animatedEntity extends entity
{
    public GreenfootImage[][] imagini={};
    public boolean animate=false;
    public boolean crazyAnimation=false;
    public boolean interactible=true;
    public boolean oneTimeAnimation=false;
    public boolean finishedAnimating=false;
    public int currentFrame = 0;
    public int animateSpeed = 5;//the lower the faster the animation
    public int timeSinceLastFrame = 0;
    public int currentAnimation=0;
    public int direction=1;//1 right -1 left
    public int scalar;
    public level lvl;
    public animatedEntity(){
    }
    public void act()
    {
        if (animate||crazyAnimation||oneTimeAnimation){
            processFrame();
            if(currentFrame== imagini[currentAnimation].length){ 
                if(crazyAnimation){
                    getWorld().removeObject(this);
                }
                else if(oneTimeAnimation){
                    oneTimeAnimation=false;
                    finishedAnimating=true;
                }
                else{
                    currentFrame=0;
                }
            }
            else{
                nextFrame();
            }
        }
        if(interactible){
            super.act();
        }
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
    public void resetAnimation(){
        timeSinceLastFrame = 0;
        currentFrame = 0;
        nextFrame();
    }
    private void nextFrame(){
        setImage(imagini[currentAnimation][currentFrame]);
        setImage(scaleSprite(getImage(),scalar));
        if(direction==-1){//rotate if left
            getImage().mirrorHorizontally();
        }
    }
}