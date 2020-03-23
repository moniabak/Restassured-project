package steps;

import cucumber.api.java.Before;
import util.RestAssuredExtension;

public class Hooks {

    @Before
    public void SetUp(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
