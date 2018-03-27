package core;

import java.io.FileOutputStream;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import freemarker.core._RegexBuiltins.replace_reBI;

import org.jsoup.Jsoup;

/**
 * 实际封装好的业务逻辑 对外提供高层服务
 * 
 * @author linxi
 *
 */
public class Service {
	public static boolean downloadMusic(String id, String path) {
		try {
			Response response = Jsoup.connect("http://music.163.com/song/media/outer/url?id=" + id + ".mp3")
					.timeout(60000).method(Method.GET).ignoreContentType(true).execute();
			return download2local(response, path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	private static boolean download2local(Connection.Response response, String path) {
		try {
			FileOutputStream out = (new FileOutputStream(new java.io.File(path)));
			out.write(response.bodyAsBytes());
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String search(String keyword, int type, int limit, int offset) {
		try {
			UrlParamPair upp = Api.getSearchResult(keyword, type, limit, offset);
			String req_str = upp.getParas().toJSONString();
			Connection.Response response = Jsoup.connect("http://music.163.com/weapi/cloudsearch/get/web?csrf_token=")
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:57.0) Gecko/20100101 Firefox/57.0")
					.header("Accept", "*/*").header("Cache-Control", "no-cache").header("Connection", "keep-alive")
					.header("Host", "music.163.com").header("Accept-Language", "zh-CN,en-US;q=0.7,en;q=0.3")
					.header("DNT", "1").header("Pragma", "no-cache")
					.header("Content-Type", "application/x-www-form-urlencoded").data(JSSecret.getDatas(req_str))
					.method(Connection.Method.POST).ignoreContentType(true).timeout(10000).execute();
			String list = response.body();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
