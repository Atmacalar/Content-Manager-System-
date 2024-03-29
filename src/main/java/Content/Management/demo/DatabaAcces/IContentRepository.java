package Content.Management.demo.DatabaAcces;

import Content.Management.demo.Entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IContentRepository extends JpaRepository<Content,Integer> {


    Content findByTitle(String title);
}
