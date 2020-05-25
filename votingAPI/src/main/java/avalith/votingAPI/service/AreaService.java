package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;

import java.util.List;

public interface AreaService {

    Area getArea(long id);

    List<Area> getAreas();

    Area save(Area area);
}
