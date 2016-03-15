package cn.program.startup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.core.algorithm.AlgoProcess;
import cn.core.algorithm.KmeansProcess;

public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AlgoProcess process = new KmeansProcess();
		
		File file = new File("C:/Users/cqk/Desktop/test.png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		process.process(image);
		
		String pathFile = "C:/Users/cqk/Desktop/result.png";
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
