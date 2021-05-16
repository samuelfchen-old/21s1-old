package SCD2_2021_T3;


import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;

import org.apache.http.client.methods.*;
// import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
// import org.apache.commons.httpclient.NameValuePair; 

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OnlineAPI implements APIFacade {

    private CloseableHttpClient client;
    private JSONParser parser;

    public OnlineAPI() {
        client = HttpClients.createDefault();
        parser = new JSONParser();
    }

    public Response processResponse(HttpResponse response) {
        try {
            int code = response.getStatusLine().getStatusCode();
            String status = String.valueOf(response.getStatusLine());
            String reason = response.getStatusLine().getReasonPhrase();
            JSONObject message = null;

            boolean success = false;
            
            if (code / 100 == 2) {
                success = true;
            }

            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                if (result.length() > 0) {
                    // System.out.println(String.valueOf(code));
                    // System.out.println(result.length());
                    // System.out.println(result);
                    // System.out.println(response.getStatusLine());
                    message = (JSONObject) parser.parse(result);
                }
            }
            
            return new Response(success, status, code, reason, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response post(String url, String token) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                    .setUri(url)
                                    .addParameter("token", token)
                                    .build();

            HttpResponse response = client.execute(req);

            return processResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Response checkStatus() {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/status/")
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response claimUsername(String username) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/token")
                                            .addParameter("token", null)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response login(String token, String username) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/users/" + username)
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response getLoans(String token) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/loans")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response takeLoan(String token, String username, String loanType) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/loans")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("type", loanType)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response seeActiveLoans(String token, String username) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/loans")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response payLoan(String token, String username, String loanID) {
        try {
            HttpUriRequest req = RequestBuilder.put()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/loans/"+loanID)
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("token", null)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response getShips(String token, String shipClass) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/ships")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("class", shipClass)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response buyShip(String token, String username, String location, String type) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/ships")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("location", location)
                                            .addParameter("type", type)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response seeOwnedShips(String token, String username) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/users/"+ username +"/ships")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response shipInfo(String token, String username, String shipID) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/users/" + username + "/ships/" + shipID)
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response purchaseFuel(String token, String username, String shipID, int quantity) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/" + username + "/purchase-orders")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("shipId", shipID)
                                            .addParameter("good", "FUEL")
                                            .addParameter("quantity", String.valueOf(quantity))
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response viewMarketplaceDetails(String token, String location) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/locations/" + location + "/marketplace")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response purchaseGoods(String token, String username, String shipID, String good, int quantity) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/" + username + "/purchase-orders")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("shipId", shipID)
                                            .addParameter("good", good)
                                            .addParameter("quantity", String.valueOf(quantity))
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response getNearbyLocations(String token, String system, String type) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/systems/"+system+"/locations")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("type", type)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response createFlightPlan(String token, String username, String shipId, String destination) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/flight-plans")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("shipId", shipId)
                                            .addParameter("destination", destination)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response getFlightPlans(String token, String system) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/game/systems/"+system+"/flight-plans")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }


    public Response viewFlightPlan(String token, String username, String flightPlanID) {
        try {
            HttpUriRequest req = RequestBuilder.get()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/flight-plans/"+flightPlanID)
                                            .setHeader("Authorization", "Bearer " + token)
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public Response sellGoods(String token, String username, String shipID, String good, int quantity) {
        try {
            HttpUriRequest req = RequestBuilder.post()
                                            .setUri("https://api.spacetraders.io/users/"+username+"/sell-orders")
                                            .setHeader("Authorization", "Bearer " + token)
                                            .addParameter("shipId", shipID)
                                            .addParameter("good", good)
                                            .addParameter("quantity", String.valueOf(quantity))
                                            .build();

            HttpResponse response = client.execute(req);
            return processResponse(response);
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }
}