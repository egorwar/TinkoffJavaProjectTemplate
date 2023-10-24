package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {

        var stackTrace = Thread.currentThread().getStackTrace();

        String className = stackTrace[2].getClassName();
        String methodName = stackTrace[2].getMethodName();
        return new CallingInfo(className, methodName);

    }
}
