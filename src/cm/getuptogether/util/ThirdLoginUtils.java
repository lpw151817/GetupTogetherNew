package cm.getuptogether.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sina.weibo.sdk.openapi.models.User;

public class ThirdLoginUtils {
	/**
	 * renren json format
	 * {
		"response": {
			"name": "刘沛文",
			"id": 737553323,
			"avatar": [
				{
					"size": "TINY",
					"url": "http://hdn.xnimg.cn/photos/hdn321/20140203/1720/tiny_f04f_1332000485d8113e.jpg"
				},
				{
					"size": "HEAD",
					"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_head_Pi28_59c3000080b1113e.jpg"
				},
				{
					"size": "MAIN",
					"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_main_dLQ9_59c3000080b1113e.jpg"
				},
				{
					"size": "LARGE",
					"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_large_u7Mb_59c3000080b1113e.jpg"
				}
			],
			"star": 1,
			"basicInformation": null,
			"education": null,
			"work": null,
			"like": null,
			"emotionalState": null
		}
	}
	 *
	 */
	
	/**
	 * 
	 * @param re 带前缀
	 * @return
	 */
	private static JSONObject rennResponse2JsonObject(String re) {
		try {
			re = re.substring(re.indexOf("{"), re.length() - 1);
			return new JSONObject(re);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param response 带前缀
	 * @return
	 */
	public static String renren_getUsername(String response) {
		try {
			return rennResponse2JsonObject(response).getJSONObject("response").getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取TINY头像
	 * @param response
	 * @return
	 */
	public static String renren_getLogoURL(String response) {
		try {
			JSONArray array = rennResponse2JsonObject(response).getJSONObject("response").getJSONArray("avatar");
			return array.getJSONObject(0).getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}

	}
	
	/**
	 * qq json format
	 * {
    "is_yellow_year_vip": "0", 
    "ret": 0, 
    "figureurl_qq_1": "http://q.qlogo.cn/qqapp/1103432471/3D625B63E871B37779B308E4B60E7BE2/40", 
    "figureurl_qq_2": "http://q.qlogo.cn/qqapp/1103432471/3D625B63E871B37779B308E4B60E7BE2/100", 
    "nickname": "              ·", 
    "yellow_vip_level": "0", 
    "is_lost": 0, 
    "msg": "", 
    "city": "", 
    "figureurl_1": "http://qzapp.qlogo.cn/qzapp/1103432471/3D625B63E871B37779B308E4B60E7BE2/50", 
    "vip": "0", 
    "level": "0", 
    "figureurl_2": "http://qzapp.qlogo.cn/qzapp/1103432471/3D625B63E871B37779B308E4B60E7BE2/100", 
    "province": "", 
    "is_yellow_vip": "0", 
    "gender": "男", 
    "figureurl": "http://qzapp.qlogo.cn/qzapp/1103432471/3D625B63E871B37779B308E4B60E7BE2/30"
}

	 */
	
	/**
	 * @param jo 带有前缀
	 * @return
	 */
	public static String qq_getNickName(String jo){
		try {
			JSONObject jsonObject = new JSONObject(jo.substring(jo.indexOf("{")));
			return jsonObject.getString("nickname");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 
	 * @param response 带有前缀
	 * @return 更改失败返回null
	 */
	public static User weibo_getUserObject(String response){
		return User.parse(response.substring(response.indexOf("{")));
	}
}
