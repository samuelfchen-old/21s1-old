package SCD2_2021_T3;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class OfflineAPI implements APIFacade {
    JSONParser parser = new JSONParser();

    public Response generateGeneric(String message) {
        try {
            JSONObject o = (JSONObject) parser.parse(message);
            return new Response(true, "200 - Success", 200, "Success", o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response checkStatus() {
        return generateGeneric("{\"status\":\"spacetraders is currently online and available to play\"}");
    }

    public Response claimUsername(String username) {
        return generateGeneric("{\"token\":\"dd4fca34-07fb-4032-934c-222138b7c34a\",\"user\":{\"username\":\""+username+"\",\"credits\":0,\"ships\":[],\"loans\":[]}}");
    }

    public Response login(String token, String username) {
        return generateGeneric("{\"token\":\"dd4fca34-07fb-4032-934c-222138b7c34a\",\"user\":{\"username\":\""+username+"\",\"credits\":0,\"ships\":[],\"loans\":[]}}");
    }

    public Response getLoans(String token) {
        return generateGeneric("{\"loans\":[{\"amount\":200000,\"collateralRequired\":false,\"rate\":40,\"termInDays\":2,\"type\":\"STARTUP\"}]}");
    }

    public Response takeLoan(String token, String username, String loanType) {
        return generateGeneric("{\"credits\":200000,\"loan\":{\"due\":\"2021-04-21T07:01:42.868Z\",\"id\":\"ckno92mhi0049iiopc2kmylkz\",\"repaymentAmount\":280000,\"status\":\"CURRENT\",\"type\":\"STARTUP\"}}");
    }

    public Response seeActiveLoans(String token, String username) {
        return generateGeneric("{\"loans\":[{\"id\":\"ckoqp3omm8162391bs6waoxies9\",\"due\":\"2021-05-18T04:45:40.840Z\",\"repaymentAmount\":280000,\"status\":\"CURRENT\",\"type\":\"STARTUP\"}]}");
    }

    public Response payLoan(String token, String username, String loanID) {
        return generateGeneric("{\"loan\":\"paid\"}");
    }

    public Response getShips(String token, String shipClass) {
        return generateGeneric("{\"ships\":[{\"class\":\"MK-I\",\"manufacturer\":\"Gravager\",\"maxCargo\":300,\"plating\":10,\"purchaseLocations\":[{\"location\":\"OE-PM-TR\",\"price\":184000},{\"location\":\"OE-NY\",\"price\":184000}],\"speed\":1,\"type\":\"GR-MK-I\",\"weapons\":5},{\"class\":\"MK-I\",\"manufacturer\":\"Jackshaw\",\"maxCargo\":100,\"plating\":5,\"purchaseLocations\":[{\"location\":\"OE-PM-TR\",\"price\":94000},{\"location\":\"OE-NY\",\"price\":94000}],\"speed\":2,\"type\":\"JW-MK-I\",\"weapons\":5},{\"class\":\"MK-I\",\"manufacturer\":\"Electrum\",\"maxCargo\":100,\"plating\":5,\"purchaseLocations\":[{\"location\":\"OE-NY\",\"price\":302400}],\"speed\":2,\"type\":\"EM-MK-I\",\"weapons\":10}]}");
    }

    public Response buyShip(String token, String username, String location, String type)  {
        return generateGeneric("{\"credits\":178875,\"ship\":{\"cargo\":[],\"class\":\"MK-I\",\"id\":\"ckno9324k0079iiop71j5nrob\",\"location\":\"OE-PM-TR\",\"manufacturer\":\"Jackshaw\",\"maxCargo\":50,\"plating\":5,\"spaceAvailable\":50,\"speed\":1,\"type\":\"JW-MK-I\",\"weapons\":5,\"x\":23,\"y\":-28}}");
    }

    public Response seeOwnedShips(String token, String username) {
        return generateGeneric("{\"ships\":[{\"id\":\"ckoqp8k568843171bs6vfw1x0tj\",\"location\":\"OE-PM-TR\",\"x\":21,\"y\":-24,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5}]}");
    }

    public Response shipInfo(String token, String username, String shipID) {
        return generateGeneric("{\"ship\":{\"id\":\"ckoqp8k568843171bs6vfw1x0tj\",\"location\":\"OE-PM-TR\",\"x\":21,\"y\":-24,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5}}");
    }

    public Response purchaseFuel(String token, String username, String shipID, int quantity) {
        return generateGeneric("{\"credits\":105960,\"order\":{\"good\":\"FUEL\",\"pricePerUnit\":2,\"quantity\":20,\"total\":40},\"ship\":{\"cargo\":[{\"good\":\"FUEL\",\"quantity\":20,\"totalVolume\":20}],\"class\":\"MK-I\",\"id\":\"ckoqp8k568843171bs6vfw1x0tj\",\"location\":\"OE-PM-TR\",\"manufacturer\":\"Jackshaw\",\"maxCargo\":100,\"plating\":5,\"spaceAvailable\":80,\"speed\":2,\"type\":\"JW-MK-I\",\"weapons\":5,\"x\":21,\"y\":-26}}");
    }

    public Response viewMarketplaceDetails(String token, String location) {
        return generateGeneric("{\"location\":{\"marketplace\":[{\"pricePerUnit\":4,\"quantityAvailable\":406586,\"symbol\":\"METALS\",\"volumePerUnit\":1},{\"pricePerUnit\":231,\"quantityAvailable\":5407,\"symbol\":\"MACHINERY\",\"volumePerUnit\":4},{\"pricePerUnit\":8,\"quantityAvailable\":406586,\"symbol\":\"CHEMICALS\",\"volumePerUnit\":1},{\"pricePerUnit\":1,\"quantityAvailable\":462806,\"symbol\":\"FUEL\",\"volumePerUnit\":1},{\"pricePerUnit\":127,\"quantityAvailable\":19961,\"symbol\":\"SHIP_PLATING\",\"volumePerUnit\":2},{\"pricePerUnit\":30,\"quantityAvailable\":403,\"symbol\":\"DRONES\",\"volumePerUnit\":2},{\"pricePerUnit\":1283,\"quantityAvailable\":8738,\"symbol\":\"SHIP_PARTS\",\"volumePerUnit\":5}],\"name\":\"Tritus\",\"symbol\":\"OE-PM-TR\",\"type\":\"MOON\",\"x\":21,\"y\":-26}}");
    }

    public Response purchaseGoods(String token, String username, String shipID, String good, int quantity) {
        return generateGeneric("{\"credits\":178725,\"order\":{\"good\":\"METALS\",\"pricePerUnit\":3,\"quantity\":30,\"total\":90},\"ship\":{\"cargo\":[{\"good\":\"FUEL\",\"quantity\":20,\"totalVolume\":20},{\"good\":\"METALS\",\"quantity\":30,\"totalVolume\":30}],\"class\":\"MK-I\",\"id\":\"ckne4w8me01141ds62dnui0c8\",\"location\":\"OE-PM-TR\",\"manufacturer\":\"Jackshaw\",\"maxCargo\":50,\"plating\":5,\"spaceAvailable\":0,\"speed\":1,\"type\":\"JW-MK-I\",\"weapons\":5,\"x\":23,\"y\":-28}}");
    }

    public Response getNearbyLocations(String token, String system, String type) {
        return generateGeneric("{\"locations\":[{\"name\":\"Carth\",\"symbol\":\"OE-CR\",\"type\":\"PLANET\",\"x\":16,\"y\":17},{\"name\":\"Koria\",\"symbol\":\"OE-KO\",\"type\":\"PLANET\",\"x\":-48,\"y\":-7},{\"name\":\"Ucarro\",\"symbol\":\"OE-UC\",\"type\":\"PLANET\",\"x\":-75,\"y\":82},{\"name\":\"Prime\",\"symbol\":\"OE-PM\",\"type\":\"PLANET\",\"x\":20,\"y\":-25}]}");
    }

    public Response createFlightPlan(String token, String username, String shipId, String destination) {
        return generateGeneric("{\"flightPlan\":{\"arrivesAt\":\"2021-03-08T06:41:19.658Z\",\"departure\":\"OE-PM-TR\",\"destination\":\"OE-PM\",\"distance\":1,\"fuelConsumed\":1,\"fuelRemaining\":19,\"id\":\"ckm07t6ki0038060jv7b2x5gk\",\"shipId\":\"ckm07ezq50354ti0j1drcey9v\",\"terminatedAt\":null,\"timeRemainingInSeconds\":67}}");
    }

    public Response getFlightPlans(String token, String system) {
        return null;
    }

    public Response viewFlightPlan(String token, String username, String flightPlanID) {
        return generateGeneric("{\"flightPlan\":{\"arrivesAt\":\"2021-03-08T06:41:19.658Z\",\"departure\":\"OE-PM-TR\",\"destination\":\"OE-PM\",\"distance\":1,\"fuelConsumed\":1,\"fuelRemaining\":19,\"id\":\"ckm07t6ki0038060jv7b2x5gk\",\"shipId\":\"ckm07ezq50354ti0j1drcey9v\",\"terminatedAt\":\"2021-03-08T06:41:18.752Z\",\"timeRemainingInSeconds\":0}}");
    }

    public Response sellGoods(String token, String username, String shipID, String good, int quantity) {
        return generateGeneric("{\"credits\":178935,\"order\":{\"good\":\"METALS\",\"pricePerUnit\":7,\"quantity\":30,\"total\":210},\"ship\":{\"cargo\":[{\"good\":\"FUEL\",\"quantity\":18,\"totalVolume\":18}],\"class\":\"MK-I\",\"id\":\"ckne4w8me01141ds62dnui0c8\",\"location\":\"OE-PM\",\"manufacturer\":\"Jackshaw\",\"maxCargo\":50,\"plating\":5,\"spaceAvailable\":32,\"speed\":1,\"type\":\"JW-MK-I\",\"weapons\":5,\"x\":20,\"y\":-25}}");
    }
}