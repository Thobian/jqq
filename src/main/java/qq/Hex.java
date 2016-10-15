package qq;
/**
 * reference apache commons <a
 * href="http://commons.apache.org/codec/">http://commons.apache.org/codec/</a>
 * 
 * byteռ��8λ��ʮ�������ַ�ռ��4λ�����Կ��԰�һ��byteת����������Ӧ��ʮ�������ַ�������byte�ĸ�4λ�͵�4λ * �ֱ�ת������Ӧ��ʮ�������ַ�H��L,������������෴��ת��Ҳ��ͬ��
 * 
 */
public class Hex {
  
    /**
     * ���ڽ���ʮ�������ַ������
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
  
    /**
     * ���ڽ���ʮ�������ַ������
     */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ����顣
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��ص�char[]���Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @return ����ʮ�������ַ���char[]
     */
    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ����顣
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��ص�char[]���Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @param toLowerCase
     *            <code>true</code> ������Сд��ʽ �� <code>false</code> �����ɴ�д��ʽ
     * @return ����ʮ�������ַ���char[]
     */
    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ����顣
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��ص�char[]���Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @param toDigits
     *            ���ڿ����������ĸ��
     * @return ����ʮ�������ַ���char[]
     */
    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ�����
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��صĵ��ַ������Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @return ʮ�������ַ���
     */
    public static String encodeHexStr(final byte[] data) {
        return encodeHexStr(data, true);
    }
    
    public static String encodeHexStr(final String data) {
        return encodeHexStr(data.getBytes(), true);
    }
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ�����
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��صĵ��ַ������Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @param toLowerCase
     *            <code>true</code> ������Сд��ʽ �� <code>false</code> �����ɴ�д��ʽ
     * @return ʮ�������ַ���
     */
    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }
  
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ�����
     * 
     * ��Ϊʹ�������ַ���ʾһ���ֽڣ����Է��صĵ��ַ������Ƚ��ǲ���byte[]���ȵ�������
     * 
     * @param data
     *            ����ת��Ϊʮ�������ַ���byte[]
     * @param toDigits
     *            ���ڿ����������ĸ��
     * @return ʮ�������ַ���
     */
    protected static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }
  
    /**
     * ��ʮ�������ַ�����ת��Ϊ�ֽ�����
     * 
     * @param data
     *            ʮ������char[]
     * @return byte[]
     * @throws RuntimeException
     *             ���Դʮ�������ַ�����ĳ��������������׳�����ʱ�쳣
     */
    public static byte[] decodeHex(char[] data) {
        int len = data.length;
  
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
  
        // һ��byte��Ӧ����ʮ�������ַ�����byte[]��С����Ϊchar[]��С��һ��
        byte[] out = new byte[len >> 1];
  
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
  
        return out;
    }
  
    /**
     * ��ʮ�������ַ�ת����һ��������
     * 
     * @param ch
     *            Ҫת�����������ַ�
     * @param index
     *            �ַ����ַ������е�λ��
     * @return һ������
     * @throws RuntimeException
     *             ��ch����һ���Ϸ���ʮ�������ַ�ʱ���׳����쳣
     */
    protected static int toDigit(final char ch, final int index) {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
  
    public static void main(String[] args) {
        String srcStr = "HelloWorld!";
        String encodeStr = encodeHexStr(srcStr.getBytes(), false);
        String decodeStr = new String(decodeHex(encodeStr.toCharArray()));
        System.out.println("Դ�ַ�����" + srcStr);
        System.out.println("�ַ�������Ϊʮ�����ƣ�" + encodeStr);
        System.out.println("ʮ�����ƽ���Ϊ�ַ�����" + decodeStr);
    }
  
}