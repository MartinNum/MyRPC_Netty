package Client.routing;

import java.util.*;

public class RoutingInformation {

    HashMap<String, Set<RouteUnit>> routingTable = new HashMap<String, Set<RouteUnit>>();

    public void pullRoutingInfor(){

    }

    public void routingInformationUpdate(String serverName, List<String> childrens){
        Set<RouteUnit> newRouteUnits = new HashSet<>();
        for (String str : childrens){
            String[] strs = str.split(",");
            String adddress = strs[0];
            int port = Integer.valueOf(strs[1]);
            RouteUnit routeUnit = new RouteUnit(adddress, port, -1);
            newRouteUnits.add(routeUnit);
        }

        routingTable.put(serverName, newRouteUnits);
    }

    public void weightRoutingInformationUpdate(String serverName, List<String> childrens){
        Set<RouteUnit> newRouteUnits = new HashSet<>();
        for (String str : childrens){
            String[] strs = str.split(",");
            String adddress = strs[0];
            int port = Integer.valueOf(strs[1]);
            int weight = Integer.valueOf(strs[2]);
            RouteUnit routeUnit = new RouteUnit(adddress, port, weight);
            newRouteUnits.add(routeUnit);
        }

        routingTable.put(serverName, newRouteUnits);
    }
}
