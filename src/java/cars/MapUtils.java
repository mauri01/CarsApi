package cars;

import grails.converters.JSON;
import org.codehaus.groovy.grails.web.json.JSONArray;
import org.codehaus.groovy.grails.web.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

class MapUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public static Map<String, Object> requestBodyToMap(HttpServletRequest request)
	{
		try
		{
			String a = request.getContentType();
			if (!request.getContentType().toLowerCase().contains("application/json"))
				return null;
			return jsonObjectToMap((JSONObject) JSON.parse(request));
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	private static String dateToString(Date date)
	{
		StringBuffer sb = new StringBuffer(dateFormat.format(date));
		return sb.insert(sb.length() - 2, ":").toString();
	}

	@SuppressWarnings("unchecked")
	private static Entry<String, Object> objectToEntry(Object entry)
	{
		return (Entry<String, Object>) entry;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> objectToMap(Object map)
	{
		return (Map<String, Object>) map;
	}

	@SuppressWarnings("unchecked")
	private static List<Object> objectToList(Object list)
	{
		return (List<Object>) list;
	}

	private static JSONArray listToJSONArray(List<Object> list)
	{
		if (list == null)
			return null;
		try
		{
			JSONArray jsonArray = new JSONArray();
			for (Object value: list)
				jsonArray.add(normalizeToJSONValue(value));
			return jsonArray;
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	public static List<Object> jsonArrayToList(JSONArray jsonArray)
	{
		if (jsonArray == null)
			return null;
		try
		{
			List<Object> list = new ArrayList<Object>();
			for (Object value: jsonArray)
				list.add(normalizeJSONValue(value));
			return list;
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	private static Object normalizeToJSONValue(Object value)
	{
		if (value instanceof Map)
			return mapToJSONObject(objectToMap(value));
		if (value instanceof List)
			return listToJSONArray(objectToList(value));
		if (value == null)
			return JSONObject.NULL;
		if (value instanceof Date)
			return dateToString((Date) value);
		if (value instanceof Calendar)
			return dateToString(((Calendar) value).getTime());
		return value;
	}

	private static Object normalizeJSONValue(Object value)
	{
		if (value instanceof JSONObject)
			return jsonObjectToMap((JSONObject) value);
		if (value instanceof JSONArray)
			return jsonArrayToList((JSONArray) value);
		if (value == JSONObject.NULL)
			return null;
		return value;
	}

	public static Map<String, Object> jsonObjectToMap(JSONObject jsonObject)
	{
		if (jsonObject == null)
			return null;
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			for (Object e: jsonObject.entrySet())
			{
				Entry<String, Object> entry = objectToEntry(e);
				map.put(Camelizer.camelize(entry.getKey()), normalizeJSONValue(entry.getValue()));
			}
			return map;
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	private static JSONObject mapToJSONObject(Map<String, Object> map)
	{
		if (map == null)
			return null;
		try
		{
			JSONObject jsonObject = new JSONObject();
			for (Entry<String, Object> entry: map.entrySet())
				jsonObject.put(Camelizer.unCamelize(entry.getKey()), normalizeToJSONValue(entry.getValue()));
			return jsonObject;
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	public static String mapToString(Map<String, Object> map) {
		JSONObject jsonObject = mapToJSONObject(map);
		if (jsonObject != null)
			return jsonObject.toString();
		return "";
	}

	public static Map<String, Object> stringToMap(String jsonString) {
		return jsonObjectToMap(new JSONObject(jsonString));
	}
}
