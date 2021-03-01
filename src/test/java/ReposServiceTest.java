import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class ReposServiceTest {

    ReposService reposService = new ReposService();
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    @BeforeClass
    public void testGetRepos(){
        reposService.getAllRepos("johanhaleby","kubetail");
    }

    @Test
    public void testlatestReleaseTag(){
        String lrt = reposService.latestReleaseTag();
        log.info("Latest Release Tag: " + lrt);
        Assert.assertEquals(lrt, "1.6.12");
        Assert.assertTrue(lrt.matches("\\d.\\d.\\d*"));
    }
}
