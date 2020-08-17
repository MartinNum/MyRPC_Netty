package Client.routing;

import java.util.Objects;

public class RouteUnit {
    private String address;
    private int port;
    private int weight = -1;

    public RouteUnit(String address, int port, int weight){
        this.address = address;
        this.port = port;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weights) {
        this.weight = weights;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteUnit)) return false;
        RouteUnit routeUnit = (RouteUnit) o;
        return port == routeUnit.port &&
                weight == routeUnit.weight &&
                Objects.equals(address, routeUnit.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port, weight);
    }
}
