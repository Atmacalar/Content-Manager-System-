package Content.Management.demo.Controller;


import Content.Management.demo.Business.DTO.ContentDTO;
import Content.Management.demo.Business.DTO.MetadataDTO;
import Content.Management.demo.Business.IContentService;
import Content.Management.demo.Entities.Content;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

   @PutMapping("/put/{id}")
   public void update( @PathVariable("id") int id, @RequestBody ContentDTO contentDTO) throws Exception {

      this.contentService.update(contentDTO,id);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable int id) {
      this.contentService.delete(id);
  }

   @GetMapping("/omdb/{imdbId}")
   public String getMovieDetails(@PathVariable String imdbId) {
     return contentService.getMovieInfoById(imdbId);
}


   @GetMapping("/getAllImdb")
   public List<MetadataDTO> getAllMediaInfo() {
   return contentService.getMediaInfo();
}


   @GetMapping("/{id}")
   public Optional<Content> getContentById(@PathVariable int id){
     return contentService.getContentById(id);
   }
}

