import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.*;

public class ReposService extends BaseServices{

    ReposService(){
        setBasePath("repos");
    }

    private Response response;

    @Parameters({"pathParam", "pathParam"})
    @BeforeSuite
    public void getAllRepos(@Optional("faran") String pathParam1,@Optional("faran-cv") String pathParam2) {
        response = reposRequest(pathParam1,pathParam2);
    }

    public String latestReleaseTag() {
        List<Map<String, String>> jp = response.jsonPath().getList("$");
        return jp.get(0).get("name");
    }

}
