package ReportGeneration;

public class MonthlyReportGenerator {

    public String generateMonthlyReport(int userId, String month, int year) {
        // Simulated report generation
        return userId > 0 ? "Report for " + month + " " + year : null;
    }

    public boolean exportReport(int reportId, String format) {
        // Simulated export logic
        return reportId > 0 && (format.equals("PDF") || format.equals("Excel"));
    }
}
