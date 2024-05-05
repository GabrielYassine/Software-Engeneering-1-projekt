package example.junit;

import dtu.app.ui.ProjectApp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestParseAndValidateWeek {

    /**
     * Test the createProject method in the projectApp class
     * @Author: Aland
     */

    private ProjectApp projectApp;

    @Before
    public void setUp() {
        projectApp = new ProjectApp();
    }

    @Test
    public void testInputDataSetA() {
        try {
            projectApp.parseAndValidateWeek("53");
        } catch (IllegalArgumentException e) {
            assertEquals("Week value out of bounds", e.getMessage());
        }
    }

    @Test
    public void testInputDataSetB() {
        try {
            projectApp.parseAndValidateWeek("0");
        } catch (IllegalArgumentException e) {
            assertEquals("Week value out of bounds", e.getMessage());
        }
    }

    @Test
    public void testInputDataSetC() {
        int week = projectApp.parseAndValidateWeek("51");
        assertEquals(51, week);
    }


    @Test
    public void testInputDataSetD() {
        try {
            projectApp.parseAndValidateWeek("");
        } catch (IllegalArgumentException e) {
            assertEquals("No week given", e.getMessage());
        }
    }

}

