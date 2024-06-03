package ProjectCode.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatosTest {

    @Test
    void filtrarExcursiones() {
        //Configurar estado inicial.

        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2024, 01, 1);
        LocalDate fechaFin = LocalDate.of(2024, 12, 31);


        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);


        // hay 4 excursiones que cumplen en el periodo indicado.
        assertEquals(4, excursionesFiltradas.size());
        // El codigo de excursion en la posicion 0 es M1
        assertEquals("M1", excursionesFiltradas.get(0).getCodigo());


        for (E0_Excursiones excursion : excursionesFiltradas) {
            assertTrue(excursion.getFecha().isAfter(fechaInicio) || excursion.getFecha().isEqual(fechaInicio),
                    "La fecha de la excursión no está dentro del rango especificado");
            assertTrue(excursion.getFecha().isBefore(fechaFin) || excursion.getFecha().isEqual(fechaFin),
                    "La fecha de la excursión no está dentro del rango especificado");
        }


    }

    @Test
    void filtrarExcursiones_SinCoincidencias() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(0, excursionesFiltradas.size());
    }

    @Test
    void filtrarExcursiones_FechasLimite() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2024, 4, 19);
        LocalDate fechaFin = LocalDate.of(2024, 4, 19);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(1, excursionesFiltradas.size());
    }

    @Test
    void filtrarExcursiones_FueraDelRango() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2025, 1, 1);
        LocalDate fechaFin = LocalDate.of(2025, 12, 31);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(0, excursionesFiltradas.size());
    }

    @Test
    void filtrarExcursiones_ExcursionesDeUnDia() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2024, 8, 11);
        LocalDate fechaFin = LocalDate.of(2024, 8, 11);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(1, excursionesFiltradas.size());
    }

    @Test
    void filtrarExcursiones_ExcursionesEnElMismoDia() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2024, 4, 19);
        LocalDate fechaFin = LocalDate.of(2024, 4, 19);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(1, excursionesFiltradas.size());
        assertEquals("M1", excursionesFiltradas.get(0).getCodigo()); // Verificar que sea la excursión correcta
    }

    @Test
    void filtrarExcursiones_ExcursionesEnDiferentesDias() {
        Datos datos = new Datos();
        datos.cargaInicialExcursiones();
        LocalDate fechaInicio = LocalDate.of(2024, 4, 19);
        LocalDate fechaFin = LocalDate.of(2024, 8, 22);

        ArrayList<E0_Excursiones> excursionesFiltradas = datos.filtrarExcursiones(fechaInicio, fechaFin);

        assertEquals(4, excursionesFiltradas.size());
    }


    @Test
    void eliminarExcursion() {
        // Preparar datos de prueba
        String codigoExcursionExistente = "M1";
        ArrayList<E0_Excursiones> excursiones = new ArrayList<>();
        // Agrega excursiones a la lista de excursiones
        excursiones.add(new E0_Excursiones("M1", "Excursión por el Montseny", LocalDate.parse("2024-04-19"), 2, 50.5));
        excursiones.add(new E0_Excursiones("A1", "Excursión por los Alpes", LocalDate.parse("2024-05-26"), 3, 220));
        excursiones.add(new E0_Excursiones("PE1", "Visita a los picos de Europa", LocalDate.parse("2024-08-22"), 5, 250));
        excursiones.add(new E0_Excursiones("E1", "Subida al Everest ", LocalDate.parse("2024-08-11"), 7, 1250));

        // Crea una instancia de la clase donde esta el metodo
        Datos datos = new Datos();


        // Verificar si la excursión fue eliminada
        boolean excursionEliminada = true;
        for (E0_Excursiones excursion : datos.mostrarExcursiones()) {
            if (excursion.getCodigo().equals(codigoExcursionExistente)) {
                excursionEliminada = false;
                break;
            }
        }
        assertTrue(excursionEliminada, "La excursión con el código " + codigoExcursionExistente + " no fue eliminada correctamente.");
    }
}