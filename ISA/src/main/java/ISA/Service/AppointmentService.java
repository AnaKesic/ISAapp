package ISA.Service;

import ISA.Model.Appointment;
import ISA.Model.DTO.SheduleAppointmentDTO;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface AppointmentService {

    public Appointment sheduleAppointment(SheduleAppointmentDTO dto) throws IOException, WriterException;
    public void cancelAppointment(SheduleAppointmentDTO dto);
    public boolean checkTime(Long appointmentId);
}
