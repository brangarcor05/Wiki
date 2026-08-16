package com.tallerwiki.thymeleaf;

import com.tallerwiki.thymeleaf.model.MensajeContacto;
import com.tallerwiki.thymeleaf.repository.MensajeContactoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ThymeleafApplicationTests {

	@Autowired
	private MensajeContactoRepository mensajeContactoRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void guardarMensajeContacto() {
		MensajeContacto mensaje = new MensajeContacto();
		mensaje.setNombre("Juan Perez");
		mensaje.setCorreo("juan@correo.com");
		mensaje.setTelefono("3001234567");
		mensaje.setAsunto("soporte");
		mensaje.setMensaje("Necesito ayuda con el registro de mi empresa.");

		mensajeContactoRepository.save(mensaje);

		assertThat(mensaje.getId()).isNotNull();
		assertThat(mensajeContactoRepository.count()).isEqualTo(1);
		MensajeContacto guardado = mensajeContactoRepository.findById(mensaje.getId()).orElseThrow();
		assertThat(guardado.getNombre()).isEqualTo("Juan Perez");
		assertThat(guardado.getFecha()).isNotNull();
	}

}
