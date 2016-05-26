package olva.oryx.util;

import java.lang.reflect.Type;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Json Util
 *
 * @author Carlos D Larico (clarico@olva.com.pe)
 */
public class JsonUtil {

	/**
	 * Parse data to json
	 * @param data data
	 * @return json
     */
	public static Map<String, String> parseJsonToMap(String data) {
		Type type = new TypeToken<Map<String, Object>>() {}.getType();		 
		return new Gson().fromJson(data, type);
	}

}
