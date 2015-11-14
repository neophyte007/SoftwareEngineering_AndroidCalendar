package com.softwareengineering_androidcalendar;

import android.test.AndroidTestCase;

/**
 * Created by j on 2015/11/13.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class TestMySQLiteHelper is the test class for {@link MySQLiteHelper}.
 */
public class TestMySQLiteHelper extends AndroidTestCase {
    /** The db test. */
    private MySQLiteHelper dbTest;

	/* (non-Javadoc)
         * @
         */
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test create get category.
     */
    public void testCreateGetCategory() {
        int categoryCount = dbTest.getCategoryCount();
        Category ctg1 = new Category("Traveling", "Green");
        long ctg1_id = dbTest.createCategory(ctg1);
        ctg1.setId(ctg1_id);
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getId(),ctg1.getId());
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getName(),ctg1.getName());
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getColor(),ctg1.getColor());
        assertEquals(categoryCount+1,dbTest.getCategoryCount());
        assertEquals(59,ctg1_id);
    }

    /**
     * Test create get event.
     */
    public void testCreateGetEvent() {
        int eventCount = dbTest.getEventCount();
        Event testEvent = new Event("Alaska","12/17/2014","12/17/2014\"","17:30","19:00","Want to see beautiful place","Repeat-OFF");
        long[] testCategoryID = {1};
        long testEvent_ID = dbTest.createEvent(testEvent,testCategoryID);
        testEvent.setId(testEvent_ID);
        assertEquals(dbTest.getEvent(testEvent_ID).getId(),testEvent.getId());
        assertEquals(eventCount+1,dbTest.getEventCount());
        assertEquals(83,testEvent_ID);
    }

    /**
     * Test get category by event.
     */
    public void testGetCategoryByEvent() {
        Event event7 = new Event("Matrix","2012-11-08","2012-11-08","22:00","23:45","Worth to watch","OFF");
        long event7_id = dbTest.createEvent(event7, new long[] { 59 });
        event7.setId(event7_id);
        Category test = dbTest.getCategoryByEvent(event7_id);
        assertEquals("Traveling",test.getName());
    }

    /**
     * Test get eventby id.
     */
    public void testGetEventbyID() {
        Event event6 = new Event("Mentor","2012-11-08","2012-11-08","22:00","23:45","Worth to watch","OFF");
        long event6_id = dbTest.createEvent(event6, new long[] { 1 });
        event6.setId(event6_id);
        Event test = dbTest.getEvent(event6_id);
        assertEquals("Mentor",test.getTitle());
    }

    /**
     * Test get eventby name.
     */
    public void testGetEventbyName() {
        Event event6 = new Event("Thor","2012-11-08","2012-11-08","22:00","23:45","Worth to watch","OFF");
        long event6_id = dbTest.createEvent(event6, new long[] { 1 });
        event6.setId(event6_id);
        Event test = dbTest.getEvent("Thor");
        assertEquals(88,test.getId());	// id+2+3
    }

    /**
     * Test update category.
     */
    public void	testUpdateCategory() {
        Category ctg2 = new Category("Important", "BLUE");
        long ctg2_id = dbTest.createCategory(ctg2);
        ctg2.setId(ctg2_id);
        ctg2.setName("Most Important");
        ctg2.setColor("RED");
        dbTest.updateCategory(ctg2);
        assertEquals(ctg2.getId(),dbTest.getCategoryID("Most Important").getId());
    }

    /**
     * Test update event.
     */
    public void testUpdateEvent() {
        Event event2 = new Event("Phone","11/01/2013","11/01/2013","22:00","22:15","I will call her","Repeat-OFF");
        long event2_id = dbTest.createEvent(event2, new long[] { 1 });
        event2.setId(event2_id);
        event2.setTitle("Call");
        event2.setDescription("I will Emi");
        event2.setEndTime("22:30");
        dbTest.updateEvent(event2);
        assertEquals(event2.getId(),dbTest.getEvent("Call").getId());
    }

    /**
     * Test delete event.
     */
    public void testDeleteEvent() {
        int eventCount = dbTest.getEventCount();
        dbTest.deleteEvent(1);
        assertEquals(eventCount,dbTest.getEventCount());
    }

    /**
     * Test delete category.
     */
    public void testDeleteCategory() {
        int categoryCount = dbTest.getCategoryCount();
        dbTest.deleteCategoryByID(1);
        assertEquals(categoryCount,dbTest.getCategoryCount());
    }

    /* (non-Javadoc)
     * @see android.test.AndroidTestCase#tearDown()
     */
    public void tearDown() throws Exception {
        dbTest.close();
        super.tearDown();
    }
}