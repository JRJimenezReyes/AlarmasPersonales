package org.iesalandalus.programacion.alarmaspersonales.modelo.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalTime;

import org.junit.Test;

public class AlarmaDiariaTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String MENSAJE1 = "Alarma de prueba 1";
	private static final String MENSAJE2 = "Alarma de prueba 2";
	private static final LocalTime HORA1 = LocalTime.of(20, 55);
	private static final LocalTime HORA2 = LocalTime.of(20, 15);

	@Test
	public void constructorValidoTest() {
		AlarmaDiaria miAlarma = new AlarmaDiaria(MENSAJE1, HORA1);
		assertEquals(MENSAJE1, miAlarma.getMensaje());
		assertEquals(HORA1, miAlarma.getHora());
		assertEquals("Alarma [mensaje=Alarma de prueba 1, hora=20:55]", miAlarma.toString());
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		AlarmaDiaria miAlarma1 = new AlarmaDiaria(MENSAJE1, HORA1);
		AlarmaDiaria miAlarma2 = new AlarmaDiaria(miAlarma1);
		assertEquals(MENSAJE1, miAlarma2.getMensaje());
		assertEquals(HORA1, miAlarma2.getHora());
		assertEquals("Alarma [mensaje=Alarma de prueba 1, hora=20:55]", miAlarma2.toString());
	}
	
	@Test
	public void constructorMensajeNoValidoTest() {
		AlarmaDiaria miAlarma = null;
		try {
			miAlarma = new AlarmaDiaria(null, HORA1);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede ser nulo.", e.getMessage());
		}
		try {
			miAlarma = new AlarmaDiaria("", HORA1);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede estar vacío.", e.getMessage());
		}
		try {
			miAlarma = new AlarmaDiaria("   ", HORA1);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(miAlarma);
			assertEquals("El mensaje de una alarma no puede estar vacío.", e.getMessage());
		}
	}
	
	@Test
	public void constructorHoraNoValidaTest() {
		AlarmaDiaria miAlarma = null;
		try {
			miAlarma = new AlarmaDiaria(MENSAJE1, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertNull(miAlarma);
			assertEquals("La hora de una alarma no puede ser nula.", e.getMessage());;
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		AlarmaDiaria miAlarma1 = null;
		AlarmaDiaria miAlarma2 = null;
		try {
			miAlarma2 = new AlarmaDiaria(miAlarma1);
		} catch (NullPointerException e) {
			assertNull(miAlarma2);
			assertEquals("No se puede copiar una alarma nula.", e.getMessage());
		}
	}
	
	@Test
	public void getSegundosRestantesTest() {
		LocalTime dentroDeUnaHora = LocalTime.now().plusHours(1);
		assertEquals(3600, new AlarmaDiaria(MENSAJE1, dentroDeUnaHora).getSegundosRestantes());
	}
	
	@Test
	public void hashCodeEqualsTest() {
		AlarmaDiaria miAlarma1 = new AlarmaDiaria(MENSAJE1, HORA1);
		AlarmaDiaria miAlarma2 = new AlarmaDiaria(MENSAJE2, HORA2);
		AlarmaDiaria miAlarma3 = new AlarmaDiaria(MENSAJE1, HORA2);
		AlarmaDiaria miAlarma4 = new AlarmaDiaria(MENSAJE1, HORA1);
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
