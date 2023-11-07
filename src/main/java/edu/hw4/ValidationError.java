package edu.hw4;

public class ValidationError {

    public static final int MAX_HEIGHT = 2000;
    public static final int MAX_WEIGHT = 80000;
    public static final int MAX_AGE = 50;

    private final ValidationErrorType type;
    private final String variableValue;

    public ValidationError(ValidationErrorType type, String variableValue) {
        this.type = type;
        this.variableValue = variableValue;
    }

    public String getFullDescription() {
        return "Type: " + type.getDescription() + "\nValue: " + variableValue + "\n\n";
    }

}
