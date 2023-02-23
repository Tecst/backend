package com.tecst.tecst.domain.answer;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class ClovaSpeechClient {

    // Clova Speech secret key
    private static final String SECRET = "bc0bf43a582e472fba516babc7121890";
    // Clova Speech invoke URL
    private static final String INVOKE_URL = "https://clovaspeech-gw.ncloud.com/external/v1/4617/aee132683639e4578eaba1ebf42b59173f2971bff8b750fa0b3ba43fe54eee2a";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final Gson gson = new Gson();

    private static final Header[] HEADERS = new Header[] {
            new BasicHeader("Accept", "application/json;UTF-8"),
            new BasicHeader("X-CLOVASPEECH-API-KEY", SECRET),
    };

    public static class NestRequestEntity {
        private static final String language = "ko-KR";
        //completion optional, sync/async
        private static final String completion = "sync";
        private final Boolean fullText = Boolean.TRUE;
        //도메인 생성시 선택한 저장소(object storage)에 결과 저장
        private final Boolean resultToObs = Boolean.TRUE;
        public String getLanguage() {
            return language;
        }

        public String getCompletion() {
            return completion;
        }

        public Boolean getFullText() {
            return fullText;
        }

        public Boolean getResultToObs() {
            return resultToObs;
        }
    }

    /**
     * objectStorage 파일 STT 실행
     * recognize media using Object Storage
     * @param dataKey required, the Object Storage key
     * @param nestRequestEntity optional
     * @return string
     */
    public String objectStorage(String dataKey, NestRequestEntity nestRequestEntity) {
        HttpPost httpPost = new HttpPost(INVOKE_URL + "/recognizer/object-storage");
        httpPost.setHeaders(HEADERS);
        Map<String, Object> body = new HashMap<>();
        body.put("dataKey", dataKey);
        body.put("language", nestRequestEntity.getLanguage());
        body.put("completion", nestRequestEntity.getCompletion());
        body.put("fullText", nestRequestEntity.getFullText());
        body.put("resultToObs", nestRequestEntity.getResultToObs());
        StringEntity httpEntity = new StringEntity(gson.toJson(body), ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        return execute(httpPost);
    }

    private String execute(HttpPost httpPost) {
        try (final CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            final HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
            return jsonObject.get("text").toString().replace("\"", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}