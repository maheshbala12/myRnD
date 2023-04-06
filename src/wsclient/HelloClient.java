package wsclient;

public class HelloClient {
	public static void main(String[] args) {
		HelloServiceService service = new HelloServiceService();
		HelloService hello = service.getHelloServicePort();
		String text = hello.sayHello("Mahesh");
		System.out.println(text);
	}
}