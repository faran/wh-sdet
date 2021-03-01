import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.*;

public class ReposService extends BaseServices{

    /**Setting base path to Repos*/
    ReposService(){
        setBasePath("repos");
    }

    private Response response;

    @Parameters({"pathParam", "pathParam"})
    @BeforeSuite
    public void getAllRepos(@Optional("faran") String pathParam1,@Optional("faran-cv") String pathParam2) {
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
