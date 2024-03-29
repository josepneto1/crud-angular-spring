package com.crud.crudspring;

import com.crud.crudspring.enums.Categoria;
import com.crud.crudspring.model.Aula;
import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CursoRepository cursoRepository){
		return args -> {
			cursoRepository.deleteAll();

			Curso c = new Curso();
			c.setNome("Angular com Spring");
			c.setCategoria(Categoria.FRONT_END);

			Aula a = new Aula();
			a.setNome("Introdução");
			a.setYoutubeUrl("sdOEpkS9l11");
			a.setCurso(c);
			c.getAulas().add(a);

			Aula a1 = new Aula();
			a1.setNome("Angular");
			a1.setYoutubeUrl("sdOEpkS9l12");
			a1.setCurso(c);
			c.getAulas().add(a1);

			cursoRepository.save(c);
		};
	}
}
