package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;
import Content.Management.demo.DatabaAcces.IContentRepository;
import Content.Management.demo.DatabaAcces.IMetadataRepository;
import Content.Management.demo.Entities.Content;
import Content.Management.demo.Entities.Metadata;
import Content.Management.demo.Mapper.ModelMapperService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Component
@Transactional
public class ContetService implements IContentService {

    @Autowired
    private IContentRepository contentRepository;
    @Autowired
    private IMetadataRepository metadataRepository;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private EntityManager entityManager;


    @Value("${omdb.api.key}")
    String omdbApiKey;

    @Value("${omdb.api.url}")
    String omdbApiUrl;

    @Override
    public List<ContentDTO> getAll() {

        List<Content> contents = contentRepository.findAll();

        List<ContentDTO> ContentsResponse = contents.stream().
                map(content -> this.modelMapperService.forkresponse().
                        map(content, ContentDTO.class)).collect(Collectors.toList());
        return ContentsResponse;

    }

    @Override
    public void add(String name) {
        Content content = new Content();
        content.setTitle(name);
        this.contentRepository.save(content);

    }

    @Override
    public void update(ContentDTO contentDTO) {


    }

    @Override
    public void delete(int id) {

        this.contentRepository.deleteById(id);
    }

    @Override
    public String getMovieInfoById(String movieId) {

        String apiUrl = omdbApiUrl + "?apikey=" + omdbApiKey + "&i=" + movieId;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);
        JSONObject jsonObject = new JSONObject(result);
        Metadata metadata = new Metadata();
        metadata.setTitle(jsonObject.getString("Title"));
        metadata.setPlot(jsonObject.getString("Plot"));
        metadata.setPoster(jsonObject.getString("Poster"));
        metadata.setYear(jsonObject.getString("Year"));
        metadata.setLanguage(jsonObject.getString("Language"));
        metadata.setCountry(jsonObject.getString("Country"));
        JSONObject movieDetails = new JSONObject();
        movieDetails.put("Title", metadata.getTitle());
        movieDetails.put("Plot", metadata.getPlot());
        movieDetails.put("Poster", metadata.getPoster());
        movieDetails.put("Year", metadata.getYear());
        movieDetails.put("Language", metadata.getLanguage());
        movieDetails.put("Country", metadata.getCountry());

        return movieDetails.toString();


    }


    @Override
    public List<MetadataDTO> getMediaInfo() {
        List<Content> mediaListe = contentRepository.findAll();
        List<String> titles = mediaListe.stream().map(Content::getTitle).collect(Collectors.toList());
        List<MetadataDTO> mediaInfoList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        List<Metadata> mediaList = metadataRepository.findAll();
        List<String> titleMeta = mediaList.stream().map(Metadata::getTitle).collect(Collectors.toList());
        for (String title : titles) {

            String apiUrl = omdbApiUrl + "?apikey=" + omdbApiKey + "&t=" + title;
            MetadataDTO mediaDto = restTemplate.getForObject(apiUrl, MetadataDTO.class);

            if (mediaDto != null) {
                mediaInfoList.add(new MetadataDTO(
                        mediaDto.getTitle(),
                        mediaDto.getYear(),
                        mediaDto.getPoster(),
                        mediaDto.getPlot(),
                        mediaDto.getCountry(),
                        mediaDto.getLanguage(),
                        mediaDto.getDirector()
                ));

                if (!titleMeta.contains(mediaDto.getTitle())) {
                    Metadata metadata = new Metadata();
                    metadata.setTitle(mediaDto.getTitle());
                    metadata.setYear(mediaDto.getYear());
                    metadata.setPoster(mediaDto.getPoster());
                    metadata.setPlot(mediaDto.getPlot());
                    metadata.setCountry(mediaDto.getCountry());
                    metadata.setLanguage(mediaDto.getLanguage());
                    metadata.setDirector(mediaDto.getDirector());

                    Metadata savedData = metadataRepository.save(metadata);
                    addReferance(savedData.getTitle(), savedData.getId());


                }
            }
        }

        return mediaInfoList;

    }

    @Override
    public void addReferance(String title, long id) {
        Query query = entityManager.createQuery("UPDATE Content c SET c.metadata.id = :id WHERE c.title = :title");
        query.setParameter("id", id);
        query.setParameter("title", title);
        query.executeUpdate();

    }
}




