package Life;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
	// to declare variables
	UnfoldingMap map;
	Map<String,Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> coutryMarkers;

	public void draw() {
		// draw a clean map
		map.draw();
	}
	


	public void setup() {
		// first setup a clean map and draw 
		size(800,600,OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this,map);
		
		//Load Life Expectancy from CSV
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
		println("Loaded " + lifeExpByCountry.size() + " data entries.");
		
		// Load country polygons and add them as markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		coutryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(coutryMarkers);
		
		// shade country markers by life expectancy 
		shadeCountries();
		
	}
	
	
	private void shadeCountries(){
		for (Marker marker : coutryMarkers){
			String coutryId = marker.getId();
			if (lifeExpByCountry.containsKey(coutryId)){
				float lifeExp = lifeExpByCountry.get(coutryId);
				// now encode values as brightness (values range between 40-90)
				int colorLevel = (int) map(lifeExp,40,90,10,255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
			} else {
				marker.setColor(color(150,150,150));
			}
		}
	}
			
			
	
	private HashMap<String,Float> loadLifeExpectancyFromCSV(String fileName){
		HashMap<String,Float> lifeExpMap = new HashMap<String,Float>();
		
		String[] rows = loadStrings(fileName);
		for (String row: rows){
			String[] columns = row.split(",");
			if(columns.length == 6 && !columns[5].equals("..")){
				lifeExpMap.put(columns[4],Float.parseFloat(columns[5]));
			}
		}
		return lifeExpMap;
		
	}
	
	

}
