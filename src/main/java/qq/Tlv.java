package qq;

public class Tlv {
	
	 
	    public static String tlv18(String uin){
	        String tlv = "";
	        tlv += Coder.trim("00 01");
	        tlv += Coder.trim("00 00 06 00");
	        tlv += Coder.trim("00 00 00 10");
	        tlv += Coder.trim("00 00 00 00");
	        tlv += uin;
	        tlv += Coder.trim("00 00 00 00");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("00 18") + tlv;;
	        return tlv;}
	    
	    public static String tlv1(String uin, String server_time){
	        String tlv = "";
	        tlv += Coder.trim("00 01");
	        tlv += Coder.genBytesHexstr(4);
	        tlv += uin ;
	        tlv += server_time;
	        tlv += Coder.trim("00 00 00 00");
	        tlv += Coder.trim("00 00");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;;
	        tlv = Coder.trim("00 01") + tlv;;
	        return tlv;}
	    
	    public static String tlv106(String uin,String server_time,String pwdMd5,String tgtKey,String imei,String appId,String pwdKey){
	        String tlv = "";
	        tlv += Coder.trim("00 03");
	        tlv += Coder.genBytesHexstr(4);
	        tlv += Coder.trim("00 00 00 05 00 00 00 10 00 00 00 00 00 00 00 00");
	        tlv += uin;
	        tlv += server_time;
	        tlv += Coder.trim("00 00 00 00 01");
	        tlv += pwdMd5;
	        tlv += tgtKey;
	        tlv += Coder.trim("00 00 00 00 01");
	        tlv += imei;
	        tlv += appId;
	        tlv += Coder.trim("00 00 00 01");
	        tlv += Coder.trim("00 00");
	        tlv = TEA.entea_hexstr(tlv, pwdKey);
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 06") + tlv;
	        return tlv;}
	    
	    public static String tlv116(){
	        String tlv = "";
	        tlv += Coder.trim("00");
	        tlv += Coder.trim("00 01 FF 7C");
	        tlv += Coder.trim("00 01 04 00");
	        tlv += Coder.trim("00");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 16") + tlv;
	        return tlv;}
	    
	    public static String tlv100(){
	        String tlv = "";
	        tlv += Coder.trim("00 01");
	        tlv += Coder.trim("00 00 00 05");
	        tlv += Coder.trim("00 00 00 10");
	        tlv += Coder.trim("20 02 9F 54");
	        tlv += Coder.trim("00 00 00 00");
	        tlv += Coder.trim("02 1E 10 E0");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 00") + tlv;
	        return tlv;}
	    
	    public static String tlv107(){
	        String tlv = "";
	        tlv += Coder.trim("00 00");
	        tlv += Coder.trim("00 00 00 01");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 07") + tlv;
	        return tlv;}
	    
	    public static String tlv144(String tgtKey,String imei, String os_type,String  os_version,String network_type,String sim_operator_name,String apn,String device,String device_product){
	        String tlv = "";
	        tlv += Coder.trim("00 04");
	        tlv += Tlv.tlv109(imei);
	        tlv += Tlv.tlv124(os_type, os_version, network_type, sim_operator_name, apn);
	        tlv += Tlv.tlv128(device, imei, device_product);
	        tlv += Tlv.tlv16e(device);
	        tlv = TEA.entea_hexstr(tlv, tgtKey);
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 44") + tlv;
	        return tlv;}
	    
	    public static String tlv109(String imei){
	        String tlv = "";
	        tlv += Coder.num2hexstr(len(imei)/2, 2) + imei;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 09") + tlv;
	        return tlv;}
	    
	    public static String tlv124(String os_type,String os_version,String network_type,String sim_operator_name,String apn){
	        String tlv = "";
	        tlv += Coder.num2hexstr((os_type.length())/2, 2) + os_type;
	        tlv += Coder.num2hexstr((os_version.length())/2, 2) + os_version;
	        tlv += Coder.num2hexstr((network_type.length())/2, 2) + network_type;
	        tlv += Coder.num2hexstr((sim_operator_name.length())/2, 2) + sim_operator_name;
	        tlv += Coder.trim("00 00");
	        tlv += Coder.num2hexstr(len(apn)/2, 2) + apn;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 24") + tlv;
	        return tlv;}
	    
	    public static String tlv128(String device,String imei,String device_product){
	        String tlv = "";
	        tlv += Coder.trim("00 00");
	        tlv += Coder.trim("01");
	        tlv += Coder.trim("01");
	        tlv += Coder.trim("00");
	        tlv += Coder.trim("11 00 00 00");
	        tlv += Coder.num2hexstr(len(device)/2, 2) + device;
	        tlv += Coder.num2hexstr(len(imei)/2, 2) + imei;
	        tlv += Coder.num2hexstr(len(device_product)/2, 2) + device_product;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 28") + tlv;
	        return tlv;}
	    
	    public static String tlv16e(String device){
	        String tlv = "";
	        tlv += Coder.num2hexstr(len(device)/2, 2) + device;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 6E") + tlv;
	        return tlv;}
	    
	    public static String tlv142(String package_name){
	        String tlv = "";
	        tlv += Coder.num2hexstr(len(package_name)/2, 4) + package_name;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 42") + tlv;
	        return tlv;}
	    
	    public static String tlv145(String imei){
	        String tlv = "";
	        tlv += imei;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 45") + tlv;
	        return tlv;}
	    
	    public static String tlv154(String seq){
	        String tlv = "";
	        tlv += Coder.num2hexstr(seq, 4);
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 54") + tlv;
	        return tlv;}
	    
	    public static String tlv141(String sim_operator_name, String network_type,String  apn){
	        	String tlv = "";
	        tlv += Coder.trim("00 01");
	        tlv += Coder.num2hexstr(len(sim_operator_name)/2, 2) + sim_operator_name;
	        tlv += Coder.num2hexstr(len(network_type)/2, 2) + network_type;
	        tlv += Coder.num2hexstr(len(apn)/2, 2) + apn;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 41") + tlv;
	        return tlv;}
	    
	    public static String tlv8(){
	        	String tlv = "";
	        tlv += Coder.trim("00 00");
	        tlv += Coder.trim("00 00 08 04"); //request_global._local_id
	        tlv += Coder.trim("00 00");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("00 08") + tlv;
	        return tlv;}
	    
	    public static String tlv16b(){
	        	String tlv = "";
	        tlv += Coder.trim("00 02");
	        tlv += Coder.trim("00 0B");
	        tlv += Coder.trim("67 61 6D 65 2E 71 71 2E 63 6F 6D"); ////game.qq.com
	        tlv += Coder.trim("00 0B");
	        tlv += Coder.trim("67 61 6D 65 2E 71 71 2E 63 6F 6D");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 6B") + tlv;
	        return tlv;}
	    
	    public static String tlv147(){
	        	String tlv = "";
	        tlv += Coder.trim("00 00 00 10");
	        tlv += Coder.trim("00 05");
	        tlv += Coder.trim("35 2E 38 2E 30"); //request_global._apk_v = 5.8.0
	        tlv += Coder.trim("00 10");
	        tlv += Coder.trim("A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D"); //request_global._apk_sig
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 47") + tlv;
	        return tlv;}
	    
	    public static String tlv177(){
	        	String tlv = "";
	        tlv += Coder.trim("01");
	        tlv += Coder.trim("55 A3 23 2E");
	        tlv += Coder.trim("00 07");
	        tlv += Coder.trim("35 2E 34 2E 30 2E 37"); //5.4.0.7
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 77") + tlv;
	        return tlv;}
	    
	    public static String tlv187(){
	        	String tlv = "";
	        tlv += Coder.trim("F9 03 BA FF 80 D5 BA AC DC EA 9C 16 49 6F 53 83");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 87") + tlv;
	        return tlv;}
	    
	    public static String tlv188(){
	        	String tlv = "";
	        tlv += Coder.trim("3F D1 F5 BA 24 67 56 F3 97 87 49 AE 1D 67 76 EE");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 88") + tlv;
	        return tlv;}
	    
	    public static String tlv191(){
	        	String tlv = "";
	        tlv += Coder.trim("01");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 91") + tlv;
	        return tlv;}
	    
	    public static String tlv194(){
	        	String tlv = "";
	        tlv += Coder.trim("65 68 D4 A4 FA CA 6E 78 B3 6B 07 40 C2 71 A8 6E");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 94") + tlv;
	        return tlv;}
	    
	    public static String tlv202(String wifi_name){
	        	String tlv = "";
	        tlv += Coder.trim("00 10");
	        tlv += Coder.trim("F5 AC 6C 03 0C 31 AE 5C 26 2E BE 49 86 23 65 1E");
	        tlv += Coder.num2hexstr(len(wifi_name)/2, 2) + wifi_name;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("02 02") + tlv;
	        return tlv;
	    }
	    
	    public static String tlv2(String verifyCode, String verifyToken1){
	        	String tlv = "";
	        tlv += Coder.num2hexstr(len(verifyCode)/2, 4) + verifyCode;
	        tlv += Coder.num2hexstr(len(verifyToken1)/2, 2) + verifyToken1;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("00 02") + tlv;
	        return tlv;
	    }
	    
	    public static String tlv8_(){
	    	String tlv = "";
	        tlv += Coder.trim("00 00");
	        tlv += Coder.trim("00 00 08 04");
	        tlv += Coder.trim("00 00 ");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("00 08") + tlv;
	        return tlv;
	    }
	    
	    public static String tlv104(String verifyToken2){
	    	String tlv = "";
	        tlv += verifyToken2;
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 04") + tlv;
	        return tlv;
	    }
	    
	    public static String tlv116_(){
	        String tlv = "";
	        tlv += Coder.trim("00");
	        tlv += Coder.trim("00 01 FF 7C");
	        tlv += Coder.trim("00 01 04 00");
	        tlv += Coder.trim("00");
	        tlv = Coder.num2hexstr((tlv.length())/2, 2) + tlv;
	        tlv = Coder.trim("01 16") + tlv;
	        return tlv;
	    }

	    
	   public static int len(String str){
		   return str.length();
	   }
	   
}
