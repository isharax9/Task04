/**
 * StudentNameTrie - Trie Data Structure for Name-Based Searches
 * 
 * A specialized Trie (prefix tree) for efficient prefix-based student name searches.
 * Stores student names in a tree structure where each path represents a name.
 * 
 * Time Complexity:
 *   - insert(Student): O(L) where L is the length of the name
 *   - searchByPrefix(String): O(L + M) where L is prefix length, M is number of matches
 *   - Overall: O(L) for search operations
 * 
 * Space Complexity: O(N * L) where N is number of students, L is average name length
 * 
 * @author H.M.Ishara Lakshitha Bandara
 * @version 1.0
 */
public class StudentNameTrie {
    private TrieNode root;

    /**
     * Constructs a new empty Trie.
     */
    public StudentNameTrie() {
        this.root = new TrieNode();
    }

    /**
     * Normalizes a string by converting to lowercase and removing spaces.
     * 
     * @param str The string to normalize
     * @return Normalized string
     */
    private String normalize(String str) {
        return str.toLowerCase().replaceAll("\\s+", "");
    }

    /**
     * Inserts a student into the Trie using their name.
     * 
     * @param student The student to insert
     */
    public void insert(Student student) {
        String name = normalize(student.getName());
        TrieNode current = root;

        // Traverse/create path for each character
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            
            // Skip non-alphabetic characters
            if (c < 'a' || c > 'z') {
                continue;
            }

            TrieNode child = current.getChild(c);
            if (child == null) {
                child = new TrieNode();
                current.setChild(c, child);
            }
            
            // Add student to every node in the path (for prefix matching)
            child.addStudent(student);
            current = child;
        }

        current.setEndOfWord(true);
    }

    /**
     * Searches for all students whose names start with the given prefix.
     * 
     * @param prefix The prefix to search for
     * @return Array of students matching the prefix, or empty array if none found
     */
    public Student[] searchByPrefix(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            return new Student[0];
        }

        String normalizedPrefix = normalize(prefix);
        TrieNode current = root;

        // Navigate to the end of the prefix
        for (int i = 0; i < normalizedPrefix.length(); i++) {
            char c = normalizedPrefix.charAt(i);
            
            // Skip non-alphabetic characters
            if (c < 'a' || c > 'z') {
                continue;
            }

            current = current.getChild(c);
            if (current == null) {
                return new Student[0]; // Prefix not found
            }
        }

        // Return all students at this node (all have this prefix)
        return current.getStudents().toArray();
    }

    /**
     * Searches for students with exact name match.
     * 
     * @param name The exact name to search for
     * @return Array of students with matching name
     */
    public Student[] searchByExactName(String name) {
        Student[] prefixMatches = searchByPrefix(name);
        
        // Filter to exact matches only
        String normalizedName = normalize(name);
        int exactMatchCount = 0;
        
        // Count exact matches
        for (Student student : prefixMatches) {
            if (normalize(student.getName()).equals(normalizedName)) {
                exactMatchCount++;
            }
        }
        
        // Create result array with exact matches
        Student[] exactMatches = new Student[exactMatchCount];
        int index = 0;
        
        for (Student student : prefixMatches) {
            if (normalize(student.getName()).equals(normalizedName)) {
                exactMatches[index++] = student;
            }
        }
        
        return exactMatches;
    }

    /**
     * Checks if the Trie contains any students.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root.getStudents().getSize() == 0;
    }
}
