package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * AreaServiceImpl implements all AreaService methods
 */
@Service
public class AreaServiceImpl implements AreaService {

    private AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    /***
     * getArea returns an Area object filtered by its id
     * @param id
     * @return Area area
     */
    @Override
    public Area getArea(long id){ return areaRepository.findById(id); }

    /***
     * getAreas() returns all Area objects
     * @return List<Area>
     */
    @Override
    public List<Area> getAreas(){ return areaRepository.findAll(); }

    /***
     * save() save a new Area object in database
     * @param area
     * @return
     */
    @Override
    public Area save(Area area){ return areaRepository.save(area); }
}
