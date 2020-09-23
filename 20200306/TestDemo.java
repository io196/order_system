



public class TestDemo {

		public static void main (String[] args) {
			System.out.println(Byte.MIN_VALUE);
			// int a = 3;
			// byte b = a;
			// short a = 128;
			// byte b = (byte)a;
			// System.out.println(a+" "+b);
			// byte a = 130;
			// float b = 2.5;
		}

	//·Ö±íÇóÈý¸öint±äÁ¿ÖÐµÄ×î´ó¡¢×îÐ¡Öµ
	public static void main2 (String[] args) {
		int a = 8;
		int b = 20;
		int c = 5;
		int max = (a>b?a:b);
		max = (max>c?max:c);
		System.out.println("max="+max);
		int min = (a<b?a:b);
		min = (min<c?min:c);
		System.out.println("min="+min);
	}

    //½»»»Á½¸öint±äÁ¿µÄÖµ
	public static void main1 (String[] args) {
		int a = 10;
		int b = 20;
		int tmp = 0;
		tmp = a;
		a = b;
		b = tmp;
		System.out.println("a =" + a);
		System.out.println("b =" + b);
	}
}