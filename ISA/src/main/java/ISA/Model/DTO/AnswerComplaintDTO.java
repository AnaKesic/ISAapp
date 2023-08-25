package ISA.Model.DTO;

import javax.validation.constraints.NotNull;

public class AnswerComplaintDTO {
    @NotNull
    public Long complaintId;
    @NotNull
    public String answer;
    @NotNull
    public String adminEmail;
}
