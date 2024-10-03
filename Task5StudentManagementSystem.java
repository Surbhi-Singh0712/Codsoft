package ProjectCodsoft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Task5StudentManagementSystem {
    public static void main(String[] args){
        // Create an instance of the StudentManagementSystemUsingSwing class
        StudentManagementSystemUsingSwing student = new StudentManagementSystemUsingSwing();
    }
}
class StudentManagementSystemUsingSwing extends JFrame {
    private JFrame frame;
    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JButton addButton;
    private JButton editButton;
    private JButton searchButton;
    private JButton displayButton;
    private JButton exitButton;
    private JButton removeButton;
    private StudentManagementSystem studentManagementSystem;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$");

    StudentManagementSystemUsingSwing() {
        studentManagementSystem = new StudentManagementSystem();
        create();
    }

    // Method to validate email format using regex
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Method to validate phone number format (only digits and 10 digits long)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");  // Ensure the phone number is exactly 10 digits
    }

    public void create() {
        frame = new JFrame("Student Management System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(500, 600);
        // Create panels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create input fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField(20);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberField = new JTextField(20);

        // Add input fields to input panel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);

        // Create buttons
        addButton = new JButton("Add Student");
        addButton.addActionListener(new AddButtonListener());

        editButton = new JButton("Edit Student");
        editButton.addActionListener(new EditButtonListener());

        removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new RemoveButtonListener());

        searchButton = new JButton("Search Student");
        searchButton.addActionListener(new SearchButtonListener());

        displayButton = new JButton("Display Students");
        displayButton.addActionListener(new DisplayButtonListener());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());

        // Add buttons to button panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(exitButton);


        // Add panels to frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);




    }

    private class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String rollNumberStr = rollNumberField.getText();
            String grade = gradeField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            // Validate roll number as an integer
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(rollNumberStr);  // Ensure roll number is an integer
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Roll Number. Please enter a valid integer.");
                return;
            }

            // Validate email
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(frame, "Invalid Email. Please enter a valid email address.");
                return;
            }

            // Validate phone number
            if (!isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(frame, "Invalid Phone Number. Please enter a valid 10-digit phone number.");
                return;
            }

            Student student = new Student(name, rollNumber, grade, email, phoneNumber);
            studentManagementSystem.addStudent(student);

            nameField.setText("");
            rollNumberField.setText("");
            gradeField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
        }
    }

    private class EditButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Implement edit student functionality
            String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number to Edit:");
            if (rollNumberStr != null) {
                int rollNumber;
                try{
                    rollNumber= Integer.parseInt(rollNumberStr);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Invalid roll Number. Please enter a valid integer.");
                    return;
                }

                Student student = studentManagementSystem.searchStudent(rollNumber);
                if (student != null) {
                    String name = JOptionPane.showInputDialog(frame, "Enter New Name:");
                    String grade = JOptionPane.showInputDialog(frame, "Enter New Grade:");
                    String email = JOptionPane.showInputDialog(frame, "Enter New Email:");
                    String phoneNumber = JOptionPane.showInputDialog(frame, "Enter New Phone Number:");

                    if (name != null && grade != null && email != null && phoneNumber != null) {
                        student.setName(name);
                        student.setGrade(grade);
                        student.setEmail(email);
                        student.setPhoneNumber(phoneNumber);
                        JOptionPane.showMessageDialog(frame, "Student Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter all fields");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Student Not Found");
                }
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {

        // actionPerformed method
        public void actionPerformed(ActionEvent e) {
            // Get the roll number from the user
            String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number to Remove:");
            if (rollNumberStr != null) {
            int rollNumber;
            try{
                rollNumber=Integer.parseInt(rollNumberStr);
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame,"Invalid roll number! Please Enter a valid integer");
                return;
            }
                studentManagementSystem.removeStudent(rollNumber);
                JOptionPane.showMessageDialog(frame, "Student Removed Successfully");
            }
        }
    }

        private class SearchButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number to Search:");
            if (rollNumberStr != null) {
                int rollNumber;
                try{
                    rollNumber=Integer.parseInt(rollNumberStr);
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame,"Invalid roll number! Please Enter a valid integer");
                    return;
                }
                Student student = studentManagementSystem.searchStudent(rollNumber);
                if (student !=null) {
                    JOptionPane.showMessageDialog(frame, "Student Found:\n" + student.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Student Not Found");
                }
            }
        }
    }


    private class DisplayButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            StringBuilder studentList = new StringBuilder();
            for (Student student : studentManagementSystem.getStudents()) {
                studentList.append(student.toString()).append("\n");
            }
            if (studentList.length() > 0) {
                JOptionPane.showMessageDialog(frame, studentList.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "No students found");
            }
        }
    }

    private class ExitButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}



class Student{
    private String name;
    private int rollNumber;
    private String grade;
    private String email;
    private String phoneNumber;
    public Student(String name,int rollNumber,String grade,String email,String phoneNumber){
        this.name=name;
        this.rollNumber=rollNumber;
        this.grade=grade;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}


class StudentManagementSystem{
    private List<Student> students;
    public StudentManagementSystem(){
        this.students=new ArrayList<>();
    }
    //add student
    public void addStudent(Student student){
        students.add(student);
    }

    //remove student
    public void removeStudent(int rollNumber){
        students.removeIf(student -> student.getRollNumber()==rollNumber);
    }
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) return student;
        }
        return null;
    }

    public List<Student> getStudents() {
        return students;
    }

}
