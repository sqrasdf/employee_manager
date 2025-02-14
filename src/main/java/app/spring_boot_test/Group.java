package app.spring_boot_test;

import java.util.ArrayList;
import java.util.List;

class Group {
    static Long groupCounter = 0L;
    private Long id;
    private String name;
    private int capacity;
    private String fullness;
    private double averageRating;
    private final List<Employee> employees = new ArrayList<>();
    private final List<Rating> ratings = new ArrayList<>();

    public Group() {
        this.id = groupCounter++;
    }

    public Group(String name, int capacity) {
        this.id = groupCounter++;
        this.name = name;
        this.capacity = capacity;
        this.fullness = "0%";
        this.averageRating = 0;
    }

    public Long getId() { return id; }

//    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getFullness() { return fullness; }

    public void setFullness(String fullness) { this.fullness = fullness; }

    public void calculateRating() {
        int ratingSum = 0;
        for (Rating rating : ratings) {
            ratingSum += rating.getScore();
        }
        double averageRating = (double) ratingSum / ratings.size();
        this.averageRating = (int) averageRating;
    }

    public List<Rating> getRatings() { return this.ratings; }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
        calculateRating();
    }

    public double getAverageRating() { return averageRating; }

    public void calculateFullness() { this.fullness = ((double) employees.size() / capacity) * 100 + "%"; }

    public List<Employee> getEmployees() { return this.employees; }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        calculateFullness();
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        calculateFullness();
    }

}
