package raisetech.StudentManagement.domain;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {
  @Valid
  private Student student;
  @Valid
  private List<StudentsCourses> studentsCourseList;
}
