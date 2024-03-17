package LoginApplication.CustomApplication.controller;


import LoginApplication.CustomApplication.entity.Employee;
import LoginApplication.CustomApplication.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list_employees")
    public String list_Employees (Model theModel){

        //get the employees from the db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees",theEmployees);

        return "list_employees";


    }
    @GetMapping("/employee_form")
    public String employee_form(Model theModel){

        //create model attributes to bind form data

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "employee_form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId, Model theModel){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        //set employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        // send ove to our form

        return "employee_form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);

        // save the redirect to prevent duplicate submission
        return "redirect:/list_employees";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){

        //delete the employee
        employeeService.deleteById(theId);

        // redirect to the /employee/list
        return "redirect:/list_employees";
    }
}

