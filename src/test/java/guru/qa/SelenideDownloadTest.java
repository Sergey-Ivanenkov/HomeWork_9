package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenideDownloadTest {

    @Test
    void downloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("#raw-url").download();//Selenide загрузка файла (build/download/"Папка с сессией"/...)
        System.out.println("Success download README.md");


        //Обычно выводиться в отдельный метод. Напривер FileInputStream
        try (InputStream is = new FileInputStream(file)) {//Чтение файла и вход в поток InputStream
            byte[] fileContent = is.readAllBytes(); //Возвращение массива данных в формате byte
            String assertString = new String(fileContent, UTF_8); //Преведение byte к String
            assertThat(assertString).contains("Contributions to JUnit 5 are both welcomed and appreciated.");//Проверяем наличие данных в полученном массиве
        } catch (Exception e) {

        }

        System.out.println("Success");
    }
}
