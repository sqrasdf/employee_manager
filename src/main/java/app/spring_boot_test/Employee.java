package app.spring_boot_test;

class Employee {
    static Long employeeCounter = 0L;
    private Long id;
    private String name;
    private String surname;
    private double salary;
    private int birthYear;
    private EmployeeCondition condition;
    private Long groupId;

    public Employee() {
        this.id = employeeCounter++;
    }

    public Employee(String name, String surname, double salary, int birthYear, EmployeeCondition condition, Long groupId) {
        this.id = employeeCounter++;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.birthYear = birthYear;
        this.condition = condition;
        this.groupId = groupId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public int getBirthYear() { return birthYear; }

    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public EmployeeCondition getCondition() { return condition; }

    public void setCondition(EmployeeCondition condition) { this.condition = condition; }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}

