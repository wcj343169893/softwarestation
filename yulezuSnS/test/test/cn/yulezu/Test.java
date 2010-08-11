package test.cn.yulezu;

public class Test {
	public static void main(String[] args) {
		String str = "http://192.168.0.104/admin/";
		System.out.println(str.matches("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?/admin/?"));
	}
}
