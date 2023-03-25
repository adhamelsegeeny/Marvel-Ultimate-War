package model.world;

import java.awt.*;

import javax.swing.ImageIcon;

public class Cover implements Damageable{
	private int currentHP;
	private Point location;
	private ImageIcon icon;

	public Cover(int x, int y) {
		location = new Point(x, y);
		currentHP = (int) ((Math.random() * 900) + 100);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/shield.png"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(250, 110, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		this.icon=icon;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	
	
	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public void setCurrentHP(int currentHP) {
		if (currentHP < 0)
			this.currentHP = 0;
		else
			this.currentHP = currentHP;
	}

	public Point getLocation() {
		return location;
	}

}
