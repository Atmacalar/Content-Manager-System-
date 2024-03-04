package Content.Management.demo.Business.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {

    private String title;
    private String metID;
    private MetadataDTO metadata;
    private List<CastDTO> actors;
    private CastDTO director;



}
