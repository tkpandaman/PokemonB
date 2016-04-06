package model;

/**
 * The inheritance hierarchy for any items used in-game
 * @author Joshua Raphael
 */
public abstract class Item
{
    private String name;
    private String description;

    /**
     * Constructs the Item superclass and sets the name and description of the item
     * @param name
     *     Sets the name of the new item
     * @param description
     *     Sets the description of the new item
     */
    public Item( String name, String description )
    {
        this.name = name;
        this.description = description;
    }
    /**
     * Returns the name of the item
     * @return
     *     String name of the item
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * Returns the description of the item
     * @return
     *     String description of the item
     */
    public String getDescription()
    {
        return this.description;
    }
}