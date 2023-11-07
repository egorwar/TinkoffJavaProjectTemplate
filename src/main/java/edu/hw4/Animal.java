package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    @SuppressWarnings("MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public Set<ValidationError> getErrors() {
        Set<ValidationError> errors = new HashSet<>();

        if (name.isEmpty()) {
            errors.add(new ValidationError(ValidationErrorType.EMPTY_NAME, name));
        }

        if (height <= 0 || height > ValidationError.MAX_HEIGHT) {
            errors.add(new ValidationError(ValidationErrorType.INVALID_HEIGHT, String.valueOf(height)));
        }

        if (weight <= 0 || weight > ValidationError.MAX_WEIGHT) {
            errors.add(new ValidationError(ValidationErrorType.INVALID_WEIGHT, String.valueOf(weight)));
        }

        if (age <= 0 || age > ValidationError.MAX_AGE) {
            errors.add(new ValidationError(ValidationErrorType.INVALID_AGE, String.valueOf(age)));
        }

        return errors;
    }

    public boolean hasErrors() {
        return !getErrors().isEmpty();
    }

}
