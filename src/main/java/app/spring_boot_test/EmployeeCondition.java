package app.spring_boot_test;

public enum EmployeeCondition {
    present("obecny"),
    delegation("w delegacji"),
    sick("chory"),
    absent("nieobecny");

    private final String condition;

    EmployeeCondition(String s) {
        condition = s;
    }

    public String getCondition() {
        return condition;
    }
}
