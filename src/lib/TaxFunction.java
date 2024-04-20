package lib;

public class TaxFunction {

	private static final double TAX_RATE = 0.05;
    private static final int STANDARD_DEDUCTIBLE = 54000000;
    private static final int MARRIED_DEDUCTIBLE = 4500000;
    private static final int CHILD_DEDUCTIBLE_PER_CHILD = 1500000;
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("Error: More than 12 months working per year");
			numberOfMonthWorking = 12;
		}
	
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
	
		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int totalDeductible = calculateTotalDeductible(deductible, isMarried, numberOfChildren);
	
		int tax = (int) Math.round(TAX_RATE * (totalIncome - totalDeductible));
	
		return Math.max(0, tax); // Ensure non-negative tax
	}

	private static int calculateTotalDeductible(int deductible, boolean isMarried, int numberOfChildren) {
		int totalDeductible = STANDARD_DEDUCTIBLE;
		if (isMarried) {
			totalDeductible += MARRIED_DEDUCTIBLE;
		}
		totalDeductible += numberOfChildren * CHILD_DEDUCTIBLE_PER_CHILD;
		return totalDeductible;
	}
	
}
