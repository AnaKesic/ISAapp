package ISA.Service;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;

import java.util.List;

public interface BloodBankService {
    public List<BloodBank> getAll();
    public List<Appointment> getAllAvailableAppointments(Long id);
}
