package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;


@NoArgsConstructor
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;
  private List<Student> studentList;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
  }

  public List<StudentDetail> searchStudentList() {
    List<Student> search = repository.search();
    List<StudentsCourses> studentCourseList = repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourseList(studentsCourses);
    return studentDetail;
  }

  public List<StudentsCourses> searchStudentsCourseList() {
    return repository.searchStudentsCoursesList();
  }

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    studentDetail.getStudentsCourseList().forEach(studentsCourses -> {
      initStudentsCourses(studentsCourses, student.getId());
      repository.registerStudentsCourses(studentsCourses);
            });
    return studentDetail;
  }
  public void initStudentsCourses(StudentsCourses studentsCourse, String studentId) {
    studentsCourse.setStudentId(studentId);
    studentsCourse.setCourseStartAt(LocalDateTime.now());
    studentsCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
      studentDetail.getStudentsCourseList().forEach(studentsCourse -> repository.updateStudentsCourses(studentsCourse));
  }
}
