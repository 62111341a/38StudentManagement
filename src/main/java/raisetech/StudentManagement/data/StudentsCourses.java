package raisetech.StudentManagement.data;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Schema(description= "受講生コース情報")
@Getter
@Setter
@Data
public class StudentsCourses {
  private String id;
  private String studentId;
  private String courseName;
  private LocalDateTime courseStartAt;
  private LocalDateTime courseEndAt;

}
