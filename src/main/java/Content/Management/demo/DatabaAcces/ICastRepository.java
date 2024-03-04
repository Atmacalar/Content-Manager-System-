package Content.Management.demo.DatabaAcces;

import Content.Management.demo.Entities.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICastRepository extends JpaRepository<Cast,Integer> {


}
