package com.softwareengineering_androidcalendar;

import junit.framework.TestCase;

/**
 * Created by j on 2015/11/13.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class TestCategory is the test class for {@link Category}.
 */
public class TestCategory extends TestCase {
    /** The Cat. */
    Category Cat=new Category("TestCat","blue");

    /**
     * Test get set id.
     */
    public void testGetSetId() {
        Cat.setId(1510);
        assertEquals(Cat.getId(),1510);
    }

    /**
     * Test get set name.
     */
    public void testGetSetName() {
        Cat.setName("Traveling");
        assertEquals(Cat.getName(),"Traveling");
    }

    /**
     * Test get set color.
     */
    public void testGetSetColor() {
        Cat.setColor("Green");
        assertEquals(Cat.getColor(),"Green");
    }
}

