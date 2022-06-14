package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.dao.DistanceRepository;
import nl.bioinf.recipespaces.model.IngredientDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DistanceService {

    private final DistanceRepository distanceRepository;

    @Autowired
    public DistanceService(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    public List<IngredientDistance> getDistancesFromId(Integer id){
        return distanceRepository.ingredientDistancesFromId(id);
    }

    public Optional<Double> getDistanceFromPair(Integer id, Integer target){
        return distanceRepository.getDistanceFromPair(id, target);
    }
}
