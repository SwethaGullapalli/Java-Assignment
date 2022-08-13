package test;

public class Student {
	float scienceMarks;
	float historyMarks;
	float accountMarks;
	float socialMarks;
	float total;
	float averageMarks;
	
	void totalMarks() {
		total=scienceMarks+historyMarks+accountMarks+socialMarks;
		
	}
	
	void averageMarks() {
		averageMarks=total/4;
	}
public static void main(String[] args) {
	
	Student Swetha=new Student();
	Swetha.scienceMarks=82;
	Swetha.historyMarks=39;
	Swetha.accountMarks=56;
	Swetha.socialMarks=48;
	Swetha.totalMarks();
	Swetha.averageMarks();
	System.out.println("Total marks of Swetha is:"+Swetha.total);
	System.out.println("Average marks of Swetha is:"+Swetha.averageMarks);
	
} 


}
