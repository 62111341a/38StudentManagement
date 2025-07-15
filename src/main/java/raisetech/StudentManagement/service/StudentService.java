package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.EnrollmentStatus;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.repository.EnrollmentStatusMapper;

@Service
public class StudentService {

  private final StudentRepository repository;
  private final StudentConverter converter;
  private final EnrollmentStatusMapper enrollmentStatusMapper;

  @Autowired
  public StudentService(StudentRepository repository,
                        StudentConverter converter,
                        EnrollmentStatusMapper enrollmentStatusMapper) {
    this.repository = repository;
    this.converter = converter;
    this.enrollmentStatusMapper = enrollmentStatusMapper;
  }

  public List<StudentDetail> searchStudentList() {
      List<Student> search = repository.search();
      List<StudentsCourses> studentCourseList = repository.searchStudentsCoursesList();
      return converter.convertStudentDetails(search, studentCourseList);
  }

  // ← 追加 start
  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourseList(studentsCourses);
    return studentDetail;
  }

  public void initStudentsCourses(StudentsCourses studentsCourse, String studentId) {
    studentsCourse.setStudentId(studentId);
    studentsCourse.setCourseStartAt(LocalDateTime.now());
    studentsCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
  }
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
      Student student = studentDetail.getStudent();
      repository.registerStudent(student);

      studentDetail.getStudentsCourseList().forEach(studentsCourses -> {
          initStudentsCourses(studentsCourses, student.getId());
          repository.registerStudentsCourses(studentsCourses);

          EnrollmentStatus status = new EnrollmentStatus();
          status.setStudentsCourseId(Long.valueOf(studentsCourses.getId()));
          status.setStatus("仮申込");
          status.setUpdatedAt(LocalDateTime.now());

          enrollmentStatusMapper.insertEnrollmentStatus(status);
      });

      return studentDetail;
      }

      @Transactional
      public void updateStudent(StudentDetail studentDetail) {
          repository.updateStudent(studentDetail.getStudent());

          studentDetail.getStudentsCourseList().forEach(studentsCourse -> {
              repository.updateStudentsCourses(studentsCourse);
          });
          }

          @Transactional
          public void updateEnrollmentStatus(Long studentsCourseId, String newStatus) {
              EnrollmentStatus status = new EnrollmentStatus();
              status.setStudentsCourseId(studentsCourseId);
              status.setStatus(newStatus);
              status.setUpdatedAt(LocalDateTime.now());

              enrollmentStatusMapper.updateEnrollmentStatus(status);
          }

      }