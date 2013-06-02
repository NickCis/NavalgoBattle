package punto;


public class Vector {

	
/**
 * Clase que representa un vector
 * 
 * @author Miguel Angel
 *
 */
		private double x;
		private double y;

		public Vector(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Vector() {
			super();
			this.x = 0;
			this.y = 0;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

		public Vector getDireccion() {
			return (new Vector(this.x, this.y));
		}

		
		// calcula el angulo entre este vetor y el que se pasa por parametro.
		public double calcularAnguloCon(Vector otroVector) {
			double aux = this.productoEscalarInternoCon(otroVector);
			return Math.acos(aux
					/ (this.calcularModulo() * otroVector.calcularModulo()));
		}

		
			

		// Calcula el angulo de un vector en relación al semi eje positivo de las x
			public double calcularAnguloConEjeX(Vector otroVector) {
				Double angulo = calcularAnguloCon(otroVector);
				//1º Cuadrante y 2º cuad
				if (otroVector.y >= 0) {
					return angulo;
				}
				//3º Cuadrante y 4º Cuad
				else{
					return ((Math.PI*2) - angulo) ;
				}
			}
		
		
		// Modulo del vector
		public double calcularModulo() {
			return (double) Math.hypot(this.x, this.y);
		}

		// Calcula el producto escalar interno del vector con otro
		public double productoEscalarInternoCon(Vector otroVector) {
			return (this.x * otroVector.getX() + otroVector.getY() * this.y);
		}

		// Multiplica el vector por un escalar
		public Vector porEscalar(Integer escalar) {
			Double x = (this.x * escalar);
			Double y = (this.y * escalar);
			return new Vector(x, y);
		}

		// suma dos vectores
		public Vector mas(Vector otroVector) {
			double x = this.x + otroVector.getX();
			double y = this.y + otroVector.getY();

			Vector nuevoVector = new Vector(x, y);
			return nuevoVector;
		}

		// resta el vector con el que entra por parametro
		public Vector menos(Vector otroVector) {
			double x = this.x - otroVector.getX();
			double y = this.y - otroVector.getY();

			Vector nuevoVector = new Vector(x, y);
			return nuevoVector;
		}

}
