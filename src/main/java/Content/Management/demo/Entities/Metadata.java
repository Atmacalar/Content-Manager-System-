package Content.Management.demo.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="metadata")
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="title")
    @JsonProperty("Title")
    private String Title;

    @Column(name="plot")
    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Poster")
    @Column(name="poster")
    private String poster;

    @Column(name="year")
    @JsonProperty("Year")
    private String year;

    @Column(name="language")
    @JsonProperty("Language")
     private String language;

    @Column(name="country")
    @JsonProperty("Country")
    private String country;

    @Column(name="Director")
    @JsonProperty("Director")
    private String Director;


}
