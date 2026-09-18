package utils;

public class TestDataUtil {

    public static String generateUniqueName(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}
