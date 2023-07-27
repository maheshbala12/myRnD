package com.mydemo.headfirstoops.urbanrailroutemapper;

public class SubUrbanStation {
    private String stationName;
    private int weightage;
    private boolean junction;

    public SubUrbanStation(String stationName, int weightage, boolean junction) {
        this.stationName = stationName;
        this.weightage = weightage;
        this.junction = junction;
    }

    public String getStationName() {
        return stationName;
    }

    public int getWeightage() {
        return weightage;
    }

    public boolean isJunction() {
        return junction;
    }

	@Override
	public int hashCode() {
		return stationName.hashCode() + weightage;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null || !obj.getClass().equals(SubUrbanStation.class))
			return false;
		SubUrbanStation stnObj = (SubUrbanStation) obj;
		if(this==stnObj)
			return true;
		
		return (this.getStationName().equals(stnObj.getStationName()) && this.weightage==stnObj.getWeightage());
	}
    
    
}
