<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
</style>
<head>
	<script>
	</script>
    <title>UrlShortening</title>
</head>
<body bgcolor="#fff5d9">
    <div align="center">
        <hr>
        <form name="urlForm" action="${pageContext.request.contextPath}/urlShortener/create" method="POST">
            <table border="1" style="height:100px">
                <tr>
                    <td><input type="text" name="targetUrl" size="80" style="height: 100%; font-size: large;" placeholder="URL Shortening 정보를 입력하세요."></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="출력" />
                        <input type="reset" value="초기화" />
                    </td>
                </tr>
            </table>
            <h4 id="info">URL Shortening 출력은 아래에 표시됩니다.</h4>
            <%
                /*
                 * validator를 이용하여 URL이 FALSE이면 유효하지 않는 URL임으로 validation 체크한다.
                 * url이 유효하다면 shortening된 url을 출력한다.
                 */
                if (request.getAttribute("isValidUrl") != null) {
                    out.println(request.getAttribute("isValidUrl").toString());
                }
                else if (request.getAttribute("returnUrl") != null) {
                	out.println(request.getAttribute("returnUrl").toString());
                }
            %>
        </form>
        <hr>
    </div>
</body>
</html>
