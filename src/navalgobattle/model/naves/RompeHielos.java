package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;

/**
 * RompeHielos. Nave de 3 casilleros, requiere dispararle dos veces en cada
 * casillero para destruirla
 */
public class RompeHielos extends Nave {

	public RompeHielos(Posicion maxPos, Posicion pos, int direccion) throws ExceptionNave {
		super(maxPos, pos, direccion);
	}

	protected int vidaPorPos() {
		return 2;
	}

	protected int size() {
		return 4;
	}
}
