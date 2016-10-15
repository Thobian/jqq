package qq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class Coder {

	public static String trim(String data) {
		return data.replace(" ", "").toLowerCase();
	}

	public static String num2hexstr(String data, int n) {
		if (n == 0) {
			n = 1;
		}

		// String data2 = Integer.toHexString(data).replace("0x",
		// "").replace("L", "");
		return null;
	}

	public static String getZeroLen(int len) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < len; i++) {
			builder.append("0");
		}
		return builder.toString();
	}

	public static String buleft0(int val, int len) {
		int youNumber = val;
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		String str = String.format("%0" + len + "d", youNumber);
		System.out.println(str); // 0001
		return str;
	}

	public static String num2hexstr(int data, int n) {
		if (n == 0) {
			n = 1;
		}
		String data2 = Integer.toHexString(data).replace("0x", "").replace("L", "");
		System.out.println(data2);
		// return 0*(2*n-(String.valueOf(data2).length())) + data2;
		return getZeroLen(2 * n - (String.valueOf(data2).length())) + data2;

	}

	public static String numlist2hexstr(int n, String... data) {
		// return ''.join([Coder.num2hexstr(li, n) for li in data])
		StringBuilder builder = new StringBuilder();
		for (String li : data) {
			builder.append(num2hexstr(li, n));
		}
		return builder.toString();
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
		// return Coder.numlist2hexstr([random.randint(0, 255) for i in
		// range(n)])
		List<String> list = new ArrayList();
		for (int i = 0; i < n; i++) {
			int j = new Random().nextInt(255);
			list.add(String.valueOf(j));
		}

		return numlist2hexstr(n, (String[]) list.toArray());
		// return null;
	}

	public static int len(String str) {
		return str.length();
	}

	public static String qqnum2hexstr(String qqnum) {
		String data = Integer.toHexString(Integer.valueOf(qqnum)).replace("0x", "").replace("L", "");
		return getZeroLen(8 - len(data)) + data;
	}

	public static String hash_qqpwd_hexstr(String qqnum, String qqpwd) {
		return DigestUtils.md5Hex(Hex.encodeHexStr(DigestUtils.md5Hex(qqpwd)) + Hex.encodeHexStr("00000000")
				+ Hex.encodeHexStr(Coder.qqnum2hexstr(qqnum)));
		// return null;
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

	public static void main(String[] args) {
		System.out.println(str2hexstr("12345"));
		System.out.println(num2hexstr(12345, 5));
	}
}
