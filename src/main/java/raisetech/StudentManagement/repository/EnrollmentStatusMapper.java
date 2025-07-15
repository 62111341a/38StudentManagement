package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentManagement.data.EnrollmentStatus;

@Mapper
public interface EnrollmentStatusMapper {

    void insertEnrollmentStatus(EnrollmentStatus status);

    void updateEnrollmentStatus(EnrollmentStatus status);

    EnrollmentStatus findByStudentsCourseId(Long studentsCourseId);
}
