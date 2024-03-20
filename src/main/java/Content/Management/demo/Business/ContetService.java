package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;
import Content.Management.demo.DatabaAcces.ICastRepository;
import Content.Management.demo.DatabaAcces.IContentRepository;
import Content.Management.demo.DatabaAcces.IMetadataRepository;
import Content.Management.demo.Entities.Cast;
import Content.Management.demo.Entities.Content;
import Content.Management.demo.Entities.Metadata;
import Content.Management.demo.Mapper.ModelMapperService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ICastRepository castRepository;
   @Autowired
    private ConfigValue configValue;






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
        getMediaInfo();
    }

    @Override
    public void update(ContentDTO contentDTO, int id) throws Exception {
    Optional<Content> optionalContent =contentRepository.findById(id);

        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();
            content.setTitle(contentDTO.getTitle());
          content.setMetadata(contentDTO.getMetadata());
          contentRepository.save(content);

 } else {
    throw new Exception("Kayıt Bulunamadı");
        }
    }

    @Override
    public void delete(int id) {

        this.contentRepository.deleteById(id);
    }

    @Override
    public String getMovieInfoById(String movieId) {

        String apiUrl = configValue.getOmdbApiUrl() + "?apikey=" + configValue.getOmdbApiKey() + "&i=" + movieId;
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
        List<Cast> mediaListActor = castRepository.findAll();
        List<Metadata> mediaList = metadataRepository.findAll();
        List<String> titleMeta = mediaList.stream().map(Metadata::getTitle).collect(Collectors.toList());
        List<String> titleActor = mediaListActor.stream().map(Cast::getName).collect(Collectors.toList());
        for (String title : titles) {

            String apiUrl = configValue.getOmdbApiUrl() + "?apikey=" + configValue.getOmdbApiKey() + "&t=" + title;
            MetadataDTO mediaDto = restTemplate.getForObject(apiUrl, MetadataDTO.class);

            if (mediaDto != null) {
                mediaInfoList.add(new MetadataDTO(
                        mediaDto.getTitle(),
                        mediaDto.getYear(),
                        mediaDto.getPoster(),
                        mediaDto.getPlot(),
                        mediaDto.getCountry(),
                        mediaDto.getLanguage(),
                        mediaDto.getDirector(),
                        mediaDto.getGenre(),
                        mediaDto.getActors()
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
                    metadata.setGenre(mediaDto.getGenre());
                    metadata.setActors(mediaDto.getActors());

                    Metadata savedData = metadataRepository.save(metadata);
                    addReferance(savedData.getTitle(), savedData.getId());

    }
                String actorsString = mediaDto.getActors();
                List<String> actors = Arrays.asList(actorsString.split(","));

                for (String actor : actors) {

                    if (!titleActor.contains(actor)) {
                        Cast cast = new Cast();
                        cast.setName(actor);
                        cast.setTitle(mediaDto.getTitle());

                        Content contentss = contentRepository.findByTitle(mediaDto.getTitle());
                        if(contentss!=null){

                            cast.setContent_id(contentss);

                        }
                         Cast savedData = castRepository.save(cast);


                    }
                }

            }
        }

        return mediaInfoList;

    }

    @Override
    public void addReferance(String title, long id) {

        List<Content> contents=contentRepository.findAll();

        for(Content c : contents){
           if(c.getTitle().equals(title)){
               c.setMetadata(metadataRepository.findById(Math.toIntExact(id)).get());
               contentRepository.save(c);
           }

        }


    }



    @Override
    public Optional<Content> getContentById(int id) {
        Optional<Content> c = contentRepository.findById(id);
        return c;
    }
}




