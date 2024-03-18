package Content.Management.demo.Business.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastDTO {

    private String name;
    private String poster;
   private int getContent_id;

}
