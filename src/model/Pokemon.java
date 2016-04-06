package model;

public class Pokemon
{
    private String name;
    private int maxHP;
    private int health;
    public Pokemon( String name, int maxHP )
    {
        this.name = name;
        this.maxHP = maxHP;
        this.health = maxHP;
    }
    public void setHP( int hp )
    {
        if( hp < 0 )
        {
            health = 0;
            return;
        }
        if( hp > maxHP )
        {
            health = maxHP;
            return;
        }
        health = hp;
    }
    public int getHP()
    {
        return health;
    }
    public String getName()
    {
        return name;
    }
}