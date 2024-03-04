package Content.Management.demo.Controller;


import Content.Management.demo.Business.DTO.CastDTO;
import Content.Management.demo.Business.ICastService;
import Content.Management.demo.Entities.Cast;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cast")
@AllArgsConstructor
public class CastController {

    private ICastService castService;

    @GetMapping("/getAll")
    public List<Cast> getAll(){
        return castService.getAll();

    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody()CastDTO castDTO)
    {
        this.castService.add(castDTO);

    }

    @PutMapping("/put")
    public void update(@RequestBody Cast cast){

        this.castService.update(cast);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {

        this.castService.delete(id);

    }


}
