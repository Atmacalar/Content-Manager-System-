package Content.Management.demo.Mapper;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperManager  implements ModelMapperService{

    private ModelMapper  modelMapper;
    @Override
    public ModelMapper forkresponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);


        return this.modelMapper;
    }

    @Override
    public ModelMapper forkrequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);


        return this.modelMapper;
    }
}
