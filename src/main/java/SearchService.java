import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class SearchService extends BaseServices{

    SearchService(){
        setBasePath("search");
    }

    private Response response;

    public void getAllRepos(String pathParam, String q, String sort, String order) {
        response = searchRequest(pathParam, q, sort.equals("any") ? null : sort,
            order.equals("any") ? null : order);
}
    /** We get list of json objects
     * Each element in map is a single json object
     * Map<String, Integer> represents key and value combination
     * List hold all the elements
     * json path $ represents root object or array */
    public Integer numberOfStars() {
        List<Map<String, Integer>> jp = response.jsonPath().getList("items");
        return Integer.parseInt(String.valueOf(jp.get(0).get("stargazers_count")));
    }

    public String topRepoName() {
        List<Map<String, String>> jp = response.jsonPath().getList("items");
        return jp.get(0).get("name");
    }
}
