package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Area getArea(long id){ return areaRepository.findById(id); }

    @Override
    public List<Area> getAreas(){ return areaRepository.findAll(); }

    @Override
    public Area save(Area area){ return areaRepository.save(area); }
}
