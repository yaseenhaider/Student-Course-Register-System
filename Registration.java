import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class Course {
    private String courseID;
    private String courseName;
    private String instructorName;

    public Course(String courseID, String courseName, String instructorName) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }


    @Override
    public String toString() {
        return courseID + " - " + courseName;
    }
}


class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses = new ArrayList<>();

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }
                    // methods
    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
        }
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        return studentID + " - " + name;
    }
}


class University {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }
          //aray of Student

    public ArrayList<Student> getStudents() {
        return students;
    }

         //aray for course register

    public ArrayList<Course> getCourses() {
        return courses;
    }
}

         //Main
 class CourseRegistration extends JFrame {

    University university = new University();

    JComboBox<Student> studentComboBox = new JComboBox<>();
    JComboBox<Course> courseComboBox = new JComboBox<>();
    JTextArea displayArea = new JTextArea(10, 40);

    public CourseRegistration() {
        setTitle("Student Course Registration System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new FlowLayout());

        JButton addStudentBtn = new JButton("Add Student");

        JButton addCourseBtn = new JButton("Add Course");

        JButton registerCourseBtn = new JButton("Register Course");

        JButton dropCourseBtn = new JButton("Drop Course");

        JButton viewCoursesBtn = new JButton("View Registered Courses");


        displayArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(new JLabel("Select Student:"));
        add(studentComboBox);
        add(new JLabel("Select Course:"));
        add(courseComboBox);

                 //add buttons
        add(addStudentBtn);
        add(addCourseBtn);
        add(registerCourseBtn);
        add(dropCourseBtn);
        add(viewCoursesBtn);
        add(scrollPane);



        addStudentBtn.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            Object[] fields = {
                    "Student ID:", idField,
                    "Student Name:", nameField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add Student",

                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {


                Student s = new Student(idField.getText(), nameField.getText());
                university.addStudent(s);
                studentComboBox.addItem(s);
                JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            }
        });

                             // Action
        addCourseBtn.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField instructorField = new JTextField();
            Object[] fields = {
                    "Course ID:", idField,
                    "Course Name:", nameField,
                    "Instructor Name:", instructorField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add Course", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Course c = new Course(idField.getText(), nameField.getText(), instructorField.getText());
                university.addCourse(c);
                courseComboBox.addItem(c);
                JOptionPane.showMessageDialog(this, "Course Added Successfully!");
            }
        });

                                   // Action
        registerCourseBtn.addActionListener(e -> {
            Student s = (Student) studentComboBox.getSelectedItem();
            Course c = (Course) courseComboBox.getSelectedItem();
            if (s != null && c != null) {
                s.registerCourse(c);
                JOptionPane.showMessageDialog(this, "Course Registered!");
            }
        });

                                 // Action
        dropCourseBtn.addActionListener(e -> {
            Student s = (Student) studentComboBox.getSelectedItem();
            Course c = (Course) courseComboBox.getSelectedItem();
            if (s != null && c != null) {
                s.dropCourse(c);
                JOptionPane.showMessageDialog(this, "Course Dropped!");
            }
        });


        viewCoursesBtn.addActionListener(e -> {
            Student s = (Student) studentComboBox.getSelectedItem();
            if (s != null)
            {

                displayArea.setText("Courses for " + s.getName() + ":\n");
                for (Course c : s.getRegisteredCourses()) {
                    displayArea.append(c.toString() + " (Instructor: " + c.getInstructorName() + ")\n");

                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CourseRegistration();
    }
}
