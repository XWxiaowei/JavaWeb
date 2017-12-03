package com.jay.ssh.action;

import com.jay.ssh.entity.Department;
import com.jay.ssh.service.DepartmentService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author  xiang.wei on 2017/10/14
 * 模型驱动
 */
@Action(value = "departmentAction")
public class DepartmentAction  extends BaseAction{
    @Autowired
    private DepartmentService departmentService;

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private List<Department> departmentList;

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }


    public String findAll() {
        departmentList= departmentService.findAll();
        return "findAll";
    }

    public String saveDepartment() {
        departmentService.save(department);
        return "addSuccess";
    }
    public String findById() {
        String did = ServletActionContext.getRequest().getParameter("did");
        department=departmentService.findByDid(Integer.valueOf(did));
        return "goEditDepartment";
    }
    public String update() {
        departmentService.update(department);
        return "updateSuccess";
    }
    public String delete() {
        return "deleteSuccess";
    }
    public String goAddDepartment(){
        return "goAddDepartment";
    }
}
