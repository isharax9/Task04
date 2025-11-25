/**
 * StudentHashTable - Custom Hash Table Implementation
 * 
 * A specialized hash table for storing students indexed by their ID.
 * Uses separate chaining for collision resolution with linked lists.
 * 
 * Time Complexity:
 *   - insert(Student): O(1) average case
 *   - search(String): O(1) average case
 *   - Overall: O(1) for both operations in expected case
 * 
 * Space Complexity: O(n) where n is the number of students
 * 
 * @author H.M.Ishara Lakshitha Bandara
 * @version 1.0
 */
public class StudentHashTable {
    private static final int DEFAULT_CAPACITY = 100;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    
    private Node[] table;
    private int size;
    private int capacity;

    /**
     * Node class for separate chaining.
     */
    private static class Node {
        String key;
        Student value;
        Node next;

        Node(String key, Student value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Constructs a new hash table with default capacity.
     */
    public StudentHashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[capacity];
        this.size = 0;
    }

    /**
     * Computes hash code for a given student ID.
     * Uses polynomial rolling hash to minimize collisions.
     * 
     * @param studentId The student ID to hash
     * @return Hash value within table bounds
     */
    private int hash(String studentId) {
        int hash = 0;
        int prime = 31;
        
        for (int i = 0; i < studentId.length(); i++) {
            hash = (hash * prime + studentId.charAt(i)) % capacity;
        }
        
        return Math.abs(hash);
    }

    /**
     * Inserts a student into the hash table.
     * 
     * @param student The student to insert
     */
    public void insert(Student student) {
        // Check load factor and resize if necessary
        if ((double) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        String studentId = student.getStudentId();
        int index = hash(studentId);

        // Check if student already exists (update)
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(studentId)) {
                current.value = student; // Update existing
                return;
            }
            current = current.next;
        }

        // Insert new node at the beginning of the chain
        Node newNode = new Node(studentId, student);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }

    /**
     * Searches for a student by ID.
     * 
     * @param studentId The ID to search for
     * @return The student if found, null otherwise
     */
    public Student search(String studentId) {
        int index = hash(studentId);
        Node current = table[index];

        while (current != null) {
            if (current.key.equals(studentId)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Not found
    }

    /**
     * Resizes the hash table when load factor exceeds threshold.
     * Rehashes all existing entries to maintain O(1) performance.
     */
    private void resize() {
        int oldCapacity = capacity;
        Node[] oldTable = table;

        // Double the capacity
        capacity = capacity * 2;
        table = new Node[capacity];
        size = 0;

        // Rehash all existing entries
        for (int i = 0; i < oldCapacity; i++) {
            Node current = oldTable[i];
            while (current != null) {
                insert(current.value);
                current = current.next;
            }
        }
    }

    /**
     * Gets the current number of students in the hash table.
     * 
     * @return Number of students
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets all students in the hash table.
     * 
     * @return Array of all students
     */
    public Student[] getAllStudents() {
        Student[] students = new Student[size];
        int index = 0;

        for (int i = 0; i < capacity; i++) {
            Node current = table[i];
            while (current != null) {
                students[index++] = current.value;
                current = current.next;
            }
        }

        return students;
    }
}
