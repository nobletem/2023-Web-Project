package T_pro;

import java.util.*;
//import T_pro.Basic;
//import T_pro.Ind;

class Solve_Eq
{
	T_pro.Basic Basic = new T_pro.Basic();
	T_pro.Ind Ind = new T_pro.Ind();
	
	// ===========================================*****
	public long[] Solve_Diophantine_Eq(long a, long b) {
		long[] EEA = new long[3];
		int i;
		long x0, y0, gcd;
		
		List<Long>[] Dio = new ArrayList[2];
		for (i = 0; i < 2; i++) {
			Dio[i] = new ArrayList<Long>();
		}
		
		EEA = Basic.Extended_Euclid(a, b);
		x0 = EEA[0]; // 절댓값: a > b -> x0=eea_x, y0=eea_y
		y0 = EEA[1]; // 절댓값: a < b -> x0=eea_y, y0=eea_x
		gcd = EEA[2];

		for (i = -4; i <= 5; i++) {
			Dio[0].add(x0 + (b / gcd) * i);
			Dio[1].add(y0 - (a / gcd) * i);
		}

	    long[] res_Dio = new long[20];
		for (i = 0; i < 10; i++) {
			res_Dio[i] = Dio[0].get(i);
		}
		for (i = 0; i < 10; i++) {
			res_Dio[i + 10] = Dio[1].get(i);
		}
	    
		return res_Dio;
	}
	
	// ===========================================*****
	public long[] Solve_linear_congruence(long a, long b, long n) {
		long gcd = Basic.GCD(a, n);
		long k = 0;
		System.out.print(a + ", " + b + ", " + n + "\n");
		
		List<Long> L_data = new ArrayList<Long>();
		
		if ((b % gcd) != 0) {
			return null;
		}
		
		k = Basic.mod(Basic.inverse(a/gcd,n/gcd) * b/gcd, n/gcd);
		L_data.add(k);
		for (int i = 1; i < gcd; i++) {
			L_data.add(k + (n/gcd) * i);
		}
		
	    long[] A_data = L_data.stream()
                .mapToLong(l -> l)
                .toArray();
	    
		return A_data;
	}
	
	// ===========================================*****
	public long[] CRT(long[] a, long[] b, long[] m) {
	    // mod m1, m2, ..., mn 들 중에서 겹칠 때와 혹은 서로소가 아닐 때의 조건 생각 필요;;
	    long M = 1, temp_result = 0;
	    long[] tmp;
	    long[] res = new long[2];
	    
	    for (int i = 0; i < a.length; i++){
	        tmp = Solve_linear_congruence(a[i], b[i], m[i]);
	        b[i] = tmp[0];
	        m[i] = m[i] / Basic.GCD(a[i], m[i]);
	    }

	    // pair 서로소가 아닌 경우, CRT_many 함수로 넘어감
	    for (int i=0; i < m.length; i++) {
	        for (int j=0; j < m.length; j++) {
	            if (i==j) continue;
	            if (Basic.GCD(m[i], m[j]) != 1) 
	                return CRT_many(b, m); 
	        }
	    }

	    for (int i = 0; i < a.length; i++) M *= m[i];
	    
	    for (int i = 0; i < a.length; i++){
	        a[i] = M / m[i];    //a가 M1, M2, ..., Mn 배열로 재할당
	        temp_result += (b[i] * a[i] * Basic.inverse(a[i], m[i]));
	    }
	    
	    res[0] = Basic.mod(temp_result, M);
	    res[1] = M;
	    return res;
	}
	/**
	 * 서로소가 아닐 때, 반복문 돌리기
	 */
	public long[] CRT_many(long[] b, long[] m) {
		long[] res = new long[2];
	    long a1 = b[0];
	    long m1 = m[0];
		
	    if(b.length != m.length) return null; // throw new Exception("b 와 m의 사이즈가 달라요...");

	    for(int i=1; i<b.length; i++) {
	        long a2 = b[i];
	        long m2 = m[i];
	        long g = Basic.GCD(m1, m2);
	        // if a1 % g != a2 % g : throw Exception
	        
	        long[] eea = Basic.Extended_Euclid(m1/g, m2/g);
	        long lcm = Basic.LCM(m1, m2);
	        long x =Basic.mod((a1*(m2/g)*eea[1] + a2*(m1/g)*eea[0]), lcm);
	        a1 = x;
	        m1 = lcm;
	    }
	    
	    res[0] = a1;
	    res[1] = m1;
	    return res;
	}
	
	 public long[] Solve_ind_congruence(long a,long e,long b,long n)
	   {
		   long root = Ind.get_one_primitive_root(n);
		   long phi = Basic.phi(n);
		   
		   if((root == -1)||(Basic.GCD(a,n)!=1)||(Basic.GCD(b,n)!= 1)) {
			   return null;
		   }
		   // ind a + e*ind x = ind b (mod phi(n))
		   
		   long[] ind_x = Solve_linear_congruence(e, Basic.mod((Ind.ind(root,b,n)-Ind.ind(root,a,n)),phi),phi);
		   if(ind_x == null) {
			   return null;
		   }
		   long[] x = new long[ind_x.length];
		   
		   for(int i = 0; i < ind_x.length; i++) {
			   x[i] = Basic.mod_pow(root,ind_x[i],n);
		   }
		   return x;
	   }
}
