package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.service.ProjectoService;

@Component
public class StringToProjectoConverter implements Converter<String, Projecto>{
	
	@Autowired
	private ProjectoService projectoService;

	@Override
	public Projecto convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return projectoService.buscarPorId(id);
	}

}
