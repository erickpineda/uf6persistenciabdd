package net.erickpineda.reptilesyanfibios.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConexionBDD {
  /**
   * Conector hacia el SGBDD.
   */
  private static Connection conexion;
  /**
   * Nombre que de la base de datos a conectar.
   */
  private String bd = "reptils";
  /**
   * Nombre del usuario de la base de datos.
   */
  private String user = "user";
  /**
   * Contraseña del usuario de la base de datos.
   */
  private String password = "password";
  /**
   * Nombre de host para el SGBDD.
   */
  private String host = "localhost";
  /**
   * Enlaza la conexión del host y bdd hacia el servidor.
   */
  private String server;

  /**
   * Constructor de la conexión hacia el SGBDD.
   */
  public ConexionBDD() {
    this.server = "jdbc:mysql://" + host + "/" + bd;
  }

  /**
   * Método que reúne las líneas de conexión hacia el SGBDD.
   */
  public ConexionBDD conectarBD() {

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conexion = (Connection) DriverManager.getConnection(server, user, password);
      System.out.println("-> Conexión a base de datos " + server + " ... OK\n");

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("-> Error cargando el Driver MySQL JDBC ... FAIL");
      return null;
    }

    return this;
  }

  /**
   * Método que realiza consultas SELECT sobre la BDD, recibiendo un String como parámetro.
   * 
   * @param miConsulta Parámetro que será una SELECT, pasado como String.
   */
  public List<String> consultar(final String miConsulta) {
    List<String> lista = new ArrayList<String>();
    try {
      Statement s = conexion.createStatement();
      ResultSet rs = s.executeQuery(miConsulta);// Puntero o cursor a la fila actual

      while (rs.next()) {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          lista.add(rs.getString(i));
        }
      }

    } catch (SQLException e) {
      return null;
    }
    return lista;
  }

  public String[] obtenerColumnasTabla(final String sql) {
    String[] nombresColumnas = null;
    try {
      ResultSet rs = conexion.createStatement().executeQuery(sql);
      ResultSetMetaData metaData = rs.getMetaData();
      int numeroColumna = metaData.getColumnCount(); // numero de columna
      nombresColumnas = new String[numeroColumna];
      for (int i = 1; i <= numeroColumna; i++) {
        nombresColumnas[i - 1] = metaData.getColumnLabel(i);
      }
    } catch (SQLException e) {
      return null;
    }
    return nombresColumnas;
  }

  /**
   * Método que se encarga de insertar datos a la base de datos.
   * 
   * @param miConsulta Parámetro que será un INSERT, pasado como String.
   */
  public void insertarDatos(final String miConsulta) {
    try {
      PreparedStatement s = (PreparedStatement) conexion.prepareStatement(miConsulta);
      s.execute(miConsulta);

    } catch (SQLException e) {
      try {
        conexion.rollback(); // Rollback si el INSERT es erróneo
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }

  /**
   * Comprobar si la conexión actual está cerrada.
   * 
   * @return retorna true si la conexión está cerrada y false si aún sigue abierta.
   */
  public boolean isClosed() {
    boolean closed = true;
    try {
      closed = conexion.isClosed();
    } catch (SQLException e) {
    }
    return closed;
  }

  /**
   * Método que cierra la conexión de la BDD.
   */
  public void desconectarDB() {
    try {
      conexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("-> Imposible cerrar conexión ... FAIL");
    }
  }
}