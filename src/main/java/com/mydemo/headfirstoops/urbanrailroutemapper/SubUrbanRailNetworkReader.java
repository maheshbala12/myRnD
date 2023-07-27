package com.mydemo.headfirstoops.urbanrailroutemapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class SubUrbanRailNetworkReader {
    public static void main(String[] args) {
    	SubUrbanRailNetworkReader subUrbanRailNetworkReader = new SubUrbanRailNetworkReader();
		Hashtable<String, LinkedHashMap<String, SubUrbanStation>> networksAndStations = subUrbanRailNetworkReader.loadNetworksAndStations();
//    	subUrbanRailNetworkReader.display(networksAndStations);
    	LinkedHashMap<String, SubUrbanStation> stationHashtable = networksAndStations.get("Central Line");
    	List<String> directions = getDirectionsBetween(stationHashtable, "Diva", "Ghatkopar");
//    	directions.forEach(System.out::println);
    }
    
	public Hashtable<String, LinkedHashMap<String, SubUrbanStation>> loadNetworksAndStations() {
        String fileName = "mumbai_urban_rail_networks.txt"; // Input file name
        Hashtable<String, LinkedHashMap<String, SubUrbanStation>> subUrbanRailRoutes = new Hashtable<>();
        System.out.println(new File(fileName).getAbsolutePath());
//       	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        try (BufferedReader br = new BufferedReader(
        		new InputStreamReader(SubUrbanRailNetworkReader.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            String currentNetwork = "";
            int weightage = 1; // Starting weightage for the first section (rail line)
            LinkedHashMap<String, SubUrbanStation> stationHashtable = null;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    if (stationHashtable != null) {
                        subUrbanRailRoutes.put(currentNetwork, stationHashtable);
                        stationHashtable = null;
                    }
                    currentNetwork = "";
                    continue;
                } else if (line.endsWith(":")) {
                    if (stationHashtable != null) {
                        subUrbanRailRoutes.put(currentNetwork, stationHashtable);
                    }
                    currentNetwork = line.substring(0, line.length() - 1);
                    weightage = 1; // Reset weightage for a new section (rail line)
                    stationHashtable = new LinkedHashMap<>();
                    continue;
                }

                String[] stationInfo = line.split(" \\(");
                String stationName = stationInfo[0].trim();
                boolean junction = false;

                if (stationInfo.length > 1) {
                    junction = true;
                }

                SubUrbanStation station = new SubUrbanStation(stationName, weightage, junction);

                if (stationHashtable != null) {
                    stationHashtable.put(stationName, station);
                }

                weightage++; // Increment weightage for the next station
            }

            // If there are remaining stations after the last rail line
            if (stationHashtable != null && !currentNetwork.isEmpty()) {
                subUrbanRailRoutes.put(currentNetwork, stationHashtable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subUrbanRailRoutes;
	}
	
    private void display(Hashtable<String, LinkedHashMap<String, SubUrbanStation>> subUrbanRailRoutes) {
        // Accessing the populated SubUrbanRailRoutes hashtable
        for (String network : subUrbanRailRoutes.keySet()) {
            LinkedHashMap<String, SubUrbanStation> stationHashtable = subUrbanRailRoutes.get(network);
            System.out.println("\nNetwork: " + network);
            for (String stationName : stationHashtable.keySet()) {
                SubUrbanStation station = stationHashtable.get(stationName);
                System.out.println(String.format("%s: %d %s", stationName, station.getWeightage(), station.isJunction()?" (Interchange)": ""));
            }
        }
    }
    
	private static List<String> getDirectionsBetween(LinkedHashMap<String, SubUrbanStation> stationHashtable, String srcStation,
			String destStation) {

        int srcWeight = stationHashtable.get(srcStation).getWeightage();
        int destWeight = stationHashtable.get(destStation).getWeightage();
        
        boolean reverseDirection = srcWeight>destWeight; // Reverse traversal
        
        List<String> collect = stationHashtable.values().stream()
				.filter(s -> srcWeight > destWeight ? (s.getWeightage() <= srcWeight) && s.getWeightage() >= destWeight
						: (s.getWeightage() >= srcWeight) && s.getWeightage() <= destWeight
				)
				.sorted((s1,s2)->reverseDirection?destWeight-srcWeight:1)
				.map(s -> s.getStationName() + ": " + s.getWeightage())
				.collect(Collectors.toList());
		return collect;
				//.forEach(System.out::println);
        
    }

}




