package raisetech.StudentManagement.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerTest {
@Autowired
    private MockMvc mockMvc;
@MockBean
    private StudentService service;
    private Validator validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();
@Test
    void 受講生詳細の一覧検索が実行できて空のリストが帰ってくること()throws Exception{

    mockMvc.perform(get("/studentList")).andExpect(status().isOk());
    verify(service, times(1)).searchStudentList();
}
@Test
    void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックにかかること(){
    Student student = new Student(
            "テストです。",
            "江波公史",
            "エナミコウジ",
            "エナミ",
            "test@example.com",
            "奈良県",
            "男性"
    );

    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertEquals("7" ,violations.size());
}
}