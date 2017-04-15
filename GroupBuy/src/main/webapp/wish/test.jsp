<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript" src="http://malsup.github.com/chili-1.7.pack.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.easing.1.3.js"></script>
<script type="text/javascript">
$.fn.cycle.defaults.speed   = 900;
$.fn.cycle.defaults.timeout = 6000;
$(function() {
    $('#demos pre code').each(function() {
        eval($(this).text());
    });

});
</script>
</head>
<body>

    <div id="demos">
        <div id="shuffle" class="pics">
            <img src="../pictures/aaa.jpg" width="450" height="300" />
            <img src="../pictures/bbb.jpg" width="450" height="300" />
            <img src="../pictures/ccc.jpg" width="450" height="300" />
        </div>
        <script>
        $('#shuffle').cycle({
            fx:     'shuffle',
            easing: 'easeOutBack',
            delay:  -4000
        });
        </script>
     </div>
</body>
</html>