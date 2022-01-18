package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.service.DistritoService;

@Component
public class StringToDistritoConverter implements Converter<String, Distrito>{
	
	@Autowired
	private DistritoService distritoService;

	@Override
	public Distrito convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return distritoService.buscarPorId(id);
	}

}
