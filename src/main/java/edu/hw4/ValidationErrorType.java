package edu.hw4;

public enum ValidationErrorType {
    EMPTY_NAME("Name should not be empty"),
    INVALID_HEIGHT("Invalid height, should be a positive number <= " + ValidationError.MAX_HEIGHT),
    INVALID_WEIGHT("Invalid weight, should be a positive number <= " + ValidationError.MAX_WEIGHT),
    INVALID_AGE("Invalid age, should be a positive number <= " + ValidationError.MAX_AGE);

    private final String description;

    ValidationErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
