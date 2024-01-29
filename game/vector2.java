import greenfoot.*;
public class vector2 extends Actor
{
    public int x;
    public int y;

    // Constructors
    public vector2() {
        this(0, 0);
    }

    public vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    //vector addition
    public vector2 add(vector2 other) {
        return new vector2(this.x + other.x, this.y + other.y);
    }

    //vector subtraction
    public vector2 subtract(vector2 other) {
        return new vector2(this.x - other.x, this.y - other.y);
    }

    //scalar multiplication
    public vector2 multiply(int scalar) {
        return new vector2(this.x * scalar, this.y * scalar);
    }

    //magnitude (length) of the vector
    public int magnitude() {
        return (int)Math.sqrt(x * x + y * y);
    }

    //normalize the vector (return a unit vector)
    public vector2 normalize() {
        int magnitude = magnitude();
        if (magnitude != 0) {
            return new vector2(x / magnitude, y / magnitude);
        } else {
            return new vector2();//return a zero vector if the magnitude is zero
        }
    }

    //dot product of two vectors
    public int dot(vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    //toString method for easy printing
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}