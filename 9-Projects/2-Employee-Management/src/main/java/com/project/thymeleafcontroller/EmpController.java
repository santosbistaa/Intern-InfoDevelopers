package com.project.thymeleafcontroller;

import com.project.entities.Employee;
import com.project.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class EmpController {
    private final EmployeeService employeeService;

//    @PostMapping("/create")

    @GetMapping("/employees")
    public String getAllEmployees(Model model){
        model.addAttribute("employees",employeeService.getAllEmployees());
        return "getAllEmployees";
    }

    @GetMapping("/employee/add")
    public String createEmployee(Model model){
        // we are creating this object to hold the employee form data
        Employee employee = new Employee();
//        model.addAttribute("employee", employeeService.createEmployee(employee));
        model.addAttribute("employee", employee);
        return "createEmployee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee",employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute("employee") Employee employee,
                                 Model model) {

        // get employee by id
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setSalary(employee.getSalary());

        // save updated employee
        employeeService.updateEmployee(id,existingEmployee);
        return "redirect:/employees";
    }

    // handler method to handle delete request
    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
