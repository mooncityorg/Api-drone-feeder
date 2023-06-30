package com.futureh.drone.feeder.service;

import com.futureh.drone.feeder.dto.DroneDto;
import com.futureh.drone.feeder.model.Drone;
import com.futureh.drone.feeder.repository.DroneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DroneService class.
 */
@Service
public class DroneService {

  @Autowired
  private DroneRepository droneRepository;

  /** addDrone method.*/
  public Drone addDrone(DroneDto drone) {
    String name = drone.getName();
    String model = drone.getModel();
    Float capacityWeightInKg = drone.getCapacityWeightInKg();
    Drone newDrone = droneRepository.save(new Drone(name, model, capacityWeightInKg));
    return newDrone;
  }

  /** getDroneByName method.*/
  public Drone getDroneByName(String droneName) {
    List<Drone> drones = droneRepository.findAll();
    Drone drone = drones.stream()
        .filter(drn -> drn.getName().equals(droneName))
        .findAny().orElse(null);
    return drone;
  }

  /** getAllDrones method.*/
  public List<Drone> getAllDrones() {
    return droneRepository.findAll();
  }

  /** getDroneById method.*/
  public Drone getDroneById(Long id) {
    return droneRepository.findById(id).orElse(null);
  }

  /** removeDrone method.*/
  public Long removeDrone(Long id) {
    Drone drone = droneRepository.findById(id).orElse(null);
    if (drone != null) {
      droneRepository.delete(drone);
      return id;
    } else {
      return null;
    }
  }

  /** updateDrone method.*/
  public Drone updateDrone(Long id, DroneDto drone) {
    Drone droneUpdate = droneRepository.findById(id).orElse(null);
    if (droneUpdate != null) {
      droneUpdate.setName(drone.getName());
      droneUpdate.setModel(drone.getModel());
      droneUpdate.setCapacityWeightInKg(drone.getCapacityWeightInKg());
      droneRepository.save(droneUpdate);
      return droneRepository.save(droneUpdate);
    } else {
      return null;
    }
  }

}