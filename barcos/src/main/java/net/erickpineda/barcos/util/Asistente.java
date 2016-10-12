package net.erickpineda.barcos.util;

public class Asistente {
  private static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
  private static final String[] NOMBRES = { "Mar�a", "Pilar", "Francisco", "Juan", "Josefa", "Miguel", "Javier",
	  "Manuel", "Sandra", "Pedro", "Pilar", "Yolanda", "Ram�n", "Jes�s", "Rosa", "Isabel", "Teresa", "Martin", "Ramona",
	  "Max", "Mateu", "Redenci�n", "Ramiro" };

  private static final String[] APELLIDOS = { "Pineda", "Labrador", "Palomino", "S�nchez", "Garc�a", "L�pez", "Mena",
	  "Hern�ndez", "Palacio", "Conde", "Goicoechea", "Sousa", "Valenzuela", "Merch�n", "Moya", "Bonilla", "Carrero",
	  "Fern�ndez", "Ferrero", "Solorzano", "Cede�o", "Mu�oz", "D�az", "Ramos", "Lopez", "Roronoa", "V�stago", "R�pido",
	  "Furioso", "Cool", "Bacca", "Toro", "Salvador", "Rigor", "Fraude", "Fraile", "Villarraga", "Jimenez", "Caicedo",
	  "M�rquez", "Bonilla" };

  private static final String[] PREFIJOS = { "A", "An", "Ab", "Ar", "B", "Ba", "Bre", "C", "Ch", "Cre", "D", "Des", "U",
	  "De", "Dr", "Dre", "P", "Pre", "Cl", "Ca", "Z", "X", "E", "Er", "F", "Fa", "G", "Gr", "H", "Gl", "I", "Im", "J",
	  "In", "K", "Pla", "L", "Lord", "Re", "M", "Mar", "N", "Nar", "O", "Ohm", "P", "Cant", "Inter", "Y" };

  private static final String[] DERIVACION = { "a", "e", "i", "o", "u", "", "ve", "be", "ba", "bu", "vo", "bo", "ro",
	  "co", "mun", "cendie", "bad", "badd", "rar", "cer", "mar", "ra", "n", "th", "tr", "bu", "di", "nb", ".", "-", " ",
	  ". " };

  private static final String[] SUFIJOS = { "a", "ia", "la", "on", "om", "um", "un", "ca", "an", "co", "io", "mo", "sa",
	  "se", "no", "ra", "or", "ion", "so", "ge", "l", "el", "ma", "me", "n", "e", "o", "u", "ka", "fa", "ope", "ohm",
	  "s", "es", "nte", "ton", "x" };

  public static final String PATRON_MATRICULA = "[1-9]{1}(-)[A-Z]{2}(-)[0-9]{1}(-)[0-9]{3}(-)[0-9]{2}";

  public static final String REGEXP_DNI = "[1-9]{8}[^iouIOU]{1}";

  public Asistente() {

  }

  /**
   * Devuelve un NIF completo a partir de un DNI. Es decir, a�ade la letra del NIF.
   * 
   * @param dni dni al que se quiere a�adir la letra del NIF.
   * @return NIF completo.
   */
  public static String generaDNI(int dni) {
	String _dni = String.format("%08d", dni);
	int nuevoDNI = dni;

	// Si el n�mero por par�metro empieza con cero '0', entonces reemplazo esa cifra por otro aleatorio
	if (_dni.substring(0, 1).matches("[0]")) {
	  String sinCeroIzquierda = String.format("%d", rand(1, 9));
	  _dni = _dni.substring(0, 1).replace("0", sinCeroIzquierda) + nuevoDNI;
	  nuevoDNI = Integer.parseInt(_dni);
	}

	_dni = String.valueOf(nuevoDNI) + NIF_STRING_ASOCIATION.charAt(nuevoDNI % 23);
	return _dni;
  }

  /**
   * Genera de forma aleatoria nombres y apellidos de personas.
   * 
   * @param cantidad la cantidad de nombres completos para generar.
   * @return un array de String con cada nombre completo.
   */
  public static String[] generaNombresPersonasCompletos(int cantidad) {
	String[] array = new String[cantidad];
	for (int i = 0; i < array.length; i++) {
	  array[i] = generaNombreCompleto();
	}
	return array;
  }

  /**
   * Genera un rango de soldado, sean: Capit�n, Jefe de grupo o Marinero.
   * 
   * @return un String con el rango que fue generado aleatoriamente.
   */
  public static String generaRango() {
	int numero = rand(0, 2);
	if (numero == 0) {
	  return "Capitan";
	}
	if (numero == 1) {
	  return "Jefe de grupo";
	}
	return "Marinero";
  }

  public static String[] generaMatriculas(int cantidad) {
	String[] array = new String[cantidad];
	for (int i = 0; i < array.length; i++) {
	  array[i] = generaMatricula();
	}
	return array;
  }

  public static String[] generaNombresBarcos(int cantidad) {
	String[] array = new String[cantidad];
	for (int i = 0; i < array.length; i++) {
	  array[i] = generaNombreBarco();
	}
	return array;
  }

  public static String generaMatricula() {
	// La categoria es un n�mero entero de 1 a 9
	int categoria = rand(1, 9);
	// La provincia son dos letras en May�scula
	String provinciaMaritima = generaCadenaAleatoria(2);
	// El distrito mar�timo es un n�mero entero de 0 a 9
	int distritoMaritimo = rand(0, 9);
	// El folio de inscripci�n es un n�mero entero de 3 cifras y pueden ir ceros a la izquierda
	String folioInscripcion = String.format("%03d", generaNumeroConCantidadDeCifras(3));
	// El a�o de matriculaci�n es un n�mero entero de 2 cifras y pueden ir ceros a la izquierda
	String anyMatriculacion = String.format("%02d", generaNumeroConCantidadDeCifras(2));
	// La matr�cula ya concatenada seg�n el formato
	String matricula = String.format("%d-%s-%d-%s-%s",
                            		categoria,
                            		provinciaMaritima,
                            		distritoMaritimo,
                            		folioInscripcion,
                            		anyMatriculacion);

	// Validaci�n de la matr�cula a nivel de Expresi�n Regular
	if (matricula.matches(PATRON_MATRICULA)) {
	  return matricula;
	}
	// Repite otra matr�cula nueva si de casualidad no cumple el patr�n
	return generaMatricula();
  }

  public static String generaNombreBarco() {
	String nombre = PREFIJOS[rand(0, PREFIJOS.length - 1)] + DERIVACION[rand(0, DERIVACION.length - 1)]
		+ SUFIJOS[rand(0, SUFIJOS.length - 1)];
	return nombre;
  }

  public static String generaNombreCompleto() {
	String nombres = "SIN NOMBRE";
	String apellidos = "SIN NOMBRE";

	if (rand(1, 2) == 1) {
	  nombres = generaNombrePersona();
	} else {
	  nombres = generaNombreCompletoPersona();
	}

	if (rand(1, 2) == 1) {
	  apellidos = generaApellido();
	} else {
	  apellidos = generaApellidos();
	}

	return String.format("%s %s", nombres, apellidos);
  }

  public static String generaCadenaAleatoria(int longitud) {
	String cadenaAleatoria = "";
	char letra;
	for (int i = 0; i < longitud; i++) {
	  letra = (char) rand(65, 90); // Obtiene la letra en may�scula
	  cadenaAleatoria += letra;
	}
	return cadenaAleatoria;
  }

  public static int generaNumeroConCantidadDeCifras(int cifras) {
	String numero = "";
	for (int i = 0; i < cifras; i++) {
	  numero += rand(0, 9);
	}
	return Integer.parseInt(numero);
  }

  public static String generaNombrePersona() {
	return NOMBRES[rand(0, NOMBRES.length - 1)];
  }

  public static String generaNombreCompletoPersona() {
	return String.format("%s %s", generaNombrePersona(), generaNombrePersona());
  }

  public static String generaApellido() {
	return APELLIDOS[rand(0, APELLIDOS.length - 1)];
  }

  public static String generaApellidos() {
	return String.format("%s %s", generaApellido(), generaApellido());
  }

  public static boolean validarMatricula(String matricula) {
	return (matricula.matches(PATRON_MATRICULA));
  }

  public static boolean validaNif(String nif) {
	if (nif.substring(0, 8).length() == 8) {
	  return nif.equalsIgnoreCase(generaDNI(Integer.parseInt(nif.substring(0, 8)))) ? true : false;
	} else {
	  return false;
	}
  }

  public static int rand(int desde, int hasta) {
	return (int) (Math.random() * (hasta - desde + 1) + desde);
  }
}