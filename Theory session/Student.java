import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    String firstName;
    String lastName;
    int registration;
    int grade;
    int year;

    // Constructors
    public Student(String firstName, String lastName, int registration, int grade, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.grade = grade;
        this.year = year;
    }

    public Student(String firstName, String lastName, int registration) {
        this(firstName, lastName, registration, 0, 1);
    }

    public Student(String firstName, String lastName) {
        this(firstName, lastName, 0, 0, 1);
    }

    // Methods
    public void printFullName() {
        System.out.println(firstName + " " + lastName);
    }

    public boolean isApproved() {
        return grade >= 60;
    }

    public int changeYearIfApproved() {
        if (isApproved()) {
            year++;
            System.out.println("Congratulations!");
        }
        return year;
    }
}

 class Course {
    String courseName;
    String professorName;
    int year;
    List<Student> enrolledStudents;

    // Constructor
    public Course(String courseName, String professorName, int year) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.year = year;
        this.enrolledStudents = new ArrayList<>();
    }

    // Methods
    public void enroll(Student student) {
        enrolledStudents.add(student);
    }

    public void unEnroll(Student student) {
        enrolledStudents.remove(student);
    }

    public int countStudents() {
        return enrolledStudents.size();
    }

    public int bestGrade() {
        int maxGrade = Integer.MIN_VALUE;
        for (Student student : enrolledStudents) {
            maxGrade = Math.max(maxGrade, student.grade);
        }
        return maxGrade;
    }

    public void enroll(Student[] students) {
        enrolledStudents.addAll(Arrays.asList(students));
    }

    public double calculateAverageGrade() {
        if (enrolledStudents.isEmpty()) return 0;

        int totalGrade = 0;
        for (Student student : enrolledStudents) {
            totalGrade += student.grade;
        }
        return (double) totalGrade / enrolledStudents.size();
    }

    public void displayRanking() {
        enrolledStudents.sort((s1, s2) -> Integer.compare(s2.grade, s1.grade));

        System.out.println("Ranking:");
        for (int i = 0; i < enrolledStudents.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledStudents.get(i).firstName + " " + enrolledStudents.get(i).lastName +
                    " - Grade: " + enrolledStudents.get(i).grade);
        }
    }

    public void displayAboveAverageStatus() {
        double averageGrade = calculateAverageGrade();
        System.out.println("Above Average Status:");
        for (Student student : enrolledStudents) {
            System.out.println(student.firstName + " " + student.lastName +
                    " - Above Average: " + (student.grade > averageGrade));
        }
    }

    public static void main(String[] args) {
        // Test the implementation
        Student student1 = new Student("John", "Doe", 123, 75, 2);
        Student student2 = new Student("Jane", "Smith", 456, 90, 1);
        Student student3 = new Student("Bob", "Johnson", 789, 55, 3);

        Course course = new Course("Java Programming", "Prof. Anderson", 2023);

        // Enroll individual students
        course.enroll(student1);
        course.enroll(student2);

        // Enroll array of students
        Student[] moreStudents = {student3};
        course.enroll(moreStudents);

        // Test other methods
        System.out.println("Total students: " + course.countStudents());
        System.out.println("Best grade: " + course.bestGrade());

        // Display ranking and above-average status
        course.displayRanking();
        course.displayAboveAverageStatus();
    }
}
