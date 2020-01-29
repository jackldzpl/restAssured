import config.VideoGameConfig;
import config.VideoGamesEnpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;

public class MyFirstTest extends VideoGameConfig {

    //String video = "http://localhost:8080/app/videogames";


    @Test
    public void myFirstTest (){
        given().
                log().all().        // request naglowki,uri     zamiast logow: w config addFilter
        when().get("videogames").
        then().
                log().all();      // response body, odp

    }

    @Test
    public void myFirstTestWithEndpoint(){
        given().log().all()
        .get(VideoGamesEnpoints.ALL_VIDEO_GAMES)
                .then().log().all();
    }



}
