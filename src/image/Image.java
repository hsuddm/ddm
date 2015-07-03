package image;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.dongdong.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class Image {
	static ImageLoaderConfiguration config;
	static ImageLoader imageLoader;
	static DisplayImageOptions options;

	@SuppressWarnings("deprecation")
	private static void init(Context c) {
		imageLoader = ImageLoader.getInstance();
		config = new ImageLoaderConfiguration.Builder(c).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()

				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.memoryCacheSize(1 * 1024 * 1024) // 硫붾え由� 罹먯돩 �궗�씠利�(�쐞�뿉爰쇰옉 李⑥씠瑜� 紐⑤Ⅴ寃좊꽕�슂 �뀪)
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);

		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.a) // 濡쒕뵫以� �씠誘몄� �꽕�젙
				.showImageOnFail(R.drawable.blank) // 濡쒕뵫 �떎�뙣�떆
				.resetViewBeforeLoading(false) // 濡쒕뵫�쟾�뿉 酉곕�� 由ъ뀑�븯�뒗嫄대뜲 false濡� �븯�꽭�슂 怨쇰��븯!
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // �뒪耳��씪���엯�꽕�젙    
				.build();
	}

	public static void loadOriginalBitmap(String strUrl, Context c, ImageView iv) {
		if (config == null || imageLoader == null || options == null) {
			init(c);
		}
		imageLoader.displayImage(strUrl, iv, options);

	}

	public static void loadBitmap(String strUrl, Context c, ImageView iv) {
		if (config == null || imageLoader == null || options == null) {
			init(c);
		}
		imageLoader.displayImage(strUrl, iv, options);

	}

	public static void loadReplyBitmap(String strUrl, Context c, ImageView iv) {
		if (config == null || imageLoader == null || options == null) {
			init(c);
		}
		imageLoader.displayImage(strUrl, iv, options);
	}

	public static Boolean sendImages(String[] imgPaths) {

		Boolean result = true;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(query.Server.imgAddress);

		FileBody bin1 = new FileBody(new File(imgPaths[0]));
		FileBody bin2 = new FileBody(new File(imgPaths[1]));
		FileBody bin3 = new FileBody(new File(imgPaths[2]));
		FileBody bin4 = new FileBody(new File(imgPaths[3]));
		FileBody bin5 = new FileBody(new File(imgPaths[4]));

		MultipartEntity builder = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		builder.addPart("front", bin1);
		builder.addPart("side", bin2);
		builder.addPart("back", bin3);
		builder.addPart("detail1", bin4);
		builder.addPart("detail2", bin5);
		post.setEntity(builder);

		try {
			client.execute(post);
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException");
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean sendReplyImages(String imgPath) {
		Boolean result = true;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(query.Server.replyimgAddress);

		FileBody bin1 = new FileBody(new File(imgPath));

		MultipartEntity builder = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		builder.addPart("image1", bin1);

		post.setEntity(builder);

		try {
			client.execute(post);
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException");
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public static Boolean sendStoreMainImages(String imgPath) {
		Boolean result = true;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(query.Server.storeimgAddress);

		FileBody bin1 = new FileBody(new File(imgPath));

		MultipartEntity builder = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		builder.addPart("image1", bin1);

		post.setEntity(builder);

		try {
			client.execute(post);
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException");
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			result = false;
			e.printStackTrace();
		}
		return result;
	}

}