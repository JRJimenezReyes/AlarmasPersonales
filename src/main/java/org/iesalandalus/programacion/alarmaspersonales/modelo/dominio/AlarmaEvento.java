package org.iesalandalus.programacion.alarmaspersonales.modelo.dominio;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AlarmaEvento extends Alarma implements Serializable {
	
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate dia;
	
	public AlarmaEvento(String mensaje, LocalTime hora, LocalDate dia) {
		super(mensaje, hora);
		setDia(dia);
	}
	
	public AlarmaEvento(AlarmaEvento alarmaEvento) {
		super(alarmaEvento);
		setDia(alarmaEvento.getDia());
	}
	
	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("El día de una alarma de evento no puede ser nulo.");
		}
		this.dia = dia;
	}
	
	public LocalDate getDia() {
		return dia;
	}
	
	@Override
	public long getSegundosRestantes() {
		LocalDateTime ahora = LocalDateTime.now();
		LocalDateTime horaDiaAlarma = LocalDateTime.of(dia, hora);
		return Duration.between(ahora, horaDiaAlarma).getSeconds();
	}

	@Override
	public int hashCode() {
		return Objects.hash(mensaje, hora, dia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof AlarmaEvento)) {
			return false;
		}
		AlarmaEvento other = (AlarmaEvento) obj;
		return Objects.equals(mensaje, other.mensaje) && Objects.equals(hora, other.hora) 
				&& Objects.equals(dia, other.dia);
	}

	@Override
	public String toString() {
		return String.format("AlarmaEvento [mensaje=%s, hora=%s, dia=%s]", 
				mensaje, hora.format(FORMATO_HORA), dia.format(FORMATO_DIA));
	}

}
