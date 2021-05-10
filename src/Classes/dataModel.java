/*
package Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class dataModel {
    private SimpleIntegerProperty studentId, score, courseId;
    private SimpleStringProperty name;
    private SimpleStringProperty courseName;

    public dataModel(Integer studentId, String name,Integer courseId, String courseName, Integer score) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.courseId = new SimpleIntegerProperty(courseId);
        this.score = new SimpleIntegerProperty(score);
        this.name = new SimpleStringProperty(name);
        this.courseName = new SimpleStringProperty(courseName);
    }


    public dataModel() {
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score  = new SimpleIntegerProperty(score);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public int getCourseId() {
        return courseId.get();
    }

    public void setCourseId(int courseId) {
        this.courseId  = new SimpleIntegerProperty(courseId);
    }

    public void setStudentId(int studentId) {
        this.studentId  = new SimpleIntegerProperty(studentId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName = new SimpleStringProperty(courseName);
    }

}
*/
package Classes;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class dataModel {
    private SimpleIntegerProperty studentId, courseId;
    private SimpleFloatProperty gpa;

    private SimpleStringProperty name;
    private SimpleStringProperty lastName;
    private SimpleStringProperty regDate;
    private SimpleStringProperty firstName;
    private SimpleStringProperty password;
    private SimpleStringProperty course_title;
    private SimpleStringProperty teacherName;


    private SimpleIntegerProperty course_id;

    public int getTeacher_id() {
        return teacher_id.get();
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id =  new SimpleIntegerProperty(teacher_id);
    }

    private SimpleIntegerProperty teacher_id;


    public dataModel() {
    }



    public dataModel(Integer studentId, String firstName, String lastName, Float gpa, String regDate, String password) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.gpa = new SimpleFloatProperty(gpa);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.regDate = new SimpleStringProperty(regDate);
        this.password = new SimpleStringProperty(password);
    }





    public String getCourse_title() {
        return course_title.get();
    }

    public SimpleStringProperty course_titleProperty() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title= new SimpleStringProperty(course_title);;
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public SimpleStringProperty teacherNameProperty() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName= new SimpleStringProperty(teacherName);;
    }

    public String getRegDate() {
        return regDate.get();
    }

    public SimpleStringProperty regDateProperty() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate= new SimpleStringProperty(regDate);
    }

    public float getGpa() {
        return gpa.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName= new SimpleStringProperty(lastName);
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public SimpleIntegerProperty course_idProperty() {
        return course_id;
    }

    public void setCourseId(int course_id) {
        this.course_id= new SimpleIntegerProperty(course_id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password= new SimpleStringProperty(password);
    }

    public void setGpa(float gpa) {
        this.gpa  = new SimpleFloatProperty(gpa);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId  = new SimpleIntegerProperty(studentId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setTeacherId(int teacher_id) {
        this.teacher_id= new SimpleIntegerProperty(teacher_id);
    }
    public SimpleIntegerProperty teacher_idProperty() {
        return teacher_id;
    }

}