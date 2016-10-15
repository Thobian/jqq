package qq;

public class HexPacket {

	int cur = 0;
	String[] data;
	int len;

	public HexPacket(String[] data) {
		this.cur = 0;
		this.data = data;
		this.len = len(data);
	}

	public String shl(int n) {
		n *= 2;
		if (n > this.cur)
			n = this.cur;
		int old = this.cur;
		this.cur -= n;
		return this.data[this.cur];
	}

	public String shr(int n) {
		n *= 2;
		if (n + this.cur > this.len)
			n = this.len - this.cur;
		int old = this.cur;
		this.cur += n;
		return this.data[this.cur];
	}

	public int remain_n() {
		return (this.len - this.cur) / 2;
	}

	public String remain(int rn) {
		return this.shr(this.remain_n() - rn);
	}

	public int len() {
		return this.len;
	}

	public String cur_byte() {
		return this.data[this.cur + 2];
	}

	public int len(String[] str) {
		return str.length;
	}

}
