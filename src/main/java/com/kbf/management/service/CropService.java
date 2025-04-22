package com.kbf.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Crop;
import com.kbf.management.repository.CropRepository;

import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop getCropById(Long id) {
        return cropRepository.findById(id).orElse(null);
    }

    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public Crop updateCrop(Long id, Crop crop) {
        Crop existing = cropRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(crop.getName());
            existing.setPlantingDate(crop.getPlantingDate());
            existing.setExpectedHarvestDate(crop.getExpectedHarvestDate());
            existing.setAreaSize(crop.getAreaSize());
            return cropRepository.save(existing);
        }
        return null;
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }
}
