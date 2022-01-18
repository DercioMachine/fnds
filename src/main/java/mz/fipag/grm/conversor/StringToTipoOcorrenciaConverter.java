package mz.fipag.grm.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.fipag.grm.domain.TipoOcorrencia;
import mz.fipag.grm.service.TipoOcorrenciaService;

@Component
public class StringToTipoOcorrenciaConverter implements Converter<String, TipoOcorrencia>{
	
	@Autowired
	private TipoOcorrenciaService tipoOcorrenciaService;

	@Override
	public TipoOcorrencia convert(String text) {

		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return tipoOcorrenciaService.buscarPorId(id);
	}

}
