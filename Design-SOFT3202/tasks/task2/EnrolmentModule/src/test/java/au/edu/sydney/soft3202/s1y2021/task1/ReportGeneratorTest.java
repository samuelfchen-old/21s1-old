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
public class ReportGeneratorTest {
    @InjectMocks
    ReportGeneratorImpl r;

    @Mock EnrolmentSystem e;
    @Mock Student s1;
    @Mock Student s2;
    @Mock Faculty f1;
    @Mock Faculty f2;
    @Mock Unit u1;
    @Mock Unit u2;

    @Before
    public void init() {
        when(e.getStudents()).thenReturn(Arrays.asList("abcd1234", "bbcd1234"));
        when(e.getStudent("abcd1234")).thenReturn(s1);
        when(e.getStudent("bbcd1234")).thenReturn(s2);

        when(s1.getSID()).thenReturn("123456789");
        when(s1.getUnikey()).thenReturn("abcd1234");
        when(s1.getName()).thenReturn("Samuel");
        when(s1.getPreferredName()).thenReturn("Sam");

        when(s2.getSID()).thenReturn("223456789");
        when(s2.getUnikey()).thenReturn("bbcd1234");
        when(s2.getName()).thenReturn("Michael");
        when(s2.getPreferredName()).thenReturn("Mike");

        when(e.getFaculties()).thenReturn(Arrays.asList("software", "computing"));
        when(e.getFaculty("software")).thenReturn(f1);
        when(e.getFaculty("computing")).thenReturn(f2);

        when(f1.getName()).thenReturn("software");
        when(f2.getName()).thenReturn("computing");
        when(f1.getAbbreviation()).thenReturn("soft");
        when(f2.getAbbreviation()).thenReturn("comp");
        when(f1.getUnits()).thenReturn(Arrays.asList("soft3202"));
        when(f2.getUnits()).thenReturn(Arrays.asList("comp2017"));
        when(f1.getUnit("soft3202")).thenReturn(u1);
        when(f2.getUnit("comp2017")).thenReturn(u2);

        when(u1.getCode()).thenReturn("soft3202");
        when(u2.getCode()).thenReturn("comp2017");
        when(u1.getName()).thenReturn("Software Design 2");
        when(u2.getName()).thenReturn("Computer Systems");

        when(u1.getStudents()).thenReturn(Arrays.asList("abcd1234"));
        when(u2.getStudents()).thenReturn(Arrays.asList("bbcd1234"));
        when(u1.getStudent("abcd1234")).thenReturn(s1);
        when(u2.getStudent("bbcd1234")).thenReturn(s2);

    }

    @Test
    public void testReportConstruction() {
        assertNotNull(r);
    }

    @Test
    public void testReportGenerateReport() {
        assertEquals(r.generateReport(), "Students:\n\t123456789 abcd1234 Samuel Sam\n\t223456789 bbcd1234 Michael Mike\nsoft software\n\tsoft3202 Software Design 2\n\t\t123456789 abcd1234 Samuel Sam\ncomp computing\n\tcomp2017 Computer Systems\n\t\t223456789 bbcd1234 Michael Mike\n");
    }

    @Test
    public void testReportErrors() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            r = new ReportGeneratorImpl(null);
        });
    }
}