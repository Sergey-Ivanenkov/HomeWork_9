package guru.qa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.domain.RequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SelenideJSONTest {
    ClassLoader classLoader = SelenideJSONTest.class.getClassLoader();


    @DisplayName("Test parse json (Jackson)")
    @Test
    void jsonParseTest() throws IOException {
        InputStream is = classLoader.getResourceAsStream("SelenideJSONTest.JSON");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestTest request = objectMapper.readValue(is, RequestTest.class);
        assertThat(request.getRequestBody().getSearchValue()).isEqualTo("selenide");
        assertThat(request.getRequestBody().getChannelCode()).isEqualTo(333);
        assertThat(request.getRequestParams().getRequestId()).isEqualTo("d5f1347a-cbbe-436b-bee1-452bc826f2fc");
        assertThat(request.getRequestParams().getSessionId()).isEqualTo("K95PaT1WRButzo5LEYG4cOC_I85jEF41O0AWMQqPSwnaBubvBPuYWsEge7_ws5dn");
    }
}
