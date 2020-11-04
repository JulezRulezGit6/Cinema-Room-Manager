import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();

        System.out.println(line2);
        System.out.println(line1);

        String s;
        String str = null;
        s = str;
        String result = s.concat("abc");
        "abc".equals(s);
    }
}