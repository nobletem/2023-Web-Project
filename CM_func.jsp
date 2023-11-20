<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="T_pro.CM_Function" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String z = request.getParameter("id");
String z2 = request.getParameter("pw");

long a = Long.parseLong(z);
long b = Long.parseLong(z2);

// long a = -1759;
// long b = 550;
// out.print(a % b);

//boolean ctest;
//long dtest;
//long[] etest;
CM_Function test = new CM_Function();

/*
dtest = test.mod(a, b);
out.print("mod: " + dtest + "<br><br>");

ctest = test.get_divisable(a, b);
out.print("나눔 여부: " + ctest + "<br><br>");

dtest = test.GCD(a, b);
out.print("gcd: " + dtest + "<br><br>");

atest = test.get_division(a, b);
out.print("몫 or 나머지: " + atest[0] + "<br><br>");

*/

/* 
btest = test.get_divisor(a);
int i = 0;
while(i < btest.length){
   out.print(btest[i] + "<br>");
   i++;
}
*/

/*
etest = test.Extended_Euclid(a, b);
out.print("x: " + etest[0] + "<br>");
out.print("y: " + etest[1] + "<br>");
out.print("d: " + etest[2] + "<br>");


etest = test.Solve_Diophantine_Eq(a, b);
for (int j = 0; j < 10; j++) {
   out.print(etest[j] + ", ");
}
out.print("<br>");
for (int j = 0; j < 10; j++) {
   out.print(etest[j + 10] + ", ");
}

*/


// long k = test.mod_pow(a, b, 134342355);
// out.print("<br>" + k);

// k = test.Solve_linear_congruence(a, b, 134342355);
// out.print("<br>" + k);

long k = test.get_order(a,b);
out.print(k);
out.print("<br>");

long[] btest = test.primitive_root(a);
int i = 0;
while(i < btest.length){
   out.print(" "+ btest[i]);
   i++;
}

out.print("<br>"+ test.phi(test.phi(a)) + "<br>" );


long[] table = test.generator_table(btest[0],a);

// ind_root a
out.print("ind_"+btest[0]+"a"+"<br>");
for( i = 1; i<= btest.length; i++){
	out.print("	"+ i);
}
out.print("<br>");

// a
i = 0;
out.print("a<br>");
while(i < btest.length){
   out.print(" "+ btest[i]);
   i++;
}
out.print("<br>");


%>

</body>
</html>
