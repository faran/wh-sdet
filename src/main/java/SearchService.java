import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Map;

public class SearchService extends BaseServices{

    SearchService(){
        setBasePath("search");
    }

    private Response response;

    @Parameters({"pathParam", "q", "sort", "order"})
    @BeforeSuite
    public void getAllRepos(@Optional("repositories") String pathParam, @Optional("user:faran")
            String q, @Optional("any") String sort, @Optional("any") String order) {
        response = searchRequest(pathParam, q, sort.equals("any") ? null : sort,
                order.equals("any") ? null : order);
    }

    public Integer numberOfStars() {
        List<Map<String, Integer>> jp = response.jsonPath().getList("items");
        return Integer.parseInt(String.valueOf(jp.get(0).get("stargazers_count")));
    }

    public String topRepoName() {
        List<Map<String, String>> jp = response.jsonPath().getList("items");
        return jp.get(0).get("name");
    }
}
