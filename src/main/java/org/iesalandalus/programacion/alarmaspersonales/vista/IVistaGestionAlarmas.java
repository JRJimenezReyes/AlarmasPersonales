package org.iesalandalus.programacion.alarmaspersonales.vista;

import org.iesalandalus.programacion.alarmaspersonales.controlador.IControladorGestionAlarmas;

public interface IVistaGestionAlarmas {

	void setControlador(IControladorGestionAlarmas controlador);

	void comenzar();

	void salir();

	void insertarAlarma();

	void borrarAlarma();

	void buscarAlarma();

	void listarAlarmas();

	void segundosRestantesAlarma();

}