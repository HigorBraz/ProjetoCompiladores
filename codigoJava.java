import java.util.Scanner;

public class codigoJava{ 
	public static void main(String args[]){ 
		Scanner sc = new Scanner(System.in);
		int  a;
		int  b;
		double  c;
		double  d;
		int  i;
		String  text;
		a = 2;

		if (a>1 && a<3) {
			System.out.println("ABC");

		}

		System.out.println(a);

		while (a+1==10) {
			a = (a+1)*(4/2);
			System.out.println(a);
		}

		d = 1.5;

		do {
			d = d+0.5;

			System.out.println(d);

		}
		while (d<3.5);

		for (int i=0;i<2;i++) {
			System.out.println(a);
		}

	}
}
