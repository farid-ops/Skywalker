package monckey.chopper.service;

import monckey.chopper.entity.Shipment;

import javax.validation.constraints.Min;

public interface ShipmentService {

    Iterable<Shipment> getShipementById(@Min(value = 1L, message = "Invalid ID") String shipmentId);
}
