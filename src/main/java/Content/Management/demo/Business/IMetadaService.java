package Content.Management.demo.Business;



import Content.Management.demo.Entities.Metadata;

import java.util.List;

public interface IMetadaService {

    List<Metadata> getAll();
    void add(Metadata metadata);
    void update(Metadata metadata);
    void delete(int id);
}
