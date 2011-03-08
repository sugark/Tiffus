package org.eclipse.client;

import hu.newphrox.java.io.NewByteArrayOutputStream;
import com.google.gwt.user.client.Window;

import java.io.ByteArrayInputStream;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.main.SWT;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class SWTClient implements EntryPoint {

	public void onModuleLoad() {
		
		//exportLoadSampleMethod(this);
		
		loadSample();
	}
	
//    private native void exportLoadSampleMethod(SWTClient instance) /*-{
//    $wnd.tiffany = function() {
//       return instance.@org.eclipse.client.SWTClient::loadSample()();
//    };
//  	}-*/;
	
		
		public void loadSample(){
			
		byte[] Tiff = ImageSamples.getJPEGSample();
		ByteArrayInputStream TiffStream = new ByteArrayInputStream(Tiff);

		//Base64Utils.fromBase64("1234");
		RootPanel rootPanel = RootPanel.get();
		final TextBox txtbxUsername = new TextBox();
		txtbxUsername.setStyleName("grey");
		txtbxUsername.setText("E-mail c\u00EDm");
//		txtbxUsername.setStyleName("grey", true);
		rootPanel.add(txtbxUsername, 42, 571);
		
		//txtbxUsername.setText("0x"+Integer.toHexString(Tiff[0])+" 0x"+Integer.toHexString(Tiff[1]));
		
		final TextBox txtbxByte = new TextBox();
		txtbxByte.setStyleName("grey");
		txtbxByte.setText("E-mail c\u00EDm");
//		txtbxByte.setStyleName("grey", true);
		rootPanel.add(txtbxByte,42,471);
		
		//txtbxByte.setText("byte 0x"+Integer.toHexString(TiffStream.read()));
		
		
		final TextBox txtbxImage = new TextBox();
		txtbxImage.setStyleName("grey");
		txtbxImage.setText("E-mail c\u00EDm");
//		txtbxImage.setStyleName("grey", true);
		rootPanel.add(txtbxImage,42,371);

//		Window.alert("before image load");
		ImageLoader imLoader = new ImageLoader();
		ImageData[] im = imLoader.load(TiffStream);
//		Window.alert("before image load finished");
		
		NewByteArrayOutputStream outTiffStream = new NewByteArrayOutputStream();
		//imLoader.save(outTiffStream, SWT.IMAGE_TIFF);
		
		txtbxImage.setText("byte 0x"+Integer.toHexString(im[0].data[2]));

		final TextArea txtArea = new TextArea();
		txtArea.setWidth("300");
		txtArea.setHeight("300");
		txtArea.setStyleName("grey");
//		txtArea.setStyleName("grey", true);
		rootPanel.add(txtArea,242,371);
		
		//txtArea.setText(Base64.byteArrayToBase64(outTiffStream.toByteArray()));
		
		
//		byte[] Gif = ImageSamples.getGIFSample();
//		ByteArrayInputStream GifStream = new ByteArrayInputStream(Gif);
//		
//		ImageLoader imLoader2 = new ImageLoader();
//		ImageData[] im2 = imLoader2.load(GifStream,SWT.IMAGE_GIF);
//		
//		
		
//		 for(int x=11;x<35;x++){
//		        for(int y=11;y<35;y++){
//		            im[0].setPixel(x,y,1);
//		        }
//		    }
		
//		imLoader.data[0].palette.colors[0].red=255;
//		imLoader.data[0].palette.colors[0].green=255;
//		imLoader.data[0].palette.colors[0].blue=255;
//		
//		imLoader.data[0].palette.colors[1].red=0;
//		imLoader.data[0].palette.colors[1].green=0;
//		imLoader.data[0].palette.colors[1].blue=0;
		
		//imLoader.data[0].palette.invertRGBs();
		//imLoader.data[0] = imLoader.data[0].scaledTo(50,-50);
		imLoader.data[0].invertImage();
		
//		Window.alert("before image resize");
		//imLoader.data[0]=imLoader.data[0].scaledTo(imLoader.data[0].width/2, imLoader.data[0].height/2);
//		Window.alert("before image resized");
		
		NewByteArrayOutputStream outGifStream = new NewByteArrayOutputStream();
//		Window.alert("before image save");
		imLoader.save(outGifStream, SWT.IMAGE_PNG);
//		Window.alert("before image saved");
		
		final HTML html = new HTML("<div>szopsz<img src=\"data:image/png;base64," +
				Base64.byteArrayToBase64(outGifStream.toByteArray()) +
				"\"/>vege</div>");
		rootPanel.add(html);
		
		

		
		
		
		
		
	}
}
