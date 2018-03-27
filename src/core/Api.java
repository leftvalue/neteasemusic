package core;

/**
 * 用户获得实际查询时加密之前的请求体
 * 
 * @author linxi
 *
 */
public class Api {
	private final static String BaseURL = "http://music.163.com/";

	public static UrlParamPair getSearchResult(String keyword, int type, int limit, int offset) {
		UrlParamPair upp = new UrlParamPair();
		upp.setUrl(BaseURL + "weapi/cloudsearch/get/web?csrf_token=");
		upp.addPara("s", keyword);
		upp.addPara("type", 1);
		upp.addPara("limit", limit);
		upp.addPara("offset", offset);
		return upp;
	}

	public static UrlParamPair getSearchResult(String keyword, int type) {
		return getSearchResult(keyword, type, 5, 0);
	}

	/**
	 * 获取用户歌单
	 *
	 * @param uid
	 * @return
	 */
	public static UrlParamPair getPlaylistOfUser(String uid) {
		UrlParamPair upp = new UrlParamPair();
		upp.setUrl(BaseURL + "weapi/user/playlist?csrf_token=");
		upp.addPara("offset", 0);
		upp.addPara("uid", uid);
		upp.addPara("limit", 5);
		upp.addPara("csrf_token", "nothing");
		return upp;
	}

	/**
	 * 获取歌单详情
	 *
	 * @param playlist_id
	 * @return
	 */
	public static UrlParamPair getDetailOfPlaylist(String playlist_id) {
		UrlParamPair upp = new UrlParamPair();
		upp.setUrl(BaseURL + "weapi/v3/playlist/detail?csrf_token=");
		upp.addPara("id", playlist_id);
		upp.addPara("offset", 0);
		upp.addPara("total", "True");
		upp.addPara("limit", 1000);
		upp.addPara("n", 1000);
		upp.addPara("csrf_token", "nothing");
		return upp;
	}
	// todo:analyse more api
}
