package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
/**
 * Nave.
 * 
 * 
 * @author todos vamos a meter mano
 */
public class Nave {
	protected int direccion; //-> Guarda en que direccion se mueve
	protected ArrayList<Posicion> posVidas; //-> Lista de posiciones con la vida respectiva de cada poisicion
	protected int xMax;
	protected int yMax;
	//protected NavalgoBattle juego;

	public Nave(int xMax, int yMax) throws ExceptionNave{
		this.xMax = xMax;
		this.yMax = yMax;
		this.posVidas = new ArrayList<Posicion>();
	}

	public Nave(int xMax, int yMax, int direccion) throws ExceptionNave{
		this(xMax, yMax);
		this.setDireccion(direccion);
	}

	public Nave(int xMax, int yMax, int direccion, int x, int y) throws ExceptionNave{
		this(xMax, yMax, direccion);
		this.setPosicion(x, y);
	}
	

	/** Setea la posicion.
	 * @param int x: posicion x
	 * @param int y: posicion y
	 */

	public void setPosicion(int x, int y) throws PosicionInvalida{
		int size = this.size();
		while((size--) > 0){
			if(x < 0 || x > this.xMax || y < 0 || y > this.yMax)
				throw new PosicionInvalida(x, y);

			Posicion estaPos = new Posicion(x, y, this.vidaPorPos());
			this.posVidas.add(estaPos);

			if((this.direccion & 1) != 0)
				x += ( ((this.direccion & 2) != 0)? -1: 1 );
			if((this.direccion & 4) != 0)
				y += ( ((this.direccion & 8) != 0)? -1: 1 );
		}
	}

	protected int vidaPorPos(){
		return 1;
	}
	protected int size(){
		return 1;
	}

	public int getSize(){
		return this.posVidas.size();
	}

	/** Setea la direccion a cual se movera.
	 * @param int dir, se va a usar como flags se usaran los ultimos 4 bytes, 
	 * 1 1 1 1
	 * | | | |_ Indicara si se mueve en x ( & 1)
	 * | | |___ Indica signo de x, 1 menos, 0 mas ( & 2 )
	 * | |_____ Indicia si se mueve en y ( & 4)
	 * |_______ Indica signo de y, 1 menos 0 mas. ( & 8 )
	 */
	public void setDireccion(int dir){
		this.direccion = dir;
	}
	/** Mueve la nave.
	 * 1) Efectua movimiento de la nave, teniendo en cuenta si rebota o no contra los costados
	 * 2) Actualiza las posiciones de su arrayList.
	 */
	
	public void mover(){
		if(!this.puedoMover())
			this.espejarDireccion();

		for(Posicion pos : this.posVidas){
			// TODO: este laburo se podria pasar a la posicion
			int newX = pos.getX();
			int newY = pos.getY();
			if((this.direccion & 1) != 0)
				newX += ( ((this.direccion & 2) != 0)? -1: 1 );
			if((this.direccion & 4) != 0)
				newY += ( ((this.direccion & 8) != 0)? -1: 1 );
			pos.setXY(newX, newY);
		}
	}

	public boolean estoyEnPosicion(int x, int y){
		Posicion estaPos = new Posicion(x, y, 0);
		for(Posicion pos : this.posVidas){
			if(pos.equals(estaPos))
				return true;
		}

		return false;
	}

	/** Dice si se puede mover a la siguiente posicion.
	 * @return verdadero si puede, falso si no.
	 */
	protected boolean puedoMover(){
		for(Posicion pos : this.posVidas){
			// TODO: este laburo se podria pasar a la posicion
			if((this.direccion & 1) != 0){
				int newX = pos.getX() + ( ((this.direccion & 2)!= 0)? -1: 1 );
				if(newX < 0 || newX > this.xMax)
					return false;
			}
			if((this.direccion & 4) != 0){
				int newY = pos.getY() + ( ((this.direccion & 8) != 0)? -1: 1 );
				if(newY < 0 || newY > this.yMax)
					return false;
			}
		}
		return true;
	}

	/** Espeja direccion de movimiento.
	 * Basicamente lo que hace es alternar los dos flags de signo de la direccion
	 */
	protected void espejarDireccion(){
		this.direccion ^= 10;
	}

	/** Esta viva.
	 * @return boolean devuelve si la nave esta viva.
	 */
	public boolean estaViva(){
		boolean estaViva = false;
		for(Posicion pos : this.posVidas){
			if(pos.getVida() > 0)
				estaViva = true;
		}

		return estaViva;
	}

	/** Danar a una nave.
	 * @param Disparo disparo, instancia de disparo que quiere danar a la nave.
	 */
	public void danar(Disparo disparo, int x, int y){
		Posicion estaPos = new Posicion(x, y, 0);

		Iterator<Posicion> it = this.posVidas.iterator();
		while (it.hasNext()){
			Posicion pos = it.next();
			if(pos.equals(estaPos)){
				int vida = pos.getVida();
				vida -= 1;
				if(vida == 0)
					it.remove();
				else
					pos.setVida(vida);
			}
		}
	}
}

/** Lancha. Nave de 2 casilleros */
class Lancha extends Nave {
	public Lancha(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}

	protected int size(){
		return 2;
	}
}

/** Destructur. Nave de 3 casilleros, solamente la danan los disparos directos */
class Destructor extends Nave{
	public Destructor(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}
	protected int size(){
		return 3;
	}
	public void danar(Disparo disparo, int x, int y){}
	public void danar(Convencional disparo, int x, int y){
		super.danar(disparo, x, y);
	}
}

/** Buque. Nave de 4 casilleros, el impacto en cualquier lado de la nave la destruye*/
class Buque extends Nave{
	public Buque(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}
	protected int size(){
		return 4;
	}
	public void danar(Disparo disparo, int x, int y){
		if(this.estoyEnPosicion(x, y))
			this.posVidas = new ArrayList<Posicion>();
	}

}

/** PortaAviones. Nave d 5 casilleros*/
class PortaAviones extends Nave{
	public PortaAviones(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}

	protected int size(){
		return 5;
	}
}

/** RompeHielos. Nave de 3 casilleros, requiere dispararle dos veces en cada casillero para destruirla*/
class RompeHielos extends Nave{
	public RompeHielos(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}

	protected int vidaPorPos(){
		return 2;
	}

	protected int size(){
		return 4;
	}
}



class ExceptionNave extends Exception {}
class PosicionInvalida extends ExceptionNave {
	public PosicionInvalida(int x, int y){}
}

class Posicion {
	private int x;
	private int y;
	private int vida;
	public Posicion(int x, int y, int vida){
		this.x = x;
		this.y = y;
		this.vida = vida;
	}
	public int getX(){
		return this.x;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getY(){
		return this.y;
	}

	public int getVida(){
		return this.vida;
	}

	public int setVida(int vida){
		this.vida = vida;
		return this.vida;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;

		Posicion pos = (Posicion)obj;
		return pos.getX() == this.getX() && pos.getY() == this.getY();
	}
}
