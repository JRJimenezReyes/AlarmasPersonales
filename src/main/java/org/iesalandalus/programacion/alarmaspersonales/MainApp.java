package org.iesalandalus.programacion.alarmaspersonales;

import org.iesalandalus.programacion.alarmaspersonales.controlador.ControladorGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.controlador.IControladorGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.modelo.IModeloGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.modelo.ModeloGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.vista.IVistaGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.vista.VistaGestionAlarmas;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de alarmas personales.");
		IModeloGestionAlarmas modelo = new ModeloGestionAlarmas();
		IVistaGestionAlarmas vista = new VistaGestionAlarmas();
		IControladorGestionAlarmas controlador = new ControladorGestionAlarmas(vista, modelo);
		controlador.comenzar();
	}

}
