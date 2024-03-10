package Content.Management.demo.Business.DTO;


import Content.Management.demo.Entities.Cast;
import Content.Management.demo.Entities.Metadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {

private int id;
    private String title;
    private String metID;
    private Metadata metadata;
    private List<Cast> actors;

}
