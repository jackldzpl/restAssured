import config.VideoGameConfig;
import config.VideoGamesEnpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDbTest extends VideoGameConfig {


    @Test
    public void getAllGames (){
        given().
        when().get(VideoGamesEnpoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void createNewGameByJSON(){
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"My new game\",\n" +
                "  \"releaseDate\": \"2020-01-20T12:43:29.239Z\",\n" +
                "  \"reviewScore\": 88,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().
                body(gameBodyJson).
        when().
                post(VideoGamesEnpoints.ALL_VIDEO_GAMES).
        then();

    }

    @Test
    public void createNewGameByXML() {

        String gameBodyXml = "<xml>";

        given().
                body(gameBodyXml)
                .header("Content-Type","application/xml")
                .header("Accept","application/xml").    // zostanie nadpisane jednorazowo to co w configu.
                // bez tego poleci 400
                when().
                post(VideoGamesEnpoints.ALL_VIDEO_GAMES).
                then();

    }

    @Test
    public void updateGameWithPut(){
        String gameBodyJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"My new game po updacie PUTEM\",\n" +
                "  \"releaseDate\": \"2020-01-20T12:43:29.239Z\",\n" +
                "  \"reviewScore\": 1908,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Milfput2\"\n" +
                "}";
        given().
                body(gameBodyJson).
                when().
                put("videogames/11").        ///ej to chuj ma znaczenie liczy sie co w body w id
                then();
    }

    @Test
    public void updateGameWithPatch(){   //nie wspierane  405
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"My new game po updacie Patchem\",\n" +
                "  \"releaseDate\": \"2020-01-20T12:43:29.239Z\",\n" +

                "  \"category\": \"Shooter\",\n" +

                "}";
        given().
                body(gameBodyJson).
                when().
                patch("videogames/1").
                then();
    }

    @Test
    public void deleteGame(){
        given().
        when()
                .delete("videogames/11").
        then();
    }

    //PARAMETRY
    @Test
    public void getSingleGame(){
        given().
                pathParam("videoGameId",5).
        when()
                .get(VideoGamesEnpoints.SINGLE_VIDEO_GAME).
        then();

    }
}
