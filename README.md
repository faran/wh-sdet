RepoServiceTest : https://docs.github.com/en/rest/reference/repos
Test description : Get latest Release tag
Base URI : https://api.github.com
Base Path = repos
Path Parameters = owner , reponame
URL : https://api.github.com/repos/johanhaleby/kubetail/tags
Expected result : We will get json response with all tag names with latest being first
Verifications : 
Response spec verifies ; Status code is 200 and content type is json
Status code also matches Status 304 not modified and 422 Unprocessed entity and 503 service unavailable.
-Unit test
Print latest release tag
Verify latest release tag matches to expected release tag
Verify release tag matches regex pattern.

SearchServiceTest : https://docs.github.com/en/rest/reference/search
Test description : Get repository based on search query i.e language and sort by stars in desc order
Base URI : https://api.github.com
Base Path = search
Path Parameters = repositories
Query Parameters = q=language:selenium+language:java&sort=stars&order=desc
URL : https://api.github.com/search/repositories?q=language:selenium+language:java&sort=stars&order=desc
Expected result : We will get json response with specific reposotories 
Verifications : 
Response spec verifies ; Status code is 200 and content type is json
Status code also matches Status 304 not modified and 422 Unprocessed entity and 503 service unavailable.
-Unit test
1-Test Number of Stars
Print highest number of stars
Expected Number of stars should be greater that 122000

2-Test Number Top repo Name
Print Name of Top Repo
Verify name of expected repo with actual repo

Test Strategy:
Using Rest Assured make sure i can test API easily in short amount of time.
I have used Jsonpath to parse json response.

Single Responsibility
Each test has single responsibility and include a single assertion.

Re-suabiity
Base service can be extended for other endpoints

POM.xml
TestNG - Testing Framework
Rest Assured - Automation testing tool for rest api endpiints.
log4j - printing information

testng.xml - Test runner for running both classes
We can kick our test suite from command prompt 
From root of our project run below maven command
mvn clean test












