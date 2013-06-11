package navalgobattle.view;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import navalgobattle.view.Boton;
import navalgobattle.view.Ventana;


public class Botonera {
	Ventana ventana;
	int x;
	int y;
	int xOffset;
	int yOffset;

	public Botonera(Ventana ventana, int x, int y, int xOffset, int yOffset){
		this.ventana = ventana;
		this.x = x;
		this.y = y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void add(final Boton boton){
		JButton comp = boton.getComponent();

		comp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton.action(ventana);
			}
		});

		comp.setBounds(this.x + this.xOffset, this.y + this.yOffset, boton.getWidth(), boton.getHeight());

		if(this.xOffset != 0)
			this.x += this.xOffset+ boton.getWidth();
		if(this.yOffset != 0)
			this.y += this.yOffset+ boton.getHeight();

		this.ventana.add(comp);

	}
}
