package util;

/**
 * This class provides methods to support tracing system execution.
 * Specifically, it provides a method to print a method call
 * and a method to log a method call to a file.
 *
 * @version 2.0
 */
public final class Call {

    /** The index of the invoking method in the stack trace. */
    private static final int INVOKER_INDEX = 3;
    /*
     * trace[0] is Thread.getStackTrace()
     * trace[1] is this constructor
     * trace[2] is the Call.print or Call.log method
     * trace[3] is the invoker
     */

    /** The details of the call. */
    private final String details;

    /**
     * Constructs a {@link Call} object with
     * the arguments of the invoking method.
     *
     * @param args the arguments of the invoking method
     */
    private Call(final Object... args) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement invoker = trace[INVOKER_INDEX];
        StringBuilder sb = new StringBuilder();
        sb.append(invoker.getClassName());
        sb.append("::");
        sb.append(invoker.getMethodName());
        sb.append("(");
        if (args.length > 0) {
            sb.append(args[0]);
        }
        for (int i = 1; i < args.length; i++) {
            sb.append(", ");
            sb.append(args[i]);
        }
        sb.append(")");
        details = sb.toString();
    }

    /**
     * Prints the method call to standard output.
     *
     * @param args the arguments of the invoking method
     */
    public static void print(final Object... args) {
        Call c = new Call(args);
        PrintFormatting.print(c.details);
    }

    /**
     * Logs the method call to the given logger.
     *
     * @param l    the {@link Logger} to log to
     * @param args the arguments of the invoking method
     */
    public static void log(final Logger l, final Object... args) {
        Call c = new Call(args);
        l.log(c.details);
    }

}
