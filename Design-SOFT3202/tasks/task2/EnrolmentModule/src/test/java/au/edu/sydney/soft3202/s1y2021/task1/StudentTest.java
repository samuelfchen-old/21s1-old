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
public class StudentTest {
    Student s;

    @Before
    public void init() {
        s = new StudentImpl("123456789", "abcd1234", "Samuel", "Sam");
    }

    @Test
    public void testStudentConstruction() {
        assertNotNull(s);
    }

    @Test
    public void testStudentGetters() {
        assertEquals("123456789", s.getSID());
        assertEquals("abcd1234", s.getUnikey());
        assertEquals("Samuel", s.getName());
        assertEquals("Sam", s.getPreferredName());
    }

    @Test
    public void testStudentSetPreferredName() {
        s.setPreferredName("Samm");
        assertEquals("Samm", s.getPreferredName());

        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s.setPreferredName("SaSamSamSamSamSamSamSamSamSamSamSamm");
        });
    }

    @Test
    public void testStudentErrors() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("1234567890", "abcd1234", "Samuel", "Sam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("123456789", "ab12cddd12", "Samuel", "Sam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("123456789", "abcd1234", "SamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuelSamuel", "Sam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("123456789", "abcd1234", "Samuel", "SamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSamSam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl(null, "abcd1234", "Samuel", "Sam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("123456789", null, "Samuel", "Sam");
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            s = new StudentImpl("123456789", "abcd1234", null, "Sam");
        });
        // assertThrows(java.lang.IllegalArgumentException.class, () -> {
        //     s = new StudentImpl("123456789", "abcd1234", "Samuel", null);
        // });
    }
}