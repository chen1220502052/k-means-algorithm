package cn.core.algorithm;

import java.util.ArrayList;

public class ClassCenter {

	private int x;
	private int y;
	
	private int rgbData;
	private int id;
	
	private int oldRGB;
	
	private int[] pixels;
	
	private ArrayList<Integer> pointList = new ArrayList<Integer>();
	
	public ClassCenter(){
		
	}
	
	public ClassCenter(int x, int y, int rgbData){
		this.setX(x);
		this.setY(y);
		this.setRgbData(rgbData);

	}

	public void addPoint(int index){
		pointList.add(index);
	}
	
	//更新中心点的颜色值
	public void reCalCenter(){
		double rSum = 0;
		double gSum = 0;
		double bSum = 0;
		for(int i = 0; i < pointList.size(); i++){
			int index = pointList.get(i);
			int pointRGB = pixels[index];
			
			int pr = (pointRGB >> 16) & 0xff;
			int pg = (pointRGB >> 8) & 0xff;
			int pb = pointRGB & 0xff;
			
			rSum += pr;
			gSum += pg;
			bSum += pb;
		}
		
		int newR = (int)(rSum/pointList.size());
		int newG = (int)(gSum/pointList.size());
		int newB = (int)(bSum/pointList.size());
		
		this.oldRGB = this.rgbData;
		this.rgbData = (255 << 24) | (newR << 16) | (newG << 8) | newB;

		
	}
	
	public void clearPoints(){
		this.pointList.clear();
	}
	
	double getPointDistence(int pointRGB){
		int pr = (pointRGB >> 16) & 0xff;
		int pg = (pointRGB >> 8) & 0xff;
		int pb = pointRGB & 0xff;
		
		int cr = (rgbData >> 16) & 0xff;
		int cg = (rgbData >> 8) & 0xff;
		int cb = (rgbData & 0xff);
		
		return Math.sqrt(Math.pow((pr-cr), 2.0)+Math.pow((pg-cg), 2.0)+Math.pow((pb-cb), 2.0));
		
	}
	public double getOldNewDis(){
		
		return Math.pow((oldRGB-rgbData), 2.0);
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRgbData() {
		return rgbData;
	}

	public void setRgbData(int rgbData) {
		this.rgbData = rgbData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOldRGB() {
		return oldRGB;
	}

	public void setOldRGB(int oldRGB) {
		this.oldRGB = oldRGB;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public ArrayList<Integer> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<Integer> pointList) {
		this.pointList = pointList;
	}
	
	
}
