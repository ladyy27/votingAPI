package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;

import java.util.List;

public interface AreaService {

    /***
     * getArea Service returns an Area object filtered by its id
     * @param id
     * @return Area area
     */
    Area getArea(long id);

    /***
     * getAreas() returns all Area objects
     * @return List<Area>
     */
    List<Area> getAreas();

    /***
     * save() save a new Area object in database
     * @param area
     * @return
     */
    Area save(Area area);
}
