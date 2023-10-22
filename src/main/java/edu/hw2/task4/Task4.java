package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        try {
            throw new Exception();
        } catch (Exception e) {
            var stackTrace = e.getStackTrace();

            String className = stackTrace[1].getClassName();
            String methodName = stackTrace[1].getMethodName();
            return new CallingInfo(className, methodName);
        }
    }
}
