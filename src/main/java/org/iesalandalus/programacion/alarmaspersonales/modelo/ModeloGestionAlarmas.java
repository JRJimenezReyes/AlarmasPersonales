package org.iesalandalus.programacion.alarmaspersonales.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alarmaspersonales.modelo.dao.Alarmas;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;

public class ModeloGestionAlarmas implements IModeloGestionAlarmas {
	
	private Alarmas alarmas;
	
	public ModeloGestionAlarmas() {
		alarmas = new Alarmas();
	}
	
	@Override
	public void insertarAlarma(Alarma alarma) throws OperationNotSupportedException {
		alarmas.insertar(alarma);
	}
	
	@Override
	public void borrarAlarma(Alarma alarma) throws OperationNotSupportedException {
		alarmas.borrar(alarma);
	}
	
	@Override
	public Alarma buscarAlarma(Alarma alarma) throws OperationNotSupportedException {
		return alarmas.buscar(alarma);
	}
	
	@Override
	public List<String> representarAlarmas() {
		return alarmas.representar();
	}
	
	@Override
	public void leerAlarmas() {
		alarmas.leer();
	}
	
	@Override
	public void escribirAlarmas() {
		alarmas.escribir();
	}
}
