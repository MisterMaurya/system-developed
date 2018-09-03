<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>System:User Details</title>
</head>
<body>
	<form action="/save" method="post">
		<table>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>Device-ID</th>
				<td><input type="text" name="device_Id"></td>
			</tr>
			<tr>
				<th>Tag-ID</th>
				<td><input type="text" name="tag_Id"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="submit"></th>
			</tr>


		</table>
	</form>

</body>
</html>