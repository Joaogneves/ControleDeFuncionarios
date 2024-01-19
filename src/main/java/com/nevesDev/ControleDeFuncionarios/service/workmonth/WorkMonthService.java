package com.nevesDev.ControleDeFuncionarios.service.workmonth;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.employee.EmployeeDtoResponse;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.WorkmonthDtoResponse;
import com.nevesDev.ControleDeFuncionarios.repository.workmonthRepository.WorkmonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkMonthService {

    @Autowired
    private WorkmonthRepository repository;

    public List<WorkmonthDtoResponse> findAllByEmployee(UUID id) {
        List<Workmonth> workmonths = repository.findAllByEmployeeId(id);
        List<WorkmonthDtoResponse> workmonthDtoResponses = new ArrayList<>();
        for(Workmonth w : workmonths) {
            Employee e = w.getEmployee();
            EmployeeDtoResponse edto = new EmployeeDtoResponse(e.getFirstName() + " " + e.getLastName());
            WorkmonthDtoResponse dto = new WorkmonthDtoResponse(w.getNormalHour(), w.getExtraHour50(), w.getExtraHour100(), edto);
            workmonthDtoResponses.add(dto);
        }
        return workmonthDtoResponses;
    }

    public Workmonth findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
