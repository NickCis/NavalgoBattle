package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;

/** PortaAviones. Nave d 5 casilleros*/
public class PortaAviones extends Nave{
	public PortaAviones(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave{
		super(xMax, yMax, x, y, direccion);
	}

	protected int size(){
		return 5;
	}
}
