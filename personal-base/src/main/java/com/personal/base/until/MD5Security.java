package com.personal.base.until;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * TODO:
 * 
 * @author yangzw
 * @version 1.0, 2013-10-30/下午5:26:01
 * 
 */
public class MD5Security {
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
	
	/** Base64映射表 */
    private static final char emap[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', // A-H;0-7
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', // I-P; 8 -15
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // Q-X; 16-23
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', // YZ, a-f; 24-31
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', // g-n; 32-39
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', // o-v; 40-47
            'w', 'x', 'y', 'z', '0', '1', '2', '3', // w-z, 0-3; 48-55
            '4', '5', '6', '7', '8', '9', '+', '/'}; // 4-9, + /; 56-63

	public static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	public static byte[] hexToBytes(String str) {
        if (str==null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i=0; i<len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
            }
            return buffer;
        }
    }
	
	public static String md5Hex(String input, int bit) throws Exception {
		return codeHex(input, bit);
	}
	
	public static String md5Hex(String input) throws Exception {
		return md5Hex(input, 32);
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String md5Base64(String input) throws Exception {
		return codeBase64(input);
	}
	
	 /**
     * 将字符串转换为MD5字节组
     * @param srcStr 源字符串
     * @return rtnByte MD5字节组
	 * @throws NoSuchAlgorithmException 
     */
	public static final byte[] encodeMD5(String srcStr) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        byte[] rtnByte = null;
        md = MessageDigest.getInstance("MD5");
        rtnByte = md.digest(srcStr.getBytes());
        return rtnByte;
    }

	public static String codeHex(String input, int bit) throws Exception {
		try {
			if (bit == 16)
				return bytesToHex(encodeMD5(input)).substring(8, 24);
			return bytesToHex(encodeMD5(input));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}
	
	public static String codeBase64(String input) throws Exception {
		try {
			return encodeBase64(encodeMD5(input));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}

//	public static String md5_3(String b) throws Exception {
//		MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
//		byte[] a = md.digest(b.getBytes());
//		a = md.digest(a);
//		return bytesToHex(a);
//	}
	
	/**
     * 将字节数组转换为Base64编码
     * @param inputBytes 字节数组
     * @return rtnStr Base64处理完成后的字符串
     * @see com.novell.ldap.util.Base64
     */
	public static final String encodeBase64(byte[] inputBytes) {
        int i, j, k;
        int t, t1, t2;
        int ntb;
        boolean onePadding = false, twoPaddings = false;
        char[] encodedChars;
        int len = inputBytes.length;
        if (len == 0) {
            return new String("");
        }
        ntb = len%3 == 0 ? (len / 3) : ((len / 3) + 1);
        if ((len % 3) == 1) {
            twoPaddings = true;
        } else if ((len % 3) == 2) {
            onePadding = true;
        }
        encodedChars = new char[ntb * 4];
        for (i = 0, j = 0, k = 1; i < len; i += 3, j += 4, k++) {
            t = 0x00ff & inputBytes[i];
            encodedChars[j] = emap[t >> 2];
            if ((k == ntb) && twoPaddings) {
                encodedChars[j + 1] = emap[(t & 0x03) << 4];
                encodedChars[j + 2] = '=';
                encodedChars[j + 3] = '=';
                break;
            } else {
                t1 = 0x00ff & inputBytes[i + 1];
                encodedChars[j + 1] = emap[((t & 0x03) << 4) + ((t1 & 0xf0) >> 4)];
            }
            if ((k == ntb) && onePadding) {
                encodedChars[j + 2] = emap[(t1 & 0x0f) << 2];
                encodedChars[j + 3] = '=';
                break;
            } else {
                t2 = 0x00ff & inputBytes[i + 2];
                encodedChars[j + 2] = (emap[(t1 & 0x0f) << 2 | (t2 & 0xc0) >> 6]);
            }
            encodedChars[j + 3] = (emap[(t2 & 0x3f)]);
        }
        return new String(encodedChars);
    }
    
	/**
	 * 注册密码 明文加密
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encryptOnconLoginPWD(String password) throws Exception{ 
        String pwdmd5 = MD5Security.md5Base64(password); 
        pwdmd5 = "{MD5}".concat(pwdmd5); 
        return pwdmd5; 
	}
	
	public static char[] getChars (byte[] bytes) {
	      Charset cs = Charset.forName ("UTF-8");
	      ByteBuffer bb = ByteBuffer.allocate (bytes.length);
	      bb.put (bytes);
	      bb.flip ();
	      CharBuffer cb = cs.decode (bb);
	  
	   return cb.array();
	}
	
	public static void main(String[] args) throws	 Exception {
		
		System.out.println(MD5Security.encryptOnconLoginPWD("111111"));
		System.out.println(new Md5Hash("111111").toString());
		String ss=new Md5Hash("111111", "wf").toString();
		System.out.println(new SimpleHash("MD5", "111111", "eteokues").toString());
		System.out.println(ss);
		String simpleHash = new SimpleHash("MD5", "111111", "eteokues",1).toString();

	}
	
	/**
	 * 注册改密码 密文加密
	 * md5 转成 base64 编码并加上{MD5}字符串
	 * @param md5str md5密文
	 * @return
	 */
	public static String  md5toBase64(String md5str){
		return "{MD5}"+ encodeBase64(hexToBytes(md5str));
	}
	
	
	private static void test(String src, String md5str) throws Exception {
		String str = MD5Security.encryptOnconLoginPWD(src);
		System.out.println(str);
		System.out.println(bytesToHex(encodeMD5(src)));
		byte[]  b = hexToBytes(md5str);
		String s = encodeBase64(b);
		System.out.println("{MD5}"+s);
	}
    
}
