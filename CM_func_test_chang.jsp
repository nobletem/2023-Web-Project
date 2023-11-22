<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="T_pro.CM_Function" %>
<%@ page import="java.util.Random" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
request.setCharacterEncoding("UTF-8");
//String z = request.getParameter("id");
//String z2 = request.getParameter("pw");

//long a = Long.parseLong(z);
//long b = Long.parseLong(z2);
Random r = new Random();

long a = r.nextInt(1000000);
long b = r.nextInt(1000000);

boolean ctest;
long dtest;
long[] atest;
long[] btest;
long[] etest;
CM_Function test = new CM_Function();

out.print("a : " + a + "<br>");
out.print("b : " + b + "<br><br>");

//===========================================*****
dtest = test.mod(a, b);
out.print("mod: " + dtest + "<br><br>");

//===========================================*****
ctest = test.get_divisable(a, b);
out.print("나눔 여부: " + ctest + "<br><br>");

//===========================================*****
dtest = test.GCD(a, b);
out.print("gcd: " + dtest + "<br><br>");

//===========================================*****
atest = test.get_division(a, b);
out.print("몫, 나머지: " + atest[0] + ", ");
out.print(atest[1] + "<br><br>");

//===========================================*****
btest = test.get_divisor(a);
int i = 0;
out.print("약수 : ");
while(i < btest.length){
	out.print(btest[i] + ", ");
	i++;
}
out.print("<br><br>");

//===========================================*****
etest = test.Extended_Euclid(a, b);
out.print("x: " + etest[0] + "<br>");
out.print("y: " + etest[1] + "<br>");
out.print("d: " + etest[2] + "<br><br>");

//===========================================*****
etest = test.Solve_Diophantine_Eq(a, b);
out.print("x0 + a/d * i : ");
for (int j = 0; j < 10; j++) {
	out.print(etest[j] + ", ");
}
out.print("<br> y0 + b/d * i : ");
for (int j = 0; j < 10; j++) {
	out.print(etest[j + 10] + ", ");
}
out.print("<br><br>");

//===========================================*****
long k = test.mod_pow(1601231, 1324232, 134342355);
out.print("a^e (mod n) : " + k);
out.print("<br><br>");
out.print("구분하십쇼<br>");

//===========================================*****
long[] slsl = test.Solve_linear_congruence(a, b, 4342355);
out.print("선형 합동식 : ");
for (int j = 0; j < slsl.length; j++){
	out.print(slsl[j] + ", ");
}
out.print("<br><br>");

//===========================================*****
long[] gtest = {5, 1, 1};
long[] htest = {37, 25, 18};
long[] jtest = {43, 49, 63};
long[] f = test.CRT(gtest, htest, jtest);
out.print("중나정(x) : " + f[0] + "<br>");
out.print("중나정(최소공배수) : " + f[1] + "<br><br>");

//===========================================*****
ctest = test.is_Prime(269);
out.print("소수임? " + ctest + "<br><br>");

%>

</body>
</html>