package unsw.dungeon;

import java.util.*;

class SayHello extends TimerTask {
    
	int x;
	
	public SayHello(int x) {
		this.x = x;
	}
	
	public void run() {
       System.out.println("Hello World!"); 
    }
    
}

// And From your main() method or any other method


