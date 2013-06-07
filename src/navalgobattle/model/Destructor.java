package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

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