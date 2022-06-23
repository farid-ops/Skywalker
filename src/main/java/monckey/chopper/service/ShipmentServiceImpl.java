package monckey.chopper.service;

import monckey.chopper.entity.Shipment;
import monckey.chopper.repo.ShipmentRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository){
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Iterable<Shipment> getShipementById(@Min(value = 1L, message = "Invalid ID") String shipmentId) {
        return this.shipmentRepository.findAllById(List.of(UUID.fromString(shipmentId)));
    }
}
