package core;

import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Demo {
	public static void main(String[] args) {
		try {
			UrlParamPair upp = Api.getSearchResult("周杰伦", 1);
			String req_str = upp.getParas().toJSONString();
			Connection.Response response = Jsoup.connect("http://music.163.com/weapi/cloudsearch/get/web?csrf_token=")
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:57.0) Gecko/20100101 Firefox/57.0")
					.header("Accept", "*/*").header("Cache-Control", "no-cache").header("Connection", "keep-alive")
					.header("Host", "music.163.com").header("Accept-Language", "zh-CN,en-US;q=0.7,en;q=0.3")
					.header("DNT", "1").header("Pragma", "no-cache")
					.header("Content-Type", "application/x-www-form-urlencoded").data(JSSecret.getDatas(req_str))
					.method(Connection.Method.POST).ignoreContentType(true).timeout(10000).execute();
			String list = response.body();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
