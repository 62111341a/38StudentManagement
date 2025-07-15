package raisetech.StudentManagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.repository.EnrollmentStatusMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    @Mock
    private EnrollmentStatusMapper enrollmentStatusMapper;  // 追加

    private StudentService sut;

    @BeforeEach
    void before(){
        sut = new StudentService(repository, converter, enrollmentStatusMapper);
    }

    @Test
    void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出されていること(){
        List<Student> studentList = new ArrayList<>();
        List<StudentsCourses> studentsCoursesList = new ArrayList<>();
        when(repository.search()).thenReturn(studentList);
        when(repository.searchStudentsCoursesList()).thenReturn(studentsCoursesList);
        when(converter.convertStudentDetails(studentList, studentsCoursesList)).thenReturn(new ArrayList<>());

        List<StudentDetail> actual = sut.searchStudentList();

        verify(repository, times(1)).search();
        verify(repository, times(1)).searchStudentsCoursesList();
        verify(converter, times(1)).convertStudentDetails(studentList, studentsCoursesList);
    }

    @Test
    void 受講生詳細の検索＿リポジトリの処理が適切に呼び出されていること(){
        String id = "999";
        Student student = new Student();
        student.setId(id);

        when(repository.searchStudent(id)).thenReturn(student);
        when(repository.searchStudentsCourses(id)).thenReturn(new ArrayList<>());

        StudentDetail expected = new StudentDetail(student, new ArrayList<>());

        StudentDetail actual = sut.searchStudent(id);

        verify(repository, times(1)).searchStudent(id);
        verify(repository, times(1)).searchStudentsCourses(id);

        Assertions.assertEquals(expected.getStudent().getId(), actual.getStudent().getId());
    }

    @Test
    void 受講生詳細の登録＿初期化処理が行われていること(){
        String id = "999";
        Student student = new Student();
        student.setId(id);

        StudentsCourses studentsCourses = new StudentsCourses();

        sut.initStudentsCourses(studentsCourses, student.getId());

        Assertions.assertEquals("999", studentsCourses.getStudentId());
        Assertions.assertEquals(LocalDateTime.now().getHour(),
                studentsCourses.getCourseStartAt().getHour());
        Assertions.assertEquals(LocalDateTime.now().plusYears(1).getYear(),
                studentsCourses.getCourseEndAt().getYear());
    }
}
