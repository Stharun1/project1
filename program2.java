package pra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
class main{
	public static void  partion(String Input,LinkedList<String> Store_Ans) {
		int Start_var_Of_String=0,End_var_Of_String=50;
		int Length_of_String=Input.length();
		while(End_var_Of_String<Length_of_String) {
			Store_Ans.add(Input.substring(Start_var_Of_String,End_var_Of_String));
			Start_var_Of_String=End_var_Of_String;
			End_var_Of_String+=50;
		}
		if(Length_of_String%50!=0) {
			Store_Ans.add(Input.substring(Start_var_Of_String,Input.length()));
		}
		return;
	}
	public static int  partionTwo(String sec_inp,LinkedList<String> list_store,int Index) {
		int strt=0,end=50;
		int str_Len=sec_inp.length();
		while(end<str_Len) {
			if(strt==0) {
				list_store.add(Index," "+sec_inp.substring(strt,end));
			}
			else {
				list_store.add(Index,sec_inp.substring(strt,end));
			}
			strt=end;
			end+=50;
			Index+=1;
		}
		if(str_Len%50!=0) {
			list_store.add(Index,sec_inp.substring(strt,sec_inp.length()));
		}
		return Index;
	}
}
class HomeWork{
	public static void main(String[] args) throws FileNotFoundException {
		main Object=new main();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the file name:");
		Scanner sc1=new Scanner(new File(sc.nextLine()));
		LinkedList<String> List_Store=new LinkedList<String>();
		String input="";
		while(sc1.hasNext()) {
			input+=sc1.nextLine();
		}
		Object.partion(input,List_Store);
		while(true) {
			System.out.print("Enter your choice:");
			String User_Input=sc.nextLine();
			int Integer_Covert=Integer.parseInt(User_Input);
			if(Integer_Covert==5) {
				System.out.println("Thank you!");
				break;
			}
			if(Integer_Covert==1) {
				System.out.print("Enter the new content:");
				String One_Input=" "+sc.nextLine();
				String String_Previous_Length=List_Store.get(List_Store.size()-1);
				int len=String_Previous_Length.length();
				if(One_Input.length()>=50) {
					String_Previous_Length+=One_Input.substring(0,50);
					List_Store.remove(List_Store.size()-1);
					List_Store.add(String_Previous_Length);
					One_Input=One_Input.substring(50);	
					Object.partion(One_Input,List_Store);
				}
				else {
					String_Previous_Length+=One_Input;
					List_Store.remove(List_Store.size()-1);
					List_Store.add(String_Previous_Length);
				}
				//for(String i2:ans)
					//System.out.println(i2);
				System.out.println();
			}
			else if(Integer_Covert==2) {
				System.out.print("After which sentence you want to insert a new line (number only):");
				String User_Input2=sc.nextLine();
				int Integer_to_num=Integer.parseInt(User_Input);
				int Count=0,Inner_loop=0,Outer_loop=0;
				String String_temp_check="";
				outer: for(Outer_loop=0;Outer_loop<List_Store.size();Outer_loop++) {
					String_temp_check=List_Store.get(Outer_loop);
					for(Inner_loop=0;Inner_loop<String_temp_check.length();Inner_loop++){
						if(String_temp_check.charAt(Inner_loop)=='.'){
							Count+=1;
						}
						if(Count==Integer_to_num) {
							break outer;
						}
					}
				}
				System.out.print("Enter the new line:");
				String secOne_Input=sc.nextLine();
				String Remaing_String=String_temp_check.substring(0,Inner_loop+1);
				List_Store.remove(Outer_loop);
				List_Store.add(Outer_loop,Remaing_String);
				String rem2=String_temp_check.substring(Inner_loop+2);
				int k=Object.partionTwo(secOne_Input,List_Store,Outer_loop+1);
				if(rem2.length()>0) {
					List_Store.add(k+1,rem2);
				}
				//for(String i2:ans)
					//System.out.println(i2);
				System.out.println();
			}
			else if(Integer_Covert==3) {
				System.out.print("The latest content of the file:"+"\n");
				for(String List_Elements:List_Store)
					System.out.print(List_Elements);
				System.out.println();
				System.out.println();
			}
			else if(Integer_Covert==4) {
				String HashCode_Address=List_Store.get(0);
				System.out.print("Address of the first block:"+HashCode_Address.hashCode()+"\n");
				System.out.print("Total number of blocks:"+List_Store.size()+"\n");
				System.out.println();
			}
		}
		//for(String i:List_Store)
		//	System.out.println(i);
	}
}