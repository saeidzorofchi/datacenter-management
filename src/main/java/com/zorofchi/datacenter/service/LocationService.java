package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.Location;
import com.zorofchi.datacenter.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }
}