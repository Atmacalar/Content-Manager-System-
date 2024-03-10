package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;

import java.util.List;

public interface IContentService {

    List<ContentDTO> getAll();
    void add(String name);
    void update(ContentDTO contentDTO,int id) throws Exception;
    void delete(int id);
    String getMovieInfoById(String movieId);
    List<MetadataDTO> getMediaInfo();

    public void addReferance(String title, long id);



}
