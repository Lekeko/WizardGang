import greenfoot.*;
public class trumpBullet extends bullet
{
    public trumpBullet(){
        enemyClass=shiro.class;
        boom=new boomTrump();
        leftUpCorner=new vector2(5,5);
        rightUpCorner=new vector2(5,5);
        leftDownCorner=new vector2(5,5);
        rightDownCorner=new vector2(5,5);
    }
    public void act()
    {
        
        moveInDirection(5);
        super.act();
    }

    // Method to move the object in the direction it's facing without using the move function
    private void moveInDirection(double distance) {
        // Calculate the new position based on the current position and rotation angle
        double radians = Math.toRadians(getRotation()); // Convert rotation angle to radians
        double newX = x + distance * Math.cos(radians); // Calculate new x-coordinate
        double newY = y + distance * Math.sin(radians); // Calculate new y-coordinate

        // Update the object's position
        x = (int)newX;
        y = (int)newY;
    }
}
