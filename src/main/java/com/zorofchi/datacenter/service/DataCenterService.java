package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.DataCenter;
import com.zorofchi.datacenter.repository.DataCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataCenterService {

    private final DataCenterRepository dataCenterRepository;

    public DataCenterService(DataCenterRepository dataCenterRepository) {
        this.dataCenterRepository = dataCenterRepository;
    }

    public DataCenter addDataCenter(DataCenter dataCenter) {
        return dataCenterRepository.save(dataCenter);
    }

    public List<DataCenter> getAllDataCenters() {
        return dataCenterRepository.findAll();
    }

    public DataCenter getDataCenterById(Long id) {
        return dataCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DataCenter not found"));
    }

    public void deleteDataCenter(Long id) {
        DataCenter dataCenter = getDataCenterById(id);
        dataCenterRepository.delete(dataCenter);
    }
}