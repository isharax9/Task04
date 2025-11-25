/**
 * TrieNode - Node structure for the Trie data structure
 * 
 * Each node represents a character in the student name and maintains:
 * - Children nodes (up to 26 for lowercase letters)
 * - List of students whose names have this prefix
 * - End of word marker
 * 
 * Space Complexity: O(ALPHABET_SIZE) per node
 * 
 * @author H.M.Ishara Lakshitha Bandara
 * @version 1.0
 */
public class TrieNode {
    private static final int ALPHABET_SIZE = 26;
    
    private TrieNode[] children;
    private StudentList students;
    private boolean isEndOfWord;

    /**
     * Simple linked list to store students at each node.
     */
    public static class StudentList {
        private StudentNode head;
        private int size;

        private static class StudentNode {
            Student student;
            StudentNode next;

            StudentNode(Student student) {
                this.student = student;
                this.next = null;
            }
        }

        public StudentList() {
            this.head = null;
            this.size = 0;
        }

        /**
         * Adds a student to the list.
         * 
         * @param student Student to add
         */
        public void add(Student student) {
            StudentNode newNode = new StudentNode(student);
            if (head == null) {
                head = newNode;
            } else {
                StudentNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        /**
         * Converts the list to an array.
         * 
         * @return Array of students
         */
        public Student[] toArray() {
            Student[] result = new Student[size];
            StudentNode current = head;
            int index = 0;
            
            while (current != null) {
                result[index++] = current.student;
                current = current.next;
            }
            
            return result;
        }

        /**
         * Gets the size of the list.
         * 
         * @return Number of students
         */
        public int getSize() {
            return size;
        }
    }

    /**
     * Constructs a new TrieNode.
     */
    public TrieNode() {
        this.children = new TrieNode[ALPHABET_SIZE];
        this.students = new StudentList();
        this.isEndOfWord = false;
    }

    /**
     * Gets the child node for a given character.
     * 
     * @param c The character
     * @return The child node, or null if doesn't exist
     */
    public TrieNode getChild(char c) {
        if (c < 'a' || c > 'z') {
            return null;
        }
        return children[c - 'a'];
    }

    /**
     * Sets the child node for a given character.
     * 
     * @param c The character
     * @param node The node to set
     */
    public void setChild(char c, TrieNode node) {
        if (c >= 'a' && c <= 'z') {
            children[c - 'a'] = node;
        }
    }

    /**
     * Gets the list of students at this node.
     * 
     * @return StudentList containing all students with this prefix
     */
    public StudentList getStudents() {
        return students;
    }

    /**
     * Adds a student to this node's list.
     * 
     * @param student Student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Checks if this node marks the end of a word.
     * 
     * @return true if end of word, false otherwise
     */
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    /**
     * Sets the end of word marker.
     * 
     * @param endOfWord true to mark as end of word
     */
    public void setEndOfWord(boolean endOfWord) {
        this.isEndOfWord = endOfWord;
    }
}
