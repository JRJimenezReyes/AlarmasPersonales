package org.iesalandalus.programacion.alarmaspersonales.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;

public interface IControladorGestionAlarmas {

	void comenzar();

	void terminar();

	void insertarAlarma(Alarma alarma) throws OperationNotSupportedException;

	void borrarAlarma(Alarma alarma) throws OperationNotSupportedException;

	Alarma buscarAlarma(Alarma alarma) throws OperationNotSupportedException;

	List<String> representarAlarmas();

}