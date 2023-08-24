package ISA.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ADDRESSES")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long Id;
    public String street;
    public String number;
    public String city;
    public String state;


    public Address(String street, String number, String city, String state){
        this.street=street;
        this.number=number;
        this.city=city;
        this.state=state;

    }
}

