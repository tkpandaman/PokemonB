package model;

import java.io.Serializable;

/**
 * The inheritance hierarchy for any items used in-game
 * @author Joshua Raphael
 */
public abstract class Item implements Serializable
{
    private static final long serialVersionUID = -5683984696004123764L;
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
    @Override
    public boolean equals(Object other) {
        // In an equals method you should always confirm that the instance of the Object
        // is the type you are looking for.
        if (!(other instanceof Item)) {
            return false;
        } else {
            Item otherItem = (Item)other;
            return this.getName().equals(otherItem.getName());
        }
    }
}