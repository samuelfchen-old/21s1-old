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
public class UnitTest {
    Unit u;

    @Mock Student s1;
    @Mock Student s2;

    @Before
    public void init() {
        u = new UnitImpl("soft3202", "software design 2");
        
        when(s1.getSID()).thenReturn("123456789");
        when(s1.getUnikey()).thenReturn("abcd1234");
        when(s2.getSID()).thenReturn("223456789");
        when(s2.getUnikey()).thenReturn("bbcd1234");
    }

    @Test
    public void testUnitConstruction() {
        assertNotNull(u);
    }

    @Test
    public void testUnitGetters() {
        assertEquals("soft3202", u.getCode());
        assertEquals("software design 2", u.getName());
    }

    @Test
    public void testUnitEnrolStudent() {
        u.enrolStudent(s1);
        u.enrolStudent(s2);
    }

    @Test
    public void testUnitRemoveStudent() {
        u.enrolStudent(s1);
        u.enrolStudent(s2);

        u.withdrawStudent(s1);
        u.withdrawStudent(s2);
    }

    @Test
    public void testUnitGetStudents() {
        u.enrolStudent(s1);
        u.enrolStudent(s2);

        List<String> ret = u.getStudents();
        assertTrue(ret.contains("abcd1234"));
        assertTrue(ret.contains("bbcd1234"));
    }

    @Test
    public void testUnitGetStudent() {
        u.enrolStudent(s1);
        u.enrolStudent(s2);

        assertEquals(s1, u.getStudent("123456789"));
        assertEquals(s1, u.getStudent("abcd1234"));
        assertEquals(null, u.getStudent("1231"));
    }

    @Test
    public void testUnitErrors() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u = new UnitImpl(null, "software design 2");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u = new UnitImpl("soft3202", null);
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u = new UnitImpl("soft3202asdfasdf", "software design 2");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u = new UnitImpl("so232ft2", "software design 2");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u = new UnitImpl("soft3202", "software design 2software design 2software design 2software design 2software design 2software design 2software design 2software design 2software design 2software design 2");
        });

        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u.enrolStudent(null);
        });
        assertThrows(java.lang.IllegalStateException.class, () -> {
            u.enrolStudent(s1);
            u.enrolStudent(s1);
        });
        assertThrows(java.lang.IllegalStateException.class, () -> {
            u.withdrawStudent(s1);
            u.withdrawStudent(s1);
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            u.withdrawStudent(null);
        });
    }

}