/**
 * Student Class - Core Data Model
 * 
 * Represents a student entity with ID, name, and GPA.
 * This immutable class ensures data integrity throughout the system.
 * 
 * Time Complexity:
 *   - Constructor: O(1)
 *   - All getters: O(1)
 *   - toString: O(1)
 * 
 * Space Complexity: O(1) - Fixed size object
 * 
 * @author H.M.Ishara Lakshitha Bandara
 * @version 1.0
 */
public class Student {
    private final String studentId;
    private final String name;
    private final double gpa;

    /**
     * Constructs a new Student object.
     * 
     * @param studentId Unique identifier for the student
     * @param name Full name of the student
     * @param gpa Grade Point Average (0.0 - 4.0)
     */
    public Student(String studentId, String name, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
    }

    /**
     * Gets the student ID.
     * 
     * @return Student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student name.
     * 
     * @return Student name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student GPA.
     * 
     * @return Student GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Returns a string representation of the student.
     * 
     * @return Formatted student information
     */
    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-30s | GPA: %.2f", 
                             studentId, name, gpa);
    }
}
