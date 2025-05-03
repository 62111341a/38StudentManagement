package raisetech.StudentManagement.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
@Schema(description= "受講生詳細")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class StudentDetail {
  @Valid
  private Student student;
  @Valid
  private List<StudentsCourses> studentsCourseList;
}
