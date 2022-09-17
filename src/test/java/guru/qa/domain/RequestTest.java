package guru.qa.domain;

public class RequestTest {

    private RequestBody requestBody;
    private RequestParams requestParams;

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public static class RequestBody{
        private String searchValue;
        private Integer channelCode;

        public String getSearchValue() {
            return searchValue;
        }

        public Integer getChannelCode() {
            return channelCode;
        }
    }

    public static class RequestParams{
        private String requestId;
        private String sessionId;

        public String getRequestId() {
            return requestId;
        }

        public String getSessionId() {
            return sessionId;
        }
    }
}
