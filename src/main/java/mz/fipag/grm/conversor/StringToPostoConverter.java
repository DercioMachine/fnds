package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.PostoAdministrativo;
import mz.fipag.grm.service.PostoAdministrativoService;

@Component
public class StringToPostoConverter implements Converter<String, PostoAdministrativo>{
	
	@Autowired
	private PostoAdministrativoService postoAdministrativoService;

	@Override
	public PostoAdministrativo convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return postoAdministrativoService.buscarPorId(id);
	}

}
