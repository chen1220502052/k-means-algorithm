package cn.core.display;

import java.awt.image.BufferedImage;

/**
 * using this interface to display a window
 *
 * @author cqk
 *
 */
public interface WindowDisplay {
	
	/**
	 * create a menu
	 */
	public void createMenu();
	
	/**
	 * create a window
	 */
	public void createWindow();
	
	/**
	 * display window
	 */
	public void displayWindow();
	
	/**
	 * open an image file and return a BufferedImage instance
	 * @return
	 */
	public BufferedImage openImage(String pathFile);
	
	/**
	 * pathfile is the path of the saving image
	 * @param image
	 * @param pathFile
	 */
	public void saveImage(BufferedImage image, String pathFile);
	
	
}
