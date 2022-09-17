package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.List;
import java.util.zip.ZipFile;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideZipParseTest {
    ClassLoader classLoader = SelenideZipParseTest.class.getClassLoader();

    String zipFileName = "zip/SelenideTestZip.zip";
    String csvFileName = "SelenideTestCsv.csv";
    String pdfFileName = "SelenideTestPdf.pdf";
    String xlsxFileName = "SelenideTestXlsx.xlsx";

    private InputStream getFile(String zipFileName, String fileName) throws Exception {
        URL zipUrl = classLoader.getResource(zipFileName);
        File file = new File(zipUrl.toURI());
        ZipFile zipFile = new ZipFile(file);
        return zipFile.getInputStream(zipFile.getEntry(fileName));
    }

    @DisplayName("Test parse csv from zip")
    @Test
    void csvParseTest() throws Exception {
        InputStream csvFileStream = getFile(zipFileName, csvFileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(csvFileStream, UTF_8));
        List<String[]> csv = csvReader.readAll();
        assertThat(csv).contains(
                new String[]{"1", "Roberta", "39", "M"},
                new String[]{"2", "Oliver", "25", "M"},
                new String[]{"3", "Shayna", "18", "F"},
                new String[]{"4", "Fechin", "18", "M"}
        );
    }

    @DisplayName("Test parse pdf from zip")
    @Test
    void pdfParseTest() throws Exception {
        InputStream pdfFileStream = getFile(zipFileName, pdfFileName);
        PDF pdf = new PDF(pdfFileStream);
        assertThat(pdf.title).isEqualTo("CheatSheet");

    }

    @DisplayName("Test parse xlsx from zip")
    @Test
    void xlsxParseTest() throws Exception {
        InputStream xlsFileStream = getFile(zipFileName, xlsxFileName);
        XLS xls = new XLS(xlsFileStream);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(3)
                .getCell(1)
                .getStringCellValue()).contains("Shayna");
    }
}