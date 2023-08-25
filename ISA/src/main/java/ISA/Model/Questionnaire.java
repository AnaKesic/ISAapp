package ISA.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="QUESTIONNAIRE")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private LocalDateTime addmited;
    @OneToOne
    private Donor donor;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Question> questionList;


}
