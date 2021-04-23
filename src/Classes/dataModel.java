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
