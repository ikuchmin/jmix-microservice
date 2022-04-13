package ru.udya.services.adminjmix.screen.department;

import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.entity.Department;

import java.util.Set;

@UiController("adm_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
public class DepartmentEdit extends StandardEditor<Department> {
    @Autowired
    private DepartmentAdapter departmentAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        departmentAdapter.create(getEditedEntity());
        return null;
    }
}