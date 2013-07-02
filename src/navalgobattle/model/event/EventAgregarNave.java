package navalgobattle.model.event;

import navalgobattle.model.Nave;
import navalgobattle.model.naves.TipoNave;

public abstract class EventAgregarNave {
	public abstract void agregarNave(Nave nave, TipoNave tipo);
}
