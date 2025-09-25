import java.util.*;

public class Calculator {
    public static void main(String args[]) {
        System.out.println("Calculator v1.0 by Bruce Li\n(c) 2025 All Rights Reserved.\nNO WARRANTY IS PROVIDED OF ANYKIND!");
        repl();
    }
    public static void repl() {
        Scanner i = new Scanner(System.in);
        while (true) {
            String line = i.nextLine();
            if (line.equals("add")) {
                int x = i.nextInt();
                int y = i.nextInt();
                System.out.println(x+y);
            }
        }
    }
}