import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task5 extends JFrame {
    private JTextField nameField, rollNumberField, gradeField, searchField;
    private JTextArea outputArea;
    private java.util.List<Student> students;

    public Task5() {
        students = new java.util.ArrayList<>();

        // Frame setup
        setTitle("Student Management System");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Components
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField(10);
        inputPanel.add(rollNumberField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        inputPanel.add(gradeField);

        inputPanel.add(new JLabel("Search Roll Number:"));
        searchField = new JTextField(10);
        inputPanel.add(searchField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton displayButton = new JButton("Display Students");
        JButton searchButton = new JButton("Search Student");

        // Add components to panels
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);

        // TextArea for displaying students
        outputArea = new JTextArea(15, 35);
        outputArea.setEditable(false);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Event listeners
        addButton.addActionListener(e -> addStudent());
        displayButton.addActionListener(e -> displayStudents());
        searchButton.addActionListener(e -> searchStudent());
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumber = rollNumberField.getText();
        String grade = gradeField.getText();

        if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
            Student student = new Student(name, rollNumber, grade);
            students.add(student);
            clearFields();
            displayStudents();
        } else {
            outputArea.setText("Please fill in all fields.");
        }
    }

    private void displayStudents() {
        outputArea.setText("Students:\n");
        for (Student student : students) {
            outputArea.append(student.toString() + "\n");
        }
    }

    private void searchStudent() {
        String searchRollNumber = searchField.getText();

        if (!searchRollNumber.isEmpty()) {
            boolean found = false;
            for (Student student : students) {
                if (student.getRollNumber().equals(searchRollNumber)) {
                    outputArea.setText("Student found:\n" + student.toString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputArea.setText("Student with Roll Number " + searchRollNumber + " not found.");
            }
        } else {
            outputArea.setText("Please enter a Roll Number for search.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
        searchField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task5 sms = new Task5();
            sms.setVisible(true);
        });
    }
}

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
