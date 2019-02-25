package org.iesalandalus.programacion.alarmaspersonales.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alarmaspersonales.controlador.IControladorGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;

public class VistaGestionAlarmas implements IVistaGestionAlarmas {

	private static final String ERROR = "ERROR: ";

	private IControladorGestionAlarmas controlador;

	public VistaGestionAlarmas() {
		Opcion.setVista(this);
	}
	
	@Override
	public void setControlador(IControladorGestionAlarmas controlador) {
		this.controlador = controlador;
	}

	@Override
	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void salir() {
		controlador.terminar();
	}

	@Override
	public void insertarAlarma() {
		Consola.mostrarCabecera("Insertar Alarma");
		try {
			Alarma alarma = Consola.leerAlarma();
			controlador.insertarAlarma(alarma);
			System.out.println("Alarma insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	@Override
	public void borrarAlarma() {
		Consola.mostrarCabecera("Borrar alarma");
		try {
			Alarma alarma = Consola.leerAlarma();
			controlador.borrarAlarma(alarma);
			System.out.println("Alarma borrada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	@Override
	public void buscarAlarma() {
		Consola.mostrarCabecera("Buscar alarma");
		Alarma alarma = null;
		try {
			alarma = Consola.leerAlarma();
			alarma = controlador.buscarAlarma(alarma);
			if (alarma != null) {
				System.out.println("La alarma buscada es: " + alarma);
			} else {
				System.out.println("No existe ninguna alarma");
			}
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	@Override
	public void listarAlarmas() {
		Consola.mostrarCabecera("Listar alarmas");
		List<String> alarmas = controlador.representarAlarmas();
		if (!alarmas.isEmpty()) {
			for (String alarma : alarmas) {
				System.out.println(alarma);
			}
		} else {
			System.out.println("No hay alarmas que listar.");
		}
	}

	@Override
	public void segundosRestantesAlarma() {
		Consola.mostrarCabecera("Tiempo restante para alarma");
		Alarma alarma = null;
		try {
			alarma = Consola.leerAlarma();
			alarma = controlador.buscarAlarma(alarma);
			if (alarma != null) {
				System.out.println("El tiempo restante de la alarma en segundos es: " + alarma.getSegundosRestantes());
			} else {
				System.out.println("No existe la alarma solicitada");

			}
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

}
