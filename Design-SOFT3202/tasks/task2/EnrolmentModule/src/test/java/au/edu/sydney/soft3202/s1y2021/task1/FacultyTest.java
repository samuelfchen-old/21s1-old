package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Ignore;		

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

// @Ignore
@RunWith(MockitoJUnitRunner.class)
public class FacultyTest {
    Faculty f;

    @Mock Unit u1;
    @Mock Unit u2;

    @Before
    public void init() {
        f = new FacultyImpl("software", "soft");
    }

    @Test
    public void testFacultyConstruction() {
        assertNotNull(f);
    }

    @Test
    public void testFacultyGetters() {
        assertEquals("software", f.getName());
        assertEquals("soft", f.getAbbreviation());
    }

    @Test
    public void testFacultyGetUnit() {
        when(u1.getCode()).thenReturn("soft");

        f.addUnit(u1);
        assertEquals(u1, f.getUnit(u1.getCode()));
        assertEquals(null, f.getUnit(u2.getCode()));
    }

    @Test
    public void testFacultyGetUnits() {
        when(u1.getCode()).thenReturn("soft");
        when(u2.getCode()).thenReturn("comp");

        f.addUnit(u1);
        f.addUnit(u2);

        List<String> ret = f.getUnits();

        assertTrue(ret.contains("soft"));
        assertTrue(ret.contains("comp"));
    }

    @Test
    public void testFacultyErrors() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl("softwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftwaresoftware", "soft");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl("software", "softsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoftsoft");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl("", "soft");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl("software", "");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl(null, "soft");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f = new FacultyImpl("software", null);
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            f.addUnit(null);
        });
        assertThrows(java.lang.IllegalStateException.class, () -> {
            when(u1.getCode()).thenReturn("soft");

            f.addUnit(u1);
            f.addUnit(u1);
        });
    }
}