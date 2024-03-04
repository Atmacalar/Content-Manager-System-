package Content.Management.demo.Business.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastDTO {
    private int id;
    private String name;
    private String poster;
    private String director;
  private int getContent_id;

}
