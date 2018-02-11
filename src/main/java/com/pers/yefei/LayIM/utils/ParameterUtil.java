package com.pers.yefei.LayIM.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.pers.yefei.LayIM.pojo.CombinedId;
import com.pers.yefei.LayIM.pojo.SimpleComId;
import com.pers.yefei.LayIM.utils.exception.ParameterException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ParameterUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(ParameterUtil.class);

	public static String getRequiredString(JSONObject json, String attributeName)
			throws ParameterException {
		String value = json.optString(attributeName);
		if (value == null) {
			throw new ParameterException("json field param [" + attributeName
					+ "] is null");
		}
		if (value.isEmpty()) {
			throw new ParameterException("json field param [" + attributeName
					+ "] is empty");
		}

		return value;
	} 

	public static String getOptionalString(JSONObject json, String attributeName)
			throws ParameterException {
		String value = json.optString(attributeName);
		return value;
	}

	public static Integer getRequiredInteger(JSONObject json,
			String attributeName) throws ParameterException {

		String valueStr = getRequiredString(json, attributeName);

		try {
			int value = Integer.parseInt(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException("json " + attributeName + " value: " + valueStr + " not a integer!", ex);
		}
	}

	public static Integer getOptionalInteger(JSONObject json,
			String attributeName) throws ParameterException {

		String valueStr = getOptionalString(json, attributeName);
		if (valueStr.isEmpty())
			return null;

		try {
			int value = Integer.parseInt(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional json " + attributeName
					+ " value: " + valueStr + " not a integer!", ex);
		}
	}

	public static Long getRequiredLong(JSONObject json, String attributeName)
			throws ParameterException {

		String valueStr = getRequiredString(json, attributeName);

		try {
			Long value = Long.parseLong(valueStr);
			return value;
		} catch (Exception ex) {
			throw new ParameterException("json " + attributeName + " value: "
					+ valueStr + " not a Long!", ex);
		}

	}

	public static Long getOptionalLong(JSONObject json, String attributeName)
			throws ParameterException {

		String valueStr = getOptionalString(json, attributeName);
		if (valueStr.isEmpty())
			return null;
		try {

			Long value = Long.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional json " + attributeName
					+ " value: " + valueStr + " not a Long!", ex);
		}

	}

	public static Double getRequiredDouble(JSONObject json, String attributeName)
			throws ParameterException {

		String valueStr = getRequiredString(json, attributeName);

		try {
			Double value = Double.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException("json " + attributeName + " value: "
					+ valueStr + " not a double!", ex);
		}

	}

	public static Double getOptionalDouble(JSONObject json, String attributeName)
			throws ParameterException {

		String valueStr = getOptionalString(json, attributeName);
		if (valueStr.isEmpty())
			return null;
		try {

			Double value = Double.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional json " + attributeName
					+ " value: " + valueStr + " not a double!", ex);
		}
	}

	/**
	 * 验证信息是否为空
	 * 
	 * @param request
	 * @param attributeName
	 * @return
	 * @throws ParameterException
	 */
	public static String getRequiredString(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String value = request.getParameter(attributeName);
		if (value == null) {
			throw new ParameterException(" request param [" + attributeName
					+ "] is null");
		}
		if (value.isEmpty()) {
			throw new ParameterException(" request param [" + attributeName
					+ "] is isEmpty");
		}

		return value;
	}

	public static String getOptionalString(HttpServletRequest request,
			String attributeName) {
		String value = request.getParameter(attributeName);
		return value;
	}

	public static Integer getRequiredInteger(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getRequiredString(request, attributeName);

		try {
			int value = Integer.parseInt(valueStr.trim());
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException("Request " + attributeName
					+ " value: " + valueStr + " not a integer!", ex);
		}

	}

	public static Integer getOptionalInteger(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getOptionalString(request, attributeName);
		if (valueStr==null ||valueStr.isEmpty())
			return null;
		try {
			int value = Integer.parseInt(valueStr.trim());
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional Request " + attributeName
					+ " value: " + valueStr + " not a integer!", ex);
		}
	}
	
	
	public static int getOptionalInt(HttpServletRequest request,
			String attributeName, int defaultVal) throws ParameterException {

		String valueStr = getOptionalString(request, attributeName);
		if (valueStr==null ||valueStr.isEmpty())
			return defaultVal;
		try {
			int value = Integer.parseInt(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional Request " + attributeName
					+ " value: " + valueStr + " not a integer!", ex);
		}
	}
	

	public static Long getRequiredLong(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getRequiredString(request, attributeName);

		try {
			Long value = Long.parseLong(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException("Request " + attributeName
					+ " value: " + valueStr + " not a Long!", ex);
		}

	}

	public static Long getOptionalLong(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getOptionalString(request, attributeName);
		if (valueStr.isEmpty())
			return null;
		try {
			Long value = Long.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional Request " + attributeName
					+ " value:" + valueStr + " not a Long!", ex);
		}

	}

	public static Double getRequiredDouble(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getRequiredString(request, attributeName);

		try {
			Double value = Double.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException("Request " + attributeName
					+ " value: " + valueStr + " not a double!", ex);
		}

	}

	public static Double getOptionalDouble(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String valueStr = getOptionalString(request, attributeName);
		if (valueStr == null ||valueStr.isEmpty())
			return null;
		try {
			Double value = Double.valueOf(valueStr);
			return value;
		} catch (NumberFormatException ex) {
			throw new ParameterException(" optional Request " + attributeName
					+ " value: " + valueStr + " not a double!", ex);
		}

	}

	public static String getRequiredHeader(HttpServletRequest request,
			String header) throws ParameterException {
		String value = request.getHeader(header);
		if (value == null) {
			throw new ParameterException(" request header [" + header
					+ "] is null");
		}
		if (value.isEmpty()) {

			throw new ParameterException(" request header [" + header
					+ "] is isEmpty");
		}

		return value;
	}

	public static String getRequiredCookie(HttpServletRequest request,
			String cookieName) throws ParameterException,
			UnsupportedEncodingException {

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					String value = cookies[i].getValue();
					if (value == null || value.isEmpty())
						throw new ParameterException(" request cookie ["+ cookieName + "] is empty");
					String devalue = URLDecoder.decode(value, "utf-8");
					if (logger.isDebugEnabled())
						logger.debug("cookie " + cookieName + "=" + devalue);
					return devalue;
				}
			}

			throw new ParameterException(" request cookie [" + cookieName+ "] not found");

		}

		throw new ParameterException(" request has no cookies");
	}

	public static Date getRequiredDate(JSONObject jsonObject,
			String attributeName) throws ParameterException {
		String requiredDate = getRequiredString(jsonObject, attributeName);
		try {
			Date date = Date.valueOf(requiredDate);
			return date;
		} catch (IllegalArgumentException ex) {
			throw new ParameterException("required json date " + attributeName
					+ " value: " + requiredDate + "   format Error!", ex);
		}
	}

	public static Timestamp getRequiredDateFormat(JSONObject jsonObject,
			String attributeName) throws ParameterException {
		String requiredTime = getRequiredString(jsonObject, attributeName);
		try {
			Timestamp timestamp = Timestamp.valueOf(requiredTime);
			return timestamp;
		} catch (IllegalArgumentException ex) {
			throw new ParameterException("required json timestamp "
					+ attributeName + " value: " + requiredTime
					+ "   format Error!", ex);
		}
	}

	public static Object getRequiredObject(JSONObject json, String attributeName)
			throws ParameterException {

		try {
			return json.getJSONObject(attributeName);

		} catch (JSONException e) {
			throw new ParameterException("required jsonObject " + attributeName
					+ " " + e.toString(), e);

		}
	}

	public static JSONArray getRequiredJSONArray(JSONObject jsonObject,
			String attributeName) throws ParameterException {
		try {
			return jsonObject.getJSONArray(attributeName);

		} catch (JSONException e) {
			throw new ParameterException("required jsonArray  " + attributeName
					+ " " + e.toString(), e);

		}
	}

	public static List<Long> getRequiredLongs(JSONObject jsonObject,
			String attributeName) throws ParameterException {

		JSONArray jsonArray = getRequiredJSONArray(jsonObject, attributeName);
		List<Long> listLongs = new ArrayList<Long>();
		try {

			for (int i = 0; i < jsonArray.length(); i++) {
				long id = jsonArray.getLong(i);
				listLongs.add(id);
			}

			return listLongs;
		} catch (Exception e) {
			throw new ParameterException(" required json Longs "
					+ attributeName + " not a Long " + e.toString(), e);
		}
	}

	public static List<String> getRequiredStrings(JSONObject jsonObject,
			String attributeName) throws ParameterException {

		JSONArray jsonArray = getRequiredJSONArray(jsonObject, attributeName);
		List<String> listStrs = new ArrayList<String>();
		try {

			for (int i = 0; i < jsonArray.length(); i++) {
				String value = jsonArray.getString(i);
				listStrs.add(value + "");
			}

			return listStrs;
		} catch (Exception e) {
			throw new ParameterException(" required json Longs "
					+ attributeName + " not a Long " + e.toString(), e);
		}
	}

	public static String getRequiredTimeFormat(JSONObject jsonObject,
			String attributeName) throws ParameterException {
		String requiredTime = getRequiredString(jsonObject, attributeName);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(requiredTime);
			return requiredTime;
		} catch (ParseException ex) {
			throw new ParameterException(" required json yyyy-mm-dd "
					+ attributeName + " value: " + requiredTime
					+ " format Error!", ex);
		}
	}

	public static List<String> getRequiredJSONArrayToObjectIds(
			JSONObject jsonObject, String attributeName)
			throws ParameterException {
		JSONArray jsonArray = getRequiredJSONArray(jsonObject, attributeName);
		List<String> list = new ArrayList<String>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				String objectId = jsonArray.getString(i);
				list.add(objectId);
			}
			return list;
		} catch (Exception e) {
			throw new ParameterException(" required json Strings "
					+ attributeName + " not a String " + e.toString(), e);
		}
	}

	public static CombinedId getRequiredCombinedId(JSONObject json,
			String attributeName) throws ParameterException {
		String value = json.optString(attributeName);
		if (value == null) {
			throw new ParameterException("json field param [" + attributeName
					+ "] is null");
		}
		if (value.isEmpty()) {
			throw new ParameterException("json field param [" + attributeName
					+ "] is empty");
		}

		return new SimpleComId(value);
	}

	public static CombinedId getRequiredCombinedId(HttpServletRequest request,
			String attributeName) throws ParameterException {

		String value = request.getParameter(attributeName);
		if (value == null) {
			throw new ParameterException(" request param [" + attributeName
					+ "] is null");
		}
		if (value.isEmpty()) {
			throw new ParameterException(" request param [" + attributeName
					+ "] is isEmpty");
		}

		return new SimpleComId(value);
	}

	public static CombinedId getOptionalCombinedId(JSONObject json,
												   String attributeName) throws ParameterException {
		String value = json.optString(attributeName);
		if (value == null || value.isEmpty()) {
			return null;
		}

		return new SimpleComId(value);
	}

	public static boolean getOptionalBool(HttpServletRequest request, String attributeName) {
		String value = request.getParameter(attributeName);
		if (value == null || value.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(value);
	}
	
	public static boolean getOptionalBool(JSONObject json, String attributeName) {
		String value = json.optString(attributeName);
		if (value == null || value.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(value);
	}

//	public static Long decryptRequiredLong(JSONObject json, String attributeName)
//			throws ParameterException {
//
//		String valueStr = getRequiredString(json, attributeName);
//
//		try {
//			return PrimaryIdSecurityUtil.decryptId(valueStr);
//		} catch (Exception ex) {
//			throw new ParameterException("json " + attributeName + " value: "
//					+ valueStr + " not a Long!", ex);
//		}
//
//	}
//
//	public static Long decryptOptionalLong(JSONObject json, String attributeName)
//			throws ParameterException {
//
//		String valueStr = json.optString(attributeName);
//		if (valueStr == null || valueStr.isEmpty()) {
//			return null;
//		}
//
//		try {
//			return PrimaryIdSecurityUtil.decryptId(valueStr);
//		} catch (Exception ex) {
//			throw new ParameterException("json " + attributeName + " value: "
//					+ valueStr + " not a Long!", ex);
//		}
//
//	}
//
//	public static PrimaryComId decryptRequiredPrimaryComId(JSONObject json,
//			String attributeName) throws ParameterException {
//
//		String valueStr = getRequiredString(json, attributeName);
//
//		try {
//			return PrimaryIdSecurityUtil.decryptPrimaryComId(valueStr);
//		} catch (Exception ex) {
//			throw new ParameterException("json " + attributeName + " value: "
//					+ valueStr + " not a Long!", ex);
//		}
//
//	}
//
//	public static Long decryptRequestLong(HttpServletRequest request,
//			String attributeName) throws ParameterException {
//
//		String valueStr = getRequiredString(request, attributeName);
//
//		try {
//			return PrimaryIdSecurityUtil.decryptId(valueStr);
//		} catch (Exception ex) {
//			throw new ParameterException("Request " + attributeName
//					+ " value: " + valueStr + " not a Long!", ex);
//		}
//
//	}
//
//	public static PrimaryComId decryptRequestPrimaryComId(
//			HttpServletRequest request, String attributeName)
//			throws ParameterException {
//
//		String valueStr = getRequiredString(request, attributeName);
//
//		try {
//			return PrimaryIdSecurityUtil.decryptPrimaryComId(valueStr);
//		} catch (Exception ex) {
//			throw new ParameterException("Request " + attributeName
//					+ " value: " + valueStr + " not a  vaild PrimaryComId!", ex);
//		}
//
//	}
//
//	public static List<Long> decryptRequiredComIds(JSONObject jsonObject,
//			String attributeName) throws ParameterException {
//		JSONArray jsonArray = getRequiredJSONArray(jsonObject, attributeName);
//		List<Long> list = new ArrayList<Long>();
//
//		for (int i = 0; i < jsonArray.length(); i++) {
//
//			String securityId = jsonArray.getString(i);
//			PrimaryComId primaryComId = PrimaryIdSecurityUtil
//					.decryptPrimaryComId(securityId);
//			list.add(primaryComId.getFirstId());
//
//		}
//		return list;
//	}
//
//	public static List<Long> decryptRequiredIds(JSONObject jsonObject,
//			String attributeName) throws ParameterException {
//		JSONArray jsonArray = getRequiredJSONArray(jsonObject, attributeName);
//		List<Long> list = new ArrayList<Long>();
//
//		for (int i = 0; i < jsonArray.length(); i++) {
//			String securityId = jsonArray.getString(i);
//			long decryptId = PrimaryIdSecurityUtil.decryptId(securityId);
//			list.add(decryptId);
//
//		}
//		return list;
//	}

	public static long getRequiredMobile(JSONObject jsonObject,
			String attributeName) {

		Long mobile = getRequiredLong(jsonObject, attributeName);
		
		return mobile;
	}

	public static Timestamp getRequiredDateFormat(HttpServletRequest request,
			String attributeName) {
		String requiredTime = getRequiredString(request, attributeName);

		try {
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			// long time = sdf.parse(requiredTime).getTime();
			Timestamp timestamp = Timestamp.valueOf(requiredTime);
			return timestamp;
		} catch (IllegalArgumentException ex) {
			throw new ParameterException("Request " + attributeName
					+ " value: " + requiredTime + "   format Error!", ex);
		}

	}

	public static List<String> getRequiredStrings(HttpServletRequest request,
			String attributeName) {

		String parameter = request.getParameter(attributeName);
		JSONArray jsonArray = new JSONArray(parameter);
		List<String> list = new ArrayList<String>();
		try {
			if (jsonArray.length() == 0) {
				throw new ParameterException("Request " + attributeName
						+ " value: json array zero length error!");
			}
			for (int i = 0; i < jsonArray.length(); i++) {
				String objectId = jsonArray.getString(i);
				list.add(objectId);
			}
			return list;
		} catch (Exception e) {
			throw new ParameterException(" Request " + attributeName
					+ " not a String " + e.toString(), e);
		}
	}

//	public static List<Long> decryptRequiredImageItems(JSONObject jsonObject,
//			String attributeName) {
//
//		try {
//			// 验证是否为空
//			String imageItems = getRequiredString(jsonObject, attributeName);
//			JSONObject item = new JSONObject(imageItems);
//			// 验证json格式中是否有数据
//			String image1 = item.optString("image1");
//			if (image1 == null || image1.isEmpty()) {
//				throw new ParameterException(" Request " + attributeName + "  is null!");
//			}
//
//			List<Long> imageList = new ArrayList<Long>();
//			// 解密操作 图片的集合
//		
//			PrimaryComId decImage1 = PrimaryIdSecurityUtil.decryptPrimaryComId(image1);
//			imageList.add(decImage1.getFirstId());
//
//			String image2 = item.optString("image2");
//			if (image2 == null || image2.isEmpty()) {
//				return imageList;
//			} else {
//				PrimaryComId decImage2 = PrimaryIdSecurityUtil.decryptPrimaryComId(image2);
//				imageList.add(decImage2.getFirstId());
//			}
//
//			String image3 = item.optString("image3");
//			if (image3 == null || image3.isEmpty()) {
//				return imageList;
//			} else {
//				
//				PrimaryComId decImage3 = PrimaryIdSecurityUtil.decryptPrimaryComId(image3);
//				imageList.add(decImage3.getFirstId());
//	
//			}
//			return imageList;
//
//		} catch (Exception e) {
//			throw new ParameterException(" Request " + attributeName + "  format Error!" + e.toString(), e);
//		}
//	}

	public static List<String> getRequiredReasonCode(JSONObject jsonObject,String attributeName) {

		try {
			String value = getRequiredString(jsonObject, attributeName);
			JSONArray jsonArray = new JSONArray(value);
			if (jsonArray.length() == 0) {
				throw new ParameterException("Request " + attributeName
						+ " value: json array zero length error!");
			}
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				String code = jsonArray.getString(i);
				list.add(code);
			}
			return list;
		} catch (Exception e) {
			throw new ParameterException(" Request " + attributeName
					+ " not a String " + e.toString(), e);
		}
	}
	
	public static List<String> getOptionalReasonCode(JSONObject jsonObject,String attributeName) {
		try {
			String value = getOptionalString(jsonObject, attributeName);
			if (value == null || "".equals(value)) {
				return null;
			}
			JSONArray jsonArray = new JSONArray(value);
			if (jsonArray.length() == 0) {
				throw new ParameterException("Request " + attributeName
						+ " value: json array zero length error!");
			}
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				String code = jsonArray.getString(i);
				list.add(code);
			}
			return list;
		} catch (Exception e) {
			throw new ParameterException(" Request " + attributeName
					+ " not a String " + e.toString(), e);
		}
	}
	

}
