package ISA.Model.DTO;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Model.Donor;
import ISA.Model.Staff;
import ISA.enums.AppStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDTO {

    public Long id;
    public LocalDateTime time;
    public int duration;
    public String doctor;
    public String bloodBank;
    public int price;
    public AppStatus status;
    public byte[] QRCode;

    public AppointmentDTO(Appointment app){
        this.id=app.Id;
        this.time=app.time;
        this.duration=app.duration;
        this.bloodBank=app.bloodBank.getName();
        this.price=app.price;
        this.status=app.status;
        this.QRCode=app.QRCode;
        this.doctor=app.doctor.getName() + " "+ app.doctor.getSurname();
    }

}

