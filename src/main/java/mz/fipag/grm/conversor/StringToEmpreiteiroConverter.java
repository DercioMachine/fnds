package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Empreiteiro;
import mz.fipag.grm.service.EmpreiterioService;

@Component
public class StringToEmpreiteiroConverter implements Converter<String, Empreiteiro>{
	
	@Autowired
	private EmpreiterioService empreiteiroService;

	@Override
	public Empreiteiro convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return empreiteiroService.buscarPorId(id);
	}

}
