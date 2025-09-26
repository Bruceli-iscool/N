import java.util.*;

public class MECL {
    public static void main(String args[]) {
        System.out.println("Calculator v1.0 by Bruce Li\n(c) 2025 All Rights Reserved.\nNO WARRANTY IS PROVIDED OF ANYKIND!");
        repl();
    }
    public static void repl() {
        Scanner i = new Scanner(System.in);
        while (true) {
            String line = i.nextLine();
            ArrayList<String> tokens = lex(line);
            while (!tokens.isEmpty()) {
                String current = tokens.get(0);
                tokens = remove(tokens);
                if ()
            }
        }
    }
    public static ArrayList<String> lex(String line) {
        ArrayList<String> result = new ArrayList<String>();
        String z = "";
        boolean ifString = false;
        String p = line;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            switch (c) {
                case '(': case ')': case ';': case '=': case '+': case '-': case '*': case '/':case '{': case '}':case ':':
                    if (!z.isEmpty()) {
                        result.add(z);
                        z = "";
                    }
                    result.add(String.valueOf(c));
                    break;
                case '"':
                    if (!z.isEmpty() && ifString) {
                        ifString = false;
                        result.add(z);
                        z = "";
                    } else {
                        ifString = true;
                    }
                    result.add("\"");
                    break;
                case ' ':
                    if (!z.isEmpty() && !ifString) {
                        result.add(z);
                        z = "";
                    } else if (ifString) {
                        z += c;
                    }
                    break;
                default:
                    z += c;
                    break;
            }
        }
        if (!z.isEmpty()) {
            result.add(z);
        }
        return result;
    }
    protected static String get(tokens) {
        if (tokens.isEmpty()) {
            return "";
        } else {
            return tokens.get(0);
        }
    }
    protected static ArrayList<String> remove(ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            return
        }
    }
}