package com.manoj.service;

import com.manoj.model.FeatureCategory;
import com.manoj.model.FeaturesItem;

import java.util.List;

public interface FeaturesService {

    public FeatureCategory createFeatureCategory(String name,Long carDId)throws Exception;

    public FeatureCategory findFeatureCategoryById(Long id) throws Exception;

    public List<FeatureCategory> findFeatureCategoryByCarDId(Long id) throws Exception;

    public FeaturesItem createFeatureItem(Long carDId,String featureName, Long categoryId) throws Exception;

    public List<FeaturesItem> findCarDsFeatures(Long CarDId) throws Exception;

    public FeaturesItem updateStock(Long id) throws Exception;

}
