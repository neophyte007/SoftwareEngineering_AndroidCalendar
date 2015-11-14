package com.softwareengineering_androidcalendar;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * Created by j on 2015/11/13.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCalendarViewTest is the test class for {@link SimpleCalendarView}.
 */
public class SimpleCalendarViewTest extends AndroidTestCase {
    /**
     * Testfind holiday.
     */
    public void testfindHoliday() {
        boolean holiday = SimpleCalendarView.findHoliday("2013-11-28");
        assertEquals(true, holiday);
    }
}
