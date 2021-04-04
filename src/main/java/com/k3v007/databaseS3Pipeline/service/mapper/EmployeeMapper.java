package com.k3v007.databaseS3Pipeline.service.mapper;

import com.k3v007.databaseS3Pipeline.dto.EmpBasicReport;
import com.k3v007.databaseS3Pipeline.model.Employee;
import org.springframework.stereotype.Service;

/**
 * The type Employee mapper.
 *
 * @author Vivek
 */
@Service
public class EmployeeMapper implements IMapper<Employee, EmpBasicReport> {

    @Override
    public EmpBasicReport map(Employee data) {
        return EmpBasicReport.builder()
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .employeeCode(data.getEmployeeCode())
                .designation(data.getDesignation())
                .dateOfJoining(data.getDateOfJoining())
                .build();
    }
}
