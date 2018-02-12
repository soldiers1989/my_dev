package com.ddf.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = false
	 * StringUtil.isEmpty("")        = false
	 * StringUtil.isEmpty(" ")       = true
	 * StringUtil.isEmpty("bob")     = true
	 * StringUtil.isEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果不为空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * 生成位数为size的随机数字符串
	 * 
	 * @param size
	 * @return
	 */
	public static String creatRandom(int size) {

		if (size <= 0) {
			size = 1;
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < size; i++) {
			sb.append(new Random().nextInt(10));
		}
		return sb.toString();

	}

	/**
	 * encode编码字符串
	 */
	public static String encodeUTF(String str) {
		String encodeRedirectUri = null;
		try {
			encodeRedirectUri = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeRedirectUri;
	}

	/**
	 * decode解码字符串
	 */
	public static String decodeUTF(String str) {
		String decodeStr = null;
		try {
			decodeStr = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodeStr;
	}

	/**
	 * 删除最后一个字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeLastChar(String str) {
		if (isNotEmpty(str)) {
			return str.substring(0, str.length() - 1);
		} else {
			return str;
		}
	}

	/**
	 * 获得最后一个字符
	 * 
	 * @param str
	 * @return
	 */
	public static String getLastChar(String str) {
		if (isNotEmpty(str)) {
			return str.substring(str.length() - 1, str.length());
		} else {
			return str;
		}
	}
	
	/**
	 * 产生长度为length的数组+字母随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(random.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * sha1加密
	 * 
	 * @param content
	 * @return
	 */
	public static String sha1(String content) {
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	public static String removeParams(String url, String[] params) {
		String reg = null;
		for (int i = 0; i < params.length; i++) {
			reg = "(?<=[\\?&])" + params[i] + "=[^&]*&?";
			url = url.replaceAll(reg, "");
		}
		url = url.replaceAll("&+$", "");
		return url;
	}

	/*
	 * public static void main(String[] args) { //
	 * System.out.print(getStringRandom(8));
	 * System.out.println(column2property("_aa_bb_ccc_dd")); }
	 */

	public static String urlJoinPath(String url, String path) {
		if (StringUtil.isEmpty(url)) {
			return url;
		}

		if (!url.endsWith("/")) {
			url = url + "/";
		}

		if (StringUtil.isEmpty(path)) {
			return url;
		}

		if (path.indexOf("/") == 0) {
			path = path.substring(1);
		}

		return url + path;

	}

	public static String column2property(String column) {
		if (StringUtil.isEmpty(column) || column.indexOf("_") < 0) {
			return column;
		}
		StringBuffer sb = new StringBuffer();
		String lowerCode = column.toLowerCase();
		String[] array = lowerCode.split("_");
		for (String str : array) {
			if (!StringUtil.isEmpty(str)) {
				str = first2upperCase(str);
				sb.append(str);
			}

		}
		String finalStr = first2lowerCase(sb.toString());
		return finalStr;
	}

	public static String first2upperCase(String str) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}
		str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		return str;
	}

	public static String first2lowerCase(String str) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}
		str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
		return str;
	}

	public static String phoneHide(String memberName) {
		return memberName.substring(0, 3) + "****" + memberName.substring(7, memberName.length());

	}

	public static String nameHide(String realName) {
		String star = "*";
		for (int i = 1; i < realName.length() - 1; i++) {
			star = star + "*";
		}
		return realName.substring(0, 1) + star;
	}

	/**
	 * 获得随机的字符和数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {

		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 钱是100.00就取整100
	 * 
	 * @param m
	 * @return
	 */
	public static String getMoney(String m) {
		Double d = Double.parseDouble(m);
		int s = (int) Math.ceil(d);
		if (s == d) {
			return Integer.toString(s);
		} else {
			if (m.substring(m.length() - 1, m.length()).equals("0")) {
				return m.substring(0, m.length() - 1);
			} else {
				return m;
			}
		}
	}

	/**
	 * 解析出下列语法所构成的<code>SENTENCE</code>。
	 * 
	 * <pre>
	 *  SENTENCE = WORD (DELIMITER* WORD)*
	 * 
	 *  WORD = UPPER_CASE_WORD | LOWER_CASE_WORD | TITLE_CASE_WORD | DIGIT_WORD
	 * 
	 *  UPPER_CASE_WORD = UPPER_CASE_LETTER+
	 *  LOWER_CASE_WORD = LOWER_CASE_LETTER+
	 *  TITLE_CASE_WORD = UPPER_CASE_LETTER LOWER_CASE_LETTER+
	 *  DIGIT_WORD      = DIGIT+
	 * 
	 *  UPPER_CASE_LETTER = Character.isUpperCase()
	 *  LOWER_CASE_LETTER = Character.isLowerCase()
	 *  DIGIT             = Character.isDigit()
	 *  NON_LETTER_DIGIT  = !Character.isUpperCase() && !Character.isLowerCase() && !Character.isDigit()
	 * 
	 *  DELIMITER = WHITESPACE | NON_LETTER_DIGIT
	 * </pre>
	 */
	private abstract static class WordTokenizer {
		protected static final char UNDERSCORE = '_';

		/**
		 * Parse sentence。
		 */
		public String parse(String str) {
			if (StringUtil.isEmpty(str)) {
				return str;
			}

			int length = str.length();
			StringBuffer buffer = new StringBuffer(length);

			for (int index = 0; index < length; index++) {
				char ch = str.charAt(index);

				// 忽略空白。
				if (Character.isWhitespace(ch)) {
					continue;
				}

				// 大写字母开始：UpperCaseWord或是TitleCaseWord。
				if (Character.isUpperCase(ch)) {
					int wordIndex = index + 1;

					while (wordIndex < length) {
						char wordChar = str.charAt(wordIndex);

						if (Character.isUpperCase(wordChar)) {
							wordIndex++;
						} else if (Character.isLowerCase(wordChar)) {
							wordIndex--;
							break;
						} else {
							break;
						}
					}

					// 1. wordIndex == length，说明最后一个字母为大写，以upperCaseWord处理之。
					// 2. wordIndex == index，说明index处为一个titleCaseWord。
					// 3. wordIndex > index，说明index到wordIndex -
					// 1处全部是大写，以upperCaseWord处理。
					if ((wordIndex == length) || (wordIndex > index)) {
						index = parseUpperCaseWord(buffer, str, index, wordIndex);
					} else {
						index = parseTitleCaseWord(buffer, str, index);
					}

					continue;
				}

				// 小写字母开始：LowerCaseWord。
				if (Character.isLowerCase(ch)) {
					index = parseLowerCaseWord(buffer, str, index);
					continue;
				}

				// 数字开始：DigitWord。
				if (Character.isDigit(ch)) {
					index = parseDigitWord(buffer, str, index);
					continue;
				}

				// 非字母数字开始：Delimiter。
				inDelimiter(buffer, ch);
			}

			return buffer.toString();
		}

		private int parseUpperCaseWord(StringBuffer buffer, String str, int index, int length) {
			char ch = str.charAt(index++);

			// 首字母，必然存在且为大写。
			if (buffer.length() == 0) {
				startSentence(buffer, ch);
			} else {
				startWord(buffer, ch);
			}

			// 后续字母，必为小写。
			for (; index < length; index++) {
				ch = str.charAt(index);
				inWord(buffer, ch);
			}

			return index - 1;
		}

		private int parseLowerCaseWord(StringBuffer buffer, String str, int index) {
			char ch = str.charAt(index++);

			// 首字母，必然存在且为小写。
			if (buffer.length() == 0) {
				startSentence(buffer, ch);
			} else {
				startWord(buffer, ch);
			}

			// 后续字母，必为小写。
			int length = str.length();

			for (; index < length; index++) {
				ch = str.charAt(index);

				if (Character.isLowerCase(ch)) {
					inWord(buffer, ch);
				} else {
					break;
				}
			}

			return index - 1;
		}

		private int parseTitleCaseWord(StringBuffer buffer, String str, int index) {
			char ch = str.charAt(index++);

			// 首字母，必然存在且为大写。
			if (buffer.length() == 0) {
				startSentence(buffer, ch);
			} else {
				startWord(buffer, ch);
			}

			// 后续字母，必为小写。
			int length = str.length();

			for (; index < length; index++) {
				ch = str.charAt(index);

				if (Character.isLowerCase(ch)) {
					inWord(buffer, ch);
				} else {
					break;
				}
			}

			return index - 1;
		}

		private int parseDigitWord(StringBuffer buffer, String str, int index) {
			char ch = str.charAt(index++);

			// 首字符，必然存在且为数字。
			if (buffer.length() == 0) {
				startDigitSentence(buffer, ch);
			} else {
				startDigitWord(buffer, ch);
			}

			// 后续字符，必为数字。
			int length = str.length();

			for (; index < length; index++) {
				ch = str.charAt(index);

				if (Character.isDigit(ch)) {
					inDigitWord(buffer, ch);
				} else {
					break;
				}
			}

			return index - 1;
		}

		protected boolean isDelimiter(char ch) {
			return !Character.isUpperCase(ch) && !Character.isLowerCase(ch) && !Character.isDigit(ch);
		}

		protected abstract void startSentence(StringBuffer buffer, char ch);

		protected abstract void startWord(StringBuffer buffer, char ch);

		protected abstract void inWord(StringBuffer buffer, char ch);

		protected abstract void startDigitSentence(StringBuffer buffer, char ch);

		protected abstract void startDigitWord(StringBuffer buffer, char ch);

		protected abstract void inDigitWord(StringBuffer buffer, char ch);

		protected abstract void inDelimiter(StringBuffer buffer, char ch);
	}

	private static final WordTokenizer LOWER_CASE_WITH_UNDERSCORES_TOKENIZER = new WordTokenizer() {
		protected void startSentence(StringBuffer buffer, char ch) {
			buffer.append(Character.toLowerCase(ch));
		}

		protected void startWord(StringBuffer buffer, char ch) {
			if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
				buffer.append(UNDERSCORE);
			}

			buffer.append(Character.toLowerCase(ch));
		}

		protected void inWord(StringBuffer buffer, char ch) {
			buffer.append(Character.toLowerCase(ch));
		}

		protected void startDigitSentence(StringBuffer buffer, char ch) {
			buffer.append(ch);
		}

		protected void startDigitWord(StringBuffer buffer, char ch) {
			if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
				buffer.append(UNDERSCORE);
			}

			buffer.append(ch);
		}

		protected void inDigitWord(StringBuffer buffer, char ch) {
			buffer.append(ch);
		}

		protected void inDelimiter(StringBuffer buffer, char ch) {
			buffer.append(ch);
		}
	};

	/**
	 * 将字符串转换成下划线分隔的小写字符串。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toLowerCaseWithUnderscores(null)  = null
	 * StringUtil.toLowerCaseWithUnderscores("")    = ""
	 * StringUtil.toLowerCaseWithUnderscores("aBc") = "a_bc"
	 * StringUtil.toLowerCaseWithUnderscores("aBc def") = "a_bc_def"
	 * StringUtil.toLowerCaseWithUnderscores("aBc def_ghi") = "a_bc_def_ghi"
	 * StringUtil.toLowerCaseWithUnderscores("aBc def_ghi 123") = "a_bc_def_ghi_123"
	 * StringUtil.toLowerCaseWithUnderscores("__a__Bc__") = "__a__bc__"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 此方法会保留除了空白以外的所有分隔符。
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 下划线分隔的小写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toLowerCaseWithUnderscores(String str) {
		return LOWER_CASE_WITH_UNDERSCORES_TOKENIZER.parse(str);
	}

	/**
	 * 随机生成n位的字符串
	 */
	public static String createRandomStr(int size) {
		String[] arr = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		if (size > 0) {
			String str = "";
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				int a = random.nextInt(52);
				str += arr[a];
			}
			return str;
		}
		return null;
	}

	/**
	 * 将emoji表情替换成空串
	 * 
	 * @param source
	 * @return 过滤后的字符串
	 */
	public static String filterEmoji(String source) {
		if (isNotEmpty(source)) {
			return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
		}
		return null;
	}
	
	public static String getFileExtName(String fileURI){
		return fileURI.substring(fileURI.lastIndexOf('.')+1, fileURI.length());
	}
	
	public static List<String> getImageSrc(String htmlCode) {
	    List<String> imageSrcList = new ArrayList<String>();
	    Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
	    Matcher m = p.matcher(htmlCode);
	    String quote = null;
	    String src = null;
	    while (m.find()) {
	        quote = m.group(1);
	 
	        // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
	        src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
	        imageSrcList.add(src);
	 
	    }
	    return imageSrcList;
	}

	
	/**
	 * 用来补充key的长度
	 * 
	 * @param key
	 * @return
	 */
	public static String replenishLength(String key,int maxLenth) {
		if(StringUtil.isEmpty(key)){
			key="0";
		}
		if (key.length() >= maxLenth) {
			return key;
		}
		key = "0" + key;
		key = replenishLength(key,maxLenth);
		return key;
	}
	
	/**
     * 将大写字母转换成下划线加小写字母
     * 例:userName--> user_name
     * @param str
     * @return
     */
    public static String lowerStrToUnderline(String str) {
        if(isEmpty(str)){
            return "";
        }
        StringBuilder sb = new StringBuilder(str);
        char c;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.replace(i+count, i+count+1, (c+"").toLowerCase());
                sb.insert(i+count, "_");
                count++;
            }
        }
        return sb.toString();
    }
    
	public static void main(String[] args) {
		/*System.out.println(System.currentTimeMillis());
		System.out.println(getShortOrderNum());
		String s="你好啊 <br/>你是 <br />你好<h1>嘿嘿</h1>";
		System.out.println(delHTMLTag(s));*/
		System.out.println(lowerStrToUnderline("UserName"));
	}
	/**
	 * 过滤html标签工具类
	 */
	public static String delHTMLTag(String htmlStr) {  
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	    String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	    String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	    
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
	
	public static String getOrderNum(){
		StringBuffer sb = new StringBuffer();
		Date currentDate = DateUtil.getCurrentDate();
		String dateStr = DateUtil.formateDate(currentDate,DateUtil.format);
		String random = creatRandom(4);
		return sb.append(dateStr).append(random).toString();
	}
	
	public static String getShortOrderNum(){
		String currentMills = String.valueOf(System.currentTimeMillis());
		return currentMills+creatRandom(1);
	}
	
}
