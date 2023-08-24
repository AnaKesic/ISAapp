package ISA.Service;

import ISA.Repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;
}
