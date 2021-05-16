package SCD2_2021_T3;

import SCD2_2021_T3.containers.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class Interface {
    private APIFacade api;

    private String username;
    private String token;
    private String status;
    private String location;

    private List<String> availableLoans = new ArrayList<>();
    private List<String[]> currentLoans = new ArrayList<>();
    private List<String> availableShips = new ArrayList<>();
    private List<String[]> currentShips = new ArrayList<>();

    private String flightPlanID;


    public Interface(APIFacade api) {
        this.api = api;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getLastStatus() {
        return status;
    }

    public boolean checkStatus() {
        Response r = api.checkStatus();
        return r.success();
    }

    public List<String> getAvailableLoans() {
        return this.availableLoans;
    }

    public List<String[]> getCurrentLoans() {
        return this.currentLoans;
    }

    public List<String> getAvailableShips() {
        return this.availableShips;
    }

    public List<String[]> getCurrentShips() {
        return this.currentShips;
    }

    public String getFlightPlanID() {
        return this.flightPlanID;
    }

    public boolean newUser(String username) {
        Response r = api.claimUsername(username);
        this.status = r.status();

        if (!r.success()) {
            if (r.code() == 409) {
                this.status = "Username is already taken";
            }

            if (r.code() == 401) {
                this.status = "Please enter a username";
            }

            return false;
        }

        JSONObject message = r.message();

        this.username = (String) ((JSONObject) message.get("user")).get("username");
        this.token = (String) message.get("token");
        // this.credits = (Long) message.get("credits");

        return true;
    }

    public boolean login(String username, String token) {
        Response r = api.login(token, username);
        this.status = r.status();
        
        if (r.success()) {
            this.username = username;
            this.token = token;
            // this.credits = (Long) r.message().get("credits");
        } else {
            if (r.code() == 401) {
                this.status = "Invalid credentials";
            }

            if (r.code() == 404) {
                this.status = "Please enter a username and token";
            }
        }

        return r.success();
    }

    public Long getCredits() {
        Response r = api.login(token, username);

        return (Long) ((JSONObject) r.message().get("user")).get("credits");
    }

    public String availableLoans() {
        Response r = api.getLoans(token);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        // Format text
        JSONArray loans = (JSONArray) r.message().get("loans");
        String ret = "Available Loans:";
        Iterator i = loans.iterator();

        while (i.hasNext()) {
            JSONObject loan = (JSONObject) i.next();
            availableLoans.add((String) loan.get("type"));
            ret += "\n\tName: " + (String) loan.get("type");
            ret += "\n\tAmount: " + (Long) loan.get("amount");
            ret += "\n\tCollateral Required: " + (Boolean) loan.get("collateralRequired");
            ret += "\n\tRate: " + (Long) loan.get("rate");
            ret += "\n\tTerm (days): " + (Long) loan.get("termInDays");

            ret += "\n";
        }

        return ret;
    }

    public String currentLoans() {
        Response r = api.seeActiveLoans(token, username);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        // Format text
        JSONArray loans = (JSONArray) r.message().get("loans");
        String ret = "Current Loans:";
        Iterator i = loans.iterator();

        while (i.hasNext()) {
            JSONObject loan = (JSONObject) i.next();
            // availableLoans.add((String) loan.get("type"));
            ret += "\n\tName: " + (String) loan.get("type");
            ret += "\n\tAmount: " + (Long) loan.get("repaymentAmount");
            ret += "\n\tDue: " + (String) loan.get("due");
            ret += "\n\tID: " + (String) loan.get("id");
            ret += "\n\tStatus: " + (String) loan.get("status");

            ret += "\n";
        }

        return ret;
    }

    

    public String startLoan(String type) {
        Response r = api.takeLoan(token, username, type);

        if (!r.success()) {
            this.status = r.status();

            if (r.code() == 422) {
                this.status = "Loan already started";
            }

            return "";
        }

        // Format text
        JSONObject loan = (JSONObject) r.message().get("loan");
        String ret = "Loan Started:";

        // credits += r.message().get("credits");

        ret += "\n\tType: " + (String) loan.get("type");
        ret += "\n\tDue: " + (String) loan.get("due");
        ret += "\n\tLoan ID: " + (String) loan.get("id");
        ret += "\n\tRepayment Amount: " + (Long) loan.get("repaymentAmount");
        ret += "\n\tStatus: " + (String) loan.get("status");

        currentLoans.add(new String[] {(String) loan.get("type"), (String) loan.get("id")});

        return ret;
    }

    public String payLoan(String type) {
        Response r = api.payLoan(token, username, type);
        for (String[] arr : currentLoans) {
            if (arr[0] == type) {
                // System.out.println(arr[1]);
                r = api.payLoan(token, username, arr[1]);
            }
        }

        if (!r.success()) {
            this.status = r.status();

            if (r.code() == 400) {
                this.status = "Insufficient funds";
            } 

            if (r.code() == 404) {
                this.status = "Loan not found";
            }
            return "";
        }

        // Format text

        return "Loan paid";
    }


    public String availableShips() {
        Response r = api.getShips(token, "MK-I");

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        // Format text
        JSONArray ships = (JSONArray) r.message().get("ships");
        String ret = "Available Ships:";
        Iterator i = ships.iterator();

        while (i.hasNext()) {
            JSONObject loan = (JSONObject) i.next();
            ret += "\n\tName: " + (String) loan.get("type");
            ret += "\n\tClass: " + (String) loan.get("class");
            ret += "\n\tManufacturer: " + (String) loan.get("manufacturer");
            ret += "\n\tMax Cargo: " + (Long) loan.get("maxCargo");
            ret += "\n\tPlating: " + (Long) loan.get("plating");
            
            JSONArray locations = (JSONArray) loan.get("purchaseLocations");
            ret += "\n\tPurchase Locations: ";
            Iterator i2 = locations.iterator();
            while (i2.hasNext()) {
                JSONObject location = (JSONObject) i2.next();
                ret += "\n\t\tLocation: " +  (String) location.get("location") + " Price: " + (Long) location.get("price");
                availableShips.add((String) loan.get("type") + ":" + (String) location.get("location"));
            }
            ret += "\n";
        }

        return ret;

        // return r.message().toString();
    }

    public String buyShip(String shipInfo) {
        String[] spl = shipInfo.split(":");

        Response r = api.buyShip(token, username, spl[1], spl[0]);

        if (!r.success()) {
            this.status = r.status();

            if (r.code() == 400) {
                this.status = "Not enough funds";
            }

            return "";
        }


        // Format text
        JSONObject ship = (JSONObject) r.message().get("ship");
        this.location = (String) ship.get("location");

        String ret = "Ship Bought:";

        // credits += r.message().get("credits");

        ret += "\n\tName: " + (String) ship.get("type");
        ret += "\n\tClass: " + (String) ship.get("class");
        ret += "\n\tID: " + (String) ship.get("id");
        ret += "\n\tLocation: " + (String) ship.get("location");
        ret += "\n\tManufacturer: " + (String) ship.get("manufacturer");
        ret += "\n\tMax Cargo: " + (Long) ship.get("maxCargo");
        ret += "\n\tPlating: " + (Long) ship.get("plating");
        ret += "\n\tSpace Available: " + (Long) ship.get("spaceAvailable");
        ret += "\n\tSpeed: " + (Long) ship.get("speed");
        ret += "\n\tWeapons: " + (Long) ship.get("weapons");
        ret += "\n\tCoords: " + "(" + (Long) ship.get("x") + "," + (Long) ship.get("y") + ")";

        currentShips.add(new String[] {(String) ship.get("type") + ":" + (String) ship.get("location"), (String) ship.get("id")});

        return ret;

        // return r.message().toString();
    }

    public String currentShips() {
        Response r = api.seeOwnedShips(token, username);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        // Format text
        JSONArray ships = (JSONArray) r.message().get("ships");
        String ret = "Owned Ships:";
        Iterator i = ships.iterator();

        while (i.hasNext()) {
            JSONObject ship = (JSONObject) i.next();
            availableLoans.add((String) ship.get("type"));
            ret += "\n\tName: " + (String) ship.get("type");
            ret += "\n\tClass: " + (String) ship.get("class");
            ret += "\n\tID: " + (String) ship.get("id");
            ret += "\n\tLocation: " + (String) ship.get("location");
            ret += "\n\tManufacturer: " + (String) ship.get("manufacturer");
            ret += "\n\tMax Cargo: " + (Long) ship.get("maxCargo");
            ret += "\n\tPlating: " + (Long) ship.get("plating");
            ret += "\n\tSpace Available: " + (Long) ship.get("spaceAvailable");
            ret += "\n\tSpeed: " + (Long) ship.get("speed");
            ret += "\n\tWeapons: " + (Long) ship.get("weapons");
            ret += "\n\tCoords: " + "(" + (Long) ship.get("x") + "," + (Long) ship.get("y") + ")";
            ret += "\n";
        }

        return ret;

        // return r.message().toString();
    }

    public String buyFuel(int amount, String shipID) {
        Response r = api.purchaseFuel(token, username, shipID, amount);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONObject ship = (JSONObject) r.message().get("ship");
        String ret = "Fuel Bought:";

        // credits += r.message().get("credits");

               

        ret += "\n\tName: " + (String) ship.get("type");
        ret += "\n\tClass: " + (String) ship.get("class");
        ret += "\n\tID: " + (String) ship.get("id");
        ret += "\n\tLocation: " + (String) ship.get("location");
        ret += "\n\tManufacturer: " + (String) ship.get("manufacturer");
        ret += "\n\tMax Cargo: " + (Long) ship.get("maxCargo");
        ret += "\n\tPlating: " + (Long) ship.get("plating");
        ret += "\n\tSpace Available: " + (Long) ship.get("spaceAvailable");
        ret += "\n\tSpeed: " + (Long) ship.get("speed");
        ret += "\n\tWeapons: " + (Long) ship.get("weapons");
        ret += "\n\tCoords: " + "(" + (Long) ship.get("x") + "," + (Long) ship.get("y") + ")";
        ret += "\n\tCargo: ";

        JSONArray cargoes =(JSONArray) ship.get("cargo");
        Iterator i = cargoes.iterator();

        int fuelAmount = 0;

        while (i.hasNext()) {
            JSONObject cargo = (JSONObject) i.next();
            // System.out.println(cargo.toString());

            ret += "\n\t\tGood: " + (String) cargo.get("good");
            ret += "\n\t\tQuantity: " + (Long) cargo.get("quantity");
            ret += "\n\t\tVolume: " + (Long) cargo.get("totalVolume");
            ret += "\n";
        } 

        return ret;
    }

    public String viewMarketplaceDetails(String location) {
        Response r = api.viewMarketplaceDetails(token, location);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONArray goods = (JSONArray) ((JSONObject) r.message().get("location")).get("marketplace");
        String ret = "Marketplace:";
        Iterator i = goods.iterator();

        while (i.hasNext()) {
            JSONObject good = (JSONObject) i.next();

            ret += "\n\tGood: " + (String) good.get("symbol");
            ret += "\n\tPrice Per Unit: " + (Long) good.get("pricePerUnit");
            ret += "\n\tQuantity Available: " + (Long) good.get("quantityAvailable");
            ret += "\n\tVolume Per Unit: " + (Long) good.get("volumePerUnit");
            ret += "\n";
        }

        return ret;
    }

    public String buyGoods(String type, int amount, String id) {
        Response r = api.purchaseGoods(token, username, id, type, amount);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONObject ship = (JSONObject) r.message().get("ship");
        String ret = "Goods Bought:";

        // credits += r.message().get("credits");

        ret += "\n\tName: " + (String) ship.get("type");
        ret += "\n\tClass: " + (String) ship.get("class");
        ret += "\n\tID: " + (String) ship.get("id");
        ret += "\n\tLocation: " + (String) ship.get("location");
        ret += "\n\tManufacturer: " + (String) ship.get("manufacturer");
        ret += "\n\tMax Cargo: " + (Long) ship.get("maxCargo");
        ret += "\n\tPlating: " + (Long) ship.get("plating");
        ret += "\n\tSpace Available: " + (Long) ship.get("spaceAvailable");
        ret += "\n\tSpeed: " + (Long) ship.get("speed");
        ret += "\n\tWeapons: " + (Long) ship.get("weapons");
        ret += "\n\tCoords: " + "(" + (Long) ship.get("x") + "," + (Long) ship.get("y") + ")";
        ret += "\n\tCargo: ";

        JSONArray cargoes =(JSONArray) ship.get("cargo");
        Iterator i = cargoes.iterator();

        while (i.hasNext()) {
            JSONObject cargo = (JSONObject) i.next();
            // System.out.println(cargo.toString());

            ret += "\n\t\tGood: " + (String) cargo.get("good");
            ret += "\n\t\tQuantity: " + (Long) cargo.get("quantity");
            ret += "\n\t\tVolume: " + (Long) cargo.get("totalVolume");
            ret += "\n";
        } 

        return ret;
    }

    public String sellGoods(String type, int amount, String id) {
        Response r = api.sellGoods(token, username, id, type, amount);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONObject ship = (JSONObject) r.message().get("ship");
        String ret = "Goods Sold:";

        // credits += r.message().get("credits");

        ret += "\n\tName: " + (String) ship.get("type");
        ret += "\n\tClass: " + (String) ship.get("class");
        ret += "\n\tID: " + (String) ship.get("id");
        ret += "\n\tLocation: " + (String) ship.get("location");
        ret += "\n\tManufacturer: " + (String) ship.get("manufacturer");
        ret += "\n\tMax Cargo: " + (Long) ship.get("maxCargo");
        ret += "\n\tPlating: " + (Long) ship.get("plating");
        ret += "\n\tSpace Available: " + (Long) ship.get("spaceAvailable");
        ret += "\n\tSpeed: " + (Long) ship.get("speed");
        ret += "\n\tWeapons: " + (Long) ship.get("weapons");
        ret += "\n\tCoords: " + "(" + (Long) ship.get("x") + "," + (Long) ship.get("y") + ")";
        ret += "\n\tCargo: ";

        JSONArray cargoes =(JSONArray) ship.get("cargo");
        Iterator i = cargoes.iterator();

        while (i.hasNext()) {
            JSONObject cargo = (JSONObject) i.next();
            // System.out.println(cargo.toString());

            ret += "\n\t\tGood: " + (String) cargo.get("good");
            ret += "\n\t\tQuantity: " + (Long) cargo.get("quantity");
            ret += "\n\t\tVolume: " + (Long) cargo.get("totalVolume");
            ret += "\n";
        } 

        return ret;
    }

    public String nearbyLocations() {
        // System.out.println(this.location);
        Response r = api.getNearbyLocations(token, this.location.substring(0,2), "PLANET");

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONArray locations = (JSONArray) r.message().get("locations");
        String ret = "Nearby Systems:";
        Iterator i = locations.iterator();

        while (i.hasNext()) {
            JSONObject l = (JSONObject) i.next();

            ret += "\n\tName: " + (String) l.get("name");
            ret += "\n\tSymbol: " + (String) l.get("symbol");
            ret += "\n\tType: " + (String) l.get("type");
            ret += "\n\tCoords: " + "(" + (Long) l.get("x") + "," + (Long) l.get("y") + ")";
            ret += "\n";
        }

        return ret;
    }

    public String startFlightPlan(String id, String destination) {
        // System.out.println(this.location);
        Response r = api.createFlightPlan(token, username, id, destination);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        this.location = destination;

        JSONObject plan = (JSONObject) r.message().get("flightPlan");
        String ret = "Flight Plan Started:";

        this.flightPlanID = (String) plan.get("id");

        ret += "\n\tDeparture: " + (String) plan.get("departure");
        ret += "\n\tDestination: " + (String) plan.get("destination");
        ret += "\n\tArrives At: " + (String) plan.get("arrivesAt");
        ret += "\n\tDistance: " + (Long) plan.get("distance");
        ret += "\n\tFuel Consumed: " + (Long) plan.get("fuelConsumed");
        ret += "\n\tFuel Remaining: " + (Long) plan.get("fuelRemaining");
        ret += "\n\tID: " + (String) plan.get("id");
        ret += "\n\tShip ID: " + (String) plan.get("shipId");
        ret += "\n\tTerminated At: " + (String) plan.get("terminatedAt");
        ret += "\n\tTime Remaining in Seconds: " + (Long) plan.get("timeRemainingInSeconds");
        
        return ret;
    }

    public String viewFlightPlan() {
        // System.out.println(this.location);
        Response r = api.viewFlightPlan(token, username, this.flightPlanID);

        if (!r.success()) {
            this.status = r.status();
            return "";
        }

        JSONObject plan = (JSONObject) r.message().get("flightPlan");
        String ret = "Flight Plan:";

        this.flightPlanID = (String) plan.get("id");

        ret += "\n\tDeparture: " + (String) plan.get("departure");
        ret += "\n\tDestination: " + (String) plan.get("destination");
        ret += "\n\tArrives At: " + (String) plan.get("arrivesAt");
        ret += "\n\tDistance: " + (Long) plan.get("distance");
        ret += "\n\tFuel Consumed: " + (Long) plan.get("fuelConsumed");
        ret += "\n\tFuel Remaining: " + (Long) plan.get("fuelRemaining");
        ret += "\n\tID: " + (String) plan.get("id");
        ret += "\n\tShip ID: " + (String) plan.get("shipId");
        ret += "\n\tTerminated At: " + (String) plan.get("terminatedAt");
        ret += "\n\tTime Remaining in Seconds: " + (Long) plan.get("timeRemainingInSeconds");
        
        return ret;
    }
}