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
	public Lancha(Posicion maxPos, Posicion posicion, int direccion) throws ExceptionNave{
		super(maxPos, posicion, direccion);
    	}

	protected int size(){
		return 2;
	}

	public TipoNave getTipo(){
		return TipoNave.LANCHA;
	}
}
