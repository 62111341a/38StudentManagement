package raisetech.StudentManagement.controller.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;

public class StudentConverterTest {

    private final StudentConverter converter = new StudentConverter();

    @Test
    void testConvertStudentDetails_withMatchingCourses() {
        // Arrange
        Student student1 = new Student();
        student1.setId(String.valueOf(1));

        Student student2 = new Student();
        student2.setId(String.valueOf(2));

        StudentsCourses course1 = new StudentsCourses();
        course1.setStudentId(String.valueOf(1));
        course1.setCourseName("Math");

        StudentsCourses course2 = new StudentsCourses();
        course2.setStudentId(String.valueOf(2));
        course2.setCourseName("Science");

        List<Student> students = Arrays.asList(student1, student2);
        List<StudentsCourses> courses = Arrays.asList(course1, course2);

        // Act
        List<StudentDetail> result = converter.convertStudentDetails(students, courses);

        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getStudent().getId());
        assertEquals("Math", result.get(0).getStudentsCourseList().get(0).getCourseName());

        assertEquals(2, result.get(1).getStudent().getId());
        assertEquals("Science", result.get(1).getStudentsCourseList().get(0).getCourseName());
    }

    @Test
    void testConvertStudentDetails_withNoMatchingCourses() {
        // Arrange
        Student student = new Student();
        student.setId(String.valueOf(1));

        StudentsCourses course = new StudentsCourses();
        course.setStudentId(String.valueOf(1)); // IDが一致しない

        List<Student> students = Collections.singletonList(student);
        List<StudentsCourses> courses = Collections.singletonList(course);

        // Act
        List<StudentDetail> result = converter.convertStudentDetails(students, courses);

        // Assert
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStudent().getId());
        assertTrue(result.get(0).getStudentsCourseList().isEmpty());
    }

    @Test
    void testConvertStudentDetails_withEmptyInputs() {
        // Act
        List<StudentDetail> result = converter.convertStudentDetails(Collections.emptyList(), Collections.emptyList());

        // Assert
        assertTrue(result.isEmpty());
    }
}
