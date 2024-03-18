package Content.Management.demo.Business;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigValue {
    @Value("${omdb.api.key}")
    String omdbApiKey;

    @Value("${omdb.api.url}")
    String omdbApiUrl;
}
