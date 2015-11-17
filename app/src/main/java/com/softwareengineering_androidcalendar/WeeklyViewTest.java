package com.softwareengineering_androidcalendar;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * Created by Neophyte on 2015/11/16.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class WeeklyViewTest is the test class for {@link WeeklyView}.
 */
public class WeeklyViewTest extends AndroidTestCase {
    /**
     * Testcal top.
     */
    public void testcalTop() {
        String[] stTime = {Integer.toString(2),Integer.toString(15)};
        int top = WeeklyView.calTop(stTime);
        assertEquals(270,top );
    }

    /**
     * Testcal height.
     */
    public void testcalHeight() {
        String[] stTime = {Integer.toString(2),Integer.toString(15)};
        String[] eTime = {Integer.toString(2),Integer.toString(15)};
        int height = WeeklyView.calHeight(stTime, eTime);
        assertEquals(0, height);
    }
}
