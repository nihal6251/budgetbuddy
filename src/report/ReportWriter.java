package report;

import service.ExpenseRepository;

import java.io.IOException;

/**
 * Common interface for all report writers.
 */
public interface ReportWriter {
    /**
     * Writes an expense report to the specified file path.
     *
     * @param filePath   the output file path
     * @param repository the expense repository containing data
     * @throws IOException if writing fails
     */
    void writeReport(String filePath, ExpenseRepository repository) throws IOException;
}
