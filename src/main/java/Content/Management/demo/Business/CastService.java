package Content.Management.demo.Business;

import Content.Management.demo.Business.DTO.CastDTO;
import Content.Management.demo.DatabaAcces.ICastRepository;
import Content.Management.demo.DatabaAcces.IContentRepository;
import Content.Management.demo.Entities.Cast;
import Content.Management.demo.Mapper.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CastService implements ICastService{

    private ICastRepository castRepository;
    private ModelMapperService modelMaperService;
    private IContentRepository contentRepository;

    @Override
    public List<Cast> getAll() {
        List<Cast>  cast = castRepository.findAll();
        return cast;
    }

    @Override
    public void add(CastDTO castDTO)
    {
        Cast cast = this.modelMaperService.forkrequest().map(castDTO, Cast.class);
        cast.setContent_id(contentRepository.findById(castDTO.getGetContent_id()).get());
        this.castRepository.save(cast);
    }

    @Override
    public void update(Cast cast) {
        this.castRepository.save(cast);
    }

    @Override
    public void delete(int id) {
    this.castRepository.deleteById(id);
    }
}
