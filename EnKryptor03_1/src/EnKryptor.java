
public class EnKryptor {
	
	private static int[] key;
	private static int[] userEncrypt, userDecrypt;
	private static int userlen, user_remain;
	private static char bigE = 'E';
	private static int numE;
	private static int[] Ecounter;
	private static int delta, sum;
	private static char[] userout;
	private static String finalOutput;
	
	public static void encryption() {
		GetKey();
		
		userlen = EnKryptor3_1GUI.inputT.length();
		user_remain = (5 - (userlen % 5)) % 5;
		userEncrypt = new int [userlen+user_remain];
		//Convert to int
		for (int i=0; i<userlen; i++) {
			userEncrypt[i] = (int)EnKryptor3_1GUI.inputT.charAt(i);
		}
		//Add characters to the end
		if (user_remain > 0) {
			for (int i=userlen; i<userlen+user_remain; i++) {
				userEncrypt[i] = (int) bigE;
			}
		}
		//System.out.println("Length is: " + userlen);
		//System.out.println("Remain is: " + user_remain);
		//System.out.println("Input is:");
		//for (int i=0; i<userlen+user_remain; i++) {
		//	System.out.println(userEncrypt[i]);
		//}
		
		//Encryption
		delta = 0xAAAA + key[0];
		sum = 0;
		for (int x=0; x<userlen+user_remain; x++) {
			userEncrypt[x] = (((userEncrypt[x]-32) + ((userEncrypt[x+4]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) %96) + 32;
			userEncrypt[x+1] = (((userEncrypt[x+1]-32) + ((userEncrypt[x]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) %96) + 32;
			userEncrypt[x+2] = (((userEncrypt[x+2]-32) + ((userEncrypt[x+1]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) %96) + 32;
			userEncrypt[x+3] = (((userEncrypt[x+3]-32) + ((userEncrypt[x+2]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) %96) + 32;
			userEncrypt[x+4] = (((userEncrypt[x+4]-32) + ((userEncrypt[x+3]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) %96) + 32;
			sum += delta;
			x += 4;
		}
		
		//Convert back to char
		userout = new char[userlen+user_remain];
		for (int x=0; x<userlen+user_remain; x++) {
			userout[x] = (char)userEncrypt[x];
		}
		//Convert to strings
		finalOutput = new String(userout);
		//Set text
		EnKryptor3_1GUI.outputT = finalOutput;
		EnKryptor3_1GUI.OutputText.setText(EnKryptor3_1GUI.outputT);
	}
	
	public static void decryption() {
		GetKey();
		
		userlen = EnKryptor3_1GUI.inputT.length();
		user_remain = (5 - (userlen % 5)) % 5;
		userDecrypt = new int [userlen+user_remain];
		//Convert to int
		for (int i=0; i<userlen; i++) {
			userDecrypt[i] = (int)EnKryptor3_1GUI.inputT.charAt(i);
		}
		
		//System.out.println("Length is: " + userlen);
		//System.out.println("Remain is: " + user_remain);
		//System.out.println("Input is:");
		//for (int i=0; i<userlen+user_remain; i++) {
		//	System.out.println(userDecrypt[i]);
		//}
		
		//Decryption
		delta = 0xAAAA + key[0];
		sum = 0;
		for (int x=0; x<userlen+user_remain; x++) {
			userDecrypt[x+4] -= 32;
			if (userDecrypt[x+4] < (userDecrypt[x+3]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10) {
				userDecrypt[x+4] += 96;
			}
			userDecrypt[x+4] = (userDecrypt[x+4] - ((userDecrypt[x+3]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) + 32;
			//----------------------------------------------
			userDecrypt[x+3] -= 32;
			if (userDecrypt[x+3] < (userDecrypt[x+2]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10) {
				userDecrypt[x+3] += 96;
			}
			userDecrypt[x+3] = (userDecrypt[x+3] - ((userDecrypt[x+2]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) + 32;
			//----------------------------------------------
			userDecrypt[x+2] -= 32;
			if (userDecrypt[x+2] < (userDecrypt[x+1]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10) {
				userDecrypt[x+2] += 96;
			}
			userDecrypt[x+2] = (userDecrypt[x+2] - ((userDecrypt[x+1]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) + 32;
			//----------------------------------------------
			userDecrypt[x+1] -= 32;
			if (userDecrypt[x+1] < (userDecrypt[x]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10) {
				userDecrypt[x+1] += 96;
			}
			userDecrypt[x+1] = (userDecrypt[x+1] - ((userDecrypt[x]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) + 32;
			//----------------------------------------------
			userDecrypt[x] -= 32;
			if (userDecrypt[x] < (userDecrypt[x+4]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10) {
				userDecrypt[x] += 96;
			}
			userDecrypt[x] = (userDecrypt[x] - ((userDecrypt[x+4]>>>4 ^ key[sum%EnKryptor3_1GUI.KEYS_NUM])%10)) + 32;
			
			sum += delta;
			x += 4;
		}
		
		//Convert back to char
		userout = new char[userlen+user_remain];
		for (int x=0; x<userlen+user_remain; x++) {
			userout[x] = (char)userDecrypt[x];
		}
		//Get rid of 'E's
		System.out.println(" ");
		Ecounter = new int [5]; // create an array to check which values were Es
		for (int a = 0; a < 5; a++) {
			numE = (userlen-1) - a;
			if (userout[numE] == bigE) {
				Ecounter[a] = 1;
			}
			else {
				Ecounter[a] = 0;
			}
		}
		int a = 0;
		while (Ecounter[a] == 1) { // Only change consecutive Es
			numE = (userlen-1) - a;
			userout[numE] = (char)0;
			a++;
		}
		//Convert to strings
		finalOutput = new String(userout);
		//Set text
		EnKryptor3_1GUI.outputT = finalOutput;
		EnKryptor3_1GUI.OutputText.setText(EnKryptor3_1GUI.outputT);
		
	}
	private static void GetKey() {
		//Extract key from password
		key = new int[EnKryptor3_1GUI.KEYS_NUM];
		for (int i=0; i<EnKryptor3_1GUI.KEYS_NUM; i++) {
			key[i] = (int) EnKryptor3_1GUI.inputP.charAt(i);
			//System.out.println(key[i]);
		}
	}
}
