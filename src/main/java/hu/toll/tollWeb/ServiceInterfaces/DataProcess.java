package hu.toll.tollWeb.ServiceInterfaces;

import hu.toll.tollWeb.Entity.Place;

import java.util.List;

public interface DataProcess {
    public List<Place> getPlaces();
    public void process() throws Exception;
    public String save(Place place) throws Exception;
}