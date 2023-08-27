package ISA.Service;

import ISA.Model.DTO.AppointmentDTO;
import ISA.Model.DTO.DataForComplaintDTO;
import ISA.Model.DTO.ShowComplaintDTO;
import ISA.Model.Donor;
import ISA.Model.User;
import ISA.enums.FilterApp;
import ISA.enums.FilterQR;

import java.util.List;

public interface UserService {



    public Donor RegisterKorisnik(Donor k);

    public List<AppointmentDTO> getAllDonorAppointments(String email);
    public void IsDonorExceptable(String email);
    public List<AppointmentDTO> getSheduledDonorAppointments(String email);
    public List<AppointmentDTO> getHistoryOfDonorAppointments(String email);
    public List<AppointmentDTO> sortHistoryOfDonorAppointments(String email, FilterApp filter);
    public List<AppointmentDTO> sortQROfDonorAppointments(String email, FilterQR filter);
    public DataForComplaintDTO getAllAllowed(String email);
    public List<ShowComplaintDTO> getAllAdminComplaints(String Email);
    public List<ShowComplaintDTO> getAllDonorComplaints(String email);

}
