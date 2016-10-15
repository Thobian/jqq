package qq;

import java.net.Socket;

public class RawSocket {
	
	Socket socket  = null;
	
	public RawSocket(String ip,int port) {
		try {
			socket = new Socket(ip, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendall(String hexstr2str) {
		// TODO Auto-generated method stub
		
	}

	public Object recv() {
		// TODO Auto-generated method stub
		return null;
	}

}
