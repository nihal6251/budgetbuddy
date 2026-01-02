package report;

/**
 * Factory for creating ReportWriter instances based on format.
 */
public class ReportWriterFactory {

    /**
     * Creates a ReportWriter based on the specified format.
     * 
     * @param format the report format ("txt" or "html")
     * @return a ReportWriter instance for the specified format
     * @throws IllegalArgumentException if format is not supported
     */
    public static ReportWriter createReportWriter(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }

        switch (format.toLowerCase()) {
            case "txt":
                return new TxtReportWriter();
            case "html":
                return new HtmlReportWriter();
            default:
                throw new IllegalArgumentException("Unsupported report format: " + format);
        }
    }
}
