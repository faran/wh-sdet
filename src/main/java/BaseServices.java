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

    /**Encapsulation for setting basepath accordingly.*/
    private String basePath = null;

    /**basepath getter*/
    public String getBasePath() {
        return basePath;
    }
    /**basepath setter*/
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**Setting request specification*/
    public RequestSpecification requestSpec() {

        return new RequestSpecBuilder()
                .addHeader("Accept", "application/vnd.github.v4+json")
                .setUrlEncodingEnabled(false)
                .setBaseUri("https://api.github.com")
                .setBasePath(getBasePath())
                .build();
    }
    /**Setting response specification*/
    public ResponseSpecification responseSpec() {

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**Creating Search request
     * Takes in 1 path param and 1 query parameter
     * sort and order are optional
     * return request*/
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

    /**Creating repos request
     * Takes in 2 path param
     * return request*/
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