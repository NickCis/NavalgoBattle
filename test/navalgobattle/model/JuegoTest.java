package navalgobattle.model;

import java.util.ArrayList;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.MinaContacto;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Jugador;
import navalgobattle.model.Nave;
import navalgobattle.model.naves.Lancha;
import navalgobattle.util.logger.Logger;

public class JuegoTest extends TestCase{
        NavalgoBattle juego;
        Posicion pos;
        Jugador jugador;
	@Before
	public void setUp(){
                this.jugador = new Jugador();
                this.pos = new Posicion(10,10);
                this.juego = new NavalgoBattle();
                this.juego.setJugador(jugador);

		Logger.initialice();
	}
        
        public void testTerminoJuego() throws Exception {
		Jugador jug = new Jugador();
                juego.addNave(new Nave(pos,pos,1));
                juego.setJugador(jug);
                                                         
		this.assertEquals(juego.terminoJuego(), true);                
                jug.addPuntos(1);                
                this.assertEquals(juego.terminoJuego(), false);                                              
                juego.siguienteTurno(); 
                this.assertEquals(juego.terminoJuego(), true);

                
	}
        public void testMatarNaveConConvensional()  throws Exception{
        //se matan en el mismo turno para hacer el test
               Lancha nave = new Lancha(pos,new Posicion(2,2),1);
               juego.addNave(nave);
               ArrayList<Posicion> posiciones = nave.getPosiciones();
               juego.disparar(new Convencional(juego,posiciones.get(0)));
               juego.disparar(new Convencional(juego,posiciones.get(1)));
               juego.siguienteTurno();
               this.assertEquals(nave.estaViva(), false);
        }
        public void testMatarNaveConMinaContacto()  throws Exception{
        //se matan en el mismo turno para hacer el test
               Lancha nave = new Lancha(pos,new Posicion(2,2),1);
               juego.addNave(nave);
               ArrayList<Posicion> posiciones = nave.getPosiciones();
               juego.disparar(new MinaContacto(juego,posiciones.get(0).getSiguiente(1)));
               juego.disparar(new MinaContacto(juego,posiciones.get(1).getSiguiente(1)));
               juego.siguienteTurno();
               this.assertEquals(nave.estaViva(), false);
        }
        public void testMatarNaveConMinaRetardada()  throws Exception{
        //se matan en el mismo turno para hacer el test
               Lancha nave = new Lancha(pos,new Posicion(2,2),1);
               juego.setMaximaPosicion(pos);
               juego.addNave(nave);
               ArrayList<Posicion> posiciones = nave.getPosiciones();
               MinaRetardada mina1 = new MinaRetardada(juego,posiciones.get(1));
               mina1.setRadio(2);
               mina1.setRetardo(1);
               //Disparo mina2 = new MinaContacto(juego,posiciones.get(1).getSiguiente(1))
               juego.disparar(mina1);
               //juego.disparar(mina2);
               juego.siguienteTurno();
               juego.siguienteTurno();
               this.assertEquals(nave.estaViva(), false);
        }

}