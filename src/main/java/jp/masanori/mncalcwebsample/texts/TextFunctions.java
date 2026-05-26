package jp.masanori.mncalcwebsample.texts;

public class TextFunctions {
    public static boolean isNullOrEmpty(String value) {
        if(value == null) {
            return true;
        }
        if(value.length() <= 0) {
            return true;
        }
        return false;
    }
}
