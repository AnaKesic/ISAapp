package ISA.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="QuestionText")
public class QuestionText {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public String text;
}
