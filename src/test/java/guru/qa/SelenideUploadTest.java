package guru.qa;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideUploadTest {

    @Test
    void uploadTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file'").uploadFromClasspath("IMG/IMG-UPLOAD.jpg");
        $(byTitle("IMG-UPLOAD.jpg")).shouldHave(text("IMG-UPLOAD.jpg"));
    }
}
