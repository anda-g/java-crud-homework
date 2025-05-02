import model.Course;
import model.Student;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Student[] studentArray;
    private static int numberOfStudent = 0;
    private static Scanner sc;
    public static void main(String[] args) {
        operate();
    }

    public static void operate(){
        sc = new Scanner(System.in);
        System.out.println("'Student Management System'");
        do{
            switch (menu()){
                case 1 -> {
                    createStudentArray();
                }
                case 2 -> {
                    if(numberOfStudent == 0){
                        System.out.println("[!] There is no student in the array");
                    }else{
                        readStudentFromList();
                    }
                }
                case 3 -> {
                    if(numberOfStudent == 0){
                        System.out.println("[!] There is no student in the array");
                    }else{
                        updateStudentByUuid();
                    }
                }
                case 4 -> {
                    if(numberOfStudent == 0){
                        System.out.println("[!] There is no student in the array");
                    }else{
                        deleteStudentByUuid();
                    }
                }
                case 5 -> {
                    if(numberOfStudent == 0){
                        System.out.println("[!] There is no student in the array");
                    }else{
                        searchStudentByUuid();
                    }
                }
                case 6 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("[!] Invalid option");
                }
            }
            System.out.print("'Press any key to continue...'"); sc.nextLine();
        }while (true);
    }

    public static int menu(){
        System.out.println("""
                1. Create
                2. Read
                3. Update
                4. Delete
                5. Search
                6. Exit""");
        return inputInteger("[+] Input one option: ");
    }

    public static void createStudentArray(){
        System.out.print("[+] Input the number of student: ");
        int n = Integer.parseInt(sc.next());
        numberOfStudent = n;
        studentArray = new Student[n];
        System.out.println("'Give each student's detail'");
        for (int i = 0; i < n; i++) {
            System.out.println("==> Student [" + (i+1) + "]");
            String name = inputString("[!] Input student name  : ");
            String gender = inputString("[!] Input student gender: ");
            Course[] courses = coursesInput();
            studentArray[i] = new Student(String.valueOf((i+1)), UUID.randomUUID().toString(), name, gender, LocalDate.now(), courses);
        }
    }

    public static void readStudentFromList(){
        System.out.println("'==< All Students In The Array >=='");
        for (int i = 0; i < numberOfStudent; i++) {
            System.out.println(studentArray[i]);
        }
    }

    public static void updateStudentByUuid(){
        String uuid = inputString("[!] Input student's uuid for updating: ");
        for (int i =0; i<numberOfStudent; i++) {
            if(studentArray[i].getUuid().equals(uuid)){
                System.out.println(studentArray[i]);
                System.out.println("""
                        1. Update name
                        2. Update gender
                        3. Update course""");
                int chosenOption = inputInteger("[+] Input number [1-3]: ");
                switch (chosenOption){
                    case 1 -> {
                        String name = inputString("[+] Input student name  : ");
                        studentArray[i] = new Student(studentArray[i].getId(), studentArray[i].getUuid(), name, studentArray[i].getGender(), studentArray[i].getEnrolledAt(), studentArray[i].getCoursesEnrolled());
                    }
                    case 2 -> {
                        String gender = inputString("[+] Input student gender  : ");
                        studentArray[i] = new Student(studentArray[i].getId(), studentArray[i].getUuid(), studentArray[i].getName(), gender, studentArray[i].getEnrolledAt(), studentArray[i].getCoursesEnrolled());
                    }
                    case 3 -> {
                        Course[] courses = coursesInput();
                        studentArray[i] = new Student(studentArray[i].getId(), studentArray[i].getUuid(), studentArray[i].getName(), studentArray[i].getGender(), studentArray[i].getEnrolledAt(), courses);
                    }
                    default -> {
                        System.out.println("[!] Invalid option");
                        return;
                    }
                }
                System.out.println("[*] Student updated success");
                return;
            }
        }
        System.out.println("[!] Cannot find student with this uuid: " + uuid);
    }

    public static void deleteStudentByUuid() {
        String uuid = inputString("[!] Input student's uuid for deleting: ");
        for (int i = 0; i < numberOfStudent; i++) {
            if (studentArray[i].getUuid().equals(uuid)){
                System.out.println(studentArray[i]);
                for (int j = i; j < numberOfStudent; j++) {
                    if(j != numberOfStudent-1){
                        studentArray[j] = studentArray[j+1];
                    }
                }
                numberOfStudent--;
                System.out.println("[*] That student above is deleted");
                return;
            }
        }
        System.out.println("[!] Cannot find student with this uuid: " + uuid);
    }

    public static void searchStudentByUuid() {
        String uuid = inputString("[!] Input student's uuid for searching: ");
        for (int i = 0; i < numberOfStudent; i++) {
            if (studentArray[i].getUuid().equals(uuid)) {
                System.out.println(studentArray[i]);
                return;
            }
        }
        System.out.println("[!] Cannot find student with this uuid: " + uuid);
    }


    public static Course[] coursesInput(){
        Course[] courses;
        int numberOfCourses = inputInteger("[!] Input number of courses: ");
        System.out.println("'Give which are the courses'");
        System.out.println("""
                            1. MOBILE
                            2. WEB
                            3. FULLSTACK""");
        courses = new Course[numberOfCourses];
        for (int j = 0; j < numberOfCourses; j++) {
            int chosenCourse = inputInteger("[+] Input number [1-3]: ");
            switch (chosenCourse){
                case 1 -> {
                    courses[j] = Course.MOBILE;
                }
                case 2 -> {
                    courses[j] = Course.WEB;
                }case 3 -> {
                    courses[j] = Course.FULLSTACK;
                }
            }
        }
        return courses;
    }

    public static String inputString(String message){
        String string;
        do{
            System.out.print(message);
            string = sc.nextLine();
        }while (string.isBlank());
        return string;
    }

    public static int inputInteger(String message){
        int integer = -1;
        do{
            System.out.print(message);
            try {
                integer = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored){}

        }while (integer == -1);
        return integer;
    }
}
