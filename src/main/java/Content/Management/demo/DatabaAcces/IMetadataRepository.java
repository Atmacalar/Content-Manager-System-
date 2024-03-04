package Content.Management.demo.DatabaAcces;

import Content.Management.demo.Entities.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetadataRepository extends JpaRepository<Metadata,Integer> {


}
