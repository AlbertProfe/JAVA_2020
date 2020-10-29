package service;


import java.util.ArrayList;

import model.Bird;


//A DB made from a list
//Contains all bird objects created in the program	
public class BirdDB {
	
	
	//create ArrayObject of Bird objects
	private ArrayList<Bird> birds;
	private String dbName;

	public BirdDB( String name) {
		this.birds =  new ArrayList<Bird>();
		this.setDbName(name);
	}
	
	
	//methods
	public ArrayList<Bird> getArray(){
		
		return this.birds;
	}
	
	public Bird getBird(int location) {
		
		return this.birds.get(location);
	}
	
	public int arraySize() {
		
		return this.birds.size();
	}
	
	////add bird
	public void addBird(Bird bird) {
		
		this.birds.add(bird);
		//System.out.println("Array length:" + birds.size() ); // Just for checking purposes
		
		//newBird.append("name", bird.getName()).append("latinName", bird.getNameLatin()).append("observations", bird.getObservations());
		//collection.insertOne(newBird);
	}
	
	////dropBird
	public void deleteBird(Bird bird) {
		
		//this.birds.remove(this.birds.indexOf(bird));
		this.birds.remove(bird);
		
		//collection.deleteOne(eq("i", 110));
	}
	
	////addObservation
	public void addObservation(Bird bird, int observations) {	
	
		bird.setObservations(bird.getObservations() + observations);
	}
	
	////showBird
	public void showBird(Bird bird) {
		
		bird.toString();
	}
		
	
	//toString
	@Override
	public String toString() {
		String chain = "\rBirds list: \n";
		for (Bird bird : this.birds) {
			chain += "\t" + bird.getName() + ", " + bird.getObservations() + " obs.\n";	
			//System.out.println("Chain status: "+ chain);
		}
		return chain;

	}


	public ArrayList<Bird> getBirds() {
		return birds;
	}


	public void setBirds(ArrayList<Bird> birds) {
		this.birds = birds;
	}
	
	


	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	
	
	
	

}

