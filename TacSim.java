import java.util.Scanner;
import java.io.*;

public class TacSim {

	public static void main(String[] args) throws IOException{
		if (args.length > 0) {
			File file = new File(args[0]); 
			tacsim(file);
		}
			
	}
	
	public static void tacsim(File file) throws IOException{
		System.out.println("TacSim v0.1 - running :" + file.getName());
		
		Scanner scanFile = new Scanner(file);
			
		int memory[] = new int[256];
		for (int i = 0; i < memory.length; i++) {
			if (scanFile.hasNextLine()) {
				memory[i] = scanFile.nextInt();
			}
			else
				memory[i] = 0;
		}
		scanFile.close();
		
		int A = 0, X = 0, SR = 0, PP = 0;
		
		boolean run = true;
		while(run && PP < 256) {
			switch (memory[PP]) {
			
			case 0:
				break;
				
			case 1:
				A = A + memory[PP+1];
				break;
				
			case 2:
				A = A + memory[memory[PP+1]];
				break;
				
			case 3:
				if (A > memory[PP+1]){
					SR = 1;
				}
				else 
					SR = 0;
				
				break;
					
			case 4:
				if (SR == 1)
					PP = memory[PP+1];
				break;
				
			case 5: 
				PP = memory[PP+1] - 2;
				break;
		
			case 6:
				A = memory[PP+1];
				break;
				
			case 7:
				A = memory[memory[PP+1]];
				break;
				
			case 8:
				A = memory[X + memory[PP+1]];
				break;
				
			case 9:
				X = A;
				break;
				
			case 10:
				memory[memory[PP+1]] = A;
				break;
				
			case 11:
				A = A & memory[PP+1];
				break;
				
			case 12:
				A = A | memory[PP+1];
				break;
				
			case 13:
				A = A ^ memory[PP+1];
				break;
				
			case 14:
				if (SR == 0) {
					PP = memory[PP+1];
				}
				break;
				
			case 15:
				run = false;
				break;
			}
			PP += 2;
			if (PP >= 256)
				run = false;
		}
		
		System.out.println("Memory Dump");
		for (int i = 0; i < memory.length;) {
			for (int j = 0; j < 16; j++, i++) {
				System.out.printf("%4d",memory[i]);
			}
			
			System.out.println();
		}
			
	}

}