package ISA.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class DataForComplaintDTO {
    public List<StaffDTO> staff = new ArrayList<>();
    public List<BBDTO> bloodBank= new ArrayList<>();
}
