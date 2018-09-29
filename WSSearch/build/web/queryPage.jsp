<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <script type="text/javascript">
            $(document).ready(function () {

                var form1 = $("#q1");
                form1.submit(function () {
                    $.ajax({
                        type: form1.attr('method'),
                        url: form1.attr('action'),
                        data: form1.serialize(),
                        success: function (data) {
                            response = $.parseJSON(data);
                            $("#result").empty();
                            $("#resTitle").empty();
                            $.each(response, function (i, item) {
                                if (i === 0) {
                                    $("#resTitle").append(item);
                                } else {
                                    $("#result").append('<li>' + item + '</li>');
                                }
                            });
                        }
                    });
                    return false;
                });

                var form2 = $("#q2");
                form2.submit(function () {
                    $.ajax({
                        type: form2.attr('method'),
                        url: form2.attr('action'),
                        data: form2.serialize(),
                        success: function (data) {
                            response = $.parseJSON(data);
                            $("#result").empty();
                            $("#resTitle").empty();
                            $.each(response, function (i, item) {
                                if (i === 0) {
                                    $("#resTitle").append(item);
                                } else {
                                    $("#result").append('<li>' + item + '</li>');
                                }
                            });
                        }
                    });
                    return false;
                });

                var form3 = $("#q3");
                form3.submit(function () {
                    $.ajax({
                        type: form3.attr('method'),
                        url: form3.attr('action'),
                        data: form3.serialize(),
                        success: function (data) {
                            response = $.parseJSON(data);
                            $("#result").empty();
                            $("#resTitle").empty();
                            $.each(response, function (i, item) {
                                if (i === 0) {
                                    $("#resTitle").append(item);
                                } else {
                                    $("#result").append('<li>' + item + '</li>');
                                }
                            });
                        }
                    });
                    return false;
                });

                var form4 = $("#q4");
                form4.submit(function () {
                    $.ajax({
                        type: form4.attr('method'),
                        url: form4.attr('action'),
                        data: form4.serialize(),
                        success: function (data) {
                            response = $.parseJSON(data);
                            $("#result").empty();
                            $("#resTitle").empty();
                            $.each(response, function (i, item) {
                                if (i === 0) {
                                    $("#resTitle").append(item);
                                } else {
                                    $("#result").append('<li>' + item + '</li>');
                                }
                            });
                        }
                    });
                    return false;
                });
            });



        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Query Page</title>
    </head>
    <body>
        <br/>
        <!--        <h5>set availability</h5>-->
        <form class="alignForm" name="q1" id="q1" action="q1.go" method="POST">
            <h5 >Q1: Search for API by specific fields</h5>
            <table border="0">

                <tbody>
                    <tr>
                        <td>Update year:</td>
                        <td><input type="text" name="upyear" value="" /></td>
                    </tr>
                    <tr>
                        <td>Protocol:</td>
                        <td> <input type="text" name="protocol" value="" /></td>
                    </tr>
                    <tr>
                        <td>Category:</td>
                        <td><input type="text" name="category" value="" /></td>
                    </tr>
                    <tr>
                        <td>Rating:</td>
                        <td><input type="text" name="rating" value="" /></td>
                    </tr>
                    <tr>
                        <td>Rating operand (<, >, <=, >=, =, !=):</td>
                        <td><input type="text" name="oper" value="" /></td>
                    </tr>
                    <tr>
                        <td>Tags (separate tags by ";" ):</td>
                        <td> <input type="text" name="tags" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit" name="submit" /></td>
                    </tr>
                </tbody>
            </table>


        </form>
        <br/>
        <hr/>
        <form class="alignForm" name="q2" id="q2" action="q2.go" method="POST">
            <h5 >Q2: Search for Mashups by specific fields</h5>
            <table border="0">
                <tbody>
                    <tr>
                        <td>Update year:</td>
                        <td> <input type="text" name="upyear" value="" /></td>
                    </tr>
                    <tr>
                        <td>Used APIs (separate apis by ";"):</td>
                        <td> <input type="text" name="apis" value="" /></td>
                    </tr>
                    <tr>
                        <td>Tags (separate tags by ";"):</td>
                        <td> <input type="text" name="tags" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit" name="submit" /></td>
                    </tr>
                </tbody>
            </table>            
        </form>
        <br/>
        <hr/>
        <form class="alignForm" name="q3" id="q3" action="q3.go" method="POST">
            <h5 >Q3: Search for API by keywords</h5>
            Keywords (separate keys by ";") : <input type="text" name="keys" value="" />
            <input type="submit" value="Submit" name="submit" />
        </form>
        <br/>
        <hr/>
        <form class="alignForm" name="q4" id="q4" action="q4.go" method="POST">
            <h5 >Q4: Search for Mashups by keywords</h5>
            Keywords (separate keys by ";") : <input type="text" name="keys" value="" />
            <input type="submit" value="Submit" name="submit" />
        </form>

        <br/>
        <hr/>
        <h5><p>Resutls:</p>
            <h4 id="resTitle" style="color:green"></h4>
            <ol class="divText" id="result" style="color:green">
            </ol></h5>
    </body>
</html>
