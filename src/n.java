import java.util.*;

public class n {
    protected static HashMap<String, Integer> vars = new HashMap<>();
    public static void main(String args[]) {
        System.out.println("N repl v1.0 by Bruce Li\nNO WARRANTY IS PROVIDED OF ANYKIND!");
        repl();
    }
    public static void repl() {
        try {
            Scanner i = new Scanner(System.in);
            boolean inDef = false;
            String varName ="";
            int currentVarValue = 0;
            int stack = 0;
            while (true) {
                String line = i.nextLine();
                if (line == null) continue;
                line = line.trim();
                ArrayList<String> tokens = lex(line);
                while (!tokens.isEmpty()) {
                    String current = get(tokens);
                    tokens = remove(tokens);
                    if (current.matches("\\+")||current.matches("\\-")||current.matches("\\*")||current.matches("\\/")||current.matches("\\%")) {
                        String operator = current;
                        current = get(tokens);
                        tokens = remove(tokens);
                        int x = 0;
                        int y = 0;
                        if (current.matches("-?\\d+")) {
                            x = Integer.parseInt(current);
                        } else if (vars.containsKey(current)) {
                            x = vars.get(current);
                        } else {
                            expectedIntError(current);
                            continue;
                        }
                        current = get(tokens);
                        tokens = remove(tokens);
                        if (current.matches("-?\\d+")) {
                            y = Integer.parseInt(current);
                        } else if (vars.containsKey(current)) {
                            y = vars.get(current);
                        } else {
                            expectedIntError(current);
                            continue;
                        }
                        current = get(tokens);
                        tokens = remove(tokens);
                        if (inDef) {
                            currentVarValue += executeOperation(x, y, operator);
                        } else {
                            System.out.println(executeOperation(x, y, operator));
                        }
                    } else if (current.matches("define")) {
                        current = get(tokens);
                        tokens = remove(tokens);
                        if (current.matches("^[a-zA-Z_$][a-zA-Z0-9_$]*$")) {
                            varName = current;
                            if (vars.containsKey(varName)) {
                                System.err.println("N: Error! Variable name '" +varName +"' already exists!");
                            } else {
                                inDef = true;
                                currentVarValue = 0; 
                                stack += 1;
                            }
                        } else {
                            notValidName(current);
                        }
                    } else if (current.matches("quit")) {
                        i.close();
                        System.exit(0);
                    } else if (current.matches("-?\\d+")&&inDef) {
                        currentVarValue += Integer.parseInt(current);
                    } else if (current.matches("end")&&inDef) {
                        inDef = false;
                        vars.put(varName, currentVarValue);
                    } else if (vars.containsKey(current)&&inDef) {
                        currentVarValue += vars.get(current);
                        stack -= 1;
                    } else if (current.matches("set")) {
                        current = get(tokens);
                        tokens = remove(tokens);
                        if (vars.containsKey(current)) {
                            inDef = true;
                            currentVarValue =0;
                            varName = current;
                            stack += 1;
                        } else {
                            System.err.println("N: Error! '"+current+"' does not exist!");
                        }
                    }
                }
            }
        } catch (NoSuchElementException e) {
            // leave blank.
        }
    }
    public static ArrayList<String> lex(String line) {
        ArrayList<String> result = new ArrayList<String>();
        String z = "";
        boolean ifString = false;
        String p = line;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '-' && !ifString && z.isEmpty() && i + 1 < p.length() && Character.isDigit(p.charAt(i + 1))) {
                z += c;
                continue;
            }
            switch (c) {
                case '(': case ')': case ';': case '=': case '+': case '-': case '*': case '/':case '{': case '}':case ':':case '%':
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
    protected static String get(ArrayList<String>tokens) {
        if (tokens.isEmpty()) {
            return "";
        } else {
            return tokens.get(0);
        }
    }
    protected static ArrayList<String> remove(ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            return tokens;
        } else {
            tokens.remove(0);
            return tokens;
        }
        
    }
    public static int executeOperation(int x, int y, String operator) {
        switch (operator) {
            case "+": return x + y;
            case "-": return x - y;
            case "*": return x * y;
            case "/": return x / y; 
            case "%": return x % y;
            default: break;
        }
        return 0;
    }
    // error functions
    protected static void expectedIntError(String current) {
        System.err.println("N: Error! Expected an Integer but recieved '" + current+"'.");
    }
    protected static void notValidName(String current) {
        System.err.println("N: Error! '"+current+"' is not a valid identifier!");
    }
}