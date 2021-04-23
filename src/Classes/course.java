package Classes;

public class course {
    String nameCourse, nameTeacher, id;

    public String getNameCourse() {
        return nameCourse;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public String getId() {
        return id;
    }

    public course(String nameCourse, String nameTeacher, String id) {
        this.nameCourse = nameCourse;
        this.nameTeacher = nameTeacher;
        this.id = id;
    }
}
