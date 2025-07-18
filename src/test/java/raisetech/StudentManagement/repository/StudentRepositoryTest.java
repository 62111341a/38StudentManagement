package raisetech.StudentManagement.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository sut;

@Test
    void 受講生の全件検索が行えること(){
        List<Student> actual = sut.search();
        assertThat(actual.size()).isEqualTo(1);
}
@Test
    void 受講生の登録が行えること(){
    Student student = new Student();
    student.setName("江波公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setArea("奈良県");
    student.setSex("男性");
    student.setRemark("");
    student.setDeleted(false);
    sut.registerStudent(student);
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(2);
}
}