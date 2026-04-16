package services;

public class ConvertService {
    public static  convert(String input) {
        for (char c : input.toCharArray()) {

        }

    };
    public static int convertChar(char c) {
            int ascii = (int) c;
        if (ascii >= 0 && ascii <= 32) {
            return 0;
        }
        else if (ascii >= 48 && ascii <= 57) {
            return 53;
        }
        else if (ascii >= 65 && ascii <= 90) {
            return 77;
        }
         else if (ascii >= 97 && ascii <= 122) {
            return 109;
        }
        else {
            return ascii;
        }
    }
}

