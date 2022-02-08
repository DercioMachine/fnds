package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.Categoria;
import mz.fipag.grm.service.CategoriaService;

@Component
public class StringToCategoriaConverter implements Converter<String, Categoria>{
	
	@Autowired
	private CategoriaService categoriaService;

	@Override
	public Categoria convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return categoriaService.buscarPorId(id);
	}

}
