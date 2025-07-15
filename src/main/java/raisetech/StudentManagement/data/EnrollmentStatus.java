package raisetech.StudentManagement.data;

import java.time.LocalDateTime;

public class EnrollmentStatus {
void insertEnrollmentStatus(EnrollmentStatus status){

    }
void updateEnrollmentStatus(EnrollmentStatus status){

    }
EnrollmentStatus findByStudentsCourseId(Long studentsCourseId){
    return null;
    }

    private Long id; // 主キー（自動採番）
    private Long studentsCourseId; // 外部キー → StudentsCourses.id
    private String status; // 仮申込・本申込・受講中・受講終了
    private LocalDateTime updatedAt;

    // --- Getter / Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentsCourseId() {
        return studentsCourseId;
    }

    public void setStudentsCourseId(Long studentsCourseId) {
        this.studentsCourseId = studentsCourseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
