package Content.Management.demo.Business;

import Content.Management.demo.DatabaAcces.IMetadataRepository;
import Content.Management.demo.Entities.Metadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MetadataService implements IMetadaService {

    private IMetadataRepository imetadataRepository;

    @Override
    public List<Metadata> getAll() {
        List<Metadata> metadata=imetadataRepository.findAll();
        return metadata;
    }

    @Override
    public void add(Metadata metadata) {
     this.imetadataRepository.save(metadata);
    }

    @Override
    public void update(Metadata metadata) {
      this.imetadataRepository.save(metadata);
    }

    @Override
    public void delete(int id) {
         this.imetadataRepository.deleteById(id);
    }
}
