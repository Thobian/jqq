package qq;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class AndroidQQ {

	public static void main(String[] args) {
		AndroidQQ androidQQ = new AndroidQQ("634545399", "xiaomin0322");
		//androidQQ.login();
		
	}

	// QQ
	String qqnum;
	String qqpwd;
	String vcode;
	String qqHexstr;// = Coder.str2hexstr(qqnum)
	String pwdMd5;// = MD5.md5_hex(qqpwd)
	String uin;// = Coder.qqnum2hexstr(qqnum)
	long HEART_INTERVAL = 8 * 60;//// 心跳时间间隔
									//// 如果在手机QQ上注销/退出帐号后，一般10分钟左右您的QQ号就不会显示在线了
	String server_time;// = Coder.num2hexstr(int(time.time()), 4)
	boolean alive = false;
	boolean verify = false;

	// Android
	String seq = "1000";
	String appId = "0"; // Coder.num2hexstr(537042772, 4)
	String extBin = "";// Coder.trim("")
	String msgCookies = "";// Coder.trim("F9 83 8D 80")
	String imei = "";// Coder.str2hexstr("864116195797922")
	String ksid = "";// Coder.trim("")
	// String extBin = "";//Coder.trim("")
	String ver = "";// Coder.str2hexstr("|460006202217491|A5.8.0.157158")
	String os_type = "";// Coder.str2hexstr("android")
	String os_version = "";// Coder.str2hexstr("4.2.2")
	String network_type = "";// Coder.str2hexstr("")
	String sim_operator_name = "";// Coder.str2hexstr("CMCC")
	String apn = "";// Coder.str2hexstr("wifi")
	String device = "";// Coder.str2hexstr("Lenovo A820t")
	String device_product = "";// Coder.str2hexstr("Lenovo")
	String package_name = "";// Coder.str2hexstr("com.tencent.mobileqq")
	String wifi_name = "";// Coder.str2hexstr("OOOOOOOOO")

	// cmd
	String loginCmd = "";// Coder.str2hexstr("wtlogin.login")

	// Keys
	String defaultKey;// "00"*16
	String randomKey = "";// Coder.genBytesHexstr(16)
	int keyId;// random.randint(0, len(Keys.pubKeys)-1)
	String pubKey = "";// Keys.pubKeys[String keyId]
	String shareKey = "";// Keys.shareKeys[String keyId]
	String pwdKey = "";// Coder.hash_qqpwd_hexstr(qqnum, qqpwd)
	String tgtKey = "";// Coder.genBytesHexstr(16)
	String sessionKey = "";// ""

	RawSocket socket = null;
	private String verifyToken1;
	private String verifyToken2;

	public AndroidQQ(String qqnum, String qqpwd) {
		this.qqnum = qqnum;
		this.qqpwd = qqpwd;
		socket = new RawSocket("113.108.90.53", 8080);
		this.vcode = "";
		this.qqHexstr = Coder.str2hexstr(qqnum);
		this.pwdMd5 = DigestUtils.md5Hex(qqpwd);

		this.uin = Coder.qqnum2hexstr(qqnum);
		this.HEART_INTERVAL = 8 * 60; // #心跳时间间隔
										// 如果在手机QQ上注销/退出帐号后，一般10分钟左右您的QQ号就不会显示在线了
		this.server_time = Coder.num2hexstr((int) (new Date().getTime()), 4);
		this.alive = false;
		this.verify = false;

		// Android
		seq = "1000";
		appId = Coder.num2hexstr(537042772, 4);
		extBin = Coder.trim("");
		msgCookies = Coder.trim("F9 83 8D 80");
		imei = Coder.str2hexstr("864116195797922");
		ksid = Coder.trim("");
		extBin = Coder.trim("");
		ver = Coder.str2hexstr("|460006202217491|A5.8.0.157158");
		os_type = Coder.str2hexstr("android");
		os_version = Coder.str2hexstr("4.2.2");
		network_type = Coder.str2hexstr("");
		sim_operator_name = Coder.str2hexstr("CMCC");
		apn = Coder.str2hexstr("wifi");
		device = Coder.str2hexstr("Lenovo A820t");
		device_product = Coder.str2hexstr("Lenovo");
		package_name = Coder.str2hexstr("com.tencent.mobileqq");
		wifi_name = Coder.str2hexstr("OOOOOOOOO");

		// cmd
		loginCmd = Coder.str2hexstr("wtlogin.login");

		// Keys
		defaultKey = "";// '00' *16;
		randomKey = Coder.genBytesHexstr(16);
		keyId = new Random().nextInt(Keys.pubKeys.size());
		pubKey = Keys.pubKeys.get(keyId);
		shareKey = Keys.shareKeys.get(keyId);
		pwdKey = Coder.hash_qqpwd_hexstr(qqnum, qqpwd);
		tgtKey = Coder.genBytesHexstr(16);
		sessionKey = "";
		
		
		   /*#debug
	        print 'uin: ', self.uin
	        print 'pwdMd5: ', self.pwdMd5
	        print 'randomKey: ', self.randomKey
	        print 'pubKey: ', self.pubKey
	        print 'shareKey: ', self.shareKey
	        print 'pwdKey: ', self.pwdKey
	        print 'tgtKey: ', self.tgtKey */
		
		System.out.println("uin :"+uin);
		System.out.println("pwdMd5 :"+pwdMd5);
		System.out.println("randomKey :"+randomKey);
		System.out.println("pubKey :"+pubKey);
		System.out.println("shareKey :"+shareKey);
		System.out.println("pwdKey :"+pwdKey);
		System.out.println("tgtKey :"+tgtKey);
		
	}

	public boolean login() {
		System.out.println("登陸");
		// 发送登录请求
		String packet = "";
		// 包头
		packet += Coder.trim("00 00 00 08 02 00 00 00 04 00");
		packet += Coder.num2hexstr((this.qqHexstr.length()) / 2 + 4, 4);
		packet += this.qqHexstr;

		// TEA加密的包体
		packet += this.packSendLoginMessage(null);

		//// 总包长
		packet = Coder.num2hexstr(len(packet) / 2 + 4, 4) + packet;

		// 发送请求
		this.socket.sendall(Coder.hexstr2str(packet));
		// 接收请求
		Object ret = this.socket.recv();
		// HexPacket pack = HexPacket(Coder.str2hexstr(ret));
		HexPacket pack = new HexPacket(null);
		// 返回包头
		pack.shr(4);
		pack.shr(8);
		pack.shr(2 + len(this.qqHexstr) / 2);
		// 返回包体
		this.unpackRecvLoginMessage(pack.remain(0));

		if (this.alive) { // 登录成功
			// threading.Thread(target=this.startHeart).start() //心跳
			return true;
		} else if (this.verify) { // 需要验证码

		} else {
			return false;
		}

		return true;
	}

	private void unpackRecvLoginMessage(String data) {
		/*
		 * data = TEA.detea_hexstr(data, this.defaultKey); HexPacket pack = new
		 * HexPacket(null); String head =
		 * pack.shr(Coder.hexstr2num(pack.shr(4))-4); String body =
		 * pack.remain(1); //head pack =new HexPacket(null);
		 * Coder.hexstr2num(pack.shr(4)) //seq pack.shr(4);
		 * pack.shr(Coder.hexstr2num(pack.shr(4))-4);
		 * Coder.hexstr2str(pack.shr(Coder.hexstr2num(pack.shr(4))-4)); //cmd
		 * pack.shr(Coder.hexstr2num(pack.shr(4))-4); //body pack =new
		 * HexPacket(null); pack.shr(4 + 1 + 2 + 10 + 2); //int retCode =
		 * Coder.hexstr2num(pack.shr(1)); int retCode = 0; if(retCode == 0){
		 * //登录成功 this.unpackRecvLoginSucceedMessage(pack.remain()); // print
		 * u'登录成功: ', this.nickname System.out.println("登陸成功"); this.alive =
		 * true; this.verify = false; else if(retCode == 2){ //需要验证码
		 * this.unpackRecvLoginVerifyMessage(); //print this.verifyReason
		 * System.out.println("verifyReason"); this.alive = false; this.verify =
		 * true; //threading.Thread(target=Img.showFromHexstr,
		 * args=(this.verifyPicHexstr, )).start() // code =
		 * raw_input(u'请输入验证码：'); //this.login(Coder.str2hexstr(null)); else{
		 * //登录失败 pack = HexPacket(TEA.detea_hexstr(null, this.shareKey));
		 * pack.shr(2 + 1 + 4 + 2); pack.shr(4); //type String title =
		 * Coder.hexstr2str(pack.shr(Coder.hexstr2num(pack.shr(2)))); String msg
		 * = Coder.hexstr2str(pack.shr(Coder.hexstr2num(pack.shr(2)))); //print
		 * title, ': ', msg this.alive = false; this.verify = false; }
		 */

	}

	public String packSendLoginMessage(String verifyCode) {
		// MessageHead
		String msgHeader = "";
		msgHeader += Coder.num2hexstr(this.seq + 1, 4);
		msgHeader += this.appId;
		msgHeader += this.appId;
		msgHeader += Coder.trim("01 00 00 00 00 00 00 00 00 00 00 00");
		msgHeader += Coder.num2hexstr(len(this.extBin) / 2 + 4, 4) + this.extBin;
		msgHeader += Coder.num2hexstr(len(this.loginCmd) / 2 + 4, 4) + this.loginCmd;
		msgHeader += Coder.num2hexstr(len(this.msgCookies) / 2 + 4, 4) + this.msgCookies;
		msgHeader += Coder.num2hexstr(len(this.imei) / 2 + 4, 4) + this.imei;
		msgHeader += Coder.num2hexstr(len(this.ksid) / 2 + 4, 4) + this.ksid;
		msgHeader += Coder.num2hexstr(len(this.ver) / 2 + 2, 2) + this.ver;
		msgHeader = Coder.num2hexstr(len(msgHeader) / 2 + 4, 4) + msgHeader;
		// Message
		String msg = "";
		msg += Coder.trim("1F 41");
		msg += Coder.trim("08 10 00 01");
		msg += this.uin;
		msg += Coder.trim("03 07 00 00 00 00 02 00 00 00 00 00 00 00 00 01 01");
		msg += this.randomKey;
		msg += Coder.trim("01 02");
		msg += Coder.num2hexstr((this.pubKey.length()) / 2, 2) + this.pubKey;
		// TEA加密的TLV
		msg += this.packSendLoginTlv(verifyCode);

		msg += Coder.trim("03");
		msg = Coder.num2hexstr(len(msg) / 2 + 2 + 1, 2) + msg;
		msg = Coder.trim("02") + msg;
		msg = Coder.num2hexstr(len(msg) / 2 + 4, 4) + msg;

		String packet = msgHeader + msg;
		packet = TEA.entea_hexstr(packet, this.defaultKey);
		return packet;
	}

	public static int len(String str) {
		return str.length();
	}

	public String packSendLoginTlv(String verifyCode) {
		if (verifyCode == null) {
			String tlv = "";
			tlv += Coder.trim("00 09");
			tlv += Coder.trim("00 14"); // tlv包个数
			// tlv组包
			tlv += Tlv.tlv18(this.uin);
			tlv += Tlv.tlv1(this.uin, this.server_time);
			tlv += Tlv.tlv106(this.uin, this.server_time, this.pwdMd5, this.tgtKey, this.imei, this.appId, this.pwdKey);
			tlv += Tlv.tlv116();
			tlv += Tlv.tlv100();
			tlv += Tlv.tlv107();
			tlv += Tlv.tlv144(this.tgtKey, this.imei, this.os_type, this.os_version, this.network_type,
					this.sim_operator_name, this.apn, this.device, this.device_product);
			tlv += Tlv.tlv142(this.package_name);
			tlv += Tlv.tlv145(this.imei);
			tlv += Tlv.tlv154(this.seq);
			tlv += Tlv.tlv141(this.sim_operator_name, this.network_type, this.apn);
			tlv += Tlv.tlv8();
			tlv += Tlv.tlv16b();
			tlv += Tlv.tlv147();
			tlv += Tlv.tlv177();
			tlv += Tlv.tlv187();
			tlv += Tlv.tlv188();
			tlv += Tlv.tlv191();
			tlv += Tlv.tlv194();
			tlv += Tlv.tlv202(this.wifi_name);
			tlv = TEA.entea_hexstr(tlv, this.shareKey);
			return tlv;
		} else {
			String tlv = "";
			tlv += Coder.trim("00 02");
			tlv += Coder.trim("00 04");
			// tlv组包
			tlv += Tlv.tlv2(verifyCode, this.verifyToken1);
			tlv += Tlv.tlv8();
			tlv += Tlv.tlv104(this.verifyToken2);
			tlv += Tlv.tlv116();
			tlv = TEA.entea_hexstr(tlv, this.shareKey);
			return tlv;
		}

	}
}
