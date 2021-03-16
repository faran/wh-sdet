import io.restassured.response.Response;

import java.util.*;

public class ReposService extends BaseServices{

    /**Setting base path to Repos*/
    ReposService(){
        setBasePath("repos");
    }

    private Response response;

    public void getAllRepos(String pathParam1, String pathParam2) {
        response = reposRequest(pathParam1,pathParam2);
    }

    /** We get list of json objects
     * Each element in map is a single json object
     * Map represents key and value combination
     * List hold all the elements
     * json path $ represents root object or array */
    public String latestReleaseTag() {
        List<Map<String, String>> jp = response.jsonPath().getList("$");
        return jp.get(0).get("name");
    }

}
