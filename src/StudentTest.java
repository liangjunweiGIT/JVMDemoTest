import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Create by junwei.liang on 2020/2/11
 */
public class StudentTest {
    static class Student {
        String id;
        String name;
        Integer scord;
        Integer pm;

        public Student(int i) {
            id = i + "_id";
            name = System.currentTimeMillis() % 1000 + "name";
            String s = Math.random() * 100 + "";
            scord = Integer.valueOf(s.substring(0, s.indexOf(".")));
            System.out.println(Math.random());
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", scord=" + scord +
                    ", pm=" + pm +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println((Math.random() * 100 + "").substring(0, 2)
        );
        List<Student> list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            list.add(new Student(i));
        }
        List<Student>[] arr = new ArrayList[101];
        for (Student student : list) {
            if (arr[student.scord] == null) {
                arr[student.scord] = new ArrayList<Student>();
            }
            arr[student.scord].add(student);
        }
        int p = 0;
        for (int i = 0; i <= 100; i++) {
            if (arr[i] != null) {
                p++;
                for (Student s : arr[i]) {
                    s.pm = p;
                    System.out.println(s);
                }
            }
        }
    }
}
