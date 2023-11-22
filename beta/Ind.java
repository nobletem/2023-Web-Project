package T_pro;

import java.util.*;
//import T_pro.Basic;

class Ind
{
	T_pro.Basic Basic = new T_pro.Basic();
	
	public long get_order(long a, long n) // O(len(phi(n).divisor)) = O(n) 약수 구하는게 O(n)임
	   {
		   if(Basic.GCD(a,n) != 1) {
			   return -1;
		   }
		   
		   long[] ind = Basic.get_divisor(Basic.phi(n));
		   
		   for(int i=0; i < ind.length; i++) {
			   if(Basic.mod_pow(a, ind[i], n)==1) {
				   return ind[i];
			   }
		   }
		   return -1;
	   }
	   
	   // ===========================================*****
	   public long[] disjoint_set(long n) { //O(n)
		   
		   List<Long> L_data = new ArrayList<Long>();
		   for(long i = 1; i <= n; i++) {
			   if(Basic.GCD(i,n)==1) {
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
		   long phi = Basic.phi(n);
		   for(long i = 1; i <= phi; i++) {
			   if(Basic.mod_pow(r,i,n) == a) {
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
		   for(int i = 1; i <= Basic.phi(n); i++) {
			   L_data.add(Basic.mod_pow(root, i, n));
		   }
		   
		   long[] A_data = L_data.stream()
	               .mapToLong(l -> l)
	               .toArray();
	      
		   return A_data;
	   }
}
