package controller;

import com.jfinal.core.Controller;

import core.Service;

public class SearchController extends Controller {
	public void index() {
		String keyword = getPara("keyword");
		int type = getParaToInt("type");
		int limit = getParaToInt("limit");
		int offset = getParaToInt("offset");
		String result_ = Service.search(keyword, type, limit, offset);
		// JSONObject root = JSONObject.parseObject(result_);
		// int code = root.getIntValue("code");
		// if (code == 200) {
		// JSONObject result = root.getJSONObject("result");
		// int total = result.getIntValue("songCount");
		// JSONArray songs=result.getJSONArray("songs");
		// for(Object o:songs) {
		// JSONObject song=JSONObject.parseObject(o.toString());
		// String
		// }
		// }
		renderJson(result_);
	}
}
