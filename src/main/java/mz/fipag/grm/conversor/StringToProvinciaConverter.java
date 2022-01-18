package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Provincia;
import mz.fipag.grm.service.ProvinciaService;

@Component
public class StringToProvinciaConverter implements Converter<String, Provincia>{
	
	@Autowired
	private ProvinciaService provinciaService;

	@Override
	public Provincia convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return provinciaService.buscarPorId(id);
	}

}
