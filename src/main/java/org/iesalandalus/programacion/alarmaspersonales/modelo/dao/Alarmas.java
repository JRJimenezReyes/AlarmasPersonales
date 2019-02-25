package org.iesalandalus.programacion.alarmaspersonales.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.Alarma;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaDiaria;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaEvento;

public class Alarmas {
	
	private static final String NOMBRE_FICHERO_ALARMAS = "ficheros/alarmas.dat";
	List<Alarma> coleccionAlarmas;

	public Alarmas() {
		coleccionAlarmas = new ArrayList<>();
	}

	public Alarmas(Alarmas alarmas) {
		if (alarmas == null) {
			throw new IllegalArgumentException("No se pueden copiar alarmas nulas.");
		}
		coleccionAlarmas = copiaProfundaAlarmas(alarmas.coleccionAlarmas);
	}

	public List<Alarma> copiaProfundaAlarmas(List<Alarma> alarmas) {
		if (alarmas == null) {
			throw new IllegalArgumentException("No se puede copiar una lista de alarmas nulas.");
		}
		List<Alarma> otrasAlarmas = new ArrayList<>();
		for (Alarma alarma : alarmas) {
			if (alarma instanceof AlarmaDiaria) {
				otrasAlarmas.add(new AlarmaDiaria((AlarmaDiaria) alarma));
			} else if (alarma instanceof AlarmaEvento) {
				otrasAlarmas.add(new AlarmaEvento((AlarmaEvento) alarma));
			}
		}
		return otrasAlarmas;
	}

	public List<Alarma> getAlarmas() {
		return copiaProfundaAlarmas(coleccionAlarmas);
	}

	public int getNumAlarmas() {
		return coleccionAlarmas.size();

	}

	public void insertar(Alarma alarma) throws OperationNotSupportedException {
		if (alarma == null) {
			throw new IllegalArgumentException("No se puede insertar una alarma nula.");
		}
		if (coleccionAlarmas.contains(alarma)) {
			throw new OperationNotSupportedException("La alarma ya existe.");
		} else {
			if (alarma instanceof AlarmaDiaria)
				coleccionAlarmas.add(new AlarmaDiaria((AlarmaDiaria) alarma));

			if (alarma instanceof AlarmaEvento) {
				if (alarma.getSegundosRestantes() > 0) {
					coleccionAlarmas.add(new AlarmaEvento((AlarmaEvento) alarma));
				} else {
					throw new OperationNotSupportedException("No se puede establecer una alarma de evento anterior al momento actual");
				}
			}
		}
	}

	public Alarma buscar(Alarma alarma) throws OperationNotSupportedException {
		Alarma alarmaEncontrada = null;
		if (alarma == null) {
			throw new IllegalArgumentException("No se puede buscar una alarma nula.");
		}
		if (coleccionAlarmas.contains(alarma)) {
			if (alarma instanceof AlarmaDiaria) {
				alarmaEncontrada = new AlarmaDiaria((AlarmaDiaria) coleccionAlarmas.get(coleccionAlarmas.indexOf(alarma)));
			} else if (alarma instanceof AlarmaEvento) {
				alarmaEncontrada = new AlarmaEvento((AlarmaEvento) coleccionAlarmas.get(coleccionAlarmas.indexOf(alarma)));
			}
		} else {
			throw new OperationNotSupportedException("La alarma buscada no existe.");
		}
		return alarmaEncontrada;
	}

	public void borrar(Alarma alarma) throws OperationNotSupportedException {
		if (alarma == null) {
			throw new IllegalArgumentException("No se puede borrar una alarma nula.");
		}
		if (coleccionAlarmas.contains(alarma)) {
			coleccionAlarmas.remove(alarma);
		} else {
			throw new OperationNotSupportedException("La alarma a borrar no existe.");
		}

	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Alarma alarma : coleccionAlarmas) {
			representacion.add(alarma.toString());
		}
		return representacion;
	}
	
	public void leer() {
		File ficheroAlarmas = new File(NOMBRE_FICHERO_ALARMAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAlarmas))) {
			Alarma alarma = null;
			do {
				alarma = (Alarma) entrada.readObject();
				insertar(alarma);
			} while (alarma != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de alarmas.");
		} catch (EOFException e) {
			System.out.println("Fichero alarmas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroAlarmas = new File(NOMBRE_FICHERO_ALARMAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAlarmas))){
			for (Alarma alarma : coleccionAlarmas)
				salida.writeObject(alarma);
			System.out.println("Fichero alarmas escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de alarmas");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}
