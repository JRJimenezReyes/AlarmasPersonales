package org.iesalandalus.programacion.alarmaspersonales.modelo.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class AlarmaEventoTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String MENSAJE1 = "Alarma de prueba 1";
	private static final String MENSAJE2 = "Alarma de prueba 2";
	private static final LocalTime HORA1 = LocalTime.of(20, 55);
	private static final LocalTime HORA2 = LocalTime.of(20, 15);
	private static final LocalDate DIA1 = LocalDate.of(2019, 3, 1);
	private static final LocalDate DIA2 = LocalDate.of(2019, 3, 2);

	@Test
	public void constructorValidoTest() {
		AlarmaEvento miAlarma = new AlarmaEvento(MENSAJE1, HORA1, DIA1);
		assertEquals(MENSAJE1, miAlarma.getMensaje());
		assertEquals(HORA1, miAlarma.getHora());
		assertEquals(DIA1, miAlarma.getDia());
		assertEquals("AlarmaEvento [mensaje=Alarma de prueba 1, hora=20:55, dia=01/03/2019]", miAlarma.toString());
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		AlarmaEvento miAlarma1 = new AlarmaEvento(MENSAJE1, HORA1, DIA1);
		AlarmaEvento miAlarma2 = new AlarmaEvento(miAlarma1);
		assertEquals(MENSAJE1, miAlarma2.getMensaje());
		assertEquals(HORA1, miAlarma2.getHora());
		assertEquals(DIA1, miAlarma1.getDia());
		assertEquals("AlarmaEvento [mensaje=Alarma de prueba 1, hora=20:55, dia=01/03/2019]", miAlarma2.toString());
	}
	
	@Test
	public void constructorMensajeNoValidoTest() {
		AlarmaEvento miAlarma = null;
		try {
			miAlarma = new AlarmaEvento(null, HORA1, DIA1);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede ser nulo.", e.getMessage());
		}
		try {
			miAlarma = new AlarmaEvento("", HORA1, DIA1);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede estar vacío.", e.getMessage());
		}
		try {
			miAlarma = new AlarmaEvento("   ", HORA1, DIA1);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede estar vacío.", e.getMessage());
		}
	}
	
	@Test
	public void constructorHoraNoValidaTest() {
		AlarmaEvento miAlarma = null;
		try {
			miAlarma = new AlarmaEvento(MENSAJE1, null, DIA1);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertNull(miAlarma);
			assertEquals("La hora de una alarma no puede ser nula.", e.getMessage());;
		}
	}
	
	@Test
	public void constructorDiaNoValidoTest() {
		AlarmaEvento miAlarma = null;
		try {
			miAlarma = new AlarmaEvento(MENSAJE1, HORA1, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertNull(miAlarma);
			assertEquals("El día de una alarma de evento no puede ser nulo.", e.getMessage());;
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		AlarmaEvento miAlarma1 = null;
		AlarmaEvento miAlarma2 = null;
		try {
			miAlarma2 = new AlarmaEvento(miAlarma1);
		} catch (NullPointerException e) {
			assertNull(miAlarma2);
			assertEquals("No se puede copiar una alarma nula.", e.getMessage());
		}
	}
	
	@Test
	public void getSegundosRestantesTest() {
		LocalDate hoy = LocalDate.now();
		LocalTime dentroDeUnaHora = LocalTime.now().plusHours(1);
		assertEquals(3600, new AlarmaEvento(MENSAJE1, dentroDeUnaHora, hoy).getSegundosRestantes());
	}
	
	@Test
	public void hashCodeEqualsTest() {
		AlarmaEvento miAlarma1 = new AlarmaEvento(MENSAJE1, HORA1, DIA1);
		AlarmaEvento miAlarma2 = new AlarmaEvento(MENSAJE2, HORA2, DIA1);
		AlarmaEvento miAlarma3 = new AlarmaEvento(MENSAJE1, HORA2, DIA2);
		AlarmaEvento miAlarma4 = new AlarmaEvento(MENSAJE1, HORA1, DIA1);
		assertEquals(miAlarma1.hashCode(), miAlarma1.hashCode());
		assertEquals(miAlarma1.hashCode(), miAlarma4.hashCode());
		assertNotEquals(miAlarma1.hashCode(), miAlarma2.hashCode());
		assertNotEquals(miAlarma1.hashCode(), miAlarma3.hashCode());
		assertEquals(miAlarma1, miAlarma1);
		assertEquals(miAlarma1, miAlarma4);
		assertNotEquals(miAlarma1, miAlarma2);
		assertNotEquals(miAlarma1, miAlarma3);
		assertNotEquals(miAlarma1, null);
		assertNotEquals(miAlarma1, "Cadena");
	}

}
