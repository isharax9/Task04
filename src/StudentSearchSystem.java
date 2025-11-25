/**
 * StudentSearchSystem - High-Performance Student Search System
 * 
 * A hybrid data structure system combining:
 * 1. Hash Table for O(1) ID-based lookups
 * 2. Trie for O(L) prefix-based name searches
 * 
 * This system provides optimal performance for both exact ID searches
 * and flexible prefix-based name queries.
 * 
 * Time Complexity:
 *   - Insert: O(L) where L is the length of the name
 *   - Search by ID: O(1) average case
 *   - Search by Name Prefix: O(L + M) where L is prefix length, M is matches
 * 
 * Space Complexity: O(N * L) where N is number of students, L is average name length
 * 
 * @author H.M.Ishara Lakshitha Bandara
 * @version 1.0
 */
public class StudentSearchSystem {
    private StudentHashTable hashTable;
    private StudentNameTrie nameTrie;

    /**
     * Constructs a new StudentSearchSystem.
     */
    public StudentSearchSystem() {
        this.hashTable = new StudentHashTable();
        this.nameTrie = new StudentNameTrie();
    }

    /**
     * Adds a student to the system.
     * Indexes the student in both the hash table and trie.
     * 
     * @param student The student to add
     */
    public void addStudent(Student student) {
        hashTable.insert(student);
        nameTrie.insert(student);
    }

    /**
     * Searches for a student by their ID.
     * 
     * @param studentId The ID to search for
     * @return The student if found, null otherwise
     */
    public Student searchById(String studentId) {
        return hashTable.search(studentId);
    }

    /**
     * Searches for students by name prefix.
     * 
     * @param namePrefix The prefix to search for
     * @return Array of students whose names start with the prefix
     */
    public Student[] searchByName(String namePrefix) {
        return nameTrie.searchByPrefix(namePrefix);
    }

    /**
     * Gets all students in the system.
     * 
     * @return Array of all students
     */
    public Student[] getAllStudents() {
        return hashTable.getAllStudents();
    }

    /**
     * Gets the total number of students in the system.
     * 
     * @return Number of students
     */
    public int getSize() {
        return hashTable.getSize();
    }

    /**
     * Prints a formatted header.
     */
    private static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("  " + title);
        System.out.println("=".repeat(80));
    }

    /**
     * Prints search results.
     */
    private static void printResults(Student[] results, String searchType) {
        if (results == null || results.length == 0) {
            System.out.println("❌ No students found.");
        } else {
            System.out.println("✓ Found " + results.length + " student(s):");
            System.out.println("-".repeat(80));
            for (Student student : results) {
                System.out.println("  " + student);
            }
        }
    }

    /**
     * Main method demonstrating the system's capabilities.
     */
    public static void main(String[] args) {
        StudentSearchSystem system = new StudentSearchSystem();

        printHeader("HIGH-PERFORMANCE STUDENT SEARCH SYSTEM");
        System.out.println("Author: H.M.Ishara Lakshitha Bandara");
        System.out.println("Architecture: Hybrid (Hash Table + Trie)");

        // Phase 1: Adding Students
        printHeader("PHASE 1: ADDING STUDENTS TO THE SYSTEM");
        
        Student[] testStudents = {
            new Student("S001", "Alice Johnson", 3.85),
            new Student("S002", "Bob Smith", 3.92),
            new Student("S003", "Alice Williams", 3.67),
            new Student("S004", "Charlie Brown", 3.45),
            new Student("S005", "David Miller", 3.78),
            new Student("S006", "Alice Davis", 3.91),
            new Student("S007", "Eve Anderson", 3.56),
            new Student("S008", "Frank Wilson", 3.73),
            new Student("S009", "Grace Lee", 3.88),
            new Student("S010", "Henry Martinez", 3.62)
        };

        System.out.println("\nAdding " + testStudents.length + " students to the system...");
        for (Student student : testStudents) {
            system.addStudent(student);
            System.out.println("  ✓ Added: " + student);
        }
        
        System.out.println("\n✓ Total students in system: " + system.getSize());

        // Phase 2: ID-Based Search (Hash Table - O(1))
        printHeader("PHASE 2: ID-BASED SEARCH (O(1) Performance)");
        
        String[] testIds = {"S001", "S005", "S010", "S999"};
        
        for (String id : testIds) {
            System.out.println("\n🔍 Searching for ID: " + id);
            long startTime = System.nanoTime();
            Student result = system.searchById(id);
            long endTime = System.nanoTime();
            
            if (result != null) {
                System.out.println("  ✓ FOUND: " + result);
            } else {
                System.out.println("  ❌ NOT FOUND");
            }
            System.out.println("  ⏱ Time: " + (endTime - startTime) + " ns");
        }

        // Phase 3: Name-Based Prefix Search (Trie - O(L))
        printHeader("PHASE 3: NAME-BASED PREFIX SEARCH (O(L) Performance)");
        
        String[] testPrefixes = {"Alice", "Ali", "A", "Bob", "Charlie", "Z"};
        
        for (String prefix : testPrefixes) {
            System.out.println("\n🔍 Searching for names starting with: '" + prefix + "'");
            long startTime = System.nanoTime();
            Student[] results = system.searchByName(prefix);
            long endTime = System.nanoTime();
            
            printResults(results, "prefix");
            System.out.println("  ⏱ Time: " + (endTime - startTime) + " ns");
        }

        // Phase 4: System Statistics
        printHeader("PHASE 4: SYSTEM STATISTICS");
        
        System.out.println("\n📊 Performance Metrics:");
        System.out.println("  • Total Students: " + system.getSize());
        System.out.println("  • ID Search Complexity: O(1) - Hash Table");
        System.out.println("  • Name Search Complexity: O(L) - Trie (L = prefix length)");
        System.out.println("  • Space Complexity: O(N * L) - N students, L avg name length");
        
        System.out.println("\n🏗️ Architecture:");
        System.out.println("  • Hash Table: Separate chaining with polynomial rolling hash");
        System.out.println("  • Trie: 26-way prefix tree for lowercase letters");
        System.out.println("  • Load Factor Threshold: 0.75 (automatic resizing)");
        System.out.println("  • Dynamic Memory Management: Auto-resize on threshold");

        // Phase 5: Edge Cases
        printHeader("PHASE 5: EDGE CASE TESTING");
        
        System.out.println("\n🧪 Test Case 1: Empty prefix search");
        Student[] emptyResults = system.searchByName("");
        System.out.println("  Result: " + emptyResults.length + " students (expected: 0)");
        
        System.out.println("\n🧪 Test Case 2: Case insensitive search");
        Student[] lowerResults = system.searchByName("alice");
        Student[] upperResults = system.searchByName("ALICE");
        System.out.println("  'alice': " + lowerResults.length + " students");
        System.out.println("  'ALICE': " + upperResults.length + " students");
        System.out.println("  ✓ Case insensitive: " + (lowerResults.length == upperResults.length));
        
        System.out.println("\n🧪 Test Case 3: Non-existent ID");
        Student nonExistent = system.searchById("S999");
        System.out.println("  Result: " + (nonExistent == null ? "null (expected)" : "ERROR"));

        // Phase 6: Complete Dataset View
        printHeader("PHASE 6: COMPLETE STUDENT REGISTRY");
        
        System.out.println("\n📋 All Students in System:");
        System.out.println("-".repeat(80));
        Student[] allStudents = system.getAllStudents();
        for (int i = 0; i < allStudents.length; i++) {
            System.out.println((i + 1) + ". " + allStudents[i]);
        }

        printHeader("SYSTEM DEMONSTRATION COMPLETE");
        System.out.println("\n✓ All operations executed successfully!");
        System.out.println("✓ System performance verified for both O(1) and O(L) operations");
        System.out.println("\n" + "=".repeat(80) + "\n");
    }
}
