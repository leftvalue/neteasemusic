package controller;

import java.io.File;

import com.jfinal.core.Controller;

import core.Service;

public class MusicController extends Controller {
	private static final String BASEPATH = "music";
	static {
		try {
			File file = new File(BASEPATH);
			if (file.isFile() && !file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void index() {
		String id = getPara("id");
		/**
		 * 下载音频文件
		 */
		String path = BASEPATH + "/" + System.currentTimeMillis() + ".mp3";
		path = new File(path).getAbsolutePath();
		Service.downloadMusic(id, path);
		renderFile(new File(path));
	}
}
