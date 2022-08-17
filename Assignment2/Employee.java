public class Employee {
	int Employee_level;
    public Employee(int level)
	{
    	Employee_level = level;
	}
    
    public double GetTakeHomeSalary() {
    	double monthlyGross = grossMonthlySalary(Employee_level);
    	return (monthlyGross - monthlyTax(monthlyGross));
    }
	
	public double grossMonthlySalary(int empLevel) {
		switch(empLevel)
		{
		case 1: return (7000*0.88) + 5000 + 2000;
		case 2: return (10000*0.88) + 7000 + 2500;
		case 3: return (12000*0.88) + 9000 + 3000;
		case 4: return (15000*0.88) + 10000 + 3500;
		}
		return 0;
	}
	
	public double monthlyTax(double monthSalary)
	{
		double yearSalary = monthSalary * 12;
		double tax = 0.0;
		tax += (Math.min(yearSalary, 250000) * 0);
		if(yearSalary > 250000)
			tax += (Math.min(yearSalary, 500000) - 250000) * 0.05;
		if(yearSalary > 500000)
			tax += (Math.min(yearSalary, 750000) - 500000) * 0.10;
		if(yearSalary > 750000)
			tax += (Math.min(yearSalary, 1000000) - 750000) * 0.15;
		if(yearSalary > 1000000)
			tax += (Math.min(yearSalary, 1250000) - 1000000) * 0.20;
		
		return (tax)/12;
	}
	
	public static void main(String[] args) {
		System.out.println("Take home salary of level 1 employee: " + new Employee(1).GetTakeHomeSalary());
		System.out.println("Take home salary of level 2 employee: " + new Employee(2).GetTakeHomeSalary());
		System.out.println("Take home salary of level 3 employee: " + new Employee(3).GetTakeHomeSalary());
		System.out.println("Take home salary of level 4 employee: " + new Employee(4).GetTakeHomeSalary());

	}

}
