package com.pers.yefei.LayIM.utils;

import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenCVUtil {
	private final static String cascadeFile;
	private static final Logger logger = LoggerFactory.getLogger(OpenCVUtil.class);
	static {
		try {
			// 读取并写入临时文件
			InputStream is = OpenCVUtil.class.getResourceAsStream("haarcascade_frontalface_alt.xml2");
			String tmpdir = System.getProperty("java.io.tmpdir");
			cascadeFile = new File(tmpdir, "haarcascade_frontalface_alt.xml2").getCanonicalPath();
			FileUtil.writeInputStream(is, cascadeFile);

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		} catch (Throwable ex) {
			logger.error(ex.toString(),ex);
			throw new RuntimeException(ex);
		}
	}

	public static List<BufferedImage> detectFaceImages(BufferedImage awtImg) throws IOException {

		byte[] pixels = ((DataBufferByte) awtImg.getRaster().getDataBuffer()).getData();
		Mat newMat = new Mat(awtImg.getHeight(), awtImg.getWidth(), CvType.CV_8UC3);
		newMat.put(0, 0, pixels);

		// Highgui.imwrite("1111.jpg", newMat);

		CascadeClassifier faceDetector = new CascadeClassifier(cascadeFile);

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(newMat, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		List<BufferedImage> faceImgs = new ArrayList<BufferedImage>();

		for (Rect rect : faceDetections.toArray()) {
			// Mat subMat = newMat.submat(rect);
			//
			// MatOfByte matOfByte = new MatOfByte();
			// Highgui.imencode(".jpg", subMat, matOfByte);
			// byte[] byteArray = matOfByte.toArray();
			// InputStream inS = new ByteArrayInputStream(byteArray);
			// BufferedImage subBufImg = ImageIO.read(inS);

			Rectangle tRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
			BufferedImage subBufImg = awtImg.getSubimage(tRect.x, tRect.y, tRect.width, tRect.height);
			faceImgs.add(subBufImg);
		}
		return faceImgs;
	}

	public static List<Rectangle> detectFaceRects(BufferedImage orginalImage) {
		List<Rectangle> faceRects = new ArrayList<Rectangle>();
		try {
			BufferedImage awtImg = imageConverter(orginalImage);
			
			long curtime = System.currentTimeMillis();
			byte[] pixels = ((DataBufferByte) awtImg.getRaster().getDataBuffer()).getData();
			Mat newMat = new Mat(awtImg.getHeight(), awtImg.getWidth(), CvType.CV_8UC3);
			newMat.put(0, 0, pixels);

			System.out.println(String.format("detectFaceRects use time millis=%s ", System.currentTimeMillis()
					- curtime));

			CascadeClassifier faceDetector = new CascadeClassifier(cascadeFile);

			long matOfRectCurtime = System.currentTimeMillis();

			MatOfRect faceDetections = new MatOfRect();
			faceDetector.detectMultiScale(newMat, faceDetections);

			System.out.println(String.format("MatOfRect use time millis=%s ", System.currentTimeMillis()
					- matOfRectCurtime));

			System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

			for (Rect rect : faceDetections.toArray()) {
				Rectangle tRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
				faceRects.add(tRect);
			}
		} catch (UnsupportedOperationException e) {
			logger.info(e.toString(), e);
		}
		return faceRects;
	}
	
	
	
	public static BufferedImage imageConverter(BufferedImage orginalImage)
	{
		
		int imageWidth = orginalImage.getWidth();  
		int imageHeight = orginalImage.getHeight();  
		
		BufferedImage newPic = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_3BYTE_BGR);  
		ColorConvertOp cco = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);  
		cco.filter(orginalImage, newPic);  
		return newPic;  
	}


}
