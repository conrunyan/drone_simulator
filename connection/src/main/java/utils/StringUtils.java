package utils;

public class StringUtils {
    public static String formatInteger(Integer value) {
        return (value==null) ? "0" : value.toString();
    }

    public static String formatDouble(Double value) {
        return (value==null) ? "0.00" : String.format("%.2f", value);
    }
}

