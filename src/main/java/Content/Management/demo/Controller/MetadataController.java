package Content.Management.demo.Controller;

import Content.Management.demo.Business.IMetadaService;
import Content.Management.demo.Entities.Metadata;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metadata")
@AllArgsConstructor
public class MetadataController {

     private IMetadaService metadaService;

     @GetMapping("/getAll")
     public List<Metadata> getAll(){
          return metadaService.getAll();

     }

     @PostMapping("/add")
     @ResponseStatus(code= HttpStatus.CREATED)
     public void add(@RequestBody() Metadata metadata)
     {
          this.metadaService.add(metadata);

     }

     @PutMapping
     public void update(@RequestBody Metadata metadata){

          this.metadaService.update(metadata);
     }

     @DeleteMapping("/{id}")
     public void delete(@PathVariable int id) {

          this.metadaService.delete(id);

     }
}
