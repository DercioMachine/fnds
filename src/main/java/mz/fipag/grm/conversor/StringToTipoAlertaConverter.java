package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.TipoAlerta;
import mz.fipag.grm.service.TipoAlertaService;

@Component
public class StringToTipoAlertaConverter implements Converter<String, TipoAlerta>{
	
	@Autowired
	private TipoAlertaService tipolertaService;

	@Override
	public TipoAlerta convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return tipolertaService.buscarPorId(id);
	}

}
