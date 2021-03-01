import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchServiceTest {

    SearchService searchService = new SearchService();
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    @BeforeClass
    public void testGetRepos(){
        searchService.getAllRepos("repositories",
                "language:selenium+language:java", "stars", "desc");
    }

    @Test
    public void testNumberOfStars(){
        Integer numOfStars = searchService.numberOfStars();
        log.info("Highest number of stars is: " + numOfStars);
        Assert.assertTrue(numOfStars > 122000);
    }

    @Test
    public void testTopRepoName(){
        String topRepo = searchService.topRepoName();
        log.info("Top Result Repo Name : " + topRepo);
        Assert.assertEquals(topRepo, "CS-Notes");
    }
}
