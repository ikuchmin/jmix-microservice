package ru.udya.services.adminjmix.screen.department;

import io.jmix.core.LoadContext;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.entity.Department;

import java.util.List;

@UiController("adm_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
public class DepartmentBrowse extends StandardLookup<Department> {
    @Autowired
    private DepartmentAdapter departmentAdapter;
    @Autowired
    private CollectionLoader<Department> departmentsDl;

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<Department> departmentsDlLoadDelegate(LoadContext<Department> loadContext) {
        return departmentAdapter.findAll();
    }

    @Install(to = "departmentsTable.create", subject = "afterCommitHandler")
    private void departmentsTableCreateAfterCommitHandler(Department department) {
        departmentsDl.load();
    }
}