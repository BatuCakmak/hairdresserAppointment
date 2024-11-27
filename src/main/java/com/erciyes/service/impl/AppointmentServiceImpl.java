package com.erciyes.service.impl;

import com.erciyes.dto.DtoAppointment;
import com.erciyes.mapper.AppointmentMapper;
import com.erciyes.model.Appointment;
import com.erciyes.repository.AppointmentRepository;
import com.erciyes.service.IAppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Override
    public DtoAppointment createAppointment(Appointment appointment) {
        appointment.setCreateTime(new Date());
        DtoAppointment dtoAppointment = new DtoAppointment();
        appointmentRepository.save(appointment);
        BeanUtils.copyProperties(appointment, dtoAppointment);
        return dtoAppointment;
    }

    @Override
    public List<DtoAppointment> getAllAppointments() {
        List<Appointment> appointmentList =appointmentRepository.findAll();
        List<DtoAppointment> dtoAppointmentList=new ArrayList<>();
        for (Appointment appointment:appointmentList){
            DtoAppointment dtoAppointment =appointmentMapper.toDto(appointment);
            dtoAppointmentList.add(dtoAppointment);
        }
        return dtoAppointmentList;
    }

    @Override
    public DtoAppointment getAppointmentById(Long id) {
        Optional<Appointment> optional=appointmentRepository.findById(id);
        if (optional.isPresent()){
            DtoAppointment dtoAppointment=appointmentMapper.toDto(optional.get());
            return dtoAppointment;
        }
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
         appointmentRepository.deleteById(id);
    }

    @Override
    public DtoAppointment updateAppointment(Long id, Appointment appointment) {
        Optional<Appointment> optional=appointmentRepository.findById(id);
        if (optional.isPresent()){
            appointment.setId(id);
            Appointment dbAppointment=appointmentRepository.save(appointment);
            return appointmentMapper.toDto(dbAppointment);
        }
        return null;
    }
}
