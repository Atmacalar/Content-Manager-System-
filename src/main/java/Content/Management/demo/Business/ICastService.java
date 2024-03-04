package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.CastDTO;
import Content.Management.demo.Entities.Cast;

import java.util.List;

public interface ICastService {

    List<Cast> getAll();
    void add(CastDTO castDTO);
    void update(Cast cast);
    void delete(int id);
}
