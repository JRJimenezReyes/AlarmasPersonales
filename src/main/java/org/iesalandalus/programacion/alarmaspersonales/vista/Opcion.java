package org.iesalandalus.programacion.alarmaspersonales.vista;

public enum Opcion {
	SALIR("Salir") {
		public void ejecutar() {
			vista.salir();
		}
	},
	INSERTAR_ALARMA("Insertar alarma") {
		public void ejecutar() {
			vista.insertarAlarma();
		}
	},
	BORRAR_ALARMA("Borrar alarma") {
		public void ejecutar() {
			vista.borrarAlarma();
		}
	},
	BUSCAR_ALARMA("Buscar alarma") {
		public void ejecutar() {
			vista.buscarAlarma();
		}
	},
	LISTAR_ALARMAS("Listar alarmas") {
		public void ejecutar() {
			vista.listarAlarmas();
		}
	},
	SEGUNDOS_RESTANTES("Tiempo restante para alarma") {
		public void ejecutar() {
			vista.segundosRestantesAlarma();
		}
	};

	private String mensajeAMostrar;
	private static IVistaGestionAlarmas vista;

	private Opcion(String mensajeAMostrar) {
		this.mensajeAMostrar = mensajeAMostrar;
	}

	public String getMensaje() {
		return mensajeAMostrar;
	}

	public abstract void ejecutar();

	protected static void setVista(IVistaGestionAlarmas vista) {
		Opcion.vista = vista;
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensajeAMostrar);
	}

	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}

	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
}
