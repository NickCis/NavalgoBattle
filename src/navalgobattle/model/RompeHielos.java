
package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

/**
 * RompeHielos. Nave de 3 casilleros, requiere dispararle dos veces en cada
 * casillero para destruirla
 */
class RompeHielos extends Nave {

    public RompeHielos(int xMax, int yMax, int x, int y, int direccion) throws ExceptionNave {
        super(xMax, yMax, x, y, direccion);
    }

    protected int vidaPorPos() {
        return 2;
    }

    protected int size() {
        return 4;
    }
}
