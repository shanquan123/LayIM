package com.pers.yefei.LayIM.utils;

import java.io.File;
import java.io.InputStream;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(OpenCVUtil.class);
	private static char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F' };	
	
	public static String[] split(String src ,String p)
	{
		List<String> list = new ArrayList<String> ();
		int curind = 0;
		for( int ind=-1; (ind= src.indexOf(p,curind)) >=0 ;)
		{
			list.add( src.substring(curind,ind));
			curind = ind+p.length();
		}
		
		list.add( src.substring(curind,src.length()));
			
		return list.toArray(new String[list.size()]);
	}
	
	public static String formatyyyyMMdd( Date date){
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	public static String formatyyyyMM( Date date){
		return new SimpleDateFormat("yyyyMM").format(date);
	}
	
	public static String formatyyyy_MM( Date date){
		return new SimpleDateFormat("yyyy-MM").format(date);
	}
	
	
	public static String formatMMdd( Date date){
		return new SimpleDateFormat("MM/dd").format(date);
	}
	
	public static Date parseyyyyMMdd( String strdate) throws ParseException{
		return new SimpleDateFormat("yyyyMMdd").parse(strdate);
	}
	public static Date parseBirthday( String strdate) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd").parse(strdate);
	}
	public static String formatyyyyMMddHHmmss( Date date){

		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	
	public static String formatyyyyMMddHHmmss2( Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
	}
	
	public static Date parseyyyyMMddHHmmss( String strdate) throws ParseException{
		return new SimpleDateFormat("yyyyMMddHHmmss").parse(strdate);
	}
	
	public static Date parseyyyy_MM_ddHHmmss( String strdate) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strdate);
	}
	
	public static String formatyyyyMMddHHmmss3( Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static String formatyyyy_MM_dd( Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String formatyyyy_MM_ddHHmm( Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	public static Date parseyyyy_MM_ddHHmm( String dateStr) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
	}
	
	public static String formatHHmmss( Date date){
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}
	
	public static String formatHHmm( Date date){
		return new SimpleDateFormat("HH:mm").format(date);
	}
	
	public static String currentyyyy_MM_dd(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static String currentHHmmss(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	public static String currentHHmm(){
		return new SimpleDateFormat("HH:mm").format(new Date());
	}
	
	
	public static String replaceAll(String source,String repstr,String newstr){
		String[] arr = split(source,repstr);
		StringBuffer strBuf = new StringBuffer();
		for(int i=0; i< arr.length;i++){
			strBuf.append( arr[i] );
			if(i< arr.length -1)
				strBuf.append(newstr);
		}
		return strBuf.toString();
	}
	
	public static String formatHHmmssSSS(long millsecond){
		 return millsecond /3600000L +":" +   (millsecond /60000)%60
			+":" + 	(millsecond /1000)%60 +"." + millsecond%1000 ;
	}
	
	public static String formatyyyyMMdd(long datetime){
		 return String.valueOf(datetime / 1000000 );
	}
	
	public static String byteArray2FHex(byte[] arrbyte) {
		return byteArray2FHex( arrbyte, 0 , arrbyte.length);
	}

	public static String byteArray2FHex(byte[] arrbyte,int start,int endInd) {
		int eInd = endInd > arrbyte.length ? arrbyte.length : endInd;
		char[] arrayCh = new char[ ( eInd -start) *3];
		for(int i= start,j=0 ; i< eInd ;i++ ,j++ ) {
			arrayCh[3*j]= hexDigit[( arrbyte[i] >> 4) & 0x0f];
			arrayCh[3*j+1] = hexDigit[ arrbyte[i] & 0x0f];
			arrayCh[3*j+2] = ' ';
		}
		return new String(arrayCh);
	}
	
	public static String byteArray2Hex(byte[] arrbyte) {
		return byteArray2Hex( arrbyte, 0 , arrbyte.length);
	}

	public static String byteArray2Hex(byte[] arrbyte,int start,int endInd) {
		int eInd = endInd > arrbyte.length ? arrbyte.length : endInd;
		char[] arrayCh = new char[ ( eInd -start) * 2 ];
		for(int i= start,j=0 ; i< eInd ;i++ ,j++ ) {
			arrayCh[2*j]= hexDigit[( arrbyte[i] >> 4) & 0x0f];
			arrayCh[2*j+1] = hexDigit[ arrbyte[i] & 0x0f];
		}
		return new String(arrayCh);
	}
	
	public static byte[] hex2ByteArray( String hexstr) {
		byte[] buf = new byte[hexstr.length()/2];
		int len = hexstr.length()/2;
		for(int i=0; i<len;i++ ){
			char ch1 = hexstr.charAt(2*i);
			char ch2 = hexstr.charAt(2*i +1);
			int val=0;
			val = hex2Int(ch1,val);
			val = hex2Int(ch2,val);
			buf[i]=(byte)val;
		}
		return buf;
	}
	
	final private static int hex2Int(char ch ,int val){
		
		switch(ch){
		case '0':	case '1': case '2': case '3': case '4':	
		case '5': case '6': case '7': case '8': case '9':
			val = (val <<4) | ( (ch-'0')&0xf);
			break;
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
			val = (val <<4) | ( (ch-55)&0xf);
			break;
		case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
			val = (val <<4) | ( (ch-87)&0xf);
			break;
		default:
			throw new  NumberFormatException(""+ch);
		}
		
		return val;
		
	}
	
	
	public static String byteBuffer2FHex(ByteBuffer  arrbyte) {
		return byteBuffer2FHex( arrbyte, 0 , arrbyte.limit());
	}

	public static String byteBuffer2FHex(ByteBuffer arrbyte,int start,int endInd) {
		int eInd = endInd > arrbyte.limit() ? arrbyte.limit() : endInd;
		char[] arrayCh = new char[ ( eInd -start) *3];
		for(int i= start,j=0 ; i< eInd ;i++ ,j++ ) {
			arrayCh[3*j]= hexDigit[( arrbyte.get(i) >> 4) & 0x0f];
			arrayCh[3*j+1] = hexDigit[ arrbyte.get(i) & 0x0f];
			arrayCh[3*j+2] = ' ';
		}
		return new String(arrayCh);
	}
	
	public static long hex2Long(String hex){
		long val =0;
		int len = hex.length();
		if(len > 16)
			throw new  NumberFormatException(hex);
		for(int i=0; i< len;i++){
			char ch = hex.charAt(i);
			switch(ch){
			case '0':	case '1': case '2': case '3': case '4':	
			case '5': case '6': case '7': case '8': case '9':
				val = (val <<4) | ( (ch-'0')&0xf);
				break;
			case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
				val = (val <<4) | ( (ch-55)&0xf);
				break;
			case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
				val = (val <<4) | ( (ch-87)&0xf);
				break;
			default:
				throw new  NumberFormatException(hex);
			}
		}
		return val;
	}
	
	public static String  long2Hex(long lval){
		return String.format("%1$016x", lval);
	}
	
	public static String formatUUID(UUID uuid){
		return String.format("%1$016x", uuid.getMostSignificantBits())+ String.format("%1$016x", uuid.getLeastSignificantBits());
	}
	
	public static String escXMLAttr( String  attrtxt){
		if(attrtxt==null)
			return null;
		StringBuilder strb = new StringBuilder();
		for(int i=0; i< attrtxt.length();i++){
			char ch = attrtxt.charAt(i);
			if(ch == '"'){
				strb.append("&quot;");
			}else if( ch == '\''){
				strb.append("&apos;");
			}else if( ch == '&'){
				strb.append("&amp;");
			}else if( ch == '<'){
				strb.append("&lt;");
			}else if( ch == '>'){
				strb.append("&gt;");
			}else{
				strb.append(ch);
			}
		}
		
		return strb.toString();
	}
	
	
	public static String escXMLText( String  attrtxt){
		if(attrtxt==null)
			return null;
		StringBuilder strb = new StringBuilder();
		for(int i=0; i< attrtxt.length();i++){
			char ch = attrtxt.charAt(i);
			if( ch == '&'){
				strb.append("&amp;");
			}else if( ch == '<'){
				strb.append("&lt;");
			}else if( ch == '>'){
				strb.append("&gt;");
			}else{
				strb.append(ch);
			}
		}
		
		return strb.toString();
	}	
	
	public static String isContainsChar(String param){
		if(param.contains("_")){
			int index = param.indexOf("_");
			String paramId=param.substring(0, index);
			return paramId;
		}
		return param;
	}

	public static String getRequiredString(String parameter) {
		if(parameter==null){
			return "";
		}
		return parameter;
	}
	
	public static int getUserAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();  
		int yearNow = cal.get(Calendar.YEAR);  
		int monthNow = cal.get(Calendar.MONTH)+1;  
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
		
		cal.setTime(birthDay);  
		int yearBirth = cal.get(Calendar.YEAR);  
		int monthBirth = cal.get(Calendar.MONTH)+1;  
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
		
		int age = yearNow - yearBirth>0? yearNow - yearBirth:1;  
		
		if (monthNow <= monthBirth) {  
			if (monthNow == monthBirth) {  
				//monthNow==monthBirth  
				if (dayOfMonthNow < dayOfMonthBirth) {  
					age--;  
				}  
			} else {  
				//monthNow>monthBirth  
				age--;  
			}  
		}  
		
		return age ;  
	}

	public static String getAgeGroupCode(int gender, int age) {
		//根据年龄 性别 获取 年龄段的code
		
		if(2==gender){
			
			if(age<20){
				//小于20岁女性
				return "012004";
			}else if(age>=20 && age<30){
				//20到30岁女性
				return "012005";
			}else if(age>=30 && age<40){
				//30-40岁女性
				return "012006";
			}else if(age>=40 && age<50){
				//40-50岁女性
				return "012007";
			}
			//50+岁女性
			return "012008";
			
		}else if(1==gender){
			if(age<20){
				//小于20岁男性
				return "012009";
			}else if(age>=20 && age<30){
				//20到30岁男性
				return "0120010";
			}else if(age>=30 && age<40){
				//30-40岁男性
				return "0120011";
			}else if(age>=40 && age<50){
				//40-50岁男性
				return "0120012";
			}
			//50+岁男性
			return "0120013";
		}
		//找不到说明有问题 code映射出问题
		logger.error(String.format("get AgeGroupCode   error! AgeGroupCode  inexistence! gender=%s age=%s", gender,age));
		throw new RuntimeException("get AgeGroupCode   error! AgeGroupCode  inexistence! gender="+gender+" age="+age+"");
	}

	public static String getBodyShapeCode(int gender, String bodyShapeCode) {
		
		// 根据性别 和身材 映射 code码
		if(1==gender){
			if("003001".equals(bodyShapeCode)){
				//V形
				return "012014";
			}else if("003002".equals(bodyShapeCode)){
				//O形
				return "012015";
			}else if("003003".equals(bodyShapeCode)){
				//矩形
				return "012016";
			}
			//身材F男性
			throw new RuntimeException("Error BodyShapeCode: %s"+bodyShapeCode+" for male! ");
			
		}else if(2==gender){
			if("002001".equals(bodyShapeCode)){
				//X形
				return "012020";
			}else if("002002".equals(bodyShapeCode)){
				//矩形
				return "012021";
			}else if("002003".equals(bodyShapeCode)){
				//O形
				return "012022";
			}else if("002004".equals(bodyShapeCode)){
				//V形
				return "012023";
			}else if("002005".equals(bodyShapeCode)){
				//A形
				return "012024";
			}
			throw new RuntimeException("Error BodyShapeCode: %s"+bodyShapeCode+" for female! ");
			
		}
		//找不到说明有问题 code映射出问题
		logger.error("get BodyShapeCode  error! gender=%s"+gender+" inexistence! ");
		throw new RuntimeException("error gender code : %s"+gender);
	}

	public static String convertPercentByUserGroup(Double value) {
		
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
		//可以设置精确几位小数  
		df.setMaximumFractionDigits(2);  
		//模式 例如四舍五入  
		df.setRoundingMode(RoundingMode.HALF_UP);
		//可能会出现0%
		if(0==value){
			return "";
			
		}
		return df.format(value)+"%";
	}

	public static String[] getStatistics(int number) {
		
		String[] statistics=new String[number];
		for(int i=0;i<9;i++){
			statistics[i]="01200"+(i+1);
		}
		for(int i=9;i<25;i++){
			statistics[i]="0120"+(i+1);
		}
		
		//性别
		statistics[25]="1";
		statistics[26]="2";
		
		return statistics;
		
	}

	public static String[] getVoteReason(int number) {
		
		String[] voteReason=new String[number];
		//建议买的原因
		voteReason[0]="009001";
		voteReason[1]="009002";
		voteReason[2]="009003";
		voteReason[3]="009004";
		voteReason[4]="009005";
		voteReason[5]="009006";
		voteReason[6]="009007";
		voteReason[7]="009008";
		voteReason[8]="009009";
		
		voteReason[9]="009010";
		voteReason[10]="009011";
		voteReason[11]="009012";
		voteReason[12]="009013";
		voteReason[13]="009014";
		voteReason[14]="009015";
		
		//建议不买的原因 
		voteReason[15]="010001";
		voteReason[16]="010002";
		voteReason[17]="010003";
		voteReason[18]="010004";
		voteReason[19]="010005";
		voteReason[20]="010006";
		voteReason[21]="010007";
		voteReason[22]="010008";
		voteReason[23]="010009";
		
		voteReason[24]="010010";
		voteReason[25]="010011";
		voteReason[26]="010012";
		voteReason[27]="010013";
		voteReason[28]="010014";
		voteReason[29]="010015";
		
		return voteReason;
	}
}
