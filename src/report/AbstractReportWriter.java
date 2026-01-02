package report;

import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for report writers.
 * Provides common functionality for data generation and formatting.
 */
public abstract class AbstractReportWriter implements ReportWriter {
    protected final DateTimeFormatter dateFormatter;
    protected final DateTimeFormatter monthFormatter;

    protected AbstractReportWriter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }

    @Override
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writeReportContent(writer, summarizer, allExpenses);
        }

        printConfirmationMessage(filePath);
    }

    protected abstract void writeReportContent(BufferedWriter writer, 
                                               Summarizer summarizer, 
                                               List<Expense> expenses) throws IOException;

    protected abstract void printConfirmationMessage(String filePath);

    protected String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    protected String formatMonth(YearMonth month) {
        return month.format(monthFormatter);
    }

    protected String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    protected double computeMaxCategoryAmount(Map<String, Double> categoryTotals) {
        return categoryTotals.values().stream()
                .max(Double::compareTo)
                .orElse(1.0);
    }
}
