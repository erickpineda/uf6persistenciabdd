package net.erickpineda.barcos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;

public class Importar {
  private File fichero;
  private static final String TRIPULANTES = "/tripulantes.txt";
  private static final String BARCOS = "/barcos.txt";
  private List<Tripulante> tripulantes;
  private List<Barco> barcos;
  private boolean ok = false;

  public Importar() {
    tripulantes = new ArrayList<Tripulante>();
    barcos = new ArrayList<Barco>();
  }

  public Importar(File fichero) {
    setFichero(fichero);
    tripulantes = new ArrayList<Tripulante>();
    barcos = new ArrayList<Barco>();
  }

  public void procesarDatos(Class<?> clase) {
    procesar(clase);
  }

  public void procesarDatosExistentes(Class<?> clase) {
    try {
      InputStream entrada = null;
      if (clase.isAssignableFrom(Tripulante.class)) {
        entrada = Importar.class.getResourceAsStream(TRIPULANTES);
      }
      if (clase.isAssignableFrom(Barco.class)) {
        entrada = Importar.class.getResourceAsStream(BARCOS);
      }
      if (entrada != null) {
        Reader leer = new InputStreamReader(entrada);
        lecturaDeLineas(new BufferedReader(leer), clase);
        ok = true;
      }
    } catch (IOException e) {
      ok = false;
    }
  }

  private void procesar(Class<?> clase) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fichero));
      lecturaDeLineas(br, clase);
      ok = true;
      br.close();
    } catch (IOException e) {
      ok = false;
    }
  }

  private void lecturaDeLineas(BufferedReader br, Class<?> clase) throws IOException {
    String linea;
    while ((linea = br.readLine()) != null) {
      String[] array = linea.split(",");
      if (clase.isAssignableFrom(Tripulante.class)) {
        if (array != null && array.length == 3) {
          if (!array[0].isEmpty() && !array[1].isEmpty() && !array[2].isEmpty()) {
            tripulantes.add(new Tripulante(array[0], array[1], array[2]));
          }
        }
      }
      if (clase.isAssignableFrom(Barco.class)) {
        if (array != null && array.length == 2) {
          if (!array[0].isEmpty() && !array[1].isEmpty()) {
            barcos.add(new Barco(array[0], array[1]));
          }
        }
      }
    }
  }

  public List<Tripulante> getTripulantes() {
    return tripulantes;
  }

  public List<Barco> getBarcos() {
    return barcos;
  }

  public void setFichero(File fichero) {
    this.fichero = fichero;
  }

  public boolean isOk() {
    return ok;
  }
}