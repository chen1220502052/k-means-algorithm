package cn.core.display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class AbstractWindow implements WindowDisplay{



	@Override
	public void displayWindow() {
		// TODO Auto-generated method stub
		createMenu();
		createWindow();
	}

	@Override
	public BufferedImage openImage(String pathFile) {
		// TODO Auto-generated method stub
		
		// openImage from pathFile
		File file = new File(pathFile);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}

	@Override
	public void saveImage(BufferedImage image, String pathFile) {
		// TODO Auto-generated method stub
		File writeFile = new File(pathFile);
		
		//find the suffix,if no : the suffix is 'jpg'
		String suffix = "jpg";
		int index = pathFile.lastIndexOf(".");
		if(index>=0){
			suffix = pathFile.substring(index+1);
		}
		
		try {
			ImageIO.write(image, suffix, writeFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
