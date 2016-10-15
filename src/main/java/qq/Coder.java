package qq;

public class Coder {

	public static String trim(String data) {
		return data.replace(" ", "").toLowerCase();
	}
	
	public static String num2hexstr(String data, int n) {
		if (n == 0) {
			n = 1;
		}

		//String data2 = Integer.toHexString(data).replace("0x", "").replace("L", "");
		return null;
	}


	public static String num2hexstr(int data, int n) {
		if (n == 0) {
			n = 1;
		}

		//String data2 = Integer.toHexString(data).replace("0x", "").replace("L", "");
		return null;
	}

	public static String numlist2hexstr(String data, int n) {
		return null;
	}

	public static String str2hexstr(String data) {
		return Hex.encodeHexStr(data.getBytes());
	}

	public static String hexstr2str(String data) {
		return new String(Hex.decodeHex(data.toCharArray()));
	}

	public static String hexstr2num(String data) {
		return null;
	}

	public static String hexstr2hexlist(String data) {
		return null;
	}

	public static String hexstr2hexstream(String data) {
		return null;
	}

	public static String genBytesHexstr(int n) {
		return null;
	}

	public static String qqnum2hexstr(String qqnum) {
		return null;
	}

	public static String hash_qqpwd_hexstr(String qqnum, String qqpwd) {
		return null;
	}

	public static String ip2long(String ip) {
		return null;
	}

	public static String long2ip(int num) {
		return null;
	}

	public static String ip2hexstr(String ip) {
		return null;
	}

	public static String hexstr2ip(String data) {
		return null;
	}
}
