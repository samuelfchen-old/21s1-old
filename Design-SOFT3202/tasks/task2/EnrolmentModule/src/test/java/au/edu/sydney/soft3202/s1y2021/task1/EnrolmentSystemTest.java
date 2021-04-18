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
public class EnrolmentSystemTest {
    @InjectMocks EnrolmentSystemImpl e;

    @Mock ReportGenerator r;

    @Mock Student s1;
    @Mock Student s2;
    @Mock Faculty f1;
    @Mock Faculty f2;
    @Mock Unit u1;
    @Mock Unit u2;

    @Before
    public void setup() {
        when(s1.getSID()).thenReturn("123456789");
        when(s1.getUnikey()).thenReturn("abcd1234");
        when(s2.getSID()).thenReturn("223456789");
        when(s2.getUnikey()).thenReturn("bbcd1234");

        when(f1.getName()).thenReturn("software");
        when(f2.getName()).thenReturn("computing");

        when(r.generateReport()).thenReturn("Dummy report");
    }

    @Test
    public void testEnrolConstruction() {
        assertNotNull(e);
    }

    @Test
    public void testEnrolSetReportGenerator() {
        e.setReportGenerator(r);
    }

    @Test
    public void testEnrolAddStudent() {
        assertEquals(s1, e.addStudent(s1));
        assertEquals(s2, e.addStudent(s2));
    }

    @Test
    public void testEnrolAddFaculty() {
        assertEquals(f1, e.addFaculty(f1));
        assertEquals(f2, e.addFaculty(f2));
    }

    @Test
    public void testEnrolAddUnit() {
        e.addFaculty(f1);
        assertEquals(u1, e.addUnit(u1, f1));
        assertEquals(u2, e.addUnit(u2, f1));
    } 

    @Test
    public void testEnrolGetFaculty() {
        e.addFaculty(f1);
        e.addFaculty(f2);
        assertEquals(f1, e.getFaculty("software"));
        assertEquals(f2, e.getFaculty("computing"));
    }

    @Test
    public void testEnrolGetStudent() {
        e.addStudent(s1);
        e.addStudent(s2);

        assertEquals(s1, e.getStudent("123456789"));
        assertEquals(s2, e.getStudent("223456789"));
        assertEquals(s1, e.getStudent("abcd1234"));
        assertEquals(s2, e.getStudent("bbcd1234"));
    }

    @Test
    public void testEnrolGetFaculties() {
        e.addFaculty(f1);
        e.addFaculty(f2);

        List<String> ret = e.getFaculties();
        assertTrue(ret.contains("software"));
        assertTrue(ret.contains("computing"));
    }
    
    @Test
    public void testEnrolGetStudents() {
        e.addStudent(s1);
        e.addStudent(s2);

        List<String> ret = e.getStudents();
        assertTrue(ret.contains("abcd1234"));
        assertTrue(ret.contains("bbcd1234"));
    }

    @Test
    public void testEnrolGetReport() {
        assertEquals("Dummy report", e.getReport());
    }


    @Test
    public void testEnrolErrors() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            e.addStudent(null);
        });
        assertThrows(java.lang.IllegalStateException.class, () -> {
            e.addStudent(s1);
            e.addStudent(s1);
        });
        assertThrows(java.lang.IllegalStateException.class, () -> {
            e.addStudent(s1);
            when(s2.getUnikey()).thenReturn("abcd1234");
            e.addStudent(s2);
        });

        assertThrows(java.lang.IllegalStateException.class, () -> {
            e.addFaculty(f1);
            e.addFaculty(f1);
        });

        assertThrows(java.lang.IllegalStateException.class, () -> {
            e.addUnit(u1, null);
        });
    }
}