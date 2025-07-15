package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;import lombok.Builder;import lombok.Data;import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "受講生詳細")
@Data
public class Student {

    @NotBlank(message = "IDは必須です")
    @Pattern(regexp = "^[0-9]+$", message = "IDは数字で入力してください")
    private String id;

    private Long studentId;

    @NotBlank(message = "名前は必須です")
    private String name;

    @NotBlank(message = "ふりがなは必須です")
    private String kanaName;

    @NotBlank(message = "ニックネームは必須です")
    private String nickname;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式が不正です")
    private String email;

    @NotBlank(message = "地域は必須です")
    private String area;

    private int age;

    private String sex;

    private String remark;

    private boolean isDeleted;
    private String status;
    }