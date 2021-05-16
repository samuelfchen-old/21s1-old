package SCD2_2021_T3;

public interface APIFacade {
    public Response checkStatus();

    public Response claimUsername(String username);

    public Response login(String token, String username);

    public Response getLoans(String token);

    public Response takeLoan(String token, String username, String loanType); 

    public Response seeActiveLoans(String token, String username);

    public Response payLoan(String token, String username, String loanID);

    public Response getShips(String token, String shipClass);

    public Response buyShip(String token, String username, String location, String type);

    public Response seeOwnedShips(String token, String username);

    public Response shipInfo(String token, String username, String shipID);

    public Response purchaseFuel(String token, String username, String shipID, int quantity);

    public Response viewMarketplaceDetails(String token, String location);

    public Response purchaseGoods(String token, String username, String shipID, String good, int quantity);

    public Response getNearbyLocations(String token, String system, String type);

    public Response createFlightPlan(String token, String username, String shipId, String destination);

    public Response getFlightPlans(String token, String system);

    public Response viewFlightPlan(String token, String username, String flightPlanID);

    public Response sellGoods(String token, String username, String shipID, String good, int quantity);
}