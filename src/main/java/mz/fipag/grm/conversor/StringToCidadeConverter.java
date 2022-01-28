package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Cidade;
import mz.fipag.grm.service.CidadeService;

@Component
public class StringToCidadeConverter implements Converter<String, Cidade>{
	
	@Autowired
	private CidadeService cidadeService;

	@Override
	public Cidade convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return cidadeService.buscarPorId(id);
	}

}
