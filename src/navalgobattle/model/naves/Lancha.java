package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;

/** Lancha. Nave de 2 casilleros */
public class Lancha extends Nave {
	public Lancha(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}

	protected int size(){
		return 2;
	}
}
