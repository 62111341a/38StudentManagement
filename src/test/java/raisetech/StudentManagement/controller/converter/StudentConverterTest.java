package raisetech.StudentManagement.controller.converter;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentConverterTest {
    private StudentConverter sut;

    @BeforeEach
    void before() {
        sut = new StudentConverter();
    }

    // createStudent() メソッドの追加
    private Student createStudent() {
        Student student = new Student();
                private Student createStudent() {
                        .id("1")
                        .name("田中太郎")
                        .kanaName("タナカタロウ")
                        .nickname("タナカ")
                        .email("tanaka@example.com")
                        .area("東京")
                        .sex("男性")
                        .remark("")
                        .deleted(false);

                }

        return student;

    }

    @Test
    void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストが作成できること() {
        Student student = new Student();
        student.setName("江波公史");
        student.setKanaName("エナミコウジ");
        student.setNickname("エナミ");
        student.setEmail("test@example.com");
        student.setArea("奈良県");
        student.setSex("男性");
        student.setRemark("");
        student.setDeleted(false);
        student.setId("1");

        StudentsCourses studentsCourses = new StudentsCourses();
        studentsCourses.setStudentId("1");
        studentsCourses.setId("1");
        studentsCourses.setCourseName("JAVAコース");
        studentsCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));
        studentsCourses.setCourseStartAt(LocalDateTime.now());
        List<Student> studentList = List.of(student);
        List<StudentsCourses> studentsCoursesList = List.of(studentsCourses);
        List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentsCoursesList);
        assertThat(actual.get(0).getStudent()).isEqualTo(student);
        assertThat(actual.get(0).getStudentsCourseList()).isEqualTo(studentsCoursesList);
    }

    @Test
    void 受講生のリストと受講生コース情報のリストを渡したときに紐付かない受講生コース情報は除外されること() {
        Student student = createStudent();  // createStudent() を呼び出す
        StudentsCourses studentsCourses = new StudentsCourses();
        studentsCourses.setId("1");
        studentsCourses.setStudentId("2");  // 学生IDが異なる
        studentsCourses.setCourseName("Javaコース");
        studentsCourses.setCourseStartAt(LocalDateTime.now());
        studentsCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));

        List<Student> studentList = List.of(student);
        List<StudentsCourses> studentsCoursesList = List.of(studentsCourses);

        // このテストでは、studentListの学生IDとstudentsCoursesのstudentIdが一致しないため、コース情報は除外されるはず
        List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentsCoursesList);

        assertThat(actual.get(0).getStudent()).isEqualTo(student);
        assertThat(actual.get(0).getStudentsCourseList().isEmpty());
    }
}
