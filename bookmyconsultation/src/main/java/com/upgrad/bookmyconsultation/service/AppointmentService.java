package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import com.upgrad.bookmyconsultation.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {


	@Autowired
	private AppointmentRepository appointmentRepository;


	@Autowired
	private UserRepository userRepository;

	public String appointment(Appointment appointment) throws InvalidInputException, SlotUnavailableException {
		ValidationUtils.validate(appointment);
		Appointment duplicate = appointmentRepository.findByDoctorIdAndTimeSlotAndAppointmentDate(appointment.getDoctorId(), appointment.getTimeSlot(), appointment.getAppointmentDate());
		if(duplicate!=null) throw new SlotUnavailableException();
		else return appointmentRepository.save(appointment).getAppointmentId();
	}

	public Appointment getAppointment(String appointmentId){
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		if(appointment.isPresent()) return appointment.get();
		else throw new ResourceUnAvailableException();
	}



	public List<Appointment> getAppointmentsForUser(String userId) {
		return appointmentRepository.findByUserId(userId);
	}
}
