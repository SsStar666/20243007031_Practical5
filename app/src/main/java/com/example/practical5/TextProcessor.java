package com.example.practical5;

/**
 * Regular Java class (NOT an Activity) that validates and normalises
 * a municipality name entered by the user.
 */
public class TextProcessor {

    /**
     * @param municipality raw user input
     * @return correctly capitalised name ("Beijing", "Shanghai", ...)
     *         or null if the input is empty, whitespace-only or unsupported.
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }
        String input = municipality.trim().toLowerCase();
        switch (input) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
