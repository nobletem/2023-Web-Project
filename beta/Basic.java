package T_pro;

import java.util.*;

public class Basic
{
	public long mod(long a, long n)
	{
		
		if (a % n < 0) {
			return ((a % n) + n);
		}

		return a % n;
	}
	
	// ===========================================*****
	public long[] get_divisor(long a){
		long i;
		List<Long> L_data = new ArrayList<Long>();
		
		for (i = 1; i <= a; i++) {
			if (a % i == 0) {
				L_data.add(i);
			}
		}
	    long[] A_data = L_data.stream()
                .mapToLong(k -> k)
                .toArray();
		
		return A_data;
	}
	
	// ===========================================*****
	public long sum_divisor(long a) {
		long sum = 0;
		long[] n;
		n = get_divisor(a);
		
		for (int i = 0; i < n.length; i++) {
			sum += n[i];
		}
		
		return sum;
	}
	
	// ===========================================*****
	public long[] get_division(long a, long b){
		long tmp;
		long[] res = new long[2];
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		res[0] = (a / b);
		res[1] = (a % b);
		
		if (res[1] < 0) {
			res[0]--;
			res[1] += b;
		}
		return res;		
	}	
	
	// ===========================================*****
	public boolean get_divisable(long a, long b){
		long tmp;
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		if ((a % b) == 0){
			return true;
		}
		return false;
	}
	
	// ===========================================*****
	public long GCD(long a, long b) {
	    if (b == 0) {
	        return a;
	    } else {
	        return GCD(b, a % b);
	    }
	}
	
	// ===========================================*****
	public long LCM(long a, long b){
		a = Math.abs(a);
		return ((a * b) / GCD(a, b));
	}
	
	public long[] Extended_Euclid(long a, long b){
		long tmp, q, r = 1;
		long x1, x2, y1, y2;
		long tmp_a, tmp_b, tmp_x, tmp_y; 
		long[] QR = new long[2];
		long[] res = new long[3];

		tmp_a = Math.abs(a);
		tmp_b = Math.abs(b);
		if (tmp_a < tmp_b) {
			tmp = tmp_a;
			tmp_a = tmp_b;
			tmp_b = tmp;
		}
		
		x1 = y2 = 1;
		x2 = y1 = 0;
		
		while(r != 0) {
			QR = get_division(tmp_a, tmp_b);
			q = QR[0];
			r = QR[1];
			tmp_a = tmp_b;
			tmp_b = r;
			
			tmp_x = x1 - (x2 * q);
			tmp_y = y1 - (y2 * q);
			x1 = x2;
			x2 = tmp_x;
			y1 = y2;
			y2 = tmp_y;
		}			
		
		if (a < 0) {
			x1 = -x1;
		}
		if (b < 0) {
			y1 = -y1;
		}
		
		if (Math.abs(a) < Math.abs(b)) {
			res[0] = y1;
			res[1] = x1;
			res[2] = tmp_a;
		}
		else {
			res[0] = x1;
			res[1] = y1;
			res[2] = tmp_a;
		}
		
		return res;
	}
	
   // ===========================================*****
   public long phi(long n) {
       if(n<=0){
           return 0;
       }

       long result = 1; 

       for (long i = 2; i < n; i++) {
           if (GCD(i, n) == 1) {
               result++;
           }
       }

       return result;
   }
   
   // ===========================================*****
	public int len_bin(long num)
	   {
	       int length = 0;

	       if (num == 0) {
	           return 1;
	       }

	       while (num > 0) {
	           length++;
	           num = num >> 1; 
	       }

	       return length;
	   }

	
   // ===========================================*****
   public long RtoL(long a, long e, long n)
   {    
       long r0 = 1;
       long r1 = a;

       for(int i = (len_bin(e)-1); i>-1; i--){
           r0 = (r0 * r0) % n;
           if(((e >> i) & 1) == 1){
               r0 = (r0 * r1) % n;
           }  

       }
       return r0;
   }

   // ===========================================*****
   public long inverse(long a, long n)
   {
       if(GCD(a,n) != 1){
           return 0;
       }
       return RtoL(a,phi(n)-1,n);
   }

   // ===========================================*****
   public long mod_pow(long a, long e, long n)
   {
       if(e<0){
           return inverse(RtoL(a,(~e+1),n),n);
       }
       else if (e == 0){
           return 1;
       }
       else {
           return RtoL(a,e,n);
       }
   }
	
}
