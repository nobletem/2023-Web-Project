package T_pro;

//import java.util.*;
//import T_pro.Basic;

class Prime_and_Disjoint
{
	T_pro.Basic Basic = new T_pro.Basic();
	
	public boolean is_relatively_prime(long a, long b){
		long t_gcd = Basic.GCD(a, b);
		if (t_gcd == 1) {
			return true;
		}
		return false;
	}
	
	public boolean miller_rabin(long a, long n) {
		long r = 0, x = 0;
		long d = n-1;
		
		while(d % 2 == 0) {
			r++;
			d >>= 1;
		}
		
		x = Basic.mod_pow(a, d, n);
		if (x == 1 || x == n-1) return true;
		
		for (int i = 0; i < r - 1; i++) {
			x = Basic.mod_pow(x, 2, n);
			if (x == n-1) return true;
		}
		
		return false;
	}
	
	public boolean is_Prime(long n) {
		long[] p = { 2, 3, 5, 7, 11, 13, 17 };
		
		if (n <= 1) return false;
		if (n == 2 || n == 3) return true;
		if (n % 2 == 0) return false;
		
		for (int i = 0; i < p.length; i++) {
			if (n == p[i]) return true;
			if (!miller_rabin(p[i], n)) return false;
		}
		
		return true;
	}
	
	public int Quadratic_residue(long a, long p) {
		   if(Basic.GCD(a,p) != 1||p==2) {
			   return 0;
		   }
		   
		   return (Basic.RtoL(a, (p-1)>>1, p)==1)? 1 : -1;
	   }
	
	   
   public int Quadratic_Reciprocity_Law(long p, long q)
   {
	   if(!(is_Prime(p))||!(is_Prime(q))) return 0;
	   return Quadratic_residue(p,q)* Quadratic_residue(q,p);
   }
}
