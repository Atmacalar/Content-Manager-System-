package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;
import Content.Management.demo.Entities.Content;

import java.util.List;
import java.util.Optional;

public interface IContentService {

    List<ContentDTO> getAll();
    void add(String name);
    void update(ContentDTO contentDTO,int id) throws Exception;
    void delete(int id);
    String getMovieInfoById(String movieId);
    List<MetadataDTO> getMediaInfo();
    void addReferance(String title, long id);
void addReferenceActor(String title, int id);
    Optional<Content> getContentById(int id);


}
