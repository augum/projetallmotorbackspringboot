package boutiqueProjet.org;

import boutiqueProjet.org.Entity.Article;
import boutiqueProjet.org.Entity.Magasin;
import boutiqueProjet.org.Entity.Taxe;
import boutiqueProjet.org.Entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BoutiqueApplication1 implements CommandLineRunner {
	@Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(BoutiqueApplication1.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Magasin.class);
		repositoryRestConfiguration.exposeIdsFor(Utilisateur.class);
		repositoryRestConfiguration.exposeIdsFor(Taxe.class);
		repositoryRestConfiguration.exposeIdsFor(Article.class);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");

			}
		};
	}
}
