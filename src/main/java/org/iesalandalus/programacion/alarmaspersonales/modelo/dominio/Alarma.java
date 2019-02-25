package org.iesalandalus.programacion.alarmaspersonales.modelo.dominio;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Alarma implements Serializable {
	
	protected static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	protected String mensaje;
	protected LocalTime hora;
	
	protected Alarma(String mensaje, LocalTime hora) {
		setMensaje(mensaje);
		setHora(hora);
	}
	
	protected Alarma(Alarma alarma) {
		if (alarma == null) {
			throw new NullPointerException("No se puede copiar una alarma nula.");
		}
		setMensaje(alarma.getMensaje());
		setHora(alarma.getHora());
	}
	
	private void setMensaje(String mensaje) {
		if (mensaje == null) {
			throw new NullPointerException("El mensaje de una alarma no puede ser nulo.");
		}
		if (mensaje.trim().isEmpty()) {
			throw new IllegalArgumentException("El mensaje de una alarma no puede estar vacío.");
		}
		this.mensaje = mensaje;
	}
	
	private void setHora(LocalTime hora) {
		if (hora == null) {
			throw new NullPointerException("La hora de una alarma no puede ser nula.");
		}
		this.hora = hora;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public LocalTime getHora() {
		return hora;
	}
	
    public abstract long getSegundosRestantes();

	@Override
	public int hashCode() {
		return Objects.hash(hora, mensaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Alarma)) {
			return false;
		}
		Alarma other = (Alarma) obj;
		return Objects.equals(hora, other.hora) && Objects.equals(mensaje, other.mensaje);
	}

	@Override
	public String toString() {
		return String.format("Alarma [mensaje=%s, hora=%s]", mensaje, hora.format(FORMATO_HORA));
	}
	
}
