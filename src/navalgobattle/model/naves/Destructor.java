package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

/** Destructur. Nave de 3 casilleros, solamente la danan los disparos directos */
public class Destructor extends Nave{
	public Destructor(Posicion maxPos, Posicion pos, int direccion) throws ExceptionNave{
		super(maxPos, pos, direccion);
	}
	protected int size(){
		return 3;
	}
	public void danar(Disparo disparo, Posicion posicion){
		if(disparo instanceof Convencional)
			super.danar(disparo, posicion);
	}
	public void danar(Convencional disparo, Posicion posicion){
		super.danar(disparo, posicion);
	}

	public TipoNave getTipo(){
		return TipoNave.DESTRUCTOR;
	}
}
