package app.spring_boot_test;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
class ApiController {
    private final Map<Long, Employee> employees = new HashMap<>();
    private final Map<Long, Group> groups = new HashMap<>();

    public ApiController() {
        // IT
        Group itGroup = new Group("IT", 10);
        Employee employee1 = new Employee("Jan", "Kowalski", 3500.00, 1990, EmployeeCondition.present, itGroup.getId());
        Employee employee2 = new Employee("Anna", "Nowak", 4500.00, 1985, EmployeeCondition.present, itGroup.getId());
        Employee employee3 = new Employee("Piotr", "Wiśniewski", 5000.00, 1982, EmployeeCondition.delegation, itGroup.getId());

        itGroup.addEmployee(employee1);
        itGroup.addEmployee(employee2);
        itGroup.addEmployee(employee3);
        employees.put(employee1.getId(), employee1);
        employees.put(employee2.getId(), employee2);
        employees.put(employee3.getId(), employee3);
        groups.put(itGroup.getId    (), itGroup);

        // Marketing
        Group marketingGroup = new Group("Marketing", 10);
        Employee employee4 = new Employee("Katarzyna", "Wójcik", 3200.00, 1992, EmployeeCondition.sick, marketingGroup.getId());
        Employee employee5 = new Employee("Paweł", "Kozłowski", 3800.00, 1988, EmployeeCondition.present, marketingGroup.getId());

        marketingGroup.addEmployee(employee4);
        marketingGroup.addEmployee(employee5);
        employees.put(employee4.getId(), employee4);
        employees.put(employee5.getId(), employee5);
        groups.put(marketingGroup.getId(), marketingGroup);

        // HR
        Group hrGroup = new Group("HR", 5);
        Employee employee6 = new Employee("Magdalena", "Wiśniewska", 4600.00, 1983, EmployeeCondition.present, hrGroup.getId());
        Employee employee7 = new Employee("Adam", "Nowakowski", 3900.00, 1986, EmployeeCondition.present, hrGroup.getId());
        Employee employee8 = new Employee("Marek", "Kowalczyk", 4300.00, 1987, EmployeeCondition.present, hrGroup.getId());
        Employee employee9 = new Employee("Barbara", "Jankowska", 4700.00, 1984, EmployeeCondition.absent, hrGroup.getId());

        hrGroup.addEmployee(employee6);
        hrGroup.addEmployee(employee7);
        hrGroup.addEmployee(employee8);
        hrGroup.addEmployee(employee9);
        employees.put(employee6.getId(), employee6);
        employees.put(employee7.getId(), employee7);
        employees.put(employee8.getId(), employee8);
        employees.put(employee9.getId(), employee9);
        groups.put(hrGroup.getId(), hrGroup);

    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employees.put(employee.getId(), employee);
        try {
            groups.get(employee.getGroupId()).addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee); // 201 CREATED
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            groups.get(employees.get(id).getGroupId()).removeEmployee(employees.get(id));
            employees.remove(id);
            return ResponseEntity.noContent().build(); // 204 NO CONTENT
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 NOT FOUND
        }
    }

    @GetMapping(value = "/employee/csv", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAllEmployeesCsv() {
        String csvData = GenerateCSV.mapToString(employees);
        return ResponseEntity.ok(csvData);
    }

    @GetMapping("/group")
    public ResponseEntity<Map<Long, Group>> getAllGroups() {
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/group")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        try {
            groups.put(group.getId(), group);
            return ResponseEntity.status(HttpStatus.CREATED).body(group); // 201
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        try {
            employees.values().removeIf(employee -> employee.getGroupId().equals(id));
            groups.remove(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @GetMapping("/group/{id}/employee")
    public ResponseEntity<List<Employee>> getEmployeesByGroup(@PathVariable Long id) {
        Group group = groups.get(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group.getEmployees());
    }

    @GetMapping("/group/{id}/fill")
    public ResponseEntity<String> getGroupFillPercentage(@PathVariable Long id) {
        Group group = groups.get(id);
        if (group == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group of id " + id + " not found");
        }
        return ResponseEntity.ok(group.getFullness());
    }

    @PostMapping("/rating")
    public ResponseEntity<String> addGroupRating(@RequestBody Rating rating) {
        try {
            Group group = groups.get(rating.getGroupId());
            group.addRating(rating);
            return ResponseEntity.ok("Rating added."); // 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
