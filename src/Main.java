
public class Main {

    public static void main(String[] args) {

        for(int j=0;j<1000;j++){
            forfor();
        }

    }

    public static void forfor(){
        System.out.println(System.currentTimeMillis());
        for(int j=0;j<1000000000;j++);
        System.out.println(System.currentTimeMillis());
        System.out.println("---------------------");
    }
}
