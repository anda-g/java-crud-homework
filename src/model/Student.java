package model;

import java.time.LocalDate;
import java.util.Arrays;

public class Student {
    String id;
    String uuid;
    String name;
    String gender;
    LocalDate enrolledAt;
    Course[] coursesEnrolled;

    public Student(String id, String uuid, String name, String gender, LocalDate enrolledAt, Course[] coursesEnrolled) {
        this.id = "ISTAD00"+ id;
        this.uuid = uuid;
        this.name = name;
        this.gender = gender;
        this.enrolledAt = enrolledAt;
        this.coursesEnrolled = coursesEnrolled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDate enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Course[] getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(Course[] coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\n\tid='" + id + '\'' +
                ",\n\tuuid='" + uuid + '\'' +
                ",\n\tname='" + name + '\'' +
                ",\n\tgender='" + gender + '\'' +
                ",\n\tenrolledAt=" + enrolledAt +
                ",\n\tcoursesEnrolled=" + Arrays.toString(coursesEnrolled) +
                "\n}";
    }
}
