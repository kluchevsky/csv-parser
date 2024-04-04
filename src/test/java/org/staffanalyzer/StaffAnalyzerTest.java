package org.staffanalyzer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffAnalyzerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Tests the main method in the StaffAnalyzer class with a csv file.
     * <p>
     * This test verifies that the main method can be executed with a data file as an argument,
     * and the program runs without throwing any exceptions or errors. It also checks that the result
     * of the program's execution matches the expected output.
     */
    @Test
    public void testMain_WithDataFile() {
        String filename = Objects.requireNonNull(getClass().getResource("/data.csv")).getFile();
        StaffAnalyzer.main(new String[] {filename});

        assertEquals("Martin Chekov earns 15000,00 less than it should.", outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the main method in the StaffAnalyzer class with a file containing a long reporting line.
     * <p>
     * This test verifies that when the program is executed with a file containing a long reporting line,
     * the appropriate message is printed to the output stream.
     */
    @Test
    public void testMain_LongReportingLine() {
        String filename = Objects.requireNonNull(getClass().getResource("/long_reporting_line.csv")).getFile();
        StaffAnalyzer.main(new String[] {filename});

        assertEquals("James Wilson has a reporting line that is 1 too long.", outputStreamCaptor.toString().trim());
    }
}
