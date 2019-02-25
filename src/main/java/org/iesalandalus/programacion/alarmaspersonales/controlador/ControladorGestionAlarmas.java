package org.iesalandalus.programacion.alarmaspersonales.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alarmaspersonales.modelo.IModeloGestionAlarmas;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;
import org.iesalandalus.programacion.alarmaspersonales.vista.IVistaGestionAlarmas;

public class ControladorGestionAlarmas implements IControladorGestionAlarmas {
	
	private IVistaGestionAlarmas vista;
	private IModeloGestionAlarmas modelo;
	
	public ControladorGestionAlarmas(IVistaGestionAlarmas vista, IModeloGestionAlarmas modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.setControlador(this);
	}
	
	@Override
	public void comenzar() {
		modelo.leerAlarmas();
		vista.comenzar();
	}
	
	@Override
	public void terminar() {
		modelo.escribirAlarmas();
		System.out.println("Hasta luego Lucas!!!");
	}
	
	@Override
	public void insertarAlarma(Alarma alarma) throws OperationNotSupportedException {
		modelo.insertarAlarma(alarma);
	}
	
	@Override
	public void borrarAlarma(Alarma alarma) throws OperationNotSupportedException {
		modelo.borrarAlarma(alarma);
	}
	
	@Override
	public Alarma buscarAlarma(Alarma alarma) throws OperationNotSupportedException {
		return modelo.buscarAlarma(alarma);
	}
	
	@Override
	public List<String> representarAlarmas() {
		return modelo.representarAlarmas();
	}
}
