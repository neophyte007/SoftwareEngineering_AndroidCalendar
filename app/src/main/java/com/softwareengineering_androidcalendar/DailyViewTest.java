package com.softwareengineering_androidcalendar;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * Created by Neophyte on 2015/11/16.
 */
// TODO: Auto-generated Javadoc
/**
 * The Class DailyViewTest for test class for daily view
 */
public class DailyViewTest extends AndroidTestCase {
    /**
     * Testfind color.
     */
    public void testfindColor() {
        String color = DailyView.findColor("Orange");
        assertEquals("#FF0000", color);
    }
}
