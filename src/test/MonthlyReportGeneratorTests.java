package test;

//MonthlyReportGeneratorTests.java
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ReportGeneration.AuditLog;
import ReportGeneration.MonthlyReportGenerator;

public class MonthlyReportGeneratorTests {

	MonthlyReportGenerator reportGen = new MonthlyReportGenerator();

	@DataProvider(name = "reportData")
	public Object[][] reportData() {
		return new Object[][] { { 1, "January", 2024, "Report for January 2024" }, { -1, "January", 2024, null } };
	}

	@Test(dataProvider = "reportData")
	public void testGenerateMonthlyReport(int userId, String month, int year, String expected) {
		Assert.assertEquals(reportGen.generateMonthlyReport(userId, month, year), expected);
	}

	@Test
	public void testExportReport() {
		Assert.assertTrue(reportGen.exportReport(1, "PDF"));
	}
}

class AuditLogTests {

	AuditLog log = new AuditLog();

	@Test
	public void testAddLogEntry() {
		Assert.assertTrue(log.addLogEntry("New Entry"));
	}

	@Test
	public void testRetrieveLogs() {
		Assert.assertEquals(log.retrieveLogs("Error"), "Filtered Logs");
	}
}
