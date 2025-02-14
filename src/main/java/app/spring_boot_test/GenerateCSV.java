package app.spring_boot_test;

import java.util.Map;
import java.util.stream.Collectors;

public class GenerateCSV {
    public static String mapToString(Map<Long, Employee> employees) {
        return "ID,Name,Surname,Salary,BirthYear,Condition,Group ID\n" + employees.values().stream()
                .map(emp -> emp.getId() + ","
                        + emp.getName() + ","
                        + emp.getSurname() + ","
                        + emp.getSalary() + ","
                        + emp.getBirthYear() + ","
                        + emp.getCondition() + ","
                        + emp.getGroupId())
                .collect(Collectors.joining("\n"));
    }
}
