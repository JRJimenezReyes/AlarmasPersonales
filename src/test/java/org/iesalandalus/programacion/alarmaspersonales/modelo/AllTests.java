package org.iesalandalus.programacion.alarmaspersonales.modelo;

import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaDiariaTest;
import org.iesalandalus.programacion.alarmaspersonales.modelo.dominio.AlarmaEventoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlarmaDiariaTest.class, AlarmaEventoTest.class })
public class AllTests {

}
