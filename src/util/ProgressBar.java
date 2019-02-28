package util;

/**
 * Provides a progress bar that can be printed
 * to indicate how much of some job has been done.
 *
 * @version 1.3
 */
public final class ProgressBar {

    /* Format:
    |--------------------| 100% (a/b)
     */

    /** The symbol used to fill the progress bar. */
    private static final String PROGRESS_BAR_DASH = "-";

    /**
     * The length of the progress bar, measured in dashes.
     *
     * @see ProgressBar#PROGRESS_BAR_DASH
     */
    private static final int PROGRESS_BAR_LENGTH = 20;

    /** The maximum amount of progress that can be shown. */
    private static final double MAX_PERCENTAGE = 100d;

    /** The amount of progress each dash represents. */
    private static final double PROGRESS_PER_DASH =
            MAX_PERCENTAGE / PROGRESS_BAR_LENGTH;

    /** Hides the constructor for this utility class. */
    private ProgressBar() {
    }

    /**
     * Calls {@link ProgressBar#formatBar(int, int, boolean)} with
     * the supplied arguments and true for the carriage return.
     *
     * @param workDone  the amount of completed work
     * @param workTotal the total amount of work that needs to be done
     * @return The formatted progress bar.
     * @see ProgressBar#formatBar(int, int, boolean)
     */
    public static String formatBar(final int workDone, final int workTotal) {
        return formatBar(workDone, workTotal, true);
    }

    /**
     * Return a progress bar formatted as:
     * |--------------------| 100% (workDone/workTotal)
     * The percentage is calculated as (workDone * 100.0 / workTotal).
     *
     * @param workDone           the amount of completed work
     * @param workTotal          the total amount of work that needs to be done
     * @param withCarriageReturn if the returned string should start with a \r
     * @return The formatted progress bar.
     */
    public static String formatBar(final int workDone,
                                   final int workTotal,
                                   final boolean withCarriageReturn) {
        StringBuilder sb = new StringBuilder();
        if (withCarriageReturn) {
            sb.append("\r");
        }
        sb.append("|");

        double progress = workDone * MAX_PERCENTAGE / workTotal;
        int numberOfDashes = (int) (progress / PROGRESS_PER_DASH);
        int i;
        for (i = 0; i < numberOfDashes; i++) {
            sb.append(PROGRESS_BAR_DASH);
        }
        while (i < PROGRESS_BAR_LENGTH) {
            sb.append(" ");
            i++;
        }
        sb.append("|\t");
        sb.append(String.format("%.2f", progress));
        sb.append("%\t");
        sb.append("(");
        sb.append(workDone);
        sb.append("/");
        sb.append(workTotal);
        sb.append(")");
        return sb.toString();
    }

}
