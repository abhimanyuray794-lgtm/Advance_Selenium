package basics;

import org.testng.annotations.Test;

public class Demo {
@Test
public void register(){
	System.out.println("register");
}
@Test(priority = 1)
public void login() {
	System.out.println("login");
}
@Test(enabled = false, priority = 2)
public void delete() {
	System.out.println("delete");
}
}
