package org.iesalandalus.programacion.alarmaspersonales.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaDiaria;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaEvento;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private Consola() {
		// Evito que se cree el constructor por defecto
	}

	public static void mostrarMenu() {
		mostrarCabecera("Gestión de alarmas");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static int elegirAlarma() {
		int ordinalAlarma;
		do {
			System.out.print("Elige una alarma (0.- Diaria, 1.- Eventual): ");
			ordinalAlarma = Entrada.entero();
		} while (ordinalAlarma < 0 || ordinalAlarma > 1);
		return ordinalAlarma;
	}

	public static LocalTime leerHora() {
		LocalTime horaLT = null;
		String hora;
		System.out.print("Introduce la hora en formato (hh:mm): ");
		hora = Entrada.cadena();
		try {
			horaLT = LocalTime.parse(hora, FORMATO_HORA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("El formato de la hora no es correcto.");
		}
		return horaLT;

	}

	public static String leerMensaje() {
		System.out.print("Introduce el mensaje: ");
		return Entrada.cadena();
	}

	public static LocalDate leerDia() {
		String dia;
		boolean diaCorrecto = false;
		do {
			System.out.print("Introduce el día (dd/mm/aaaa): ");
			dia = Entrada.cadena();
			try {
				LocalDate.parse(dia, FORMATO_DIA);
				diaCorrecto = true;
			} catch (DateTimeParseException e) {
				diaCorrecto = false;
			}
		} while (!diaCorrecto);
		return LocalDate.parse(dia, FORMATO_DIA);
	}

	public static Alarma leerAlarma() {
		LocalDate dia = null;
		Alarma alarma = null;
		LocalTime hora = leerHora();
		String mensaje = leerMensaje();
		int ordinalAlarma = Consola.elegirAlarma();

		if (ordinalAlarma == 0) {
			alarma = new AlarmaDiaria(mensaje, hora);
		} else if (ordinalAlarma == 1) {
			dia = leerDia();
			alarma = new AlarmaEvento(mensaje, hora, dia);
		}

		return alarma;
	}

}
