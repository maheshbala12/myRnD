package com.mydemo.headfirstoops.urbanrailroutemapper;

public enum SuburbanRailRoutesEnum {
	CENTRAL_LINE, WESTERN_LINE, HARBOUR_LINE, TRANS_HARBOUR_LINE;

	@Override
	public String toString() {
		switch (this) {
    		case CENTRAL_LINE:
    			return "Central Line";
    		case WESTERN_LINE:
    			return "Western Line";
    		case HARBOUR_LINE:
    			return "Harbour Line";
    		case TRANS_HARBOUR_LINE:
    			return "Trans-Harbour Line";
    		default:
    			return "Central Line";
		}
	}	
}
