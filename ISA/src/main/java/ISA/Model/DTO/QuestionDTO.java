package ISA.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class QuestionDTO {
    @NotNull
    public List<Boolean> answers;
    @NotNull
    public String email;
}
