package org.iesalandalus.programacion.alarmaspersonales.modelo.dominio;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class AlarmaDiaria extends Alarma implements Serializable {

	public AlarmaDiaria(String mensaje, LocalTime hora) {
		super(mensaje, hora);
	}
	
	public AlarmaDiaria(AlarmaDiaria alarma) {
		super(alarma);
	}
	
	@Override
	public long getSegundosRestantes() {
		return Duration.between(LocalTime.now(), hora).getSeconds();
	}
}
