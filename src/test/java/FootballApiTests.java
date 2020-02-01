import config.FootballApiConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootballApiConfig {


    @Test
    public void GetAreas(){
        given().
                when().get("areas").
                then();
    }

    @Test
    public void GetDetailsOfOneArea(){
        given().
                when().get("areas?areas=2019").    // tak też działa: "areas/2019"
                then();
    }

    @Test
    public void getDateFounded(){
        given().
        when().
                get("teams/57").
        then().
                body("founded",equalTo(1886));
    }

    @Test
    public void getFirstTeamName(){
        given().
                when().
                get("competitions/2021/teams").
                then().
                body("teams.name[0]",equalTo("Arsenal FC")).
                body("teams.name[1]",equalTo("Aston Villa FC"));
    }


    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckList(){  //jak wydobyc dane
        Response response =           //musi być z restAssured
                given().
                when().
                        get("teams/57").
                then().
                        contentType(ContentType.JSON).
                        extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println("-----JSON");
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response =
                given().
                when().
                        get("teams/57").
                then().contentType(ContentType.JSON).
                        extract().response();

        Headers headers = response.getHeaders();

        String singleHeader = response.getHeader("Content-Type");
        System.out.println("---------WYNIK");
        System.out.println(singleHeader);
    }

    //How to extract explicit data from JSON body      UŻYCIE JSON path
    @Test
    public void extractFirstTeamName (){
        String firstTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[0]");

        System.out.println("\n +WYNIK");
        System.out.println(firstTeamName);
    }
    //or drugi sposob
    @Test
    public void extractFirstTeamName2 (){
        Response response = get("competitions/2021/teams").then().extract().response();

        String firstTeamName = response.jsonPath().getString("teams.name[0]");

        System.out.println("\n +WYNIK");
        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamNames(){
        Response response =
                given().
                when().get("competitions/2021/teams").
                then().extract().response();
                                //automatycznie na json path, wiec mozna albo path albo Jsonpath
        List<String> teamNames = response.path("teams.name");

        System.out.println("TEAM NAMES");
        for(String name: teamNames){
            System.out.println(name);
        }
    }
    // or hmm?

    @Test
    public void extractAllTeamNames2(){
        List<String> teamNames =
                 given().
                        when().get("competitions/2021/teams").
                        then().extract().response().path("teams.name");

        System.out.println("TEAM NAMES");
        for(String name: teamNames){
            System.out.println(name);
        }
    }
}
