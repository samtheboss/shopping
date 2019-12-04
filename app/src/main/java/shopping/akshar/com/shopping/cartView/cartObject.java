package shopping.akshar.com.shopping.cartView;

public class cartObject {
    private String rideId;
    private String requestTime;

    public cartObject(String rideId, String requestTime) {
        this.rideId = rideId;
        this.requestTime = requestTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }


}
