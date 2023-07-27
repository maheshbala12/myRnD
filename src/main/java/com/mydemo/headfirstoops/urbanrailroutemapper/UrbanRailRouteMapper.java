package com.mydemo.headfirstoops.urbanrailroutemapper;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.InitialContext;

public class UrbanRailRouteMapper {
	Hashtable<String, LinkedHashMap<String, SubUrbanStation>> railRoutesAndStations = null;

	public Hashtable<String, LinkedHashMap<String, SubUrbanStation>> getRailRoutesAndStations() {
		if (railRoutesAndStations == null)
			loadRailRouteMaps();
		return railRoutesAndStations;
	}

	// Setup urban-lines & stations (read from file); urban-lines each have a section listing stations on each newline,
	// separated by a blank line. Also list if a station is a junction for 2/ more urban-lines.
	// Each such section names the urban-line on first such line. Refer <mumbai_urban_rail_networks.txt>, which uses Mumbai urban rail network as example.
	// Assign weight to all stations (based on UP/DOWN and position of interchanges)
	void loadRailRouteMaps() {
		SubUrbanRailNetworkReader reader = new SubUrbanRailNetworkReader();
		railRoutesAndStations = reader.loadNetworksAndStations();
	}

	// Main piece of logic- prints the list of stations in passenger itinerary & returns a list of SubUrbanStation objects.
	// Given source/destination stations for a traveler, Calculate:
	// if both on same urban rail-line
	// 		determine the direction to take & no of stops along the network line
	// if destination lies on different urban rail-lines
	// 		determine the direction to take & no of stops along each network line(s), hopping through interchanges
	List<String> calculateCompleteRailRoutes(String srcStationStr, String destStationStr) {
		Map<String, LinkedHashMap<String, SubUrbanStation>> routes = getSourceAndDestRoutes(srcStationStr, destStationStr);
		List<String> itinerary = null;
		if(routes!=null && routes.size()>=1) {
			LinkedHashMap<String, SubUrbanStation> srcStnRoute = routes.get("Source");
			LinkedHashMap<String, SubUrbanStation> destStnRoute = routes.get("Destination");
			if(routes.size()==1) {// On same route
				itinerary = traverseInARailRoute(srcStnRoute, srcStationStr, destStationStr);
			}
			else {
				String interchange = srcStnRoute.entrySet().stream().filter(kv -> kv.getValue().isJunction() && destStnRoute.containsKey(kv.getKey())).map(kv->kv.getKey()).collect(Collectors.joining());
				
				itinerary = traverseInARailRoute(srcStnRoute, srcStationStr, interchange);
				itinerary.addAll(traverseInARailRoute(destStnRoute, interchange, destStationStr));
			}
		}
		return itinerary;
	}

	// Given source/destination inputs, return the source/destination routes
	Map<String, LinkedHashMap<String, SubUrbanStation>> getSourceAndDestRoutes(String srcStation,
			String destStation){
		Map<String, LinkedHashMap<String, SubUrbanStation>> routes = new HashMap<String, LinkedHashMap<String, SubUrbanStation>>();
		
		// 1. Without using functional construct.
		/*int routeCount = 0;
		for(SuburbanRailRoutesEnum line: SuburbanRailRoutesEnum.values()) {
			if(railRoutesAndStations.get(line.toString()).containsKey(srcStation)) {
				routes.put("Source", railRoutesAndStations.get(line.toString()));
				routeCount++;
			}
			if(railRoutesAndStations.get(line.toString()).containsKey(destStation)) {
				routes.put("Destination", railRoutesAndStations.get(line.toString()));
				routeCount++;
			}
			if(routeCount==2)
				break;
		}*/
		
		// 2. Using functional construct.
		// Does any line contains both stations? (No interchange required)
		routes = railRoutesAndStations.entrySet().stream()
				.filter(line -> (line.getValue().get(srcStation) != null) && line.getValue().get(destStation) != null)
				.collect(Collectors.toMap(
						line -> "Source", Map.Entry::getValue));
		
		// Interchange required
		if(!routes.containsKey("Source")) {
			routes = railRoutesAndStations.entrySet().stream()
					.filter(line -> (line.getValue().get(srcStation) != null) || line.getValue().get(destStation) != null)
					.collect(Collectors.toMap(
							line -> line.getValue().get(srcStation) != null ? "Source"
									: (line.getValue().get(destStation) != null ? "Destination" : null),
							Map.Entry::getValue));
		}
		return routes;
	}

	// Traverses between 2 stations on a rail line
	List<String> traverseInARailRoute(LinkedHashMap<String, SubUrbanStation> stationHashtable, String srcStation,
			String destStation) {

		int srcWeight = stationHashtable.get(srcStation).getWeightage();
		int destWeight = stationHashtable.get(destStation).getWeightage();

		boolean reverseDirection = srcWeight > destWeight; // Reverse traversal

		List<String> collect = stationHashtable.values().stream()
				.filter(s -> srcWeight > destWeight ? (s.getWeightage() <= srcWeight) && s.getWeightage() >= destWeight
						: (s.getWeightage() >= srcWeight) && s.getWeightage() <= destWeight)
				.sorted((s1, s2) -> reverseDirection ? destWeight - srcWeight : 1)
				.map(s -> s.getStationName() + ": " + s.getWeightage()).collect(Collectors.toList());
//				.forEach(System.out::println);
		return collect;
	}
}
