package tv.mannyocrity.discordbot.exception;

/**
 * Custom exception for TimeConversion Class.
 */
public final class TimeConversionException extends Exception  {
    /**
     * Class constructor.
     * @param errorMessage - Message to send with error.
     * @param err - Thrown exception.
     */
    public TimeConversionException(final String errorMessage, final Throwable err) {
        super(errorMessage, err);
    }
}
