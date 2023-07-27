package com.mydemo.headfirstoops.urbanrailroutemapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.mydemo.headfirstoops.urbanrailroutemapper.SubUrbanStation;
import com.mydemo.headfirstoops.urbanrailroutemapper.SuburbanRailRoutesEnum;
import com.mydemo.headfirstoops.urbanrailroutemapper.UrbanRailRouteMapper;

public class UrbanRailRouteMapperTest {
	final static UrbanRailRouteMapper mapper = new UrbanRailRouteMapper();
	private static Hashtable<String, LinkedHashMap<String, SubUrbanStation>> railRoutesAndStations;

	@BeforeClass
	public static void setUp() throws Exception {
		railRoutesAndStations = mapper.getRailRoutesAndStations();
	}

	// 1. Check stats on rail routes loaded (as per mumbai_urban_rail_networks.txt)
	@Test
	public void testLoadRailRouteMaps() {
		// Station count for each line
		int num = railRoutesAndStations.entrySet().stream().collect(Collectors.summingInt(network1->network1.getValue().size()));
		assertEquals("Equal", 105, num);
	}

	// 2. Get source & destination routes
	// 	2.1	When travel route falls in the same line - No interchange needed
	@Test
	public void testGetTravelRouteSameLine() {
		String srcStation = "Cotton Green";
		String destStation = "Nerul";
		Map<String, LinkedHashMap<String, SubUrbanStation>> sourceAndDestRoutes = mapper.getSourceAndDestRoutes(srcStation, destStation);
		assertEquals(1, sourceAndDestRoutes.size());
		assertEquals(sourceAndDestRoutes.get("Source"), railRoutesAndStations.get(SuburbanRailRoutesEnum.HARBOUR_LINE.toString()));
	}
	
	// 	2.2	Travel requires changing rail routes - Interchange needed
	@Test
	public void testGetTravelRoutesInterchangeRequired() {
		String srcStation = "Elphinstone Road";
		String destStation = "Mulund";
		Map<String, LinkedHashMap<String, SubUrbanStation>> sourceAndDestRoutes = mapper.getSourceAndDestRoutes(srcStation, destStation);
		assertEquals(sourceAndDestRoutes.size(), 2);
		assertEquals(railRoutesAndStations.get(SuburbanRailRoutesEnum.WESTERN_LINE.toString()), sourceAndDestRoutes.get("Source"));
		assertEquals(railRoutesAndStations.get(SuburbanRailRoutesEnum.CENTRAL_LINE.toString()), sourceAndDestRoutes.get("Destination"));
		assertNotEquals(sourceAndDestRoutes.get("Source"), sourceAndDestRoutes.get("Destination"));
	}
	
	// 3. Get complete rail route (single line)
	@Test
	public void testGetItinerarySingleLIne() {
		String srcStation = "Cotton Green";
		String destStation = "Nerul";
		List<String> sourceAndDestRoutes = mapper.traverseInARailRoute(railRoutesAndStations.get(SuburbanRailRoutesEnum.HARBOUR_LINE.toString()), srcStation, destStation);
		sourceAndDestRoutes.stream().forEach(System.out::println);
		assertEquals(sourceAndDestRoutes.size(), 14);
	}

	// 4. Get complete rail route (interchange required)
	@Test
	public void testGetItineraryInterchangeRequired() {
		String srcStation = "Elphinstone Road";
		String destStation = "Mulund";
		List<String> sourceAndDestRoutes = mapper.calculateCompleteRailRoutes(srcStation, destStation);
		sourceAndDestRoutes.stream().forEach(System.out::println);
		assertEquals(sourceAndDestRoutes.size(), 13);
	}

	// 5. Get complete rail route (interchange required)
	@Test
	public void testGetItineraryIfLineContainsMultipleInterchanges() {
		String srcStation = "Bhandup";
		String destStation = "Airoli";
		List<String> sourceAndDestRoutes = mapper.calculateCompleteRailRoutes(srcStation, destStation);
		sourceAndDestRoutes.stream().forEach(System.out::println);
		assertEquals(sourceAndDestRoutes.size(), 7);
	}
	
}
