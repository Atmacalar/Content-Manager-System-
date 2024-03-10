package Content.Management.demo.Controller;


import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;
import Content.Management.demo.Business.IContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ContentController {

   private IContentService contentService;


   @GetMapping("/getAll")
   public List<ContentDTO> getAll() {
      return contentService.getAll();

   }

   @PostMapping("/add")
   @ResponseStatus(code = HttpStatus.CREATED)
   public void add(@RequestBody() String name) {
      this.contentService.add(name);

   }

   @PutMapping("/put")
   public void update(@RequestBody ContentDTO contentDTO, int id ) throws Exception {

      this.contentService.update(contentDTO,id);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable int id) {
      this.contentService.delete(id);
  }

   @GetMapping("/{imdbId}")
   public String getMovieDetails(@PathVariable String imdbId) {
     return contentService.getMovieInfoById(imdbId);
}


   @GetMapping("/getAllImdb")
   public List<MetadataDTO> getAllMediaInfo() {
   return contentService.getMediaInfo();
}


}
