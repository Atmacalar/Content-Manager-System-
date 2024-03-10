package Content.Management.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  long id;

    @Column(name="title")
    private String title;

    @Column(name="metID")
    private String metID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata")
    private Metadata metadata;


    @OneToMany(mappedBy = "content_id")
    @JsonManagedReference
     private List<Cast> actors;



}
