package com.softwareengineering_androidcalendar;

import junit.framework.TestCase;

/**
 * Created by Neophyte on 2015/11/16.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class TestEvent is the test class for {@link Event}.
 */
public class TestEvent extends TestCase {
    /** The Test event. */
    Event TestEvent = new Event("Event_Title","20-June-1985","21-June-1985","12:00am","11:50am","Event description","");

    /**
     * Test to string.
     */
    public void testToString() {
        assertEquals(TestEvent.toString(),"Event [id=0, title=Event_Title, startDate=20-June-1985, endDate=20-June-1985, startTime=12:00am, endTime=11:50am, description=Event description]");
    }

    /**
     * Test get set id.
     */
    public void testGetSetId() {
        TestEvent.setId(12345);
        assertEquals(TestEvent.getId(),12345);
    }

    /**
     * Test get set title.
     */
    public void testGetSetTitle() {
        TestEvent.setTitle("Software Engineering Exam");
        assertEquals(TestEvent.getTitle(),"Software Engineering Exam");
    }

    /**
     * Test get set start date.
     */
    public void testGetSetStartDate() {
        TestEvent.setStartDate("20/06/1985");
        assertEquals(TestEvent.getStartDate(),"20/06/1985");
    }

    /**
     * Test get set end date.
     */
    public void testGetSetEndDate() {
        TestEvent.setEndDate("21/06/1985");
        assertEquals(TestEvent.getEndDate(),"21/06/1985");
    }

    /**
     * Test get set start time.
     */
    public void testGetSetStartTime() {
        TestEvent.setStartTime("11:15am");
        assertEquals(TestEvent.getStartTime(),"11:15am");
    }

    /**
     * Test get set end time.
     */
    public void testGetSetEndTime() {
        TestEvent.setEndTime("12:40pm");
        assertEquals(TestEvent.getEndTime(),"12:40pm");
    }

    /**
     * Test get set description.
     */
    public void testGetSetDescription() {
        TestEvent.setDescription("Software Engineering Exam");
        assertEquals(TestEvent.getDescription(),"Software Engineering Exam");
    }
}
