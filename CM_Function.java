package T_pro;

import java.util.*;

/****************************************************
 * Class Name : CM_Function
 * 기능 : 암호 수학(정수론) 계산 기능 제공
 * 
 * [Providing Function]
 * 
 * long mod(long a, long n)
 * long[] get_divisor(long a)
 * long Sum_divisor(long a)
 * long[] get_division(long a, long b)
 * boolean get_divisable(long a, long b)
 * long GCD(long a, long b)
 * long get_LCM(long a, long b)
 * boolean is_relatively_prime(long a, long b)
 * long[] Extended_Euclid(long a, long b)
 * long[] Solve_Diophantine_Eq(long a, long b)
 * long[] Solve_linear_congruence(long a, long b, long n)
 * long[] CRT(long[] a, long[] b, long[] m)
 * long[] CRT_many(long[] b, long[] m)
 * boolean miller_rabin(long a, long n)
 * boolean is_Prime(long n)
 * int len_bin(long num)
 * long phi(long n)
 * long RtoL(long a, long e, long n)
 * long inverse(long a, long n)
 * long mod_pow(long a, long e, long n)
 * long get_order(long a, long n)
 * long[] disjoint_set(long n)
 * long[] primitive_root(long n)
 * long get_one_primitive_root(long n)
 * long ind(long r, long a, long n)
 * long[] generator_table(long root, long n)
 * long[] Solve_ind_congruence(long a,long e,long b,long n)
 * int Quadratic_residue(long a, long p)
 * int Quadratic_Reciprocity_Law(long p, long q)
 * long One_Solution_linear_congruence(long a, long b, long p)
 * long[] Solve_Quadratic_congruences(long a, long b, long c, long d, long p)
 * 
 * @author ChangMin, LeeSo
 * @version 1.0
*************************************************** */

public class CM_Function {

	// ===========================================*****
	public long mod(long a, long n) {

		if (a % n < 0) {
			return ((a % n) + n);
		}

		return a % n;
	}

	// ===========================================*****
	public long[] get_divisor(long a) {
		long i;
		List<Long> L_data = new ArrayList<Long>();

		for (i = 1; i <= a; i++) {
			if (a % i == 0) {
				L_data.add(i);
			}
		}
		long[] A_data = L_data.stream().mapToLong(k -> k).toArray();

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
	public long[] get_division(long a, long b) {
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
	public boolean get_divisable(long a, long b) {
		long tmp;
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}

		if ((a % b) == 0) {
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
	public long LCM(long a, long b) {
		a = Math.abs(a);
		return ((a * b) / GCD(a, b));
	}

	// ===========================================*****
	public boolean is_relatively_prime(long a, long b) {
		long t_gcd = GCD(a, b);
		if (t_gcd == 1) {
			return true;
		}
		return false;
	}

	// ===========================================*****
	public long[] Extended_Euclid(long a, long b) {
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

		while (r != 0) {
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
		} else {
			res[0] = x1;
			res[1] = y1;
			res[2] = tmp_a;
		}

		return res;
	}

	// ===========================================*****
	public long[] Solve_Diophantine_Eq(long a, long b) {
		long[] EEA = new long[3];
		int i;
		long x0, y0, gcd;

		List<Long>[] Dio = new ArrayList[2];
		for (i = 0; i < 2; i++) {
			Dio[i] = new ArrayList<Long>();
		}

		EEA = Extended_Euclid(a, b);
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
		long gcd = GCD(a, n);
		long k = 0;

		List<Long> L_data = new ArrayList<Long>();

		if ((b % gcd) != 0) {
			return null;
		}

		k = mod(inverse(a / gcd, n / gcd) * b / gcd, n / gcd);
		L_data.add(k);
		for (int i = 1; i < gcd; i++) {
			L_data.add(k + (n / gcd) * i);
		}

		long[] A_data = L_data.stream().mapToLong(l -> l).toArray();

		return A_data;
	}

	// ===========================================*****
	public long[] CRT(long[] a, long[] b, long[] m) {
		// mod m1, m2, ..., mn 들 중에서 겹칠 때와 혹은 서로소가 아닐 때의 조건 생각 필요;;
		long M = 1, temp_result = 0;
		long[] tmp;
		long[] res = new long[2];

		for (int i = 0; i < a.length; i++) {
			tmp = Solve_linear_congruence(a[i], b[i], m[i]);
			b[i] = tmp[0];
			m[i] = m[i] / GCD(a[i], m[i]);
		}

		// pair 서로소가 아닌 경우, CRT_many 함수로 넘어감
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (i == j)
					continue;
				if (GCD(m[i], m[j]) != 1)
					return CRT_many(b, m);
			}
		}

		for (int i = 0; i < a.length; i++)
			M *= m[i];

		for (int i = 0; i < a.length; i++) {
			a[i] = M / m[i]; // a가 M1, M2, ..., Mn 배열로 재할당
			temp_result += (b[i] * a[i] * inverse(a[i], m[i]));
		}

		res[0] = mod(temp_result, M);
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

		if (b.length != m.length)
			return null; // throw new Exception("b 와 m의 사이즈가 달라요...");

		for (int i = 1; i < b.length; i++) {
			long a2 = b[i];
			long m2 = m[i];
			long g = GCD(m1, m2);
			// if a1 % g != a2 % g : throw Exception

			long[] eea = Extended_Euclid(m1 / g, m2 / g);
			long lcm = LCM(m1, m2);
			long x = mod((a1 * (m2 / g) * eea[1] + a2 * (m1 / g) * eea[0]), lcm);
			a1 = x;
			m1 = lcm;
		}

		res[0] = a1;
		res[1] = m1;
		return res;
	}

	// ===========================================*****
	public boolean miller_rabin(long a, long n) {
		long r = 0, x = 0;
		long d = n - 1;

		while (d % 2 == 0) {
			r++;
			d >>= 1;
		}

		x = mod_pow(a, d, n);
		if (x == 1 || x == n - 1)
			return true;

		for (int i = 0; i < r - 1; i++) {
			x = mod_pow(x, 2, n);
			if (x == n - 1)
				return true;
		}

		return false;
	}

	public boolean is_Prime(long n) {
		long[] p = { 2, 3, 5, 7, 11, 13, 17 };

		if (n <= 1)
			return false;
		if (n == 2 || n == 3)
			return true;
		if (n % 2 == 0)
			return false;

		for (int i = 0; i < p.length; i++) {
			if (n == p[i])
				return true;
			if (!miller_rabin(p[i], n))
				return false;
		}

		return true;
	}

	// ===========================================*****
	public int len_bin(long num) {
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
		if (n <= 0) {
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
	public long RtoL(long a, long e, long n) {
		long r0 = 1;
		long r1 = a;

		for (int i = (len_bin(e) - 1); i > -1; i--) {
			r0 = (r0 * r0) % n;
			if (((e >> i) & 1) == 1) {
				r0 = (r0 * r1) % n;
			}

		}
		return r0;
	}

	// ===========================================*****
	public long inverse(long a, long n) {
		if (GCD(a, n) != 1) {
			return 0;
		}
		return RtoL(a, phi(n) - 1, n);
	}

	// ===========================================*****
	public long mod_pow(long a, long e, long n) {
		if (e < 0) {
			return inverse(RtoL(a, (~e + 1), n), n);
		} else if (e == 0) {
			return 1;
		} else {
			return RtoL(a, e, n);
		}
	}

	// ===========================================*****
	public long get_order(long a, long n) // O(len(phi(n).divisor)) = O(n) 약수 구하는게 O(n)임
	{
		if (GCD(a, n) != 1) {
			return -1;
		}

		long[] ind = get_divisor(phi(n));

		for (int i = 0; i < ind.length; i++) {
			if (mod_pow(a, ind[i], n) == 1) {
				return ind[i];
			}
		}
		return -1;
	}

	// ===========================================*****
	public long[] disjoint_set(long n) { // O(n)

		List<Long> L_data = new ArrayList<Long>();
		for (long i = 1; i <= n; i++) {
			if (GCD(i, n) == 1) {
				L_data.add(i);
			}
		}

		long[] A_data = L_data.stream().mapToLong(l -> l).toArray();

		return A_data;
	}

	// ===========================================*****
	public long[] primitive_root(long n) // O(phi*n)
	{
		long[] disj = disjoint_set(n);
		long phi = disj.length;
		List<Long> L_data = new ArrayList<Long>();

		for (int i = 0; i < phi; i++) {
			if (get_order(disj[i], n) == phi) {
				L_data.add(disj[i]);
			}
		}

		if (L_data.isEmpty()) {
			return null;
		}
		long[] A_data = L_data.stream().mapToLong(l -> l).toArray();

		return A_data;
	}

	public long get_one_primitive_root(long n) {
		long[] disj = disjoint_set(n);
		long phi = disj.length;
		for (int i = 0; i < phi; i++) {
			if (get_order(disj[i], n) == phi) {
				return disj[i];
			}
		}
		return -1;
	}

	public long ind(long r, long a, long n) { // O(phi(n)*log n)
		long phi = phi(n);
		for (long i = 1; i <= phi; i++) {
			if (mod_pow(r, i, n) == a) {
				return i; // ind a
			}
		}
		return -1;
	}

	public long[] generator_table(long root, long n) // a
	{
		if (get_one_primitive_root(n) == -1) {
			return null;
		}
		List<Long> L_data = new ArrayList<Long>();
		for (int i = 1; i <= phi(n); i++) {
			L_data.add(mod_pow(root, i, n));
		}

		long[] A_data = L_data.stream().mapToLong(l -> l).toArray();

		return A_data;
	}

	public long[] Solve_ind_congruence(long a, long e, long b, long n) {
		long root = get_one_primitive_root(n);
		long phi = phi(n);

		if ((root == -1) || (GCD(a, n) != 1) || (GCD(b, n) != 1)) {
			return null;
		}
		// ind a + e*ind x = ind b (mod phi(n))

		long[] ind_x = Solve_linear_congruence(e, mod((ind(root, b, n) - ind(root, a, n)), phi), phi);
		if (ind_x == null) {
			return null;
		}
		long[] x = new long[ind_x.length];

		for (int i = 0; i < ind_x.length; i++) {
			x[i] = mod_pow(root, ind_x[i], n);
		}
		return x;
	}

	public int Quadratic_residue(long a, long p) {
		if (GCD(a, p) != 1 || p == 2) {
			return 0;
		}

		return (RtoL(a, (p - 1) >> 1, p) == 1) ? 1 : -1;
	}

	public int Quadratic_Reciprocity_Law(long p, long q) {
		if (!(is_Prime(p)) || !(is_Prime(q)))
			return 0;
		return Quadratic_residue(p, q) * Quadratic_residue(q, p);
	}

	public long One_Solution_linear_congruence(long a, long b, long p) {
		if (!(is_Prime(p))) {
			return -1;
		}
		if (GCD(a, p) != 1) {
			return -1;
		}
		return mod(inverse(a, p) * b, p);
	}

	public long[] Solve_Quadratic_congruences(long a, long b, long c, long d, long p) // ax^2 + bx + c = d mod n, 일단 모드
																						// 값은 소수만 가짐
	{
		if (!(is_Prime(p))) {
			return null;
		}

		long tmp = mod(mod_pow(b, 2, p) - (4 * a * c), p);
		boolean t = (Quadratic_residue(tmp, p) == -1) ? true : false;

		if (t) {
			return null;
		}

		long[] y = Solve_ind_congruence(1, 2, tmp, p);
		y[0] = One_Solution_linear_congruence(mod(2 * a, p), mod(y[0] - b, p), p);
		y[1] = One_Solution_linear_congruence(mod(2 * a, p), mod(y[1] - b, p), p);

		return y;
	}

	/*
	 * 의미가 있을까... public long Sigma(int i_min, int i_max, long[] value) { long sum =
	 * 0;
	 * 
	 * if(value == null) { return 0; } if((i_min<1)||i_max > value.length) { //index
	 * error return -1; } for(int i = i_min-1; i< i_max; i++) { sum += value[i]; }
	 * return sum; }
	 */

}
