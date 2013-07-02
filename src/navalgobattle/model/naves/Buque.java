package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.ParteNave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;


/** Buque. Nave de 4 casilleros, el impacto en cualquier lado de la nave la destruye*/
public class Buque extends Nave{
	public Buque(Posicion maxPos, Posicion pos, int direccion) throws ExceptionNave{
		super(maxPos, pos, direccion);
	}
	protected int size(){
		return 4;
	}
	public void danar(Disparo disparo, Posicion posicion){
		if(this.estoyEnPosicion(posicion))
			this.partes = new ArrayList<ParteNave>();
	}

	public TipoNave getTipo(){
		return TipoNave.BUQUE;
	}

}
