package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class coursesStudent {

    int id= 2;
/*
    public coursesStudent(int id) {
        this.id = id;
    }
*/

    public void setId(int id) {
        this.id = id;
    }

    ArrayList<String> nameCourse= new ArrayList<String>();
    ArrayList<String> nameStudent = new ArrayList<String>();
    ArrayList<String> idCourse= new ArrayList<String>();
    int i=0;

    public String getId(){
        return String.valueOf(id);
    }

    public String getNameCourse(int index) {
        return nameCourse.get(index);
    }

    public String getNameTeacher(int index) {
        return nameStudent.get(index);
    }

    public String getIdCourse(int index) {
        return idCourse.get(index);
    }

    public int getArraySize(){
        return nameCourse.size();
    }

    static Connection connection = null;
    static String databaseName = "database1";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "root123";

    public void getCourses() throws SQLException {
        //PreparedStatement ps = connection.prepareStatement("SELECT course.idCourse, course.nameCourse , teacher.fNameTeacher , teacher.lNameTeacher FROM database1.course JOIN database1.teacher ON teacher.idTeacher = course.teacher_idTeacher WHERE teacher.idTeacher = 20002");
        PreparedStatement ps = connection.prepareStatement("SELECT course.idCourse, course.nameCourse , student.fnameStudent, student.lnameStudent FROM database1.teacher JOIN database1.course ON database1.course.teacher_idTeacher=database1.teacher.idTeacher JOIN database1.student_has_course ON database1.student_has_course.course_idCourse =database1.course.idCourse JOIN database1.student ON database1.student.idStudent = database1.student_has_course.student_idStudent WHERE idTeacher = 20001;");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
            idCourse.add(rs.getString("idCourse"));
            nameCourse.add(rs.getString("nameCourse"));
            nameStudent.add(rs.getString("fNameStudent") +"  "+ rs.getString("lNameStudent"));
            i++;
        }

    }
}
