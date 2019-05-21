package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {
        return issueState(issueId).equals("Open");
    }

    public String issueState(int issueId) throws IOException {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        JsonElement issueState = issue.getAsJsonObject().get("state_name");
        return new Gson().fromJson(issueState, new TypeToken<String>() {
        }.getType());
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
