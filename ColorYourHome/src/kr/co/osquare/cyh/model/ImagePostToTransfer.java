package kr.co.osquare.cyh.model;

public class ImagePostToTransfer {
	
	static ImagePost imgdata;
	public static ImagePost getImagePost(){
		return imgdata;
	}
	public static void setImagePost(ImagePost data){
		imgdata = data;
	}
}
