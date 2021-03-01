import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseServices{

    private String basePath = null;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public RequestSpecification requestSpec() {

        return new RequestSpecBuilder()
                .addHeader("Accept", "application/vnd.github.v4+json")
                .setUrlEncodingEnabled(false)
                .setBaseUri("https://api.github.com")
                .setBasePath(getBasePath())
                .build();
    }

    public ResponseSpecification responseSpec() {

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public Response searchRequest(String pathParm, String q, String sort, String order) {
        RequestSpecification request = RestAssured
                .given()
                .header("Accept", "application/vnd.github.v4+json")
                .spec(requestSpec()).log().all()
                .queryParam("q",q)
                .pathParam("pathParam", pathParm)
                .when();

        if(sort != null)
            request.queryParam("sort", sort);

        if(order != null)
            request.queryParam("order", order);

        return request.get("/{pathParam}")
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

    public Response reposRequest(String owner,String reponame) {
        RequestSpecification request = RestAssured
                .given()
                .header("Accept", "application/vnd.github.v3+json")
                .spec(requestSpec()).log().all()
                .pathParam("pathParm", owner)
                .pathParam("reponame",reponame)
                .when();


        return request.get("/{pathParm}/{reponame}/tags")
                .then()
                .log()
                .ifStatusCodeMatches(anyOf(is(304), is(422), is(503)))
                .spec(responseSpec())
                .extract()
                .response();
    }

}