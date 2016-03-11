package cn.core.display;

import java.awt.image.BufferedImage;

public abstract class AbstractWindow implements WindowDisplay{



	@Override
	public void displayWindow() {
		// TODO Auto-generated method stub
		createMenu();
		createWindow();
	}

	@Override
	public BufferedImage openImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveImage(BufferedImage image, String pathFile) {
		// TODO Auto-generated method stub
		
	}

}
