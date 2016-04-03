package model;

public class Trainer
{
    private String name;
    private double speed;
    public Trainer( String name )
    {
        this.name = name;
        this.speed = 1.0;
    }
    public String getName()
    {
        return name;
    }
    public double getSpeed()
    {
        return this.speed;
    }
    public void setSpeed( double newSpeed )
    {
        if( newSpeed < 0.1 )
        {
            this.speed = 0.1;
            return;
        };
        if( newSpeed > 10 )
        {
            this.speed = 10;
            return;
        }
        this.speed = newSpeed;
    } 
}