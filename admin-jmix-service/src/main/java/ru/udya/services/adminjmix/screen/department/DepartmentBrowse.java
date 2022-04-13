package ru.udya.services.adminjmix.screen.department;

import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.client.DepartmentControllerApi;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.adminjmix.mapper.DepartmentMapper;

import java.util.List;

@UiController("adm_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
public class DepartmentBrowse extends StandardLookup<Department> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentBrowse.class);

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentControllerApi departmentControllerApi;

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<Department> departmentsDlLoadDelegate(LoadContext<Department> loadContext) {
        return departmentControllerApi.findAllDepartments()
                .map(departmentMapper::departmentDtoToDepartment)
                .collectList()
                .block();
    }
}