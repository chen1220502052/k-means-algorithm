package cn.core.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * using k-means algorithm to process an image
 * @author cqk
 *
 */
public class KmeansProcess implements AlgoProcess {
	
	private int clusterNum = 8;
	private double mu = 5;
	private int width;
	private int height;
	private int[] pixels;
	
	private ArrayList<ClassCenter> centerList = new ArrayList<ClassCenter>();
	
	public void initKmeans(BufferedImage image){
		this.width = image.getWidth();
		this.height = image.getHeight();
		pixels = new int [width*height];
		int type = image.getType();
		
		//init image's pixels
		if(type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_BGR)
			image.getRaster().getDataElements(0, 0, width, height, pixels);
		else 
			image.getRGB(0, 0, width, height, pixels, 0, width);
		
	}
	
	//优化模式确定聚类中点
	
	public void initBymeans(){
		InitKMeans init = new InitKMeans();
		init.initAll(pixels, width, height);
		this.centerList = init.getCenterList();
		this.clusterNum = init.getK();
	}
	
	//随机模式确定聚类中点
	public void initClusterCenter(){
		Random random = new Random();
		for(int i = 0; i < clusterNum; i++){
			int randomNumerX = random.nextInt(width);
			int randomNumerY = random.nextInt(height);
			int index = randomNumerX + randomNumerY * width;
			
			ClassCenter center = new ClassCenter(randomNumerX, randomNumerY, pixels[index]);
			center.setId(i);
			center.setPixels(pixels);
			centerList.add(center);
		}
	}
	
	/**
	 * cluster points
	 */
	public void clusterPoints(){
		
		for(int i = 0; i < pixels.length; i++){
			int minIndex = 0;
			double minDis = Double.MAX_VALUE;
			for(int j = 0; j < clusterNum; j++){
				double dis = centerList.get(j).getPointDistence(pixels[i]);
				if(dis < minDis){
					minIndex = j;
					minDis = dis;
				}
			}
			centerList.get(minIndex).addPoint(i);
		}
		
	}
	
	public boolean isEnd(){
		
		double diffSum = 0;
		
		for(int j = 0; j < clusterNum; j++){
			diffSum += centerList.get(j).getOldNewDis();
		}
		
		if(diffSum <= mu)
			return true;
		
		return false;
	}
	


	@Override
	public void process(BufferedImage image) {
		// TODO Auto-generated method stub

		initKmeans(image);
		
//		initClusterCenter();//随机模式执行kmeans算法
		initBymeans();//优化模式执行kmeans算法
		
		while(true){
			clusterPoints();
			
			for(int j = 0; j < clusterNum; j++){
				centerList.get(j).reCalCenter();
			}
			
			if(isEnd()){
				break;
				
			}else{
				for(int k = 0; k < clusterNum; k++){
					centerList.get(k).clearPoints();
				}
			}
				
		}
		
		writeResult(image);
	}
	
	public void writeResult(BufferedImage image){
		int[] newPixels = new int[width*height];
		
		for(int j = 0; j < clusterNum; j++){
			List<Integer> list = centerList.get(j).getPointList();
			
			for(int i = 0; i < list.size(); i++){
				int index = list.get(i);
				newPixels[index] = centerList.get(j).getRgbData();
			}
		}
		
		image.setRGB(0, 0, width, height, newPixels, 0, width);
	}

}
