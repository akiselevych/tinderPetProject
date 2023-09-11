package Utils;

public class Converting {
    public static Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
    public static Integer convertToInteger(Object o){
        String stringToConvert = String.valueOf(o);
        Integer convertedInteger = Integer.parseInt(stringToConvert);
        return convertedInteger;
    }
}
