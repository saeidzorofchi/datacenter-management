package com.zorofchi.datacenter.config;

import com.zorofchi.datacenter.entity.*;
import com.zorofchi.datacenter.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Initializes the database with default data when the application starts
@Component
public class DataInitializer implements CommandLineRunner {


    private final LocationRepository locationRepository;
    private final DataCenterRepository dataCenterRepository;
    private final RackRowRepository rackRowRepository;
    private final RackRepository rackRepository;
    private final EquipmentRepository equipmentRepository;
    private final PortRepository portRepository;
    private final ConnectionRepository connectionRepository;


    public DataInitializer(LocationRepository locationRepository,
                           DataCenterRepository dataCenterRepository,
                           RackRowRepository rackRowRepository,
                           RackRepository rackRepository, EquipmentRepository equipmentRepository, PortRepository portRepository, ConnectionRepository connectionRepository
    ) {

        this.locationRepository = locationRepository;
        this.dataCenterRepository = dataCenterRepository;
        this.rackRowRepository = rackRowRepository;
        this.rackRepository = rackRepository;
        this.equipmentRepository = equipmentRepository;
        this.portRepository = portRepository;
        this.connectionRepository = connectionRepository;
    }


    @Override
    public void run(String... args) {

        if (dataCenterRepository.count() > 0) {
            return;
        }

        String[] cities = {
                "Tehran",
                "Isfahan",
                "Fars",
                "Khorasan Razavi",
                "East Azerbaijan"
        };


        for (String city : cities) {
            Location location = new Location();
            location.setName(city);
            locationRepository.save(location);
        }

        List<Location> locations = locationRepository.findAll();


        for (Location location : locations) {
            DataCenter dataCenter = new DataCenter();
            dataCenter.setName(location.getName() + " DataCenter");
            dataCenter.setLocation(location);
            dataCenterRepository.save(dataCenter);

            for (int i = 1; i <= 5; i++) {
                RackRow rackRow = new RackRow();
                rackRow.setName("RackRow " + i);
                rackRow.setDataCenter(dataCenter);
                rackRowRepository.save(rackRow);

                for (int j = 1; j <= 10; j++) {
                    Rack rack = new Rack();
                    rack.setName("Rack " + j);
                    rack.setRackNumber(j);
                    rack.setTotalUnits(42);
                    rack.setRackRow(rackRow);
                    rackRepository.save(rack);

                    Equipment switchEquipment = new Equipment();
                    switchEquipment.setName("Switch");
                    switchEquipment.setType("SWITCH");
                    switchEquipment.setStartUnit(42);
                    switchEquipment.setHeightUnits(1);
                    switchEquipment.setRack(rack);
                    equipmentRepository.save(switchEquipment);
                    List<Port> switchPorts = createPorts(switchEquipment, 24, null);

                    Equipment patchPanel = new Equipment();
                    patchPanel.setName("PatchPanel");
                    patchPanel.setType("PATCH_PANEL");
                    patchPanel.setStartUnit(41);
                    patchPanel.setHeightUnits(1);
                    patchPanel.setRack(rack);
                    equipmentRepository.save(patchPanel);
                    List<Port> patchPanelFrontPorts =
                            createPorts(patchPanel, 24, PortSide.FRONT);

                    List<Port> patchPanelBackPorts =
                            createPorts(patchPanel, 24, PortSide.BACK);

                    int currentUnit = 1;
                    int connectionPortIndex = 0;

// Create five 1U servers
                    for (int serverOneUnitIndex = 1; serverOneUnitIndex <= 5; serverOneUnitIndex++) {
                        Equipment server = new Equipment();
                        server.setName("Server 1U - " + serverOneUnitIndex);
                        server.setType("SERVER");
                        server.setStartUnit(currentUnit);
                        server.setHeightUnits(1);
                        server.setRack(rack);
                        equipmentRepository.save(server);
                        List<Port> serverPorts = createPorts(server, 4, null);
                        // Server to PatchPanel
                        Connection connection1 = new Connection();
                        connection1.setSourcePort(serverPorts.get(0));
                        connection1.setDestinationPort(
                                patchPanelFrontPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection1);

// PatchPanel to Switch
                        Connection connection2 = new Connection();
                        connection2.setSourcePort(
                                patchPanelBackPorts.get(connectionPortIndex)
                        );
                        connection2.setDestinationPort(
                                switchPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection2);

                        connectionPortIndex++;
                        currentUnit += 1;
                    }

// Create five 2U servers
                    for (int serverTwoUnitIndex = 1; serverTwoUnitIndex <= 5; serverTwoUnitIndex++) {
                        Equipment server = new Equipment();
                        server.setName("Server 2U - " + serverTwoUnitIndex);
                        server.setType("SERVER");
                        server.setStartUnit(currentUnit);
                        server.setHeightUnits(2);
                        server.setRack(rack);
                        equipmentRepository.save(server);

                        List<Port> serverPorts = createPorts(server, 4, null);

// Server to PatchPanel
                        Connection connection1 = new Connection();
                        connection1.setSourcePort(serverPorts.get(0));
                        connection1.setDestinationPort(
                                patchPanelFrontPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection1);

// PatchPanel to Switch
                        Connection connection2 = new Connection();
                        connection2.setSourcePort(
                                patchPanelBackPorts.get(connectionPortIndex)
                        );
                        connection2.setDestinationPort(
                                switchPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection2);

                        connectionPortIndex++;

                        currentUnit += 2;
                    }

// Create two 4U servers
                    for (int serverFourUnitIndex = 1; serverFourUnitIndex <= 2; serverFourUnitIndex++) {
                        Equipment server = new Equipment();
                        server.setName("Server 4U - " + serverFourUnitIndex);
                        server.setType("SERVER");
                        server.setStartUnit(currentUnit);
                        server.setHeightUnits(4);
                        server.setRack(rack);
                        equipmentRepository.save(server);

                        List<Port> serverPorts = createPorts(server, 4, null);

// Server to PatchPanel
                        Connection connection1 = new Connection();
                        connection1.setSourcePort(serverPorts.get(0));
                        connection1.setDestinationPort(
                                patchPanelFrontPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection1);

// PatchPanel to Switch
                        Connection connection2 = new Connection();
                        connection2.setSourcePort(
                                patchPanelBackPorts.get(connectionPortIndex)
                        );
                        connection2.setDestinationPort(
                                switchPorts.get(connectionPortIndex)
                        );
                        connectionRepository.save(connection2);

                        connectionPortIndex++;

                        currentUnit += 4;
                    }


                }


            }
        }


    }

    private List<Port> createPorts(Equipment equipment, int count, PortSide side) {

        List<Port> ports = new ArrayList<>();

        for (int portIndex = 1; portIndex <= count; portIndex++) {
            Port port = new Port();
            port.setPortNumber(portIndex);
            port.setEquipment(equipment);
            port.setSide(side);

            ports.add(portRepository.save(port));
        }

        return ports;
    }

}
