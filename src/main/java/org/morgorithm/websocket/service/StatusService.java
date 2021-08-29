package org.morgorithm.websocket.service;



import org.morgorithm.websocket.dto.EventDTO;
import org.morgorithm.websocket.dto.RealTimeStatusDTO;



public interface StatusService {

    RealTimeStatusDTO getFacilityStatus();

}
