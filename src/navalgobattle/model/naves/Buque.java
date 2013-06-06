package navalgobattle.model.naves;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.model.exceptions.ExceptionNave;


/** Buque. Nave de 4 casilleros, el impacto en cualquier lado de la nave la destruye*/
public class Buque extends Nave{
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
