package T_pro;

import java.util.*;

public class CM_Function {
   
   // ===========================================*****
   public long mul(long a, long b){
      return (a * b);
   }
   
   // ===========================================*****
   public long mod(long a, long n){
      
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
   public long Sum_divisor(long a) {
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
   public long get_LCM(long a, long b){
      a = Math.abs(a);
      return ((a * b) / GCD(a, b));
   }
   
   // ===========================================*****
   public boolean is_relatively_prime(long a, long b){
      long t_gcd = GCD(a, b);
      if (t_gcd == 1) {
         return true;
      }
      return false;
   }

   // ===========================================*****
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
      
      res[0] = x1;
      res[1] = y1;
      res[2] = tmp_a;
      return res;
   }
   
   // ===========================================*****
   public long[] Solve_Diophantine_Eq(long a, long b) {
      long[] EEA = new long[3];
      int i;
      long x0, y0, gcd;
      a = Math.abs(a);
      b = Math.abs(b);
      
      List<Long>[] Dio = new ArrayList[2];
      for (i = 0; i < 2; i++) {
         Dio[i] = new ArrayList<Long>();
      }
      
      EEA = Extended_Euclid(a, b);
      x0 = EEA[0];
      y0 = EEA[1];
      gcd = EEA[2];
      
      
      if (a > b) {
         for (i = -4; i <= 5; i++) {
            Dio[0].add(x0 + (b / gcd) * i);
            Dio[1].add(y0 - (a / gcd) * i);
         }
      }
      else if (a < b) {
         for (i = -4; i <= 5; i++) {
            Dio[1].add(x0 + (a / gcd) * i);
            Dio[0].add(y0 - (b / gcd) * i);
         }
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
      long gcd = GCD(a, n);
      long k = 0;
      
      List<Long> L_data = new ArrayList<Long>();
      
      if ((b % gcd) != 0) {
         return null;
      }
      
      k = mod(inverse(a/gcd,n/gcd) * b/gcd, n/gcd);
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
   
   // ===========================================*****
   public long get_order(long a, long n) // O(len(phi(n).divisor)) = O(n) 약수 구하는게 O(n)임
   {
	   if(GCD(a,n) != 1) {
		   return -1;
	   }
	   
	   long[] ind = get_divisor(phi(n));
	   
	   for(int i=0; i < ind.length; i++) {
		   if(mod_pow(a, ind[i], n)==1) {
			   return ind[i];
		   }
	   }
	   return -1;
   }
   
   // ===========================================*****
   public long[] disjoint_set(long n) { //O(n)
	   
	   List<Long> L_data = new ArrayList<Long>();
	   for(long i = 1; i <= n; i++) {
		   if(GCD(i,n)==1) {
			   L_data.add(i);
		   }
	   }
	   
	   long[] A_data = L_data.stream()
               .mapToLong(l -> l)
               .toArray();
      
     return A_data;
   }
   
   // ===========================================*****
   public long[] primitive_root(long n) // O(phi*n)
   {
	   long[] disj = disjoint_set(n);
	   long phi = disj.length;
	   List<Long> L_data = new ArrayList<Long>();
	   
	   for(int i = 0; i < phi; i++) {
		   if(get_order(disj[i],n)==phi) {
			   L_data.add(disj[i]);
		   }
	   }
	   
	   if(L_data.isEmpty()) {
		   return null;
	   }
	   long[] A_data = L_data.stream()
               .mapToLong(l -> l)
               .toArray();
      
	   return A_data;
   }
   
   public long get_one_primitive_root(long n)
   {
	   long[] disj = disjoint_set(n);
	   long phi = disj.length;
	   for(int i = 0; i < phi; i++) {
		   if(get_order(disj[i],n)==phi) {
			   return disj[i];
		   }
	   }
	   return -1;
   }
   
   public long ind(long r, long a, long n) { // O(phi(n)*log n)
	   long phi = phi(n);
	   for(long i = 1; i <= phi; i++) {
		   if(mod_pow(r,i,n) == a) {
			   return i; // ind a
		   }
	   }
	   return -1;
   }
   
   public long[] generator_table(long root, long n) // a 
   {
	   if(get_one_primitive_root(n)==-1) {
		   return null;
	   }
	   List<Long> L_data = new ArrayList<Long>();
	   for(int i = 1; i <= phi(n); i++) {
		   L_data.add(mod_pow(root, i, n));
	   }
	   
	   long[] A_data = L_data.stream()
               .mapToLong(l -> l)
               .toArray();
      
	   return A_data;
   }
   
   public long[] Solve_ind_congruence(long a,long e,long b,long n)
   {
	   long root = get_one_primitive_root(n);
	   long phi = phi(n);
	   
	   if((root == -1)||(GCD(a,n)!=1)||(GCD(b,n)!= 1)) {
		   return null;
	   }
	   // ind a + e*ind x = ind b (mod phi(n))
	   
	   long[] ind_x = Solve_linear_congruence(e, mod((ind(root,b,n)-ind(root,a,n)),phi),phi);
	   if(ind_x == null) {
		   return null;
	   }
	   long[] x = new long[ind_x.length];
	   
	   for(int i = 0; i < ind_x.length; i++) {
		   x[i] = mod_pow(root,ind_x[i],n);
	   }
	   return x;
   }
   
   public int Quadratic_residue(long a, long p) {
	   if(GCD(a,p) != 1||p==2) {
		   return 0;
	   }
	   
	   return (RtoL(a, (p-1)>>1, p)==1)? 1 : -1;
   }
   
   public int Quadratic_Reciprocity_Law(long p, long q)
   {
	   //if(!(is_Prime(p))||!(is_Prime(q)))return 0;
	   return Quadratic_residue(p,q)* Quadratic_residue(q,p);
   }
   
}
